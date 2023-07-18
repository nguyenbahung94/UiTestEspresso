package com.example.uiespressotest.espresso._01

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.uiespressotest.databinding.BasicActivityShowText01Binding

class ShowTextActivity : AppCompatActivity() {

    private lateinit var binding: BasicActivityShowText01Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BasicActivityShowText01Binding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        event()
    }

    private fun event() {

    }

    private fun init() {
        binding.showTextView.text = intent.getStringExtra(KEY_EXTRA_MESSAGE)
    }

    companion object {
        const val KEY_EXTRA_MESSAGE = "key_extra_message"
        fun startActivity(message: String, context: Context) {
            context.startActivity(Intent(context, ShowTextActivity::class.java).apply {
                putExtra(KEY_EXTRA_MESSAGE, message)
            })
        }
    }
}