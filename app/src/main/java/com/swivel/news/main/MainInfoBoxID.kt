package com.swivel.news.main

/**
 * @author Yohan Dushmantha
 * @class MainInfoBoxID
 *
 * consists of info box message id's of main activity. this is for preventing duplication of info
 * box callbacks.
 *
 * @param infoBoxID info box id for identifying info box
 */
enum class MainInfoBoxID (val infoBoxID : Short) {
    INTERNET_ACCESSIBILITY_ISSUE(10001)
}