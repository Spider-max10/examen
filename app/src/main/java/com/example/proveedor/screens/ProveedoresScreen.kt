package com.example.proveedor.screens

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.proveedor.database.entidades.ProveedorEntidad
import com.example.proveedor.navigation.AppScreens

@Composable
fun ProveedoresScreen(navController: NavController, scrollState: ScrollState ,proveedoresViewModel: ProveedoresViewModel = viewModel()) {
    val proveedores by remember { derivedStateOf { proveedoresViewModel.proveedores } }
    if (proveedores.isNotEmpty()) {
        Column (modifier = Modifier.verticalScroll(scrollState)) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .background(color = Color.Black)
                        .fillMaxWidth()
                        .padding(horizontal = 110.dp)
                ) {
                    Text(text = "Proveedores", fontWeight = FontWeight.SemiBold, fontStyle = FontStyle.Italic, color = Color.White)
                }
            }
            proveedores.forEach { proveedor ->
                ProveedorPantalla(navController, scrollState= scrollState, proveedor)
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    } else {
        Text(text = "No hay proveedores registrados")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProveedorPantalla(navController: NavController, scrollState: ScrollState ,proveedor: ProveedorEntidad) {
    Card(
        onClick = {
            println(proveedor.id)
            navController.navigate("${AppScreens.Producto.route}/${proveedor.id}")
        },
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Yellow)
            .padding(5.dp)
            .zIndex(4f),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column() {
            Row() {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(proveedor.Logo)
                        .build(),
                    contentDescription = "",
                    modifier = Modifier
                        .scale(1f)
                        .size(100.dp),
                )
                Column() {
                    Text(text = "ID: ${proveedor.id}")
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = "Nombre: ${proveedor.Nombre}")
                    Spacer(modifier = Modifier.height(7.dp))
                    Text(text = "Descripción: ${proveedor.Descripcion}")
                    Spacer(modifier = Modifier.height(7.dp))
                    Text(text = "Fundacion: ${proveedor.Fundacion}")
                }
            }

        }
    }
}