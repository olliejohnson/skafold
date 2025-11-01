package io.oliverj.skafold

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import io.oliverj.skaffold.skafold
import io.oliverj.skafold.ui.theme.SKAFOLDTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SKAFOLDTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    skafold(modifier = Modifier.padding(innerPadding)) {
                        builtin(it)

                        "auton" page AutonPage(it go "teleop", it go "game")
                        "teleop" page TeleopPage(it go "end", it go "auton")
                        "end" page EndPage(it go "home", it go "teleop")
                    }
                }
            }
        }
    }
}