package com.example.demo.controller.push_notification.request

import com.fasterxml.jackson.annotation.JsonProperty

data class DeletePushTokenRequest(
    @JsonProperty("device_uid")
    var deviceUid: String = ""
)