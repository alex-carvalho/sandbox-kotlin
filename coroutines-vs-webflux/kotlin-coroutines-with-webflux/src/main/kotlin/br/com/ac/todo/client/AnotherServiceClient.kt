package br.com.ac.todo.client

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.awaitExchange
import reactor.core.publisher.Mono

@Component
class AnotherServiceClient(private val webClient: WebClient) {

    suspend fun makeExternalCall(taskTitle: String): String {
        return webClient.get().uri("http://localhost:8085?title=$taskTitle")
            .awaitExchange { it.awaitBody(String::class) }
    }
}