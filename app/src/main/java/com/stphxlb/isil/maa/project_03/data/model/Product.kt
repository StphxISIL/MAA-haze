package com.stphxlb.isil.maa.project_03.data.model

// Una fila de la tabla. Los nombres en inglés son del código.
// Lo que se ve en pantalla sale de strings.xml y de lo que manda el JSON.
data class Product(
    val id: Int,            // idproducto, lo uso de key en la lista, no se muestra
    val name: String,       // nombre
    val category: String,   // categoria
    val price: String,      // precio, llega como texto desde el PHP
    val description: String,
    val available: Boolean  // disponible: "1" es sí
)
