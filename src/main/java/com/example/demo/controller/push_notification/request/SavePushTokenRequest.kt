package com.example.demo.controller.push_notification.request

import com.fasterxml.jackson.annotation.JsonProperty

data class SavePushTokenRequest(
    @JsonProperty("push_token")
    var pushToken: String = "",
    @JsonProperty("device_uid")
    var deviceUid: String = ""
)