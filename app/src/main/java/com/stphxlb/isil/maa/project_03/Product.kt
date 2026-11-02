package com.stphxlb.isil.maa.project_03

import android.app.Activity
import android.util.Log
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

// URL del PHP en AlwaysData. Si la abro en el navegador tiene que salir un arreglo JSON.
private const val PRODUCTS_URL = "https://stphxlb.alwaysdata.net/productos.php"

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

// El PHP manda un arreglo: [ { idproducto, nombre, ... }, ... ].
// mysqli manda los números como texto, por eso getString y después toInt.
fun parseProducts(json: String): List<Product> {
    val array = JSONArray(json)
    val list = mutableListOf<Product>()

    for (index in 0 until array.length()) {
        val row: JSONObject = array.getJSONObject(index)
        list.add(
            Product(
                id = row.getString("idproducto").toInt(),
                name = row.getString("nombre"),
                category = row.getString("categoria"),
                price = row.getString("precio"),
                description = row.getString("descripcion"),
                available = row.getString("disponible") == "1"
            )
        )
    }

    return list
}

// La red no puede ir en el hilo de la pantalla: Android la corta.
// Por eso abro un Thread y, cuando termina, vuelvo a la UI con runOnUiThread.
// El texto del error lo pido antes, para no llamar a getString desde el hilo de red.
fun Activity.fetchProducts(
    onReady: (List<Product>) -> Unit,
    onError: (String) -> Unit
) {
    val errorText = getString(R.string.products_error)

    Thread {
        try {
            val connection = URL(PRODUCTS_URL).openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connectTimeout = 10000
            connection.readTimeout = 10000

            val json = connection.inputStream.bufferedReader().use { it.readText() }
            connection.disconnect()

            val list = parseProducts(json)
            runOnUiThread { onReady(list) }
        } catch (error: Exception) {
            Log.e("Haze", "No se pudo leer el JSON", error)
            runOnUiThread { onError(errorText) }
        }
    }.start()
}
