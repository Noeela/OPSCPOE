package com.example.foodgenieapp


import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import java.util.*

class LocalizationManager(private val context: Context) {

    companion object {
        const val PREF_LANGUAGE = "pref_language"
        const val DEFAULT_LANGUAGE = "en"
    }

    private val sharedPreferences = context.getSharedPreferences("foodgenie_prefs", Context.MODE_PRIVATE)

    fun setAppLanguage(language: String) {
        sharedPreferences.edit().putString(PREF_LANGUAGE, language).apply()
        updateAppLanguage(language)
    }

    fun getCurrentLanguage(): String {
        return sharedPreferences.getString(PREF_LANGUAGE, DEFAULT_LANGUAGE) ?: DEFAULT_LANGUAGE
    }

    fun updateAppLanguage(language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)

        val resources: Resources = context.resources
        val configuration: Configuration = resources.configuration

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            configuration.setLocale(locale)
        } else {
            configuration.locale = locale
        }

        resources.updateConfiguration(configuration, resources.displayMetrics)
    }

    fun getSupportedLanguages(): List<Language> {
        return listOf(
            Language("en", "English", "English"),
            Language("es", "Español", "Spanish"),
            Language("fr", "Français", "French"),
            Language("de", "Deutsch", "German")
        )
    }
}

data class Language(
    val code: String,
    val nativeName: String,
    val englishName: String
)