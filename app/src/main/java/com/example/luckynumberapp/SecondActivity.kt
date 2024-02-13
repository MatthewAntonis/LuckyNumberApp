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
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.luckynumberapp.ui.theme.LuckyNumberAppTheme

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val username = intent.getStringExtra("username") ?: "Unknown"
        val luckyNumber = intent.getIntExtra("luckyNumber", 0)

        setContent {
            LuckyNumberAppTheme {
                SecondActivityContent(username, luckyNumber)
            }
        }
    }
}

@Composable
fun SecondActivityContent(username: String, luckyNumber: Int) {
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background2),
            contentDescription = "Background image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "$username's Lucky Number is $luckyNumber",
                color = Color.White,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Button(onClick = {
                val shareText = "$username's lucky number is $luckyNumber"
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, shareText)
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                context.startActivity(shareIntent)
            }) {
                Text(
                    "Share my Lucky Number",
                    color = Color.White
                )
            }
        }
    }
}
