package com.example.uiespressotest._02

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.uiespressotest.R
import com.example.uiespressotest.espresso._02.SampleFragment
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SampleFragmentTest {
    @Test
    fun launchFragmentAndVerifyUi() {
        launchFragmentInContainer<SampleFragment>(Bundle())
        onView(withId(R.id.textView)).check(matches(withText("I am a fragment")))
    }


}