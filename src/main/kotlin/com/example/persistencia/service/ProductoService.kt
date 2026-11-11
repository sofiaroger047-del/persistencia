
package com.example.persistencia.service

import com.example.persistencia.dto.ProductoRequest
import com.example.persistencia.dto.ProductoResponse
import com.example.persistencia.entity.Producto
import com.example.persistencia.exception.ProductoNoEncontradoException
import com.example.persistencia.repository.ProductoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductoService(
    private val productoRepository: ProductoRepository
) {

    @Transactional(readOnly = true)
    fun listar(pageable: Pageable): Page<ProductoResponse> {
        return productoRepository.findAll(pageable)
            .map { ProductoResponse.fromEntity(it) }
    }

    @Transactional(readOnly = true)
    fun obtener(id: Long): ProductoResponse {
        val producto = productoRepository.findById(id)
            .orElseThrow { ProductoNoEncontradoException(id) }

        return ProductoResponse.fromEntity(producto)
    }

    @Transactional
    fun crear(req: ProductoRequest): ProductoResponse {
        val nuevoProducto = Producto(
            nombre = req.nombre,
            precio = req.precio,
            stock = req.stock
        )

        val guardado = productoRepository.save(nuevoProducto)

        return ProductoResponse.fromEntity(guardado)
    }
}
