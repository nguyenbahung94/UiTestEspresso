package com.example.uiespressotest._03

import android.app.Activity
import android.app.Instrumentation.ActivityResult
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import com.example.uiespressotest.R
import com.example.uiespressotest._03.ImageViewHasDrawableMatcher.hasDrawable
import com.example.uiespressotest.espresso._03_intent.ImageViewerActivity03
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class ImageViewerActivityTest {

    @get : Rule
    val mActivityScenarioRule =
        ActivityScenarioRule(ImageViewerActivity03::class.java)

    @Before
    fun stubCameraIntent() {
        // Initializes Intents and begins recording intents.
        Intents.init()

        val result: ActivityResult = createImageCaptureActivityResultStub()

        //stub the Intent
        intending(hasAction(MediaStore.ACTION_IMAGE_CAPTURE)).respondWith(result)
    }

    @After
    fun tearDown() {
        // Clears Intents state.
        Intents.release()
    }

    @Test
    fun takePhoto_drawableIsApplied(){
        // Check that the ImageView doesn't have a drawable applied.
        Espresso.onView(withId(R.id.imageView)).check(ViewAssertions.matches(not(hasDrawable())))

    }

    private fun createImageCaptureActivityResultStub(): ActivityResult {
        // Put the drawable in a bundle.
        val bundle = Bundle()

        bundle.putParcelable(
            ImageViewerActivity03.KEY_IMAGE_DATA, BitmapFactory.decodeResource(
                InstrumentationRegistry.getInstrumentation().targetContext.resources,
                R.drawable.ic_launcher_background
            )
        )
        // Create the Intent that will include the bundle.
        val resultData = Intent()
        resultData.putExtras(bundle)

        // Create the ActivityResult with the Intent.
        return ActivityResult(Activity.RESULT_OK, resultData)
    }
}