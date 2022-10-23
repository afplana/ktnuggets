package io.owndev.ktnuggets.designpattern

import org.junit.jupiter.api.Test
import kotlin.test.assertIs

internal class DecoratorTest {

    @Test
    fun `should draw shapes`() {
        val rectangle = Rectangle()
        val shape = GreyShapeDecorator(rectangle)

        shape.draw()
        assertIs<Shape>(shape)
    }
}
