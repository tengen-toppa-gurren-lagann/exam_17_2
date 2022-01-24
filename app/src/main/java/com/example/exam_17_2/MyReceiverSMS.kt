package com.example.exam_17_2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.telephony.SmsMessage
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.lang.StringBuilder

class MyReceiverSMS : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        Log.d("MyReceiver", "Action: ${intent.action}")
        val bundle = intent.extras
        if (bundle != null) {
            val pdus = bundle.get("pdus") as Array<*>
            val size = pdus.size
            val messages : Array<SmsMessage?> = arrayOfNulls(size)
            val format = bundle.getString("format")
            val sb = StringBuilder()
            for (i in pdus.indices) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    messages[i] = SmsMessage.createFromPdu(pdus[i] as ByteArray, format)
                }
                else {
                    messages[i] = SmsMessage.createFromPdu(pdus[i] as ByteArray)
                }
                sb.append(messages[i]?.messageBody)
            }
            val message = sb.toString()
            Log.d("MyReceiver", "SMS message: $message")
            val textView = (context as AppCompatActivity).findViewById(R.id.textView) as TextView
            textView.text = message

        }
    }
}