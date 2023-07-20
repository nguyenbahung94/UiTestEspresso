package com.example.uiespressotest.espresso._01_activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.uiespressotest.R
import com.example.uiespressotest.databinding.BasicActivityMain01Binding

class BasicEspressoMainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: BasicActivityMain01Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BasicActivityMain01Binding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        event()
    }

    private fun event() {

    }

    private fun init() {
        with(binding) {
            changeTextBt.setOnClickListener(this@BasicEspressoMainActivity)
            activityChangeTextBtn.setOnClickListener(this@BasicEspressoMainActivity)
            textToBeChanged.setOnClickListener(this@BasicEspressoMainActivity)
            editTextUserInput.setOnClickListener(this@BasicEspressoMainActivity)
        }
    }

    override fun onClick(view: View?) {
        val text: String = binding.editTextUserInput.text.toString()

        val changeTextBtId: Int = R.id.changeTextBt
        val activityChangeTextBtnId: Int = R.id.activityChangeTextBtn

        if (view?.id == changeTextBtId) {
            // First button's interaction: set a text in a text view.
            binding.textToBeChanged.text = text
        } else if (view?.id == activityChangeTextBtnId) {
            // Second button's interaction: start an activity and send a message to it.
            ShowTextActivity.startActivity(text, this@BasicEspressoMainActivity)
        }
    }
}