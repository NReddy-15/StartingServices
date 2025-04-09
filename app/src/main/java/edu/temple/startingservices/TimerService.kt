package edu.temple.startingservices

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TimerService : Service() {

    val TIME_KEY = "time"

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.getIntExtra(TIME_KEY, 0)?.let { runTimer(it) }
        return super.onStartCommand(intent, flags, startId)
    }

    fun runTimer(value: Int) {
        CoroutineScope(Dispatchers.Main).launch{
            for (i in value downTo 0) {
                Log.d("Countdown", i.toString())
                delay(1000)
            }
        }
        stopSelf()
    }

    override fun onDestroy() {
        Log.d("Service State", "STOPPED")
        super.onDestroy()
    }
}