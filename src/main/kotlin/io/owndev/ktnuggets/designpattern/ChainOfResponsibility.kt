package io.owndev.ktnuggets.designpattern

import java.time.LocalDateTime

const val DEBUG = 1
const val INFO = 2
const val WARN = 3
const val ERROR = 4

interface Logger {

    val next: Logger?
    val level: Int

    fun write(message: String?)

    fun log(level: Int, message: String?) {
        if (this.level == level) {
            write(message)
        }

        next?.log(level, message)
    }
}

class KLog : Logger {
    override val level: Int = Int.MIN_VALUE
    override val next: Logger = Debug()

    override fun write(message: String?) {
        next.log(DEBUG, message)
    }
}

class Debug(override var next: Logger? = Info()) : Logger {
    override val level: Int = DEBUG
    override fun write(message: String?) {
        println("[${LocalDateTime.now()}] - [DEBUG]: $message")
    }
}

class Info(override var next: Logger? = Warn()) : Logger {
    override val level: Int = INFO
    override fun write(message: String?) {
        println("[${LocalDateTime.now()}] - [INFO]: $message")
    }
}

class Warn(override var next: Logger? = Error()) : Logger {
    override val level: Int = WARN
    override fun write(message: String?) {
        println("[${LocalDateTime.now()}] - [WARN]: $message")
    }
}

class Error(override var next: Logger? = null) : Logger {
    override val level: Int = ERROR
    override fun write(message: String?) {
        println("[${LocalDateTime.now()}] - [ERROR]: $message")
    }
}
