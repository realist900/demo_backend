package com.example.demo.model.response

import com.fasterxml.jackson.annotation.JsonProperty

data class PagingData<T>(
    @JsonProperty("values")
    val values: List<T>,
    @JsonProperty("total")
    val total: Long,
)