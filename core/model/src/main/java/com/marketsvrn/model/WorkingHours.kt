package com.marketsvrn.model

data class WorkingHours(
    val dayOfWeek: Int,
    val openingTime: String,
    val closingTime: String,
) {
    companion object {
        fun getStub(): WorkingHours {
            return WorkingHours(
                dayOfWeek = 0,
                openingTime = "",
                closingTime = ""
            )
        }
    }
}