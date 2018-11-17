package com.codechacha.espresso

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class LocaleActivityTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(LocaleActivity::class.java)

    @Test
    fun noCountryExtra() {
        activityRule.launchActivity(Intent())
        onView(withId(R.id.tvLocale))
                .check(matches(withText(R.string.no_country_extra)))
    }

    @Test
    fun countryKorea() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val countryKorea= context.resources.getString(R.string.country_korea)
        val expectedResult = context.resources.getString(R.string.locale_korea)

        val intent = Intent()
        intent.putExtra(LocaleActivity.EXTRA_COUNTRY, countryKorea)
        activityRule.launchActivity(intent)

        onView(withId(R.id.tvLocale))
                .check(matches(withText(expectedResult)))
    }

    @Test
    fun countryChina() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val countryChina = context.resources.getString(R.string.country_china)
        val expectedResult = context.resources.getString(R.string.locale_china)

        val intent = Intent()
        intent.putExtra(LocaleActivity.EXTRA_COUNTRY, countryChina)
        activityRule.launchActivity(intent)

        onView(withId(R.id.tvLocale))
                .check(matches(withText(expectedResult)))
    }

    @Test
    fun unknownCountry() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val countryFrance = "france"
        val expectedResult = context.resources.getString(R.string.unknown_country, countryFrance)

        val intent = Intent()
        intent.putExtra(LocaleActivity.EXTRA_COUNTRY, countryFrance)
        activityRule.launchActivity(intent)

        onView(withId(R.id.tvLocale))
                .check(matches(withText(expectedResult)))
    }
}