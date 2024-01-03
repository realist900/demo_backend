package com.example.demo.model.websocket_event

import com.fasterxml.jackson.annotation.JsonProperty

data class BaseEvent<T>(
    @JsonProperty("type")
    val type: String,

    @JsonProperty("payload")
    val payload: T
)