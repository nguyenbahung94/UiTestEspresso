package com.example.uiespressotest._01

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.uiespressotest.R
import com.example.uiespressotest.espresso._01.BasicEspressoMainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class ChangeTextBehaviorKtTest {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<BasicEspressoMainActivity>()

    @Test
    fun changeText_sameActivity() {
        //check the test and press button
        Espresso.onView(ViewMatchers.withId(R.id.editTextUserInput)).perform(
            typeText(STRING_TO_BE_TYPE), closeSoftKeyboard()
        )
        Espresso.onView(ViewMatchers.withId(R.id.changeTextBt)).perform(click())
        Espresso.onView(ViewMatchers.withId(R.id.textToBeChanged)).check(matches(withText(
            STRING_TO_BE_TYPE
        )))
    }

    @Test
    fun changText_newActivity() {
        Espresso.onView(ViewMatchers.withId(R.id.editTextUserInput)).perform(
            typeText(
                STRING_TO_BE_TYPE
            ), closeSoftKeyboard()
        )

        Espresso.onView(ViewMatchers.withId(R.id.activityChangeTextBtn)).perform(click())

        Espresso.onView(ViewMatchers.withId(R.id.show_text_view)).check(matches(withText(
            STRING_TO_BE_TYPE
        )))
    }


    companion object {
        const val STRING_TO_BE_TYPE = "string_to_be_type"
    }
}