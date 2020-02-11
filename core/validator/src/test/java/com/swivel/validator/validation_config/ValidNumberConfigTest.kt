package com.swivel.validator.validation_config

import org.assertj.core.api.Assertions.*
import org.junit.Test

/**
 * @author Yohan Dushmantha
 * @class ValidNumberConfigTest
 *
 * Test class for ValidNumberConfig
 */
class ValidNumberConfigTest {

    @Test
    fun given_TextIsNull_When_IsRequiredTrue_Expect_IsValidFalse(){
        val validNumberConfig = ValidNumberConfig().apply {
            fieldName = "Age"
            isRequired = true
            text = null
        }

        assertThatThrownBy { validNumberConfig.validate() }.isExactlyInstanceOf(KotlinNullPointerException::class.java)
    }

    @Test
    fun given_TextIsEmpty_When_IsRequiredTrue_Expect_IsValidFalse(){
        val config = ValidNumberConfig().apply {
            fieldName = "Age"
            isRequired = true
            text = ""
        }

        config.validate().let {
            assertThat(it.isValid).isFalse()
            assertThat(it.resourceId).isNotNull()
        }
    }

    @Test
    fun given_Text_When_IsInvalidNumber_Expect_IsValidFalse_And_ErrorMessageResourceId(){
        val validNumberConfig = ValidNumberConfig().apply {
            isRequired = true
            fieldName = "Age"
            text = "35r"
        }

        validNumberConfig.validate().let {
            assertThat(it.isValid).isFalse()
            assertThat(it.resourceId).isNotNull()
        }
    }

    @Test
    fun given_Text_When_IsValidNumber_Expect_IsValidTrue(){
        val validNumberConfig = ValidNumberConfig().apply {
            fieldName = "Age"
            text = "35"
        }

        validNumberConfig.validate().let {
            assertThat(it.isValid).isTrue()
            assertThat(it.resourceId).isNull()
            assertThat(it.customSuccessMessage).isNull()
        }
    }

    @Test
    fun given_Text_With_CustomErrorMessage_When_IsValid_Expect_IsValidFalse_And_CustomErrorMessage(){
        val customErrorMessage = "Provided Text is not a number"
        val validNumberConfig = ValidNumberConfig().apply {
            fieldName = "Age"
            text = "35r"
            this.customErrorMessage = customErrorMessage
        }

        validNumberConfig.validate().let {
            assertThat(it.isValid).isFalse()
            assertThat(it.customErrorMessage).isEqualTo(customErrorMessage)
        }
    }

    @Test
    fun given_Text_With_CustomSuccessMessage_And_IsRequiredCustomSuccessMessage_When_IsInvalid_Expect_IsValidTrue_And_CustomSuccessMessage(){
        val customSuccessMessage = "Provided Text is a number"
        val validNumberConfig = ValidNumberConfig().apply {
            fieldName = "Age"
            text = "35"
            isSuccessMessageRequired = true
            this.customSuccessMessage = customSuccessMessage
        }

        validNumberConfig.validate().let {
            assertThat(it.isValid).isTrue()
            assertThat(it.customSuccessMessage).isEqualTo(customSuccessMessage)
        }
    }
}