package com.example.demo.common

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import java.io.FileNotFoundException

@Component
class FirebaseUtil {

    @Bean
    fun firebaseMessaging() : FirebaseMessaging {
        val classLoader = Thread.currentThread().contextClassLoader
        val resourceAsStream = classLoader.getResourceAsStream("serviceAccountKey.json")
            ?: throw FileNotFoundException("Cannot find serviceAccountKey.json")


        val options = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(resourceAsStream))
            .build()

        val app = FirebaseApp.initializeApp(options)

        return FirebaseMessaging.getInstance(app);
    }
}