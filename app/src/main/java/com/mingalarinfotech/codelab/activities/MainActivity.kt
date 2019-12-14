package com.mingalarinfotech.codelab.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.mingalarinfotech.codelab.R

class MainActivity : BaseActivity() {
    companion object{
        fun newIntent(context : Context) : Intent {
            return Intent(context, MainActivity::class.java)
        }
        private const val TAG = "FireBaseToken"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w(TAG, "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                val token = task.result?.token

                // Log and toast

                Log.d(TAG, token)
            })
    }
}
