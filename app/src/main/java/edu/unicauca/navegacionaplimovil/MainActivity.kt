package edu.unicauca.navegacionaplimovil

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.layout.*
import androidx.compose.ui.text.TextStyle
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    iraSegunda: () -> Unit
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
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
            label = { Text("Usuario") },
            modifier = Modifier.offset(x = 0.dp, y = 100.dp),
            singleLine = true
        )
        var texto2 by rememberSaveable { mutableStateOf("") }
        OutlinedTextField(
            value = texto2,
            onValueChange = { texto2 = it },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
            label = { Text("Contraseña") },
            modifier = Modifier.offset(x = 0.dp, y = 100.dp),
            singleLine = true

        )

        Image(
            painter = painterResource(id = R.drawable.unnamed),
            contentDescription = null,
            modifier = Modifier.offset(x = 0.dp, y = -200.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = iraSegunda,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue)
            ) {
            Text(text = "Iniciar Sesion")

        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = onNextClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue)
            ) {
            Text(text = "Crear ususario")
        }
    }
}
@Composable
fun SegundaPantalla(

    iraTercera: () -> Unit,
    iraQuinta: () -> Unit,
    iraCuarta: () -> Unit,
    iraSexta: () -> Unit,
    irAinicio: () -> Unit
) {
    val context = LocalContext.current
    var fruta1 by remember { mutableStateOf("") }
    var fruta2 by remember { mutableStateOf("") }
    var verdura1 by remember { mutableStateOf("") }
    var verdura2 by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),


    ) {
        BotonPersonalizado("LISTA DE COMPRA",)

        Button(modifier = Modifier.offset( x = 150.dp ),
            onClick = iraTercera,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue)

        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(2.dp) ,// espacio entre texto e ícono
            ) {
                Text("Carnes y lacteos")

                Image(
                    painter = painterResource(id = R.drawable.flecha_derecha),
                    contentDescription = "flecha",
                    modifier = Modifier
                        .size(20.dp)

                    )
              }
            }

        SeccionCategoria("FRUTAS", R.drawable.image_removebg_preview)
        CampoDeTexto(fruta1) { fruta1 = it }
        CampoDeTexto(fruta2) { fruta2 = it }
        Spacer(modifier = Modifier.height(16.dp))

        SeccionCategoria("VERDURAS", R.drawable.image__1__removebg_preview)
        CampoDeTexto(verdura1) { verdura1 = it }
        CampoDeTexto(verdura2) { verdura2 = it }

        Spacer(modifier = Modifier.height(16.dp))

        BotonPersonalizado("GUARDAR LISTA") {
            Toast.makeText(context, "Lista guardada", Toast.LENGTH_SHORT).show()
        }

        Button(onClick =  iraCuarta,
            modifier = Modifier.offset(y = 15.dp)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "mis compras",
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = "Mis compras",
                    textAlign = TextAlign.Center
                )
            }
        }
        Button(onClick = iraSexta,
           modifier = Modifier.offset(x=105.dp, y = 30.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Icono Acerca de",
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = "Acerca de",
                    textAlign = TextAlign.Center
                )
            }
        }
        Button(onClick = iraQuinta,
            modifier = Modifier.offset(x=200.dp,y=-100.dp)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Default.List,
                    contentDescription = "Icono mis listas",
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = "Mis listas",
                    textAlign = TextAlign.Center
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))


        Button(
            modifier = Modifier.fillMaxWidth(),

            onClick = irAinicio,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red)

        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(2.dp) ,// espacio entre texto e ícono
            ) {
                Text("Cerrar session")

            }
        }

    }
}

@Composable
fun BotonPersonalizado(texto: String, onClick: () -> Unit = {}) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors( containerColor = Color(0xFFD6EAF8)),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(texto, color = Color.Black)
    }
}

@Composable
fun SeccionCategoria(titulo: String, icono: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFD6EAF8), shape = RoundedCornerShape(8.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(titulo, fontSize = 18.sp, modifier = Modifier.weight(1f))
        Image(painter = painterResource(id = icono), contentDescription = titulo, modifier = Modifier.size(60.dp))
    }
}

@Composable
fun CampoDeTexto(valor: String, onValueChange: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        BasicTextField(
            value = valor,
            onValueChange = onValueChange,
            textStyle = TextStyle(fontSize = 16.sp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        Divider(color = Color.Gray, thickness = 1.dp)
    }
}

@Composable
fun TerceraPantalla(
    onClick: () -> Unit,
    iraQuinta: () -> Unit,
    iraCuarta: () -> Unit,
    iraSexta: () -> Unit,
    irAinicio: () -> Unit,
    iraSegunda: () -> Unit
) {
    val context = LocalContext.current
    var fruta1 by remember { mutableStateOf("") }
    var fruta2 by remember { mutableStateOf("") }
    var verdura1 by remember { mutableStateOf("") }
    var verdura2 by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        BotonPersonalizadop3("LISTA DE COMPRA")
        Button(modifier = Modifier,
            onClick = iraSegunda,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue)

        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                Image(
                    painter = painterResource(id = R.drawable.flecha_izquirda),
                    contentDescription = "flecha",
                    modifier = Modifier
                        .size(20.dp)

                )
            }
            Text("Frutas y Verduras")

        }

        SeccionCategoriap3("CARNES", R.drawable.gratis_png_carne_cruda_en_rodajas_carne_de_bistec_carne_roja_carne_ingredientes_de_carne)
        CampoDeTextop3(fruta1) { fruta1 = it }
        CampoDeTextop3(fruta2) { fruta2 = it }

        Spacer(modifier = Modifier.height(16.dp))

        SeccionCategoriap3("LACTEOS", R.drawable.lacteos)
        CampoDeTextop3(verdura1) { verdura1 = it }
        CampoDeTextop3(verdura2) { verdura2 = it }

        Spacer(modifier = Modifier.height(16.dp))

        BotonPersonalizadop3("GUARDAR LISTA") {
            Toast.makeText(context, "Lista guardada", Toast.LENGTH_SHORT).show()
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(onClick = iraCuarta,
            modifier = Modifier.offset(y = 15.dp)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "mis compras",
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = "Mis compras",
                    textAlign = TextAlign.Center
                )
            }
        }
        Button(onClick = iraSexta,
            modifier = Modifier.offset(x=105.dp, y = 30.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Icono Acerca de",
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = "Acerca de",
                    textAlign = TextAlign.Center
                )
            }
        }
        Button(onClick = iraQuinta,
            modifier = Modifier.offset(x=200.dp,y=-100.dp)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Default.List,
                    contentDescription = "Icono mis listas",
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = "Mis listas",
                    textAlign = TextAlign.Center
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))


        Button(
            modifier = Modifier.fillMaxWidth(),

            onClick = irAinicio,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red)

        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(2.dp) ,// espacio entre texto e ícono
            ) {
                Text("Cerrar session")

            }
        }
    }
}

@Composable
fun BotonPersonalizadop3(texto: String, onClick: () -> Unit = {}) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors( containerColor = Color(0xFFD6EAF8)),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.fillMaxWidth()

    ) {
        Text(texto, color = Color.Black)
        Spacer(modifier = Modifier.height(16.dp))
    }

}

@Composable
fun SeccionCategoriap3(titulo: String, icono: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFD6EAF8), shape = RoundedCornerShape(8.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(titulo, fontSize = 18.sp, modifier = Modifier.weight(1f))
        Image(painter = painterResource(id = icono), contentDescription = titulo, modifier = Modifier.size(60.dp))

    }
}

@Composable
fun CampoDeTextop3(valor: String, onValueChange: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        BasicTextField(
            value = valor,
            onValueChange = onValueChange,
            textStyle = TextStyle(fontSize = 16.sp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        Divider(color = Color.Gray, thickness = 1.dp)

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
fun QuintaPantalla(
    irAinicio: () -> Unit,
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
fun SextaPantalla(
    irAinicio: () -> Unit
){
    Column (horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()){
        Text(text = "Organiza tu lista de mercado de forma rápida y sencilla,")
        Text(text = "Agrega productos, clasifícalos por categorías y guarda todo")
        Text(text = "en un solo lugar")
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
                iraSegunda= { navHostController.navigate(Pantallas.Segunda.name) },
                onNextClick = { navHostController.navigate(Pantallas.Segunda.name) }
            )
        }
        composable(route = Pantallas.Segunda.name) {
            SegundaPantalla(
                iraTercera = { navHostController.navigate(Pantallas.Tercera.name) },
                iraCuarta = { navHostController.navigate(Pantallas.Cuarta.name) },
                iraQuinta = { navHostController.navigate(Pantallas.Quinta.name) },
                iraSexta = { navHostController.navigate(Pantallas.Sexta.name) },
               irAinicio =  { navHostController.navigate(Pantallas.Inicio.name) }
            )
        }
        composable(route = Pantallas.Tercera.name) {
            TerceraPantalla(
                onClick = { navHostController.navigate(Pantallas.Cuarta.name) },
                iraCuarta = { navHostController.navigate(Pantallas.Cuarta.name) },
                iraSegunda = { navHostController.navigate(Pantallas.Segunda.name) },
                iraQuinta = { navHostController.navigate(Pantallas.Quinta.name) },
                iraSexta = { navHostController.navigate(Pantallas.Sexta.name) },
                irAinicio =  { navHostController.navigate(Pantallas.Inicio.name) })
        }
            composable(route = Pantallas.Cuarta.name) {
                CuartaPantalla(
                    irAinicio = { navHostController.navigate(Pantallas.Inicio.name) }
                )
            }
        composable(route = Pantallas.Quinta.name) {
            QuintaPantalla (
                irAinicio = { navHostController.navigate(Pantallas.Inicio.name) }
            )
        }
        composable(route = Pantallas.Sexta.name) {
            SextaPantalla (
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
                iraSegunda = {}
            )
        }
    }

    enum class Pantallas() {
    Inicio,
    Segunda,
    Tercera,
    Cuarta,
        Quinta,
        Sexta

    }




