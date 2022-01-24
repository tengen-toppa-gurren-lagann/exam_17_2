package com.example.exam_17_2

import android.Manifest
import android.content.BroadcastReceiver
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import android.content.pm.PackageManager
import android.provider.Telephony
import android.util.Log
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Запрашиваем у пользователя разрешение на получение SMS
        checkForSmsReceivePermissions()

        // Регистрируем broadcast receiver
        val br : BroadcastReceiver = MyReceiverSMS()
        val filter = IntentFilter()
//        filter.addAction(android.provider.Telephony.SMS_RECEIVED) // Почему-то эта константа не определена!
        filter.addAction(Telephony.Sms.Intents.SMS_RECEIVED_ACTION) // Используем другую константу
        registerReceiver(br,filter)

    }

    private fun checkForSmsReceivePermissions() {
        // Check if App already has permissions for receiving SMS
        if (ContextCompat.checkSelfPermission(baseContext, "android.permission.RECEIVE_SMS") == PackageManager.PERMISSION_GRANTED) {
            // App has permissions to listen incoming SMS messages
            Log.d("checkForPermissions", "Allowed")
        }
        else {
            // App don't have permissions to listen incoming SMS messages
            Log.d("checkForPermissions", "Denied")
            // Request permissions from user
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECEIVE_SMS), 12345)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 12345) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("PermissionsResult", "Permissions granted")
            }
            else {
                Log.d("PermissionsResult", "Permissions denied")
            }
        }
    }
}