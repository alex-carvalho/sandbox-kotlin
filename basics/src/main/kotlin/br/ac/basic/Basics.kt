@file:JvmName("TestInteroperability")

package br.ac.basic

import java.io.IOException


fun variables() {
    var mutable = "a"
    mutable = "changed"

    val imutable = "a";
    // imutable = "b" compile error

    val teste: String = "with type"

    // desconstruct
    val (key, value) = Pair("key", "value")

    val (key2, value2) = "key" to "value"
}

// global functions
fun max(a: Int, b: Int) = if (a > b) a else b

class FooClass {

    fun insideClass(): Int {

        fun funInsideFun() = 5

        return funInsideFun() * 5
    }
}

fun defaults() {
    println(listOf(1, 2, 3).joinToString(separator = "-"))

    displaySeparator(size = 5)
}

fun displaySeparator(character: Char = '*', size: Int = 10) {
    repeat(size) {
        print(character)
    }
}

fun exampleWhen() {
    val foo = 20
    var result = when {
        foo == 0 -> "Zero"
        foo > 5 -> "Maior que 5"
        foo < 5 -> "Menor que 5"
        else -> "É 5"
    }

    when (foo) {
        in 0..5 -> print("Between 0 and 5")
        6 -> print("É seis")
        !in 10..20 -> print("Não está entre 10 e 20")
    }

}

fun loopsAndRanges() {

    val list = listOf(10, 20, 30)
    for (v in list) print("$v   ")
    lineBreak()
    for ((index, value) in list.withIndex()) print("$index = $value    ")
    lineBreak()

    val map = mapOf(1 to "a", 2 to "b")
    for ((key, value) in map) print("$key = $value      ")

    lineBreak()
    for (i in 0..9) print(i)
    lineBreak()
    for (i in 0 until 9) print(i)
    lineBreak()
    for (i in 9 downTo 0 step 2) print(i)
    lineBreak()
    for (c in "abc") print("$c  ")
    lineBreak()
    for (c in '0' until '9') { print(c) }

    println("Kotlin" in "Java".."Scala")
    println("Kotlin" in setOf("Java", "Scala"))

    print("a" in setOf("b", "c"))

}

fun lineBreak() = print("\n")

@Throws(IOException::class)
fun exceptionWithoutWithAnnotationForJavaCode() {
    throw IOException("bar")
}
