package com.example.uiespressotest._03

import android.app.Activity
import android.app.Instrumentation
import android.app.Instrumentation.ActivityResult
import android.content.Intent
import android.net.Uri
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.core.internal.deps.guava.collect.Iterables
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName
import androidx.test.espresso.intent.matcher.IntentMatchers.*
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.ext.truth.content.IntentSubject.assertThat
import androidx.test.filters.LargeTest
import androidx.test.rule.GrantPermissionRule
import com.example.uiespressotest.R
import com.example.uiespressotest.espresso._03_intent.ContactActivity03
import com.example.uiespressotest.espresso._03_intent.DialerActivity03
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class DialerActivityTest {
    private val VALID_PHONE_NUMBER = "123-456-789"
    private val INTENT_DATA_PHONE_NUMBER = Uri.parse("tel:$VALID_PHONE_NUMBER")

   @get : Rule
    val grantPermissionRule: GrantPermissionRule =
        GrantPermissionRule.grant("android.permission.CALL_PHONE")
    @get : Rule
    val mActivityRule = IntentsTestRule<DialerActivity03>(DialerActivity03::class.java)

    @Before
    fun stubAllExternalIntents() {
        // By default Espresso Intents does not stub any Intents. Stubbing needs to be setup before
        // every test run. In this case all external Intents will be blocked.
        intending(not(isInternal())).respondWith(
            Instrumentation.ActivityResult(
                Activity.RESULT_OK,
                null
            )
        )


    }

    @Test
    fun typeNumber_ValidInput_InitiatesCall() {
        //Types a phone number into the dialer edit text field and presses the call button.
        onView(withId(R.id.edit_text_caller_number)).perform(typeText(VALID_PHONE_NUMBER),
            closeSoftKeyboard()
        )
        onView(withId(R.id.button_call_number)).perform(click())

        // Verify that an intent to the dialer was sent with the correct action, phone
        // number and package. Think of Intents intended API as the equivalent to Mockito's verify.
        intended(allOf(
            hasAction(Intent.ACTION_CALL),
            hasData(INTENT_DATA_PHONE_NUMBER)
        ))
    }

    /**
     * Duplicate typeNumber_ValidInput_InitiatesCall, but using truth assertions
     */
    @Test
    fun typeNumber_ValidInput_InitiatesCall_truth(){
        // Types a phone number into the dialer edit text field and presses the call button.

        onView(withId(R.id.edit_text_caller_number)).perform(typeText(VALID_PHONE_NUMBER),
            closeSoftKeyboard()
        )

        onView(withId(R.id.button_call_number)).perform(click())
        // Verify that an intent to the dialer was sent with the correct action, phone
        // number and package.
        val receivedIntent:Intent = Iterables.getOnlyElement(Intents.getIntents()) as Intent
        assertThat(receivedIntent).hasAction(Intent.ACTION_CALL)
        assertThat(receivedIntent).hasData(INTENT_DATA_PHONE_NUMBER)
    }

    @Test
    fun pickContactButton_click_SelectsPhoneNumber(){
        // Stub all Intents to ContactsActivity to return VALID_PHONE_NUMBER. Note that the Activity
        // is never launched and result is stubbed.
        val intentResult = ContactActivity03.createResultData(VALID_PHONE_NUMBER)
        intending(hasComponent(ContactActivity03.Companion::class.java.name))
            .respondWith(ActivityResult(Activity.RESULT_OK,intentResult))

        // Perform action that throws the Intent
        onView(withId(R.id.button_pick_contact)).perform(click())

        //Verify your Activity
        onView(withId(R.id.edit_text_caller_number)).check(matches(withText(VALID_PHONE_NUMBER)))
    }
}