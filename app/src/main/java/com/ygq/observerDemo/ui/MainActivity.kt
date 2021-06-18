package com.ygq.observerDemo.ui

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ygq.observerDemo.R
import com.ygq.observerDemo.listener.TestListener
import java.util.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    private val observer: InnerObserver by lazy { InnerObserver() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        TestListener.getInstance().addObserver(observer)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG,"onDestroy")
        TestListener.getInstance().deleteObserver(observer)
    }

    inner class InnerObserver : Observer {

        override fun update(o: Observable, arg: Any) {
            Thread {
                for (index in 1..100) {
                    SystemClock.sleep(1000)
                    Log.i(TAG, "sleep for index:$index")
                }
            }.start()
        }
    }

    fun jumpToA(view: View) {
        startActivity(Intent(this, ActivityA::class.java))
        finish()
    }

    /**
     * 模拟内存泄漏
     */
    fun dataChange(view: View) {
        TestListener.getInstance().onDataChange("")
        startActivity(Intent(this@MainActivity, ActivityA::class.java))
        finish()
    }
}