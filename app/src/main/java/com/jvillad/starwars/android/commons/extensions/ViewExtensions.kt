package com.jvillad.starwars.android.commons.extensions

import android.app.Activity
import android.content.Context
import android.os.SystemClock
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.children
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import com.jvillad.starwars.android.commons.settings.TimeSettings.DEBOUNCE_INTERVAL_MILLISECONDS
import java.util.*


/**
 * Extension functions for View.
 *
 * @author juan.villada
 */

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun Fragment.hideKeyboard() {
    val activity = this.activity
    if (activity is AppCompatActivity) {
        activity.hideKeyboard()
    }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Fragment.showKeyboard() {
    val activity = this.activity
    if (activity is AppCompatActivity) {
        activity.showKeyboard()
    }
}

fun Activity.showKeyboard() {
    showKeyboard(currentFocus ?: View(this))
}

fun Context.showKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.showSoftInput(view, 0)
}

/**
 * Extension function to enable all child's within a [ViewGroup].
 */
fun View.setAllEnabled(enabled: Boolean) {
    isEnabled = enabled

    if (this is ViewGroup) {
        children.forEach { child ->
            child.setAllEnabled(enabled)
        }
    }
}

/**
 * Extension function to set OnClickListener with an ActionDebounce.
 */
fun View.setOnDebouncedClickListener(action: () -> Unit) {
    val actionDebounce =
        ActionDebounce(action)

    // This is the only place in the project where we should actually use setOnClickListener
    setOnClickListener {
        actionDebounce.notifyAction()
    }
}

/**
 * Extension function to remove OnClickListener with an ActionDebounce.
 */
fun View.removeOnDebouncedClickListener() {
    setOnClickListener(null)
    isClickable = false
}

/**
 * ActionDebounce class for using setOnClickListener safely.
 */
private class ActionDebounce(private val action: () -> Unit) {

    private var lastActionTime = 0L

    fun notifyAction() {
        val now = SystemClock.elapsedRealtime()

        val millisecondsPassed = now - lastActionTime
        val actionAllowed = millisecondsPassed > DEBOUNCE_INTERVAL_MILLISECONDS
        lastActionTime = now

        if (actionAllowed) {
            action.invoke()
        }
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        private var timer: Timer? = null

        override fun afterTextChanged(editable: Editable?) {
            timer = Timer().apply {
                schedule(object : TimerTask() {
                    override fun run() {
                        afterTextChanged.invoke(editable.toString())
                    }
                }, DEBOUNCE_INTERVAL_MILLISECONDS)
            }
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            if (timer != null) {
                timer!!.cancel()
            }
        }
    })
}

fun Fragment.waitForTransition(targetView: View) {
    postponeEnterTransition()
    targetView.doOnPreDraw { startPostponedEnterTransition() }
}

internal fun View?.findSuitableParent(): ViewGroup? {
    var view = this
    var fallback: ViewGroup? = null
    do {
        if (view is CoordinatorLayout) {
            // We've found a CoordinatorLayout, use it
            return view
        } else if (view is FrameLayout) {
            if (view.id == android.R.id.content) {
                // If we've hit the decor content view, then we didn't find a CoL in the
                // hierarchy, so use it.
                return view
            } else {
                // It's not the content view but we'll use it as our fallback
                fallback = view
            }
        }

        if (view != null) {
            // Else, we will loop and crawl up the view hierarchy and try to find a parent
            val parent = view.parent
            view = if (parent is View) parent else null
        }
    } while (view != null)

    // If we reach here then we didn't find a CoL or a suitable content view so we'll fallback
    return fallback
}
