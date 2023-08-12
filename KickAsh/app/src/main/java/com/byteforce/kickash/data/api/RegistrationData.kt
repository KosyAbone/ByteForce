package com.byteforce.kickash.data.api

import com.squareup.moshi.Json


data class RegistrationData(
    @Json(name = "firstName") val firstName: String,
    @Json(name = "lastName") val lastName: String,
    @Json(name = "username") val username: String,
    @Json(name = "email") val email: String,
    @Json(name = "password") val password: String
)