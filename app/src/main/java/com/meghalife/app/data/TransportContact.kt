package com.meghalife.app.data

data class TransportContact(
    val name: String,
    val phone: String,
    val type: TransportType
)

enum class TransportType {
    SHARED_TAXI,
    LOCAL_TAXI,
    TOURIST_CAB
}
