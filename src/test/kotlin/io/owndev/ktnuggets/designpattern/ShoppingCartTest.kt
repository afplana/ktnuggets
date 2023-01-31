package io.owndev.ktnuggets.designpattern

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream




internal class ShoppingCartTest {

    private val outContent: ByteArrayOutputStream = ByteArrayOutputStream()
    private val errContent: ByteArrayOutputStream = ByteArrayOutputStream()
    private val originalOut = System.out
    private val originalErr = System.err

    @BeforeEach
    fun setUpStreams() {
        System.setOut(PrintStream(outContent))
        System.setErr(PrintStream(errContent))
    }

    @AfterEach
    fun restoreStreams() {
        System.setOut(originalOut)
        System.setErr(originalErr)
    }

    @Test
    fun `should pay with paypal`() {
        val payPal = PayPalStrategy("afps@gmail.com", "superS3cur3P4ssw0rd")
        val item1: ShoppingCartItem = object: ShoppingCartItem { override val amount: Long = 500L}
        val item2: ShoppingCartItem = object: ShoppingCartItem { override val amount: Long = 700L}
        val item3: ShoppingCartItem = object: ShoppingCartItem { override val amount: Long = 200L}
        val item4: ShoppingCartItem = object: ShoppingCartItem { override val amount: Long = 800L}

        val shoppingCart = ShoppingCart(listOf(item1, item2, item3, item4))
        shoppingCart.setPaymentStrategy(payPal)
        shoppingCart.pay()

        shoppingCart.totalAmount shouldBe 2200
        outContent.toString() shouldBe "Paying 2200 using PayPal.\n"
    }

    @Test
    fun `should pay with credit card`() {
        val creditCard = CreditCardStrategy("DE89 5566 3562 4432 22", "000", "23/03/30")
        val item1: ShoppingCartItem = object: ShoppingCartItem { override val amount: Long = 500L}
        val item2: ShoppingCartItem = object: ShoppingCartItem { override val amount: Long = 700L}
        val item3: ShoppingCartItem = object: ShoppingCartItem { override val amount: Long = 200L}
        val item4: ShoppingCartItem = object: ShoppingCartItem { override val amount: Long = 800L}

        val shoppingCart = ShoppingCart(listOf(item1, item2, item3, item4))
        shoppingCart.setPaymentStrategy(creditCard)
        shoppingCart.pay()

        shoppingCart.totalAmount shouldBe 2200
        outContent.toString() shouldBe "Paying 2200 using credit card.\n"
    }
}