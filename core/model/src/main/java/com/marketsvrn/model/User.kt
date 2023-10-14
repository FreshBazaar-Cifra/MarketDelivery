package com.marketsvrn.model

data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val phone: String?,
    val email: String?,
    val login: String
) {
    companion object {
        fun getStub(): User {
            return User(
                id = 0,
                firstName = "Александр",
                lastName = "Ерешкин",
                phone = "+79201234567",
                email = "blabla@mail.ru",
                login = "AlexEreh"
            )
        }
    }
}
