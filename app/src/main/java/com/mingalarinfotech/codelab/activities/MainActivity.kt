package com.mingalarinfotech.codelab.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.tabs.TabLayout
import com.google.firebase.iid.FirebaseInstanceId
import com.mingalarinfotech.codelab.R
import com.mingalarinfotech.codelab.adapters.MainFragmentAdapter
import com.mingalarinfotech.codelab.data.CategoryVO
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : BaseActivity() {
    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }

        private const val TAG = "FireBaseToken"
        private const val PIX_COUNT = 5
        private const val IMAGE_PICKER_CODE = 101
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbarMain)

        tabLayout!!.addTab(tabLayout!!.newTab().setText("Product"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Inspiration"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Shop"))
        tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = MainFragmentAdapter(this, supportFragmentManager, tabLayout!!.tabCount)
        viewPager!!.adapter = adapter

        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager!!.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

        ivToolBar.setOnClickListener {
            pickFromGallery()
        }


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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_PICKER_CODE && resultCode == Activity.RESULT_OK) {
            val uri = data!!.data
            uploadToStorage(uri)
        }
    }

    private fun pickFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICKER_CODE)
    }

    private fun uploadToStorage(uri: Uri?) {
        val file = Uri.fromFile(File(uri.toString()))
        val riversRef = storageRef.child("images/${file.lastPathSegment}")
        val uploadTask = riversRef.putFile(uri!!)

        uploadTask.addOnProgressListener {

        }.continueWithTask { task ->
            if (!task.isSuccessful) {
                throw task.exception!!
            }
            riversRef.downloadUrl
        }.addOnCompleteListener {
            Toast.makeText(this, "Download URL ${it.result}}", Toast.LENGTH_LONG).show()
            storeToDataBase(it.result.toString())
            Log.v("URI",it.result.toString())
        }

        uploadTask.addOnSuccessListener {
            it.storage.downloadUrl.addOnSuccessListener { downloadUrl ->
                Toast.makeText(this, "Download URL $downloadUrl.path}", Toast.LENGTH_LONG).show()
            }
        }
        uploadTask.addOnFailureListener {
            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
        }
    }

    private fun storeToDataBase(downloadURI : String?) {
        val categoryVO = CategoryVO(5, "C# Code", downloadURI, 100)
        db.collection("Category").document(categoryVO.label.toString()).set(categoryVO)
            .addOnSuccessListener {
                Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
            }.addOnFailureListener {
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            }
    }
}
