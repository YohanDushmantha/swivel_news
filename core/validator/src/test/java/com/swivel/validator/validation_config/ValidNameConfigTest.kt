package com.swivel.validator.validation_config

import org.assertj.core.api.Assertions.*
import org.junit.Test

/**
 * @author Yohan Dushmantha
 * @class ValidNameConfigTest
 *
 * Test class for ValidNameConfig
 */

class ValidNameConfigTest {

    @Test
    fun given_TextIsNull_When_IsRequiredEqualsTrue_Expect_IsValidFalse() {
        val nameValidationConfig = ValidNameConfig().apply {
            fieldName = "First Name"
            isRequired = true
            text = null
        }

        assertThatThrownBy { nameValidationConfig.validate() }.isExactlyInstanceOf(KotlinNullPointerException::class.java)
    }

    @Test
    fun given_TextIsEmpty_When_IsRequiredTrue_Expect_IsValidFalse(){
        val config = ValidNameConfig().apply {
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
    fun given_Text_When_TextLengthIsShorterThanMinLength_Expect_IsValidFalse(){
        val nameValidNameConfig = ValidNameConfig().apply {
            fieldName = "First Name"
            isRequired = true
            minLength = 4
            text = "ab"
        }

        nameValidNameConfig.validate().let {
            assertThat(it.isValid).isFalse()
            assertThat(it.customErrorMessage).isNull()
            assertThat(it.customSuccessMessage).isNull()
            assertThat(it.resourceId).isNotNull()
        }
    }

    @Test
    fun given_Text_When_TextLengthIsGreaterThanMaxLength_Expect_IsValidFalse(){
        val nameValidNameConfig = ValidNameConfig().apply {
            fieldName = "First Name"
            isRequired = true
            maxLength = 10
            text = "Fintechnology"
        }

        nameValidNameConfig.validate().let {
            assertThat(it.isValid).isFalse()
            assertThat(it.customErrorMessage).isNull()
            assertThat(it.customSuccessMessage).isNull()
            assertThat(it.resourceId).isNotNull()
        }
    }

    @Test
    fun given_Text_When_TextContainsNumbers_Expect_IsValidFalse(){
        val nameValidNameConfig = ValidNameConfig().apply {
            fieldName = "First Name"
            isRequired = true
            text = "F1intechno2logy3"
        }

        nameValidNameConfig.validate().let {
            assertThat(it.isValid).isFalse()
            assertThat(it.customSuccessMessage).isNull()
            assertThat(it.customSuccessMessage).isNull()
            assertThat(it.resourceId).isNotNull()
        }
    }

    @Test
    fun given_Text_When_TextContainsSpecialCharacters_Expect_IsValidFalse(){
        val nameValidNameConfig = ValidNameConfig().apply {
            fieldName = "First Name"
            isRequired = true
            text = "abc@def"
        }

        nameValidNameConfig.validate().let {
            assertThat(it.isValid).isFalse()
            assertThat(it.customSuccessMessage).isNull()
            assertThat(it.customErrorMessage).isNull()
            assertThat(it.resourceId).isNotNull()
        }
    }

    @Test
    fun given_ValidText_When_TextIsValid_Expect_IsValidTrue(){
        val nameValidNameConfig = ValidNameConfig().apply {
            fieldName = "First Name"
            isRequired = true
            text = "Finap"
        }

        nameValidNameConfig.validate().let {
            assertThat(it.isValid).isTrue()
            assertThat(it.customErrorMessage).isNull()
            assertThat(it.customSuccessMessage).isNull()
            assertThat(it.resourceId).isNull()
        }
    }

    @Test
    fun given_CustomErrorMessage_And_Text_When_TextLengthIsShorterThanMinLength_Expect_IsValidFalse_And_CustomErrorMessage(){
        val customErrorMessage = "Text length is shorter than expected length"
        val nameValidationConfig = ValidNameConfig().apply {
            fieldName = "First Name"
            isRequired = true
            text = "Finap"
            minLength = 10
            this.customErrorMessage = customErrorMessage
        }

        nameValidationConfig.validate().let {
            assertThat(it.isValid).isFalse()
            assertThat(customErrorMessage).isEqualTo(it.customErrorMessage)
            assertThat(it.customSuccessMessage).isNull()
        }
    }

    @Test
    fun given_CustomSuccessMessage_And_IsRequiredSuccessMessageTrue_And_ValidText_When_TextIsValid_Expect_IsValidTrue_And_CustomSuccessMessage(){
        val customSuccessMessage = "Name is Successfully Validated"
        val nameValidationConfig = ValidNameConfig().apply {
            fieldName = "First Name"
            isRequired = true
            text = "Finap"
            isSuccessMessageRequired = true
            this.customSuccessMessage = customSuccessMessage
        }

        nameValidationConfig.validate().let {
            assertThat(it.isValid).isTrue()
            assertThat(it.isSuccessMessageRequired).isTrue()
            assertThat(it.customSuccessMessage).isEqualTo(customSuccessMessage)
            assertThat(it.customErrorMessage).isNull()
            assertThat(it.resourceId).isNull()
        }
    }

}
