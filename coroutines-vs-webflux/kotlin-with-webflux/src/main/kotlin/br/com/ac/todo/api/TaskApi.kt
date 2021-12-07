package br.com.ac.todo.api

import br.com.ac.todo.api.request.CreateTaskRequest
import br.com.ac.todo.api.request.TaskRequest
import br.com.ac.todo.api.response.TaskResponse
import br.com.ac.todo.domain.Task
import br.com.ac.todo.repository.TaskRepository
import java.util.UUID
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/tasks")
class TaskApi(
    private val taskRepository: TaskRepository,
    private val webClient: WebClient
) {
    @GetMapping
    fun allTasks(): Flux<TaskResponse> {
        return taskRepository.findAll()
            .map { it.mapToResponse() }
    }

    @GetMapping("/{id}")
    fun getTaskById(@PathVariable("id") id: Long): Mono<TaskResponse> {
        return taskRepository.findById(id)
            .flatMap { task ->
                webClient.get().uri("http://localhost:8085?title=" + task.title)
                    .retrieve()
                    .bodyToMono<String>()
                    .map { task.mapToResponse(it) }
            }

    }

    @PostMapping
    fun create(@RequestBody taskRequest: CreateTaskRequest): Mono<TaskResponse> {
        val task = Task(title = taskRequest.title, completed = false)
        return taskRepository.save(task)
            .map { it.mapToResponse() }
    }


    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long): Mono<ResponseEntity<*>> {
        return taskRepository.deleteById(id)
            .map { ResponseEntity<Any?>(HttpStatus.OK) }
    }

    @PutMapping("/{id}")
    fun updateById(@PathVariable id: Long, @RequestBody taskRequest: TaskRequest): Mono<TaskResponse> {
        val task = Task(id, taskRequest.title, taskRequest.isCompleted)
        return taskRepository.save(task)
            .map { it.mapToResponse() }
    }

    private fun Task.mapToResponse(otherInfo: String? = null) =
        TaskResponse(this.id, this.title, this.completed, otherInfo)
}