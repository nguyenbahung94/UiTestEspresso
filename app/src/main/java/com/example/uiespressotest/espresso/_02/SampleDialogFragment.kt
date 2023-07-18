package com.example.uiespressotest.espresso._02

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.uiespressotest.databinding.FragmentSample02Binding

class SampleDialogFragment : DialogFragment() {

    private var _binding: FragmentSample02Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSample02Binding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        event()
    }

    private fun init() {

    }

    private fun event() {

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance(): SampleDialogFragment {
            return SampleDialogFragment().apply {
                arguments = Bundle().apply {
                }
            }
        }
    }

}