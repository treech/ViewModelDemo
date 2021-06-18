package com.ygq.observerDemo.listener

import java.util.*

class TestListener : Observable() {

    companion object {
        val INSTANCE: TestListener =
            TestListener()

        fun getInstance(): TestListener {
            return INSTANCE
        }
    }

    fun onDataChange(response: String) {
        setChanged()
        notifyObservers(response)
    }
}