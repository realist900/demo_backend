package com.example.demo.controller.cat

import com.example.demo.common.CommonUtil
import com.example.demo.common.ResourceNotFoundException
import com.example.demo.controller.cat.request.CatRequest
import com.example.demo.model.common.PagingData
import com.example.demo.model.table.Cat
import com.example.demo.repository.CatRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RestController
@RequestMapping("/cats")
class CatController(private val catRepository: CatRepository) {

    @GetMapping
    fun getCats(
        @RequestParam(value = "offset", defaultValue = "0") offset: Int,
        @RequestParam(value = "limit", defaultValue = "20") limit: Int
    ): ResponseEntity<PagingData<Cat>> {
        val userId = CommonUtil.getUserId()
        val cats = catRepository.findCatsWithLimitAndOffset(userId, limit, offset)
        val total = catRepository.countCatsByUserId(userId)
        return ResponseEntity.ok(PagingData(values = cats, total = total))
    }

    @GetMapping("/{id}")
    fun getCat(@PathVariable id: Int): ResponseEntity<Cat> {
        val cat = catRepository.findByIdAndUserId(id, CommonUtil.getUserId())

        return if (cat != null) {
            ResponseEntity.ok(cat)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun createCat(@RequestBody catRequest: CatRequest): ResponseEntity<Void> {
        val created = LocalDateTime.parse(catRequest.created, DateTimeFormatter.ISO_DATE_TIME)
        val userId: Int = CommonUtil.getUserId()

        val cat = Cat(
            userId = userId,
            breed = catRequest.breed,
            picture = catRequest.picture,
            created = created,
        )
        catRepository.save(cat)

        return ResponseEntity.ok().build()
    }

    @PutMapping("/{id}")
    fun updateCat(@PathVariable id: Int, @RequestBody catRequest: CatRequest): ResponseEntity<Void> {
        val cat = catRepository.findByIdAndUserId(id, CommonUtil.getUserId())
            ?: throw ResourceNotFoundException("Cat not found with id $id")

        cat.breed = catRequest.breed
        cat.picture = catRequest.picture
        catRepository.save(cat)

        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{id}")
    fun deleteCat(@PathVariable id: Int): ResponseEntity<Void> {
        return if (catRepository.existsCatByIdAndUserId(id, CommonUtil.getUserId())) {
            catRepository.deleteById(id)
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}