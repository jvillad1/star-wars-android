package com.jvillad.starwars.android.presentation.search

import androidx.recyclerview.widget.RecyclerView.OnFlingListener
import timber.log.Timber
import kotlin.math.abs

open class RecyclerViewSwipeListener internal constructor(
    private val isScrollingVertically: Boolean,
    private val onSwipeListener: OnSwipeListener
) : OnFlingListener() {

    override fun onFling(velocityX: Int, velocityY: Int): Boolean {
        if (isScrollingVertically && abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
            if (velocityY < 0) {
                onSwipeListener.onSwipeDown()
            } else {
                onSwipeListener.onSwipeUp()
            }
            return true
        } else if (!isScrollingVertically && abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
            if (velocityX < 0) {
                onSwipeListener.onSwipeLeft()
            } else {
                onSwipeListener.onSwipeRight()
            }
            return true
        }
        return false
    }

    interface OnSwipeListener {
        fun onSwipeRight() {
            Timber.d("Optional")
        }

        fun onSwipeLeft() {
            Timber.d("Optional")
        }

        fun onSwipeUp()

        fun onSwipeDown()
    }

    companion object {
        private const val SWIPE_VELOCITY_THRESHOLD = 2000
    }
}
