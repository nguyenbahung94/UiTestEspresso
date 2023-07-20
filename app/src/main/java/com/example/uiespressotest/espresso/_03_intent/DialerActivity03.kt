package com.example.uiespressotest.espresso._03_intent

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.uiespressotest.R
import com.example.uiespressotest.databinding.ActivityDialer03Binding

class DialerActivity03 : AppCompatActivity() {

    private lateinit var binding: ActivityDialer03Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDialer03Binding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        event()
    }

    private fun event() {

    }

    private fun init() {

    }

    fun onCall(view: View){
        val hasCallPhonePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED
        if (hasCallPhonePermission) startActivity(createCallIntentFromNumber())
        else Toast.makeText(
            this,
            R.string.warning_call_phone_permission,
            Toast.LENGTH_SHORT
        ).show()

    }

    @SuppressLint("SuspiciousIndentation")
    fun onPickContact(view: View){
    val intent = Intent(this,ContactActivity03::class.java)
        startActivityForResult(intent, REQUEST_CODE_PICK)
    }

    private fun createCallIntentFromNumber():Intent{
    return Intent(Intent.ACTION_CALL).apply {
        data = Uri.parse("tel:${binding.editTextCallerNumber.text}")
    }
    }


    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_PICK && resultCode == RESULT_OK){
            binding.editTextCallerNumber.setText(data?.getStringExtra(ContactActivity03.KEY_PHONE_NUMBER))
        }
    }

    companion object {
        const val REQUEST_CODE_PICK = 16
    }
}