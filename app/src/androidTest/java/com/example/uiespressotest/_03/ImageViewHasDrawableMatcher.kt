package com.example.uiespressotest._03

import android.view.View
import android.widget.ImageView
import androidx.test.espresso.matcher.BoundedDiagnosingMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

object ImageViewHasDrawableMatcher {
    fun hasDrawable(): Matcher<View?> {
        return object : BoundedDiagnosingMatcher<View?, ImageView>(ImageView::class.java) {
            override fun describeMoreTo(description: Description) {
                description.appendText("has drawable")
            }

            override fun matchesSafely(
                imageView: ImageView,
                mismatchDescription: Description
            ): Boolean {
                return imageView.drawable != null
            }
        }
    }
}