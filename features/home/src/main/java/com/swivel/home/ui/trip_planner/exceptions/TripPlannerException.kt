package com.swivel.home.ui.trip_planner.exceptions

import java.lang.Exception

/**
 * @author Yohan Dushmantha
 * @class TripPlannerException
 *
 * TripPlannerException should be throw when error occurred inside trip planner
 */
class TripPlannerException constructor(text : String? = null) : Exception() {
    override val message: String? = text ?: "Trip Planner Exception"
}