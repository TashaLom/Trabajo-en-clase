package com.example.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.util.Log
import android.widget.Toast

import kotlinx.coroutines.launch
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.rememberCoroutineScope

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Llamamos a nuestra pantalla de Login aquí adentro
            LoginScreen()
        }
    }
}

@Composable
fun LoginScreen() {
    // Variables para guardar lo que el usuario escriba
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var dat by remember { mutableStateOf<Data?>(null) }

    // Una columna para organizar todo de arriba hacia abajo
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp), // Margen alrededor de la pantalla
        horizontalAlignment = Alignment.CenterHorizontally // Centrar horizontalmente
    ) {

        // Espacio arriba para que no quede pegado al borde del celular
        Spacer(modifier = Modifier.height(60.dp))

        // 1. TÍTULO
        Text(
            text = "Mi Aplicación",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 2. SALUDO DE BIENVENIDA
        Text(
            text = "¡Bienvenido de nuevo!",
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        // 3. CAMPO DE EMAIL
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo electrónico") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 4. CAMPO DE CONTRASEÑA
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation() // Oculta los puntos de la contraseña
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 5. BOTÓN DE LOG IN
        Button(
            onClick = { /* Aquí irá la acción de iniciar sesión más adelante */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "Log In", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 6. BOTÓN: CONTINÚA CON GOOGLE
        OutlinedButton(
            onClick = {
                scope.launch {
                    try {
                        dat = retrofitclient.api.getData(3)

                        Log.d("API", "ID: ${dat?.id}")
                        Log.d("API", "Usuario: ${dat?.userId}")
                        Log.d("API", "Título: ${dat?.title}")
                        Log.d("API", "Contenido: ${dat?.body}")

                        Toast.makeText(
                            context,
                            dat?.title ?: "Sin título",
                            Toast.LENGTH_SHORT
                        ).show()
                    } catch (e: Exception) {
                        Log.e("Error Retrofit ", e.message.toString())
                        Toast.makeText(
                            context,
                            "¡Error al conectar al servidor!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "Continúa con Google", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 7. BOTÓN DE TEXTO: CREAR CUENTA
        TextButton(
            onClick = { /* Acción para crear cuenta más adelante */ }
        ) {
            Text(text = "Crear cuenta", fontSize = 14.sp)
        }
    }
}