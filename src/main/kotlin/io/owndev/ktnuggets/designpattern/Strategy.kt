@file:Suppress("unused")

package io.owndev.ktnuggets.designpattern

import kotlin.properties.Delegates

interface PaymentStrategy {
    fun pay(amount: Long)
}

interface ShoppingCartItem {
    val amount: Long
}

class CreditCardStrategy(private val cardNumber: String, private val cvv: String, private val expiryDate: String) :
    PaymentStrategy {
    override fun pay(amount: Long) {
        println("Paying $amount using credit card.")
    }
}

class PayPalStrategy(private val email: String, private val password: String) : PaymentStrategy {
    override fun pay(amount: Long) {
        println("Paying $amount using PayPal.")
    }
}

class ShoppingCart(private val items: List<ShoppingCartItem>) {
    private lateinit var paymentStrategy: PaymentStrategy
    var totalAmount by Delegates.notNull<Long>()

    fun setPaymentStrategy(paymentStrategy: PaymentStrategy) {
        this.paymentStrategy = paymentStrategy
    }

    fun pay() {
        totalAmount = items.sumOf { it.amount }
        paymentStrategy.pay(totalAmount)
    }
}
