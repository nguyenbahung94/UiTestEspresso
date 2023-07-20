package com.example.uiespressotest.espresso._03_intent

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import com.example.uiespressotest.databinding.ActivityImageViewer03Binding

public class ImageViewerActivity03 : AppCompatActivity() {


    private lateinit var binding: ActivityImageViewer03Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageViewer03Binding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        event()
    }

    private fun event() {

    }

    private fun init() {

    }

    private fun dispatchTakePictureIntent() {
        // Open the camera to take a photo.
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(
            takePictureIntent,
            REQUEST_IMAGE_CAPTURE
        )
    }

    private fun onOpenCamera(view: View) {
        dispatchTakePictureIntent()
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // If an image is received, display it on the ImageView.
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val extras = data!!.extras
            if (extras == null || !extras.containsKey(KEY_IMAGE_DATA)) {
                return
            }
            val imageBitmap = extras.get(KEY_IMAGE_DATA) as Bitmap
            binding.imageView.setImageBitmap(imageBitmap)
        }
    }

    companion object {
        @VisibleForTesting
        const val KEY_IMAGE_DATA = "key_image_data"
        const val REQUEST_IMAGE_CAPTURE = 1
    }
}