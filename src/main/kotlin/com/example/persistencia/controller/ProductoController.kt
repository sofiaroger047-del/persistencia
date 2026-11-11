
package com.example.persistencia.controller

import com.example.persistencia.dto.ProductoRequest
import com.example.persistencia.dto.ProductoResponse
import com.example.persistencia.service.ProductoService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/productos")
class ProductoController(
    private val productoService: ProductoService
) {

    @GetMapping
    fun listar(pageable: Pageable): ResponseEntity<Page<ProductoResponse>> {
        return ResponseEntity.ok(productoService.listar(pageable))
    }

    @GetMapping("/{id}")
    fun obtenerPorId(
        @PathVariable id: Long
    ): ResponseEntity<ProductoResponse> {
        return ResponseEntity.ok(productoService.obtener(id))
    }

    @PostMapping
    fun crear(
        @Valid @RequestBody req: ProductoRequest
    ): ResponseEntity<ProductoResponse> {
        val creado = productoService.crear(req)
        return ResponseEntity.status(HttpStatus.CREATED).body(creado)
    }
}
