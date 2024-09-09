package ru.mrfix1033.calculator

class Operation(private val firstOperand: String, private val secondOperand: String) {
    private fun parse() = Pair(parse(firstOperand), parse(secondOperand))

    fun plus() = encode(parse().run { first + second })

    fun minus() = encode(parse().run { first - second })
}
