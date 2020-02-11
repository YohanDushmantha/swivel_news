package com.swivel.models.features.shared.confirm_box.event_listeners

/**
 * @author Yohan Dushmantha
 * @class ConfirmBoxEventResult
 * object for handling confirm box callback event results, if need to parse some data when triggering
 * callback events, can be used eventResult object for parsing data to destination
 */
data class ConfirmBoxEventResult constructor(
    var sampleData : String
)