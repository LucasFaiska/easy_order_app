package com.sraccelerator.easyorder.analytics

sealed class AnalyticsEvent (val name: String, val params: Map<String, String> = emptyMap()) {
    data class CategoryClicked(val id: Int, val categoryName: String) : AnalyticsEvent(
        name = "category_clicked",
        params = mapOf("id" to id.toString(), "name" to categoryName)
    )

    data object ViewCart : AnalyticsEvent(name = "view_cart")
}