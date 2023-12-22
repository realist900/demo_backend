package com.example.demo.model.response

import com.fasterxml.jackson.annotation.JsonProperty

data class TokenResponse(
    @JsonProperty("access_token")
    val accessToken: String
)