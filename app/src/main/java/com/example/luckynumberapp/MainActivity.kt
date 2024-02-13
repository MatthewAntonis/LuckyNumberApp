@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.luckynumberapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.luckynumberapp.ui.theme.LuckyNumberAppTheme
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.graphics.Color

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LuckyNumberAppTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    val context = LocalContext.current
    var name by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.background),
            contentDescription = "Background image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {
            Text(
                text = "Welcome to Lucky Number",
                modifier = Modifier.padding(bottom = 16.dp),
                color = colorResource(id = R.color.white)
            )
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Please Enter your Name", color = Color.White) },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    val randomNumber = (1..100).random()
                    val intent = Intent(context, SecondActivity::class.java).apply {
                        putExtra("username", name)
                        putExtra("luckyNumber", randomNumber)
                    }
                    context.startActivity(intent)
                }),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White,
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Button(onClick = {
                val randomNumber = (1..100).random()
                val intent = Intent(context, SecondActivity::class.java).apply {
                    putExtra("username", name)
                    putExtra("luckyNumber", randomNumber)
                }
                context.startActivity(intent)
            }) {
                Text("Wish Me Luck")
            }
        }
    }
}
