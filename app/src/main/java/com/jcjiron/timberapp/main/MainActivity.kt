package com.jcjiron.timberapp.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jcjiron.timberapp.ui.theme.TimberAppTheme
import timber.log.Timber
import java.lang.RuntimeException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TimberAppTheme {
                MainContent()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier.clickable {
            try {
                throw RuntimeException("Controlled Exception")
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TimberAppTheme {
        Greeting("Android")
    }
}