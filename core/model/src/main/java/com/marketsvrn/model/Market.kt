package com.marketsvrn.model

data class Market(
    val id: Int,
    val name: String,
    val images: List<String>,
    val address: Address,
    val workingHours: List<WorkingHours>
){

    companion object {
        fun getStub(): Market {
            return Market(
                id = -1,
                name = "Центральный рынок",
                images = listOf(
                    "https://avatars.mds.yandex.net/get-altay/1335362/2a00000185f37df673c70d2e2dc23f45a08d/XXXL",
                    "https://avatars.mds.yandex.net/get-altay/1335362/2a00000185f37df673c70d2e2dc23f45a08d/XXXL",
                    "https://avatars.mds.yandex.net/get-altay/1335362/2a00000185f37df673c70d2e2dc23f45a08d/XXXL",
                    "https://avatars.mds.yandex.net/get-altay/1335362/2a00000185f37df673c70d2e2dc23f45a08d/XXXL",
                ),
                address = Address.getStub(),
                workingHours = listOf(
                    WorkingHours.getStub().copy(dayOfWeek = 1),
                    WorkingHours.getStub().copy(dayOfWeek = 2),
                    WorkingHours.getStub().copy(dayOfWeek = 3),
                    WorkingHours.getStub().copy(dayOfWeek = 4),
                    WorkingHours.getStub().copy(dayOfWeek = 5),
                )
            )
        }
    }
}