package com.aherrera.fakelogin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.aherrera.fakelogin.navigation.FakeLoginNavGraph
import com.aherrera.fakelogin.ui.theme.FakeLoginTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FakeLoginTheme {
                val navController = rememberNavController()
                FakeLoginNavGraph(navController)
            }
        }
    }
}
