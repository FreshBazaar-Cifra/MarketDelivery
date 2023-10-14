package com.marketsvrn.delivery

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import com.marketsvrn.delivery.di.appModule
import com.yandex.mapkit.MapKitFactory
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication : Application(), ImageLoaderFactory{
    override fun newImageLoader(): ImageLoader {
        return get<ImageLoader>()
    }

    override fun onCreate() {
        super.onCreate()

        MapKitFactory.setApiKey("3efd7662-e30c-4b49-b4f7-e709c89d0b8c")
        startKoin {
            androidContext(applicationContext)
            androidLogger(Level.DEBUG)
            modules(appModule)
        }

    }

}