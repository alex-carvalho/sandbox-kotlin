package br.ac.algorithms

import java.lang.Character.isAlphabetic

fun reverse(content: String): String {
    val value = content.toCharArray()
    var start = 0
    var end = value.size - 1
    while (end > start) {
        val temp = value[start]
        value[start] = value[end]
        value[end] = temp
        end--
        start++
    }
    return String(value)
}

fun reverseOnlyAlphabeticChars(value: String): String {
    val content = value.toCharArray()

    var end = content.size - 1
    var start = 0

    while (start < end) {
        if (content[start].isNotAlphabetic()) start++
        else if (content[end].isNotAlphabetic()) end--
        else {
            val tmp = content[start]
            content[start] = content[end]
            content[end] = tmp
            start++
            end--
        }
    }

    return String(content)
}

private fun Char.isNotAlphabetic() = !isAlphabetic(this.code)