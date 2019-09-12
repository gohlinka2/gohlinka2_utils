package cz.hlinkapp.gohlinka2_utils2.utils

import android.content.SharedPreferences
import java.util.*
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
     * Returns any string shared preference with the provided key, or null if not found.
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

    /**
     * Returns any int shared preference with the provided key, or null if the value was not found or it is equal to [Int.MIN_VALUE].
     * If the value is equal to [Int.MIN_VALUE], this function treats it as non-existent and returns null.
     */
    fun getIntSharedPref(key: String) : Int? = with (sharedPreferences.getInt(key,Int.MIN_VALUE)) {
        return if (this != Int.MIN_VALUE) this else null
    }

    /**
     * Saves any int shared preference using the provided key.
     * If the value is null, this function saves it as [Int.MIN_VALUE], for compatibility with [getIntSharedPref].
     */
    fun setIntSharedPref(key: String, value: Int?) {
        with(sharedPreferences.edit()) {
            value?.let { putInt(key, it) } ?: putInt(key, Int.MIN_VALUE)
            apply()
        }
    }

    /**
     * Returns any float shared preference with the provided key, or null if the value was not found or it is equal to [Float.MIN_VALUE].
     * If the value is equal to [Float.MIN_VALUE], this function treats it as non-existent and returns null.
     */
    fun getFloatSharedPref(key: String) : Float? = with (sharedPreferences.getFloat(key,Float.MIN_VALUE)) {
        return if (this != Float.MIN_VALUE) this else null
    }

    /**
     * Saves any float shared preference using the provided key.
     * If the value is null, this function saves it as [Float.MIN_VALUE], for compatibility with [getFloatSharedPref].
     */
    fun setFloatSharedPref(key: String, value: Float?) {
        with(sharedPreferences.edit()) {
            value?.let { putFloat(key, it) } ?: putFloat(key, Float.MIN_VALUE)
            apply()
        }
    }

    /**
     * Returns any boolean shared preference with the provided key, or [defValue] if not found.
     */
    fun getBooleanSharedPref(key: String, defValue: Boolean) : Boolean{
        return sharedPreferences.getBoolean(key,defValue)
    }

    /**
     * Saves any boolean shared preference using the provided key.
     */
    fun setBooleanSharedPref(key: String, value: Boolean) {
        with(sharedPreferences.edit()) {
            putBoolean(key,value)
            apply()
        }
    }

    /**
     * Returns any Long shared preference with the provided key, or null if the value was not found or it is equal to [Long.MIN_VALUE].
     * If the value is equal to [Long.MIN_VALUE], this function treats it as non-existent and returns null.
     */
    fun getLongSharedPref(key: String) : Long? = with (sharedPreferences.getLong(key,Long.MIN_VALUE)) {
        return if (this != Long.MIN_VALUE) this else null
    }

    /**
     * Saves any Long shared preference using the provided key.
     * If the value is null, this function saves it as [Long.MIN_VALUE], for compatibility with [getLongSharedPref].
     */
    fun setLongSharedPref(key: String, value: Long?) {
        with(sharedPreferences.edit()) {
            value?.let { putLong(key, it) } ?: putLong(key, Long.MIN_VALUE)
            apply()
        }
    }

    /**
     * Sets the value of a timestamp with the given key to this moment.
     */
    fun updateTimestampToNow(key: String?) {
        with(sharedPreferences.edit()) {
            putLong(key, Calendar.getInstance().timeInMillis)
            apply()
        }
    }

    /**
     * Determines whether the timestamp with the given key is expired, based on the given [expirationMillis].
     * If the timestamp with the given key doesn't exist, true will be returned.
     * @return True if the timestamp has expired (=the difference between current time and the saved timestamp is greater than [expirationMillis]) or the timestamp doesn't exist.
     */
    fun isTimestampExpired(key: String, expirationMillis: Long) : Boolean {
        var stamp = getLongSharedPref(key)
        if (stamp == null) stamp = 0
        return (Calendar.getInstance().timeInMillis - stamp) > expirationMillis
    }

}