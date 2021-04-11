package br.ac



infix fun <T> T.eq(other: T) {
    if (this == other) println("OK")
    else println("Error: $this != $other")
}

fun nullSafe() {

    val a: Int? = null
    val b: Int? = 1
    val c: Int = 2

    // elvis operator ?:
    val s1 = (a ?: 0) + c
    val s2 = (b ?: 0) + c
    print("$s1$s2")


    val foo: String? = null

    // NPE explicit
    foo!!.length

    // smart cast
    if (foo == null) throw RuntimeException("is null")
    foo.length


    fun foo(list1: List<Int?>, list2: List<Int>?) {
        list1.size
        list2?.size

        val i: Int? = list1.get(0)
        val j: Int? = list2?.get(0)
    }


    fun String?.isEmptyOrNull() = this?.isEmpty() ?: true
    fun test() {
        val s1: String? = null
        val s2: String? = ""
        s1.isEmptyOrNull() eq true
        s2.isEmptyOrNull() eq true

        val s3 = "   "
        s3.isEmptyOrNull() eq false
    }
}

fun smartCast() {

    val any: Any = "foo"

    if (any is String) {
        val foo = any as String
        // or only use
        any.substring(1)

    }
}
