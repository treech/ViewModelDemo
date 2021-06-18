package com.ygq.observerDemo.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.ygq.observerDemo.R
import com.ygq.observerDemo.databinding.ActivityABinding
import com.ygq.observerDemo.model.TestViewModel

class ActivityA : AppCompatActivity() {

    private lateinit var mDatabind: ActivityABinding

    private val testViewModel: TestViewModel by lazy { ViewModelProvider(this).get(TestViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDatabind = DataBindingUtil.setContentView(this, R.layout.activity_a)
        mDatabind.viewmodel = testViewModel
        mDatabind.lifecycleOwner = this
        mDatabind.clickProxy = ClickProxy()
    }

    override fun onDestroy() {
        super.onDestroy()
        mDatabind.unbind()
    }

    inner class ClickProxy {

        fun click() {
            testViewModel.test()
            startActivity(Intent(this@ActivityA, ActivityB::class.java))
            finish()
        }
    }
}