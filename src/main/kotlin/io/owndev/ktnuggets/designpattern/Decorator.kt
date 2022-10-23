package io.owndev.ktnuggets.designpattern

interface Shape {

    fun draw()

    fun fill()
}

class Rectangle : Shape {
    override fun draw() {
        println("::Rectangle")
        println(
            """
            ---------------------
            |                   |
            |                   |
            |                   |
            |                   |
            ---------------------
            """.trimIndent()
        )
    }

    override fun fill() {
        println("::Filled Rectangle")
    }
}

class GreyShapeDecorator(private val shape: Shape) : Shape by shape {

    private var border: String = "::Grey =========================>"

    override fun draw() {
        shape.draw()
        setGreyBorder()
    }

    private fun setGreyBorder() = println(border)
}
