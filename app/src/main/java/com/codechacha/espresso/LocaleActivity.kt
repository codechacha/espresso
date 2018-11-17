package com.codechacha.espresso

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_locale.*

class LocaleActivity : AppCompatActivity() {
    companion object {
        val EXTRA_COUNTRY = "country"
        val EXTRA_LOCALE = "locale"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_locale)

        val country = intent.getStringExtra(EXTRA_COUNTRY)
        if (country == null) {
            tvLocale.text = getString(R.string.no_country_extra)
        } else {
            val localeStr: String = when (country.toLowerCase()) {
                "korea" -> getString(R.string.locale_korea)
                "japan" -> getString(R.string.locale_japan)
                "china" -> getString(R.string.locale_china)
                else -> getString(R.string.unknown_country, country)
            }
            tvLocale.text = localeStr
        }

        btnOk.setOnClickListener {view ->
            val result = Intent()
            result.putExtra(EXTRA_LOCALE, tvLocale.text)
            setResult(Activity.RESULT_OK, result)
            finish()
        }
    }
}