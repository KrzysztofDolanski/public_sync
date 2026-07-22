package com.krzysztof.twopublicsync

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.krzysztof.twopublicsync.ui.navigation.AppNavGraph
import com.krzysztof.twopublicsync.ui.theme.PublicSyncTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PublicSyncTheme {
                AppNavGraph()
            }
        }
    }
}
