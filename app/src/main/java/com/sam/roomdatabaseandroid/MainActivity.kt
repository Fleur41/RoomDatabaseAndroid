package com.sam.roomdatabaseandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.sam.roomdatabaseandroid.room.UserScreen
import com.sam.roomdatabaseandroid.ui.theme.RoomDatabaseAndroidTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            RoomDatabaseAndroidTheme {
                UserScreen()
            }
        }
    }
}

