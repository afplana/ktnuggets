package io.owndev.ktnuggets.designpattern

import mu.KotlinLogging

interface PriceConfiguration
class FixedPriceConfiguration : PriceConfiguration
class RangePriceConfiguration : PriceConfiguration

private val logger = KotlinLogging.logger {}

abstract class PriceConfigurationFactory {
    abstract fun instantiate(): PriceConfiguration

    companion object {
        inline fun <reified T : PriceConfiguration> factory(): PriceConfigurationFactory = when (T::class) {
            FixedPriceConfiguration::class -> FixedPriceConfigurationFactory()
            RangePriceConfiguration::class -> RangePriceConfigurationFactory()
            else -> throw IllegalArgumentException()
        }
    }
}

class FixedPriceConfigurationFactory : PriceConfigurationFactory() {
    override fun instantiate(): PriceConfiguration = FixedPriceConfiguration().also {
        logger.debug { "Getting fixed price config factory" }
    }
}

class RangePriceConfigurationFactory : PriceConfigurationFactory() {
    override fun instantiate(): RangePriceConfiguration = RangePriceConfiguration().also {
        logger.debug { "Getting range price config factory" }
    }
}
