package br.com.ac.todo.api

import br.com.ac.todo.api.request.CreateTaskRequest
import br.com.ac.todo.api.request.TaskRequest
import br.com.ac.todo.api.response.TaskResponse
import br.com.ac.todo.client.AnotherServiceClient
import br.com.ac.todo.domain.Task
import br.com.ac.todo.repository.TaskRepository
import java.util.UUID
import org.slf4j.LoggerFactory
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
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.awaitExchange

@RestController
@RequestMapping("/tasks")
class TaskApi(
    private val taskRepository: TaskRepository,
    private val anotherServiceClient: AnotherServiceClient
) {

    private val log = LoggerFactory.getLogger(TaskApi::class.java)

    @GetMapping
    suspend fun allTasks(): List<TaskResponse> {
        val tasks = taskRepository.findAllList()

        return tasks.map { it.mapToResponse() }
    }

    @GetMapping("/{id}")
    suspend fun getTaskById(@PathVariable("id") id: Long): TaskResponse {
        val task = taskRepository.findById(id)!!

        val response = try {
             anotherServiceClient.makeExternalCall(task.title)
        }catch (ex: Exception){
            ex.message
        }

        return task.mapToResponse(response)
    }

    @PostMapping
    suspend fun create(@RequestBody taskRequest: CreateTaskRequest): TaskResponse {
        val task = Task(title = taskRequest.title, completed = false)
        taskRepository.save(task)

        return task.mapToResponse()
    }


    @DeleteMapping("/{id}")
    suspend fun deleteById(@PathVariable id: Long): ResponseEntity<*> {
        taskRepository.deleteById(id)

        return ResponseEntity<Any?>(HttpStatus.OK)
    }

    @PutMapping("/{id}")
    suspend fun updateById(@PathVariable id: Long, @RequestBody taskRequest: TaskRequest): TaskResponse {
        val task = Task(id, taskRequest.title, taskRequest.isCompleted)

        taskRepository.save(task)

        return task.mapToResponse()
    }

    private fun Task.mapToResponse(otherInfo: String? = null) =
        TaskResponse(this.id, this.title, this.completed, otherInfo)
}