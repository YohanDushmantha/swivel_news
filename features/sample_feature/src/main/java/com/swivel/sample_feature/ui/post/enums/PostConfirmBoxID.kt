package com.swivel.sample_feature.ui.post.enums

/**
 * @author Yohan Dushmantha
 * @class PostConfirmBoxID
 *
 * consists of confirm box message id's of post fragment. this is for preventing duplication of confirm
 * box callbacks. Developer should create this kind of class for every fragment or activity
 * which is using confirm box component
 *
 * @param confirmBoxID confirm box id for identifying confirm box
 */

enum class PostConfirmBoxID (val confirmBoxID: Short) {
    SAMPLE_CONFIRM_MESSAGE_1(1)
}