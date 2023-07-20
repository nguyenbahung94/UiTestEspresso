package com.example.uiespressotest.espresso._03_intent

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.uiespressotest.databinding.ActivityContact03Binding
import com.google.common.annotations.VisibleForTesting

class ContactActivity03 : AppCompatActivity() {

    private lateinit var binding: ActivityContact03Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContact03Binding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        event()
    }

    private fun event() {
        setResult(Activity.RESULT_OK, createResultData("123-456-789"))
        finish()
    }

    private fun init() {
        setResult(Activity.RESULT_OK)
    }

    companion object {

        const val KEY_PHONE_NUMBER = "key_phone_number"

        @VisibleForTesting
        fun createResultData(phoneNumber: String): Intent {
            return Intent().apply {
                putExtra(KEY_PHONE_NUMBER, phoneNumber)
            }
        }
    }

}