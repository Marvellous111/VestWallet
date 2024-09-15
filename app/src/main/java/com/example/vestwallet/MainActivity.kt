package com.example.vestwallet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.vestwallet.ui.AuthScreen
import com.example.vestwallet.ui.theme.VestWalletTheme
import io.realm.kotlin.Realm
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.AppConfiguration



//val appId = "vestwalletusers-waiuitr"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Realm
//        lateinit var app: App
//        val appId = "vestwalletusers-waiuitr"
//        app = App.create(AppConfiguration.create(appId))

        //enableEdgeToEdge()
        setContent {
            VestWalletTheme {
//                val windowInsets = WindowInsets.systemBars
//                val density = LocalDensity.current
//                val statusBarHeight = with(density) { windowInsets.getTop(density).toDp() }
                Surface(
                    modifier = Modifier
                        .background(color = MaterialTheme.colorScheme.background)
                ) {
                    AuthScreen()
                }
            }
        }
    }
}