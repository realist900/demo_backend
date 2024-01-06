package com.example.demo.controller.push_notification

import com.example.demo.common.CommonUtil
import com.example.demo.controller.push_notification.request.DeletePushTokenRequest
import com.example.demo.controller.push_notification.request.SavePushTokenRequest
import com.example.demo.model.table.PushToken
import com.example.demo.repository.PushTokenRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PushNotificationController(private val pushTokenRepository: PushTokenRepository) {

    @PostMapping("/save-push-token")
    fun savePushToken(@RequestBody request: SavePushTokenRequest): ResponseEntity<Unit> {
        val userId = CommonUtil.getUserId()

        val existingToken = pushTokenRepository.findByToken(request.pushToken)

        val pushToken = existingToken?.apply {
            this.deviceUid = request.deviceUid
            this.userId = userId
        }
            ?: PushToken(
                token = request.pushToken,
                deviceUid = request.deviceUid,
                userId = userId,
            )

        pushTokenRepository.save(pushToken)

        return ResponseEntity.ok().build()
    }

    @PostMapping("/delete-push-token")
    fun deletePushToken(@RequestBody request: DeletePushTokenRequest): ResponseEntity<Unit> {
        val userId = CommonUtil.getUserId()

        pushTokenRepository.deleteByDeviceUidAndUserId(deviceUid = request.deviceUid, userId = userId)

        return ResponseEntity.ok().build()
    }

}