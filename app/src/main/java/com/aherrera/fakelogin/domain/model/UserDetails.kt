package com.aherrera.fakelogin.domain.model

import com.aherrera.fakelogin.data.model.response.LoginResponse

class UserDetails(
    val fullName: String,
    val avatarUrl: String
) {
    companion object {

        fun mapFromResponse(response: LoginResponse): UserDetails = UserDetails(
            fullName = buildFullName(firstName = response.firstName, lastName = response.lastName),
            avatarUrl = response.image ?: "https://i.pravatar.cc/300"
        )

        private fun buildFullName(firstName: String?, lastName: String?): String =
            listOfNotNull(firstName, lastName)
                .filter { it.isNotBlank() }
                .joinToString(" ")

    }
}