package com.sraccelerator.easyorder.analytics

interface AnalyticsHandler {
    fun logEvent(event: AnalyticsEvent)
}