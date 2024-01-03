package com.example.demo.scheduler

import com.example.demo.service.CatService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class DeleteOldCatsScheduler(private val catService: CatService) {

    @Scheduled(fixedRate = 60000)
    fun checkAndDeleteOldCats() {
        catService.deleteOldCats()
    }
}