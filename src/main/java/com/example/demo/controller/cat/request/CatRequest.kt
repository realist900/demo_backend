package com.example.demo.controller.cat.request

import com.fasterxml.jackson.annotation.JsonProperty

data class CatRequest(
    @JsonProperty("id")
    var id: Int? = null,
    @JsonProperty("breed")
    var breed: String? = null,
    @JsonProperty("picture")
    var picture: String? = null,
    @JsonProperty("created")
    var created: String,
)