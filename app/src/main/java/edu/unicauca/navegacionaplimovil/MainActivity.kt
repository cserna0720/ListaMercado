package edu.unicauca.navegacionaplimovil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.unicauca.navegacionaplimovil.ui.theme.NavegacionAplimovilTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavegacionAplimovilTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navegacion()
                }
            }
        }
    }
}

@Composable
fun PantallaInicial(
    onNextClick: ()-> Unit,
    irAtercera: ()-> Unit
){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ){
        var texto by rememberSaveable { mutableStateOf("") }
        OutlinedTextField(
            value = texto,
            onValueChange = { texto = it },
            label = { Text("Usuario") },
            modifier = Modifier.offset(x = 0.dp, y = 100.dp),
            singleLine = true
        )
        var texto2 by rememberSaveable { mutableStateOf("") }
        OutlinedTextField(
            value = texto2,
            onValueChange = { texto2 = it },
            label = { Text("Contraseña") },
            modifier = Modifier.offset(x = 0.dp, y = 100.dp),
            singleLine = true

        )

        Image(
            painter = painterResource(id = R.drawable.whatsapp_image_2025_03_31_at_12_29_08_am),
            contentDescription = null,
            modifier = Modifier.offset(x = 0.dp, y = -200.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = onNextClick) {
            Text(text = "Iniciar Sesion")

        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = irAtercera) {
            Text(text = "Crear ususario")
        }

        }


    }

@Composable
fun SegundaPantalla(
    onClick:()->Unit
){
    Column (horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()){
        Text(text = "Segunda pantalla")
        Button(onClick = onClick) {
            Text(text = "Siguiente")
        }
    }
}

@Composable
fun TerceraPantalla(
    onClick : ()->Unit
){
    Column (horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()){
        Text(text = "Tercera Pantalla")
        Button(onClick = onClick ) {
            Text(text = "Siguiente")
        }
    }
}
    @Composable
    fun CuartaPantalla(
        irAinicio: () -> Unit
    ){
        Column (horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()){
            Text(text = "Ultima Pantalla")
            Button(onClick = irAinicio) {
                Text(text = "Ir al Inicio")
            }
        }
    }


@Composable
fun Navegacion() {
    val navHostController = rememberNavController()
    NavHost(
        navController = navHostController,
        startDestination = Pantallas.Inicio.name

    ) {
        composable(route = Pantallas.Inicio.name) {
            PantallaInicial(
                onNextClick = { navHostController.navigate(Pantallas.Segunda.name) },
                irAtercera= { navHostController.navigate(Pantallas.Tercera.name) }
            )
        }
        composable(route = Pantallas.Segunda.name) {
            SegundaPantalla(
                onClick = { navHostController.navigate(Pantallas.Tercera.name) }
            )
        }
        composable(route = Pantallas.Tercera.name) {
            TerceraPantalla(
                onClick = { navHostController.navigate(Pantallas.Cuarta.name) }
            )
        }
            composable(route = Pantallas.Cuarta.name) {
                CuartaPantalla(
                    irAinicio = { navHostController.navigate(Pantallas.Inicio.name) }
                )
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun PreviewInicial() {
        NavegacionAplimovilTheme {
            PantallaInicial(
                onNextClick = {},
                irAtercera = {}
            )
        }
    }

    enum class Pantallas() {
    Inicio,
    Segunda,
    Tercera,
    Cuarta
}




