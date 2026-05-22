package com.sraccelerator.easyorder.core.featureflag

enum class FeatureKey(val key: String) {
    CHECKOUT_PICKUP("checkout_pickup_at_store"),
    CHECKOUT_COUPON("checkout_coupon_input"),
    SUPPORT_CHANNEL("checkout_support_channel")
}
