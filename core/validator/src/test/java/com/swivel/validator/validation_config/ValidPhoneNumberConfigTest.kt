package com.swivel.validator.validation_config

import org.assertj.core.api.Assertions.*
import org.junit.Test

/**
 * @author Yohan Dushmantha
 * @class ValidPhoneNumberConfigTest
 */
class ValidPhoneNumberConfigTest {

    @Test
    fun given_TextIsNull_When_IsRequiredTrue_Expect_IsValidFalse(){
        val config = ValidPhoneNumberConfig().apply {
            fieldName = "Mobile Number"
            isRequired = true
            text = null
        }

        assertThatThrownBy { config.validate() }.isExactlyInstanceOf(KotlinNullPointerException::class.java)
    }

    @Test
    fun given_TextIsEmpty_When_IsRequiredTrue_Expect_IsValidFalse(){
        val config = ValidPhoneNumberConfig().apply {
            fieldName = "Mobile Number"
            isRequired = true
            text = ""
        }

        config.validate().let {
            assertThat(it.isValid).isFalse()
            assertThat(it.resourceId).isNotNull()
        }
    }

    @Test
    fun given_Text_When_EqualsToShortLength_And_ContainsWithAlphabetCharacters_Expect_IsValidFalse(){
        val config = ValidPhoneNumberConfig().apply {
            fieldName = "Mobile Number"
            text = "071513412A"
        }

        config.validate().let {
            assertThat(it.isValid).isFalse()
            assertThat(it.resourceId).isNotNull()
        }
    }

    @Test
    fun given_Text_When_EqualsToShortLength_And_ContainsWithSpecialCharacters_Expect_IsValidFalse(){
        val config = ValidPhoneNumberConfig().apply {
            fieldName = "Mobile Number"
            text = "071513412+"
        }

        config.validate().let {
            assertThat(it.isValid).isFalse()
            assertThat(it.resourceId).isNotNull()
        }

    }

    @Test
    fun given_Text_When_EqualsToShortLength_Expect_IsValidTrue(){
        val config = ValidPhoneNumberConfig().apply {
            fieldName = "Mobile Number"
            text = "0715134120"
        }

        config.validate().let {
            assertThat(it.isValid).isTrue()
        }
    }

    @Test
    fun given_Text_When_EqualsToLongLength_And_NotStartsWithPlusMark_Expect_IsValidFalse(){
        val config = ValidPhoneNumberConfig().apply {
            fieldName = "Mobile Number"
            text = "-94715134120"
        }

        config.validate().let {
            assertThat(it.isValid).isFalse()
            assertThat(it.resourceId).isNotNull()
        }
    }

    @Test
    fun given_Text_When_EqualsToLongLength_And_BaseCurrencyCodeIsInvalid_Expect_IsValidFalse(){
        val config = ValidPhoneNumberConfig().apply {
            fieldName = "Mobile Number"
            text = "+99715134120"
        }

        config.validate().let {
            assertThat(it.isValid).isFalse()
            assertThat(it.resourceId).isNotNull()
        }
    }

    @Test
    fun given_Text_When_EqualsToLongLength_And_ContainsWithAlphabetCharacters_Expect_IsValidFalse(){
        val config = ValidPhoneNumberConfig().apply {
            fieldName = "Mobile Number"
            text = "+9471513412A"
        }

        config.validate().let {
            assertThat(it.isValid).isFalse()
            assertThat(it.resourceId).isNotNull()
        }
    }

    @Test
    fun given_Text_When_EqualsToLongLength_Expect_IsValidTrue(){
        val config = ValidPhoneNumberConfig().apply {
            fieldName = "Mobile Number"
            text = "+94715134121"
        }

        config.validate().let {
            assertThat(it.isValid).isTrue()
        }
    }


    @Test
    fun given_Text_When_NotEqulsToShortOrLongLength_Expect_IsValidFalse(){
        val config = ValidPhoneNumberConfig().apply {
            fieldName = "Mobile Number"
            text = "84098534"
        }

        config.validate().let {
            assertThat(it.isValid).isFalse()
            assertThat(it.resourceId).isNotNull()
        }
    }
}