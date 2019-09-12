package cz.hlinkapp.gohlinka2_utils2.utils

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

/**
 * A simple helper class for saving and reading values from shared preferences.
 */
@Singleton
class SharedPrefUtil
/**
 * Default constructor for this class. It's important to pass the DEFAULT shared prefs.
 * @param sharedPreferences The default shared preferences of the app.
 */
@Inject constructor(private val sharedPreferences: SharedPreferences) {

    /**
     * Reads any string shared preference with the provided key, or null if not found.
     */
    fun getStringSharedPref(key: String) : String?{
        return sharedPreferences.getString(key,null)
    }

    /**
     * Saves any string shared preference using the provided key.
     */
    fun setStringSharedPref(key: String, value: String?) {
        with(sharedPreferences.edit()) {
            putString(key,value)
            apply()
        }
    }

}