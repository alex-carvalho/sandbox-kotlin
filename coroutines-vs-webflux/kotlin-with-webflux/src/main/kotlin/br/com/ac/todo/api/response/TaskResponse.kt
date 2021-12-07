package br.com.ac.todo.api.response

data class TaskResponse(
    var id: Long?,
    var title: String,
    var isCompleted: Boolean,
    var otherInfo: String? = null
)