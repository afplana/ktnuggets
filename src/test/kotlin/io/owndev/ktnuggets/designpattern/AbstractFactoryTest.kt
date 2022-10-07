package io.owndev.ktnuggets.designpattern

import org.junit.jupiter.api.Test
import kotlin.test.assertIs

internal class AbstractFactoryTest {

    @Test
    fun `should build a fixed price configuration`() {
        val configurationFactory = PriceConfigurationFactory.factory<FixedPriceConfiguration>()
        val configuration = configurationFactory.instantiate()
        assertIs<FixedPriceConfiguration>(
            configuration,
            "Building a Price Configuration: ${configuration::class.simpleName}"
        )
    }

    @Test
    fun `should build a range price configuration`() {
        val configurationFactory = PriceConfigurationFactory.factory<RangePriceConfiguration>()
        val configuration = configurationFactory.instantiate()
        assertIs<RangePriceConfiguration>(
            configuration,
            "Building a Price Configuration: ${configuration::class.simpleName}"
        )
    }
}
