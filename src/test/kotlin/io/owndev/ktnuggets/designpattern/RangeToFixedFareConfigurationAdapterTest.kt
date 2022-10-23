package io.owndev.ktnuggets.designpattern

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class RangeToFixedFareConfigurationAdapterTest {

    @Test
    fun `should convert ranged fares to fix value`() {
        val rangeFare = object : RangeFareConfiguration {
            override var lowerBoundValueInMinor: Long = 5
            override var upperBoundValueInMinor: Long = 10
        }

        val adapter = RangeToFixedFareConfigurationAdapter(rangeFare)

        assertEquals(adapter.valueInMinor, 7)
    }
}
