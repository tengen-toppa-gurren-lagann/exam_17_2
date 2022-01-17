package com.example.exam_17_2

import android.content.BroadcastReceiver
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
/*
        val br : BroadcastReceiver = MyReceiverSMS()
        val filter = IntentFilter()
        filter.addAction(android.provider.Telephony.SMS_RECEIVED) // Почему-то эта константа не определена!
        registerReceiver(br,filter)
*/

    }
}