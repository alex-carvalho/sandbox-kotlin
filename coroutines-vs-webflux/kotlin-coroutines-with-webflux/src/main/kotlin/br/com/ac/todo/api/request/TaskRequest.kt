package br.com.ac.todo.api.request

data class TaskRequest (
    var title: String,
    var isCompleted: Boolean
)