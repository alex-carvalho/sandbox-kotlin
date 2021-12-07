package br.com.ac.todo.repository

import br.com.ac.todo.domain.Task
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskRepository : CoroutineCrudRepository<Task, Long> {

    @Query("""SELECT * FROM  TASKS""")
    suspend fun findAllList(): List<Task>

}
