package br.com.ac.todo

import br.com.ac.todo.domain.Task
import br.com.ac.todo.repository.TaskRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class KotlinCoroutineApplicationTests {

    @Autowired
    lateinit var webTestClient: WebTestClient


    @Autowired
    lateinit var taskRepository: TaskRepository


    @AfterEach
    fun tearDown() {
        runBlocking {
            taskRepository.deleteAll()
        }
    }

    @Test
    fun `test list tasks API returns all tasks`() {
        runBlocking {
            taskRepository.saveAll(
                listOf(
                    Task(title = "taskk", completed = false),
                    Task(title = "taskk222", completed = false)
                )
            )
        }

        webTestClient
            .get().uri("/tasks")
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$[0].title").isEqualTo("taskk")
            .jsonPath("$[1].title").isEqualTo("taskk22")
    }
}