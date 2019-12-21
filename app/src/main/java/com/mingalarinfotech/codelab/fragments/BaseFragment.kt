package com.mingalarinfotech.codelab.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

abstract class BaseFragment : Fragment() {
    protected lateinit var db: FirebaseFirestore
    protected lateinit var storage: FirebaseStorage
    protected lateinit var storageRef: StorageReference

    override fun onCreate(savedInstanceState: Bundle?) {
        db = FirebaseFirestore.getInstance()
        storage = FirebaseStorage.getInstance()
        storageRef = storage.reference
        super.onCreate(savedInstanceState)
    }
}
