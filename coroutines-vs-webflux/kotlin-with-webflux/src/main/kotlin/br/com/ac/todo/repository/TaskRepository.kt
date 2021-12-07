package br.com.ac.todo.repository

import br.com.ac.todo.domain.Task
import java.util.UUID
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskRepository : ReactiveCrudRepository<Task, Long> {
}
