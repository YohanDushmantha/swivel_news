package com.swivel.validator.validation_config

import org.assertj.core.api.Assertions.*
import org.junit.Test

/**
 * @author Yohan Dushmantha
 * @class ValidEmailConfigTest
 *
 * Test class for ValidEmailConfig
 */

class ValidEmailConfigTest {

    @Test
    fun given_TextIsNull_When_IsRequiredEqualsTrue_Expect_IsValidFalse() {
        val validEmailConfig = ValidEmailConfig().apply {
            fieldName = "Email"
            isRequired = true
            text = null
        }

        assertThatThrownBy { validEmailConfig.validate() }.isExactlyInstanceOf(KotlinNullPointerException::class.java)
    }

    @Test
    fun given_TextIsEmpty_When_IsRequiredTrue_Expect_IsValidFalse() {
        val config = ValidEmailConfig().apply {
            fieldName = "Email"
            isRequired = true
            text = ""
        }

        val result = config.validate()

        assertThat(result.isValid).isFalse()
        assertThat(result.resourceId).isNotNull()

    }

    @Test
    fun given_Text_When_TextIsShorterThanMinLength_Expect_IsValidFalse() {
        val validEmailConfig = ValidEmailConfig().apply {
            fieldName = "Email"
            minLength = 5
            text = "abc"
        }

        validEmailConfig.validate().let {
            assertThat(it.isValid).isFalse()
            assertThat(it.resourceId).isNotNull()
        }
    }

    @Test
    fun given_Text_When_TextLength_Is_GreaterThanMaxLength_Expect_IsValidFalse() {
        val validEmailConfig = ValidEmailConfig().apply {
            fieldName = "Email"
            maxLength = 10
            text = "abc@gmail.com"
        }

        validEmailConfig.validate().let {
            assertThat(it.isValid).isFalse()
            assertThat(it.resourceId).isNotNull()
        }
    }

    @Test
    fun given_Text_When_InvalidEmail_Expect_IsValidFalse() {
        val validEmailConfig = ValidEmailConfig().apply {
            fieldName = "Email"
            text = "example.com"
        }

        validEmailConfig.validate().let {
            assertThat(it.isValid).isFalse()
            assertThat(it.resourceId).isNotNull()
        }
    }

    @Test
    fun given_Text_When_ValidEmail_Expect_IsValidTrue() {
        val validEmailConfig = ValidEmailConfig().apply {
            fieldName = "Email"
            text = "test@domain.com"
        }

        validEmailConfig.validate().let {
            assertThat(it.isValid).isTrue()
            assertThat(it.resourceId).isNull()
        }
    }

    @Test
    fun given_Text_CustomErrorMessage_When_Invalid_Expect_IsValidFalse_And_CustomErrorMessage() {
        val customErrorMessage = "Provided Email is not valid."
        val validEmailConfig = ValidEmailConfig().apply {
            fieldName = "Email"
            text = "example.com"
            this.customErrorMessage = customErrorMessage
        }

        validEmailConfig.validate().let {
            assertThat(it.isValid).isFalse()
            assertThat(it.customErrorMessage).isEqualTo(customErrorMessage)
        }
    }

    @Test
    fun given_Text_CustomSuccessMessage_And_IsRequiredSuccessMessageTrue_When_valid_Expect_IsValidTrue_And_CustomSuccessMessage() {
        val customSuccessMessage = "Provided Email is valid"
        val validEmailConfig = ValidEmailConfig().apply {
            fieldName = "Email"
            text = "test@domain.com"
            this.customSuccessMessage = customSuccessMessage
            isSuccessMessageRequired = true
        }

        validEmailConfig.validate().let {
            assertThat(it.isValid).isTrue()
            assertThat(it.resourceId).isNull()
            assertThat(it.isSuccessMessageRequired).isTrue()
            assertThat(it.customSuccessMessage).isEqualTo(customSuccessMessage)
        }
    }
}