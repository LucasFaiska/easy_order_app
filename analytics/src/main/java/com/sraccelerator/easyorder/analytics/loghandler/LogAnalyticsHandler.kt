package com.sraccelerator.easyorder.analytics.loghandler

import android.util.Log
import com.sraccelerator.easyorder.analytics.AnalyticsEvent
import com.sraccelerator.easyorder.analytics.AnalyticsHandler
import javax.inject.Inject

class LogAnalyticsHandler @Inject constructor() : AnalyticsHandler {
    override fun logEvent(event: AnalyticsEvent) {
        Log.d(
        "Analytics",
        "Event - Log: ${event.name}, Params: ${event.params}"
        )
    }
}