package com.example.exam_17_2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.os.Bundle
import android.telephony.SmsMessage
import android.util.Log
import java.lang.StringBuilder
import java.util.*

class MyReceiverSMS : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        Log.d("MyReceiver", "")
        val bundle = intent.extras
        if (bundle != null) {
            val pdus : Array<Object> = bundle.get("pdus") as Array<Object>
            val size : Int = pdus.size
            var msgs : MutableList<SmsMessage> = mutableListOf()
            val sb = StringBuilder()
            for (i in 0..size) {
                msgs.add(i, SmsMessage.createFromPdu(pdus as ByteArray))
                sb.append(msgs[i].messageBody)
            }
            val message = sb.toString()
            Log.d("MyReceiver", "SMS message: $message")
        }
    }
}