package com.example.demo.model.request

data class CatRequest(
    var id: Int? = null,
    var breed: String? = null,
    var picture: String? = null
)