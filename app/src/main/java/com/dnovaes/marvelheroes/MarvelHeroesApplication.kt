package com.dnovaes.marvelheroes

import android.app.Application
import android.content.Context
import com.dnovaes.marvelheroes.database.ObjectBox
import com.dnovaes.marvelheroes.models.AppState
import com.github.raulccabreu.redukt.Redukt
import timber.log.Timber

class MarvelHeroesApplication: Application() {

    companion object {
        lateinit var redukt: Redukt<AppState>

        fun initializeRedukt(context: Context, appState: AppState, isDebug: Boolean = false):
            Redukt<AppState> {
            val redukt = Redukt(appState, isDebug)

            addReducers(redukt)
            addMiddlewares(context, redukt)

            return redukt
        }

        private fun addReducers(redukt: Redukt<AppState>) { }

        private fun addMiddlewares(context: Context, redukt: Redukt<AppState>) { }
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) startLogging()

        ObjectBox.build(this)
        initializeRedukt(applicationContext,
                 AppState(syncRunning= false),
                 BuildConfig.DEBUG).let {
            redukt = it
            //ActionCreator.instance.loadState()
        }
    }

    private fun startLogging() = Timber.plant(Timber.DebugTree())

    override fun onTerminate() {
        redukt.stop()
        super.onTerminate()
    }
}
