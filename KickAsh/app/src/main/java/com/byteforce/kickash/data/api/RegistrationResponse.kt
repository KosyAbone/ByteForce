package com.byteforce.kickash.data.api


import com.squareup.moshi.Json

data class RegistrationResponse(
    @Json(name = "message") val message: String
)