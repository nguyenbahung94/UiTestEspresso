package com.example.uiespressotest._02

import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.uiespressotest.espresso._02_fragment.SampleDialogFragment
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import com.example.uiespressotest.R


@RunWith(AndroidJUnit4::class)
class SampleDialogFragmentTest {

    @Test
    fun launchDialogFragmentAndVerifyUI(){
        // Use launchFragment to launch the dialog fragment in a dialog.
        val scenario = launchFragment<SampleDialogFragment>()

        scenario.onFragment{fragment->
            assertThat(fragment.dialog).isNotNull()
            assertThat(fragment.requireDialog().isShowing).isTrue()
        }

        // Now use espresso to look for the fragment's text view and verify it is displayed.
        onView(ViewMatchers.withId(R.id.textView)).inRoot(isDialog()).check(ViewAssertions.matches(ViewMatchers.withText("I am a fragment")))
    }

    @Test
    fun launchDialogFragmentEmbeddedToHostActivityAndVerifyUI(){
        // Use launchFragmentInContainer to inflate a dialog fragment's view into Activity's content view.
        val scenario = launchFragmentInContainer<SampleDialogFragment>()
        scenario.onFragment{fragment->
            // Dialog is not created because you use launchFragmentInContainer and the view is inflated
            // into the Activity's content view.
            assertThat(fragment.dialog).isNull()
        }

        onView(ViewMatchers.withId(R.id.textView)).check(ViewAssertions.matches(ViewMatchers.withText("I am a fragment")))
    }
}