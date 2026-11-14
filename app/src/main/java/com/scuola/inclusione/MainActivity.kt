package com.scuola.inclusione

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    InclusioneApp()
                }
            }
        }
    }
}

@Composable
fun InclusioneApp() {
    var currentScreen by remember { mutableStateOf("Home") }

    Column(modifier = Modifier.padding(16.dp).fillMaxSize()) {
        Text(text = "Guess The Quiz!", fontSize = 28.sp, modifier = Modifier.padding(bottom = 24.dp))

        when (currentScreen) {
            "Home" -> {
                BigButton("Semplificatore di Testi") { currentScreen = "Semplificatore" }
                BigButton("Generatore Mappe e Schede") { currentScreen = "Generatore" }
                BigButton("Tutor Interattivo") { currentScreen = "Tutor" }
            }
            "Semplificatore" -> FeatureScreen("Semplificatore", "Incolla qui il testo del libro...") { currentScreen = "Home" }
            "Generatore" -> FeatureScreen("Mappe e Schede", "Es. La prima guerra mondiale...") { currentScreen = "Home" }
            "Tutor" -> FeatureScreen("Tutor Interattivo", "Fai una domanda per farti aiutare a ragionare...") { currentScreen = "Home" }
        }
    }
}

@Composable
fun BigButton(text: String, onClick: () -> Unit) {
    Button(onClick = onClick, modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp).height(80.dp)) {
        Text(text = text, fontSize = 20.sp)
    }
}

@Composable
fun FeatureScreen(title: String, placeholder: String, onBack: () -> Unit) {
    Column {
        Button(onClick = onBack, modifier = Modifier.padding(bottom = 16.dp)) { Text("Torna Indietro") }
        Text(text = title, fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))
        OutlinedTextField(
            value = "", onValueChange = {},
            modifier = Modifier.fillMaxWidth().height(150.dp),
            label = { Text(placeholder) }
        )
        Button(onClick = { /* API Gemini in futuro */ }, modifier = Modifier.fillMaxWidth().padding(top = 16.dp).height(60.dp)) {
            Text("Elabora con AI (Prossimamente)", fontSize = 18.sp)
        }
    }
}
