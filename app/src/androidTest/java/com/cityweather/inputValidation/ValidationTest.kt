package com.cityweather.inputValidation

import com.cityweather.utils.Validators
import org.junit.Test


import org.junit.Assert.*

class ValidationTest {

    @Test
    fun citiesAreDistinct(){
        assertTrue(Validators.isValidCitiesNames("dubai,dubai,dubai,dubai"))
    }


    @Test
    fun citiesNamesValidate() {
        assertTrue(Validators.isValidCitiesNames("dubai,riyadh,karachi"))
    }


    @Test
    fun CitiesArrayValidate() {
        val cities = "london,london,london"
        val citiesArr = arrayOf("london", "london", "london")
        assertEquals(citiesArr, Validators.getCitiesArr(cities))
    }
}