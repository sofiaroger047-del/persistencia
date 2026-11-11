package com.example.persistencia.exception

class ProductoNoEncontradoException(id: Long) :
    RuntimeException("Producto no encontrado con el ID: $id")
