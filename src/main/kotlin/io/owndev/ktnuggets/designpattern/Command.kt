package io.owndev.ktnuggets.designpattern

interface Command {
    fun execute()
}

class PrintCommand (private val printable: Printable): Command {

    interface Printable {
        override fun toString(): String
    }

    override fun execute() {
        println("Print: $printable")
    }
}

class SumCommand(private val op1: Long, private val op2: Long) : Command {
    override fun execute() {
        println("Result of: $op1 + $op2 = ${op1 + op2}")
    }
}

class CommandProcessor(private val queue: MutableList<Command> = ArrayList()) {

    fun addCommand(order: Command) : CommandProcessor = apply {
        queue.add(order)
    }

    fun process() = apply {
        queue.forEach { it.execute() }
        queue.clear()
    }
}