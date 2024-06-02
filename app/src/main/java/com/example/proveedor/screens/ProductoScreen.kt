package com.example.proveedor.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.proveedor.database.entidades.ProductoEntidad

@Composable
fun ProductoScreen(productoId: String?, productoViewModel: ProductoViewModel = viewModel()) {
    val productoIdInt: Int = productoId?.toIntOrNull() ?: 0

    val producto by remember { derivedStateOf { productoViewModel.productos } }

    LaunchedEffect(productoIdInt) {
        productoViewModel.fetchProducto(productoIdInt)
    }

    if (producto.isNotEmpty()) {

        Column {
            Card(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .background(color = Color.Black)
                        .fillMaxWidth()
                        .padding(horizontal = 110.dp)
                ) {
                    Text(
                        text = "Productos",
                        fontWeight = FontWeight.SemiBold,
                        fontStyle = FontStyle.Italic,
                        color = Color.White
                    )
                }
            }
            ProductoPantalla(producto)
        }
    } else {
        Text(text = "No se encontró ningun producto.")
    }
}

@Composable
fun ProductoPantalla(productos: List<ProductoEntidad>) {
    productos.forEach { producto ->
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Blue)
                .padding(5.dp)
                .zIndex(4f),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(Modifier.padding(20.dp)) {
                Row() {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(producto.Imagen)
                            .build(),
                        contentDescription = "",
                        modifier = Modifier
                            .scale(1f)
                            .size(100.dp),
                    )
                    Column() {
                        Text(text = "ID: ${producto.id}")
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(text = "Nombre: ${producto.Nombre}")
                        Spacer(modifier = Modifier.height(7.dp))
                        Text(text = "Descripción: ${producto.Descripcion}")
                        Spacer(modifier = Modifier.height(7.dp))
                        Text(text = "Precio: ${producto.Precio}")
                        Spacer(modifier = Modifier.height(7.dp))

                    }

                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}

