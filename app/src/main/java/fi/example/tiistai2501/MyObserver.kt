package fi.example.tiistai2501

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner

class MyObserver: LifecycleObserver {

    fun myFun(source: LifecycleOwner, event: Lifecycle.Event){
        Log.d("TAG", event.toString())
    }
}