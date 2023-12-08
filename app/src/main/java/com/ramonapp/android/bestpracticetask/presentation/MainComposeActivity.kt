package com.ramonapp.android.bestpracticetask.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ramonapp.android.bestpracticetask.presentation.albums.AlbumsScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainComposeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlbumsScreen()
        }
    }
}