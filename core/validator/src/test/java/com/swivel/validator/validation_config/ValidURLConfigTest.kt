package com.swivel.validator.validation_config

import org.assertj.core.api.Assertions.*
import org.junit.Test

/**
 * @author Yohan Dushmantha
 * @class ValidURLConfigTest
 */
class ValidURLConfigTest {

    @Test
    fun given_TextIsNull_When_IsRequiredEqualsTrue_Expect_IsValidFalse() {
        val nameValidationConfig = ValidURLConfig().apply {
            fieldName = "Image Url"
            isRequired = true
            text = null
        }

        assertThatThrownBy { nameValidationConfig.validate() }.isExactlyInstanceOf(KotlinNullPointerException::class.java)

    }

    @Test
    fun given_TextIsEmpty_When_IsRequiredTrue_Expect_IsValidFalse(){
        val config = ValidURLConfig().apply {
            fieldName = "Image Url"
            isRequired = true
            text = ""
        }

        config.validate().let {
            assertThat(it.isValid).isFalse()
            assertThat(it.resourceId).isNotNull()
        }
    }

    /*@Test
    fun given_Text_When_URLIsInvalid_Expect_IsValidFalse(){
        val config = ValidURLConfig().apply {
            fieldName = "Image Url"
            isRequired = true
            text = "httphomepages.cae.wisc.edu/~ece533/images/airplane.png"
        }

        val result = config.validate()
        assertFalse(result.isValid)
    }

    @Test
    fun given_Text_When_URLIsValid_Expect_IsValidTrue(){
        val config = ValidURLConfig().apply {
            fieldName = "Image Url"
            isRequired = true
            text = "http://homepages.cae.wisc.edu/~ece533/images/airplane.png"
        }

        val result = config.validate()
        assertTrue(result.isValid)
    }*/

}