package com.marketsvrn.network.fake

import com.marketsvrn.network.model.dto.AddressDTO
import com.marketsvrn.network.model.dto.DeliveryManDTO
import com.marketsvrn.network.model.dto.MarketDTO
import com.marketsvrn.network.model.dto.OrderDTO
import com.marketsvrn.network.model.dto.PlaceDTO
import com.marketsvrn.network.model.dto.PositionDTO
import com.marketsvrn.network.model.dto.ProductCategoryDTO
import com.marketsvrn.network.model.dto.ProductDTO
import com.marketsvrn.network.model.dto.PromoCodeDTO
import com.marketsvrn.network.model.dto.UserDTO
import com.marketsvrn.network.model.dto.WorkingHoursDTO

object FakeModels {
    private val FAKE_ADDRESS_DTO = AddressDTO (
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
    internal val FAKE_USER_DTO = UserDTO(
        id = 0,
        firstName = "Имя",
        lastName = "Фамилия",
        phone = "+78005553535",
        email = "email@mail.com",
        login = "login"
    )
    internal val FAKE_MARKET_DTO = MarketDTO(
        id = 0,
        name = "Центральный рынок",
        images = listOf(
            "https://avatars.mds.yandex.net/get-altay/1335362/2a00000185f37df673c70d2e2dc23f45a08d/XXXL"
        ),
        address = FAKE_ADDRESS_DTO,
        workingHours = listOf(
            WorkingHoursDTO(
                dayOfWeek = 0,
                openingTime = "10:00",
                closingTime = "22:00"
            ),
            WorkingHoursDTO(
                dayOfWeek = 1,
                openingTime = "10:00",
                closingTime = "22:00"
            ),
            WorkingHoursDTO(
                dayOfWeek = 2,
                openingTime = "10:00",
                closingTime = "22:00"
            ),
            WorkingHoursDTO(
                dayOfWeek = 3,
                openingTime = "10:00",
                closingTime = "22:00"
            ),
            WorkingHoursDTO(
                dayOfWeek = 4,
                openingTime = "10:00",
                closingTime = "22:00"
            ),
            WorkingHoursDTO(
                dayOfWeek = 5,
                openingTime = "10:00",
                closingTime = "22:00"
            )
        )
    )

    internal val FAKE_PLACE_DTO = PlaceDTO(
        id = 0,
        name = "Свежие фрукты от Ивана",
        logo = "https://avatars.mds.yandex.net/get-altay/1335362/2a00000185f37df673c70d2e2dc23f45a08d/XXXL",
        description = "Lorem ipsum dolor sit amet lorem ipsum dolor sit amet lorem lorem ipsum dolor sit amet lorem",
        locationPhoto = "https://s1.media.ngoisao.vn/news/2021/06/07/meo-uop-va-xao-thit-bo-ngoisaovn-6-ngoisaovn-w1080-h648.jpg",
        phones = listOf("+78005553535", "+78005553535", "+78005553535"),
        market = FAKE_MARKET_DTO,
        estimate = 0.0F,
        workingHours = listOf(
            WorkingHoursDTO(
                dayOfWeek = 0,
                openingTime = "10:00",
                closingTime = "22:00"
            ),
            WorkingHoursDTO(
                dayOfWeek = 1,
                openingTime = "10:00",
                closingTime = "22:00"
            ),
            WorkingHoursDTO(
                dayOfWeek = 2,
                openingTime = "10:00",
                closingTime = "22:00"
            ),
            WorkingHoursDTO(
                dayOfWeek = 3,
                openingTime = "10:00",
                closingTime = "22:00"
            ),
            WorkingHoursDTO(
                dayOfWeek = 4,
                openingTime = "10:00",
                closingTime = "22:00"
            ),
            WorkingHoursDTO(
                dayOfWeek = 5,
                openingTime = "10:00",
                closingTime = "22:00"
            )
        )
    )

    internal val FAKE_PRODUCT_CATEGORY_DTO = ProductCategoryDTO(
        id = 0,
        name = "Молочная продукция"
    )

    internal val FAKE_PRODUCT_DTO = ProductDTO(
        id = 0,
        placeId = 0,
        description = "Описание товара",
        images = listOf(
            "https://avatars.mds.yandex.net/get-altay/1335362/2a00000185f37df673c70d2e2dc23f45a08d/XXXL",
            "https://avatars.mds.yandex.net/get-altay/1335362/2a00000185f37df673c70d2e2dc23f45a08d/XXXL",
            "https://avatars.mds.yandex.net/get-altay/1335362/2a00000185f37df673c70d2e2dc23f45a08d/XXXL"
        ),
        name = "Название товара",
        price = 5.66f,
        attributes = emptyList(),
        estimate = 5.0f,
        weight = 500,
        category = FAKE_PRODUCT_CATEGORY_DTO,
        manufacturer = "ООО \"АРМЯНСОЮЗ\""
    )

    private val FAKE_POSITION_DTO = PositionDTO(
        product = FAKE_PRODUCT_DTO,
        count = 4
    )

    private val FAKE_DELIVERYMAN_DTO = DeliveryManDTO(
        id = 0,
        firstName = "Иван",
        lastName = "Иванов",
        regDate = "21.08.2023",
        phone = "+78005553535"
    )

    private val FAKE_PROMOCODE_DTO = PromoCodeDTO(
        id = 0,
        sale = 5,
        code = "CODE23"
    )

    internal val FAKE_ORDER_DTO = OrderDTO(
        id = 0,
        date = "2023-08-21 10:58:34",
        status = "",
        deliveryman = FAKE_DELIVERYMAN_DTO,
        price = 777.22f,
        deliveryPrice = 20f,
        promocode = FAKE_PROMOCODE_DTO,
        total = 100f,
        market = FAKE_MARKET_DTO,
        address = FAKE_ADDRESS_DTO,
        positions = listOf(FAKE_POSITION_DTO, FAKE_POSITION_DTO, FAKE_POSITION_DTO)
    )
}