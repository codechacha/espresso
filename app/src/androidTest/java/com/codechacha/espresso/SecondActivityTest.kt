package com.codechacha.espresso

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class SecondActivityTest {
    @Rule
    @JvmField
    var activeRule = ActivityTestRule(SecondActivity::class.java)

    @Test
    fun userInputTest() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        val inputStr = context.getString(R.string.country_korea)
        onView(withId(R.id.editTextUserInput))
                .perform(typeText(inputStr), closeSoftKeyboard())

        onView(withId(R.id.fab))
                .perform(click())

        onView(withId(R.id.tvLocale))
                .check(matches(withText(R.string.locale_korea)))

        onView(withId(R.id.btnOk))
            .perform(click())

        onView(withId(R.id.tvResultLocale))
            .check(matches(withText(R.string.locale_korea)))
    }

    @Test
    fun userInputTest2() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        val inputStr = context.getString(R.string.country_china)
        onView(withId(R.id.editTextUserInput))
            .perform(typeText(inputStr), closeSoftKeyboard())

        onView(withId(R.id.fab))
            .perform(click())

        onView(withId(R.id.tvLocale))
            .check(matches(withText(R.string.locale_china)))

        onView(withId(R.id.btnOk))
            .perform(click())

        onView(withId(R.id.tvResultLocale))
            .check(matches(withText(R.string.locale_china)))
    }
}