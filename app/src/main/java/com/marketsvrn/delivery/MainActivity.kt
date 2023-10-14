package com.marketsvrn.delivery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.arkivanov.decompose.defaultComponentContext
import com.marketsvrn.common.SplashScreenSingleton
import com.marketsvrn.delivery.navigation.root.component.DefaultRootComponent
import com.marketsvrn.delivery.navigation.root.ui.RootContent
import com.marketsvrn.designsystem.theme.DeliveryTheme
import com.marketsvrn.designsystem.util.screenModifier
import com.yandex.mapkit.MapKitFactory
import org.koin.android.ext.android.get

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapKitFactory.initialize(this)

        val splashScreen = installSplashScreen()

        val root = DefaultRootComponent(defaultComponentContext())

        WindowCompat.setDecorFitsSystemWindows(window, false)

        val splashSingleton = get<SplashScreenSingleton>()
        splashScreen.setKeepOnScreenCondition {
            splashSingleton.isReady.value
        }

        setContent {
            DeliveryTheme {
                Surface(
                    modifier = Modifier
                        .screenModifier(),
                    //color = MaterialTheme.colorScheme.background
                ) {
                    RootContent(component = root, modifier = Modifier.screenModifier())
                }
            }
        }
    }

    override fun onStop() {
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
    }
}