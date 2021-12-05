package br.ac

fun utils() {

    val number = 5

    val greaterTen: Int? = number.takeIf { it > 10 }
    val lessTen: Int? = number.takeUnless { it > 10 }

    println(greaterTen)
    println(lessTen)


    repeat(5) {
        println(it)
    }

}

fun main() {
    utils()
}