package br.ac

fun String.lastChar() = this.get(this.length - 1)

fun String.repeat(n: Int): String {
    return IntArray(n).map { this }.joinToString(separator = "")

//    return IntStream.range(0, n)
//        .mapToObj { this }
//        .collect(Collectors.joining())

//    val strb = StringBuilder(n * length)
//    for (i in 1..n){
//        strb.append(this)
//    }
//    return strb.toString()
}

fun infixSyntax() {
    infix fun Int.multiply(mult: Int): Int = this * mult;

    print(9 multiply 2)
}

fun stringExtensions() {
    val regex = "a-z".toRegex()
    println(regex.matches("alex"))

    println("123".toInt())
    println("xx".toIntOrNull())
}

fun List<Int>.sum(): Long {
    var sum: Long = 0L
    for (element in this) {
        sum += element
    }
    return sum
}

fun main() {
    println("bar".lastChar())
    println("a".repeat(5))
    println('a'.isLetterOrDigit())
    stringExtensions()

    val list = listOf(1, 2, 3)
    println(list.sum())    // 6

}