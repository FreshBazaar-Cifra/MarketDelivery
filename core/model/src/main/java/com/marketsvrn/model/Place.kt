package com.marketsvrn.model

data class Place (
    val id: Int,
    val name: String,
    val logo: String,
    val description: String,
    val locationPhoto: String,
    val phones: List<String>,
    val market: Market,
    val workingHours: List<WorkingHours>
) {
    companion object {
        fun getStub(): Place {
            return Place(
                id = 0,
                name = "Свежие фрукты от Ивана",
                logo = "https://avatars.mds.yandex.net/get-altay/1335362/2a00000185f37df673c70d2e2dc23f45a08d/XXXL",
                description = "Lorem ipsum dolor sit amet lorem ipsum dolor sit amet lorem lorem ipsum dolor sit amet lorem",
                locationPhoto = "https://avatars.mds.yandex.net/get-altay/1335362/2a00000185f37df673c70d2e2dc23f45a08d/XXXL",
                phones = listOf("+78005553535", "+78005553535", "+78005553535"),
                market = Market.getStub(),
                workingHours = listOf(
                    WorkingHours(
                        dayOfWeek = 0,
                        openingTime = "10:00",
                        closingTime = "22:00"
                    ),
                    WorkingHours(
                        dayOfWeek = 1,
                        openingTime = "10:00",
                        closingTime = "22:00"
                    ),
                    WorkingHours(
                        dayOfWeek = 2,
                        openingTime = "10:00",
                        closingTime = "22:00"
                    ),
                    WorkingHours(
                        dayOfWeek = 3,
                        openingTime = "10:00",
                        closingTime = "22:00"
                    ),
                    WorkingHours(
                        dayOfWeek = 4,
                        openingTime = "10:00",
                        closingTime = "22:00"
                    ),
                    WorkingHours(
                        dayOfWeek = 5,
                        openingTime = "10:00",
                        closingTime = "22:00"
                    )
                )
            )
        }
    }
}