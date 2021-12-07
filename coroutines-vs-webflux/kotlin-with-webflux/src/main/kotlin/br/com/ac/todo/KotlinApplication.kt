package br.com.ac.todo

import br.com.ac.todo.domain.Task
import br.com.ac.todo.repository.TaskRepository
import io.r2dbc.spi.ConnectionFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator
import org.springframework.web.reactive.function.client.WebClient
import java.util.function.Consumer

@SpringBootApplication
class KotlinApplication : CommandLineRunner {

	@Autowired
	private lateinit var taskRepository: TaskRepository

	override fun run(vararg args: String?) {
		taskRepository.save(Task(title = "test", completed = false )).subscribe { println(it) }
	}
}


fun main(args: Array<String>) {
	runApplication<KotlinApplication>(*args)
}

@Configuration
class Config {
	@Bean
	fun initializer(connectionFactory: ConnectionFactory): ConnectionFactoryInitializer {
		val initializer = ConnectionFactoryInitializer()
		initializer.setConnectionFactory(connectionFactory)
		val populator = CompositeDatabasePopulator()
		populator.addPopulators(ResourceDatabasePopulator(ClassPathResource("./sql/schema.sql")))
		initializer.setDatabasePopulator(populator)
		return initializer
	}

	@Bean
	fun webClient() = WebClient.create()
}