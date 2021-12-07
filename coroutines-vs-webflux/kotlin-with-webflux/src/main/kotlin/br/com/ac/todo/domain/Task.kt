package br.com.ac.todo.domain


import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("tasks")
data class Task(
    @Id var id: Long? = null,
    val title: String,
    val completed: Boolean
)
