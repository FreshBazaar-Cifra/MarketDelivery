package com.marketsvrn.model

data class Address(
    val city: String,
    val district: String?,
    val street: String,
    val home: String,
    val entrance: String?,
    val apartment: String?,
    val floor: String?,
    val intercom: String?,
    val latitude: Float,
    val longitude: Float,
) {
    fun getAddressAsString(): String {
        return "$city, $street, $home"
    }
    companion object {
        fun getStub(): Address {
            return Address(
                city = "Воронеж",
                district = "Ленинский район",
                street = "ул. Плехановская",
                home = "1",
                entrance = null,
                apartment = null,
                floor = null,
                intercom = null,
                latitude = 0.0F,
                longitude = 0.0F
            )
        }
    }
}
