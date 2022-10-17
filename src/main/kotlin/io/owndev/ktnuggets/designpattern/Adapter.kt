package io.owndev.ktnuggets.designpattern

interface RangeFareConfiguration {
    var lowerBoundValueInMinor: Long
    var upperBoundValueInMinor: Long
}

interface FareConfiguration {
    var valueInMinor: Long
}

class RangeToFixedFareConfigurationAdapter(
    private val fareConfiguration: RangeFareConfiguration
) : FareConfiguration {

    companion object {
        private const val DEFAULT_BOUND = 2
    }

    override var valueInMinor: Long
        get() = convertToFixedAmount(fareConfiguration)
        set(value) {
            fareConfiguration.lowerBoundValueInMinor = value - DEFAULT_BOUND
            fareConfiguration.upperBoundValueInMinor = value + DEFAULT_BOUND
        }

    private fun convertToFixedAmount(fareConfiguration: RangeFareConfiguration): Long =
        (fareConfiguration.lowerBoundValueInMinor + fareConfiguration.upperBoundValueInMinor) / 2
}