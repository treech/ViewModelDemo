package com.ygq.observerDemo.model

import android.os.SystemClock
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ygq.observerDemo.bean.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class TestViewModel : ViewModel() {

    companion object {
        const val TAG = "TestViewModel"
    }

    var userData: MutableLiveData<User> = MutableLiveData()

    init {
        userData.value = User("张三", 24, "杭州")

        // 延迟3秒后修改数据，UI自动更新
        Thread {
            SystemClock.sleep(3000)
            userData.value?.name = "李四"
            userData.postValue(userData.value)
        }.start()
    }

    var job: Job? = null

    fun test() {
        job = viewModelScope.launch(Dispatchers.IO) {
            for (index in 1..100) {
                if (isActive) {
                    SystemClock.sleep(1000)
                    Log.i(TAG, "sleep for index:$index")
                }
            }
        }
    }

    override fun onCleared() {
        Log.i(TAG, "onCleared：ViewModel")
        job?.cancel()
    }
}