package com.mingalarinfotech.codelab.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

abstract class BaseActivity : AppCompatActivity() {
    protected lateinit var db: FirebaseFirestore
    protected lateinit var storage: FirebaseStorage
    protected lateinit var storageRef: StorageReference
    override fun onStart() {
        super.onStart()
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this)
    }

    @Subscribe
    fun onEvent(event: Any?) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        db = FirebaseFirestore.getInstance()
        storage = FirebaseStorage.getInstance()
        storageRef = storage.reference
        super.onCreate(savedInstanceState)
    }

}