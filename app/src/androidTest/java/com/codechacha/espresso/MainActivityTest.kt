package com.codechacha.espresso

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.*
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.core.AllOf.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {
    @Rule
    @JvmField
    var activeRule = IntentsTestRule(MainActivity::class.java)

    @Test
    fun launchActivity() {
        onView(withId(R.id.fab))
                .perform(click())
        onView(withId(R.id.tvLocale))
                .check(matches(withText(R.string.locale_korea)))
    }

    @Test
    fun localeIntended() {
        onView(withId(R.id.fab))
                .perform(click())

        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val country = context.getString(R.string.country_korea)

        intended(hasExtra(LocaleActivity.EXTRA_COUNTRY, country))
        intended(toPackage("com.codechacha.espresso"))
        intended(hasComponent(
                "com.codechacha.espresso.LocaleActivity"))

        intended(allOf(
            hasExtra(LocaleActivity.EXTRA_COUNTRY, country),
            toPackage("com.codechacha.espresso"),
            hasComponent("com.codechacha.espresso.LocaleActivity")))
    }


    @Test
    fun localeIntending() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val locale = context.getString(R.string.locale_korea)

        val intent = Intent()
        intent.putExtra(LocaleActivity.EXTRA_LOCALE, locale)
        val result =
                Instrumentation.ActivityResult(Activity.RESULT_OK, intent)

        intending(toPackage("com.codechacha.espresso"))
                .respondWith(result)

        onView(withId(R.id.fab))
                .perform(click())
        onView(withId(R.id.tvResult))
                .check(matches(withText(locale)))
    }

}