package com.example.demo.controller

import com.example.demo.common.ResourceNotFoundException
import com.example.demo.model.Cat
import com.example.demo.repository.CatRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cats")
class CatController(private val catRepository: CatRepository) {

    @GetMapping
    fun getAllCats(): ResponseEntity<Iterable<Cat>> {
        return ResponseEntity.ok(catRepository.findAll())
    }

    @GetMapping("/{id}")
    fun getCat(@PathVariable id: Int): ResponseEntity<Cat> {
        val cat = catRepository.findByIdOrNull(id)

        return if (cat != null) {
            ResponseEntity.ok(cat)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun createCat(@RequestBody cat: Cat): ResponseEntity<Void> {
        catRepository.save(cat)

        return ResponseEntity.ok().build()
    }

    @PutMapping("cats/{id}")
    fun updateCat(@PathVariable id: Int, @RequestBody catDetails: Cat): ResponseEntity<Void> {
        val cat = catRepository.findById(id)
                .orElseThrow { ResourceNotFoundException("Cat not found with id $id") }!!
        cat.breed = catDetails.breed
        cat.picture = catDetails.picture
        catRepository.save(cat)
        
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{id}")
    fun deleteCat(@PathVariable id: Int): ResponseEntity<Void> {
        return if (catRepository.existsById(id)) {
            catRepository.deleteById(id)
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}