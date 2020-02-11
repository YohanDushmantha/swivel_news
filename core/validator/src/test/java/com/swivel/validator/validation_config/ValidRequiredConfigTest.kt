package com.swivel.validator.validation_config

import org.assertj.core.api.Assertions.*
import org.junit.Test

/**
 * @author Yohan Dushmantha
 * @class ValidRequiredConfigTest
 */
class ValidRequiredConfigTest {

    @Test
    fun given_TextIsNull_When_IsRequiredEqualsTrue_Expect_IsValidFalse() {
        val nameValidationConfig = ValidRequiredConfig().apply {
            fieldName = "First Name"
            isRequired = true
            text = null
        }

        assertThatThrownBy { nameValidationConfig.validate() }.isExactlyInstanceOf(KotlinNullPointerException::class.java)

    }

    @Test
    fun given_TextIsEmpty_When_IsRequiredTrue_Expect_IsValidFalse(){
        val config = ValidRequiredConfig().apply {
            fieldName = "First Name"
            isRequired = true
            text = ""
        }

        config.validate().let {
            assertThat(it.isValid).isFalse()
            assertThat(it.resourceId).isNotNull()
        }
    }

    @Test
    fun given_Text_When_IsRequiredTrue_And_TextIsNotEmpty_Expect_IsValidTrue(){
        val config = ValidRequiredConfig().apply {
            fieldName = "First Name"
            isRequired = true
            text = "Yohan"
        }

        config.validate().let {
            assertThat(it.isValid).isTrue()
        }
    }

}