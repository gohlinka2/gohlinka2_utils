package cz.hlinkapp.gohlinka2_utils2.utils

import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*


/**
 * Sets the given field of this Calendar to the given value and returns itself afterwards.
 */
fun Calendar.setAndReturnSelf(field: Int, value: Int) : Calendar {
    this.set(field,value)
    return this
}

/**
 * Increments the given field of this Calendar by [byHowMuch].
 */
fun Calendar.increment(field: Int, byHowMuch: Int) : Calendar {
    val prev = this.get(field)
    return this.setAndReturnSelf(field,prev + byHowMuch)
}

/**
 * Sets the [RecyclerView.LayoutManager] of this [RecyclerView], but only if no other instance
 * was set previously, preventing crashes.
 */
fun RecyclerView.setLayoutManagerSafely(layoutManager: RecyclerView.LayoutManager) {
    val previous: RecyclerView.LayoutManager? = this.layoutManager
    this.layoutManager = previous ?: layoutManager
}

/**
 * Attaches this [LinearSnapHelper] to the given [RecyclerView], but only if no snap helper
 * was attached before, preventing crashing.
 */
fun LinearSnapHelper.attachToRecyclerViewSafely(recyclerView: RecyclerView) {
    if (recyclerView.onFlingListener == null) this.attachToRecyclerView(recyclerView)
}

/**
 * Returns the index of the first occurrence of the specified string or null if not found.
 */
fun String.firstIndexOfOrNull(str : String) : Int? {
    val i = this.indexOf(str,0)
    return if (i >= 0) i else null
}

/**
 * Returns the substring as normal or null if the argument is null.
 */
fun String.substringOrNull(int : Int?) : String?{
    return if (int != null) this.substring(int) else null
}

/**
 * Sets the view's visibility to [View.VISIBLE].
 */
fun View.setVisible() {
    this.visibility = View.VISIBLE
}

/**
 * Sets the view's visibility to [View.GONE].
 */
fun View.setGone() {
    this.visibility = View.GONE
}

/**
 * Convenience for [Intent.resolveActivity]. The only difference is that this will return null if the given [pm] is null.
 */
fun Intent.resolveActivityOrNull(pm: PackageManager?) : ComponentName? {
    return if (pm != null) this.resolveActivity(pm) else null
}

/**
 * Resets this Calendar to the first millisecond of the day set.
 */
fun Calendar.resetToStartOfDay() {
    set(Calendar.HOUR_OF_DAY,0)
    set(Calendar.MINUTE,0)
    set(Calendar.SECOND,0)
    set(Calendar.MILLISECOND,0)
}

/**
 * Returns a timestamp of the start of the current day.
 */
fun getStartOfDayTimestamp() : Long {
    val cal = Calendar.getInstance()
    cal.resetToStartOfDay()
    return cal.timeInMillis
}

/**
 * Returns a [CoordinatorLayout] behavior of this view.
 * From https://github.com/Semper-Viventem/Material-backdrop/blob/master/README.md
 */
fun <T : CoordinatorLayout.Behavior<*>> View.findBehavior(): T = layoutParams.run {
    require(this is CoordinatorLayout.LayoutParams) { "View's layout params should be CoordinatorLayout.LayoutParams" }
    (layoutParams as CoordinatorLayout.LayoutParams).behavior as? T ?: throw IllegalArgumentException("Layout's behavior is not current behavior")
}

/**
 * Returns the fragment that is currently displayed in this ViewPager.
 * Note that this will only work if this ViewPager's adapter is a subclass of [FragmentPagerAdapter], and it will crash otherwise.
 * @param supportFragmentManager is needed for this to work
 */
fun ViewPager.getCurrentFragment(supportFragmentManager: FragmentManager) : Fragment? {
    require(this.adapter is FragmentPagerAdapter)
    return supportFragmentManager.findFragmentByTag("android:switcher:${this.id}:${this.currentItem}")
}

/**
 * Converts the [json] string to [T].
 */
inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object: TypeToken<T>() {}.type)