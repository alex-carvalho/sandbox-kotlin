package br.ac



fun lambdas() {

    val list = listOf(1,2,3)

    list
        .filter({value -> value > 2 })
        .filter { value -> value > 2 }
        .filter { it > 2 }

    list.withIndex().forEach { (index, value) -> println("$index=$value") }
    list.withIndex().forEach { (_, value) -> println("$value") }

}

fun usingLetFunction() {
    Bar().email?.let {
        printValue(it)
    }
}

fun printValue(value: String) {
    println(value)
}

class Bar {
    var email: String? = null
}





fun analyzeUser(session: Session){

    fun smartCastDoesWork() {
        // smart cast does work with inheritance properties
        if(session.user is FacebookUser) {
            // session.user.hello(); not compile
        }
    }

    fun smartCastWork() {
        // smart cast with inheritance properties using local variable
        val user = session.user
        if (user is FacebookUser) {
            user.hello();
        }
    }

    // alternative is force cast and use let
    (session.user as? FacebookUser)?.let {
        it.hello()
    }
}


interface Session {
    val user: User
}

interface User

class FacebookUser: User {

    fun hello() = "hello"
}
