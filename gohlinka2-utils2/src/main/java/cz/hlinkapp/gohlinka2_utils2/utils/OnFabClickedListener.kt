package cz.hlinkapp.gohlinka2_utils2.utils

/**
 * An interface Fragments can implement to listen for click events of parent Activity's FloatingActionButton.
 */
interface OnFabClickedListener {
    /**
     * Called when the fab was clicked.
     */
    fun onFabClicked()
}