package com.swivel.sample_feature.ui.post.enums

/**
 * @author Yohan Dushmantha
 * @class PostInfoBoxID
 *
 * consists of info box message id's of post fragment. this is for preventing duplication of info
 * box callbacks. Developer should create this kind of class for every fragment or activity
 * which is using info box component
 *
 * @param infoBoxID info box id for identifying info box
 */
enum class PostInfoBoxID(val infoBoxID : Short) {
    SUCCESS_INFO_BOX(1),
    ERROR_INFO_BOX(2),
    WARNING_INFO_BOX(3),
    INFO_INFO_BOX(4)
}