package com.jvillad.starwars.android.commons.presentation.widget

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.ContentViewCallback
import com.jvillad.starwars.android.R
import com.jvillad.starwars.android.commons.extensions.gone
import com.jvillad.starwars.android.commons.extensions.setOnDebouncedClickListener
import com.jvillad.starwars.android.commons.extensions.visible
import kotlinx.android.synthetic.main.view_error_banner.view.*
import timber.log.Timber

/**
 * Custom view for the [ErrorBanner].
 *
 * @author juan.villada
 */
class ErrorBannerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), ContentViewCallback {

    private var errorBannerListener: ErrorBannerListener? = null

    init {
        View.inflate(context, R.layout.view_error_banner, this)
        clipToPadding = false
        initErrorBannerListeners()
    }

    private fun initErrorBannerListeners() {
        retryBannerImageView.setOnDebouncedClickListener {
            errorBannerListener?.onErrorBannerRetry()
        }

        dismissBannerImageView.setOnDebouncedClickListener {
            errorBannerListener?.onErrorBannerDismiss()
        }
    }

    fun setTitle(@StringRes titleResId: Int) {
        bannerTitleTextView.text = context.getText(titleResId)
    }

    fun setMessage(@StringRes messageResId: Int) {
        bannerMessageTextView.text = context.getText(messageResId)
    }

    fun toggleRetryVisibility(visible: Boolean) = with(retryBannerImageView) {
        if (visible) {
            visible()
        } else {
            gone()
        }
    }

    fun toggleDismissVisibility(visible: Boolean) = with(dismissBannerImageView) {
        if (visible) {
            visible()
        } else {
            gone()
        }
    }

    fun setListener(errorBannerListener: ErrorBannerListener) {
        this.errorBannerListener = errorBannerListener
    }

    override fun animateContentIn(delay: Int, duration: Int) {
        val scaleX = ObjectAnimator.ofFloat(dismissBannerImageView, View.SCALE_X, 0F, 1F)
        val scaleY = ObjectAnimator.ofFloat(dismissBannerImageView, View.SCALE_Y, 0F, 1F)
        val animatorSet = AnimatorSet().apply {
            interpolator = OvershootInterpolator()
            setDuration(500)
            playTogether(scaleX, scaleY)
        }
        animatorSet.start()
    }

    override fun animateContentOut(delay: Int, duration: Int) {
        // NO-OP
    }

    interface ErrorBannerListener {
        /**
         * To be called when the user press retry in the banner.
         */
        fun onErrorBannerRetry() {
            Timber.d("onErrorBannerRetry")
        }

        /**
         * To be called when the user press dismiss in the banner.
         */
        fun onErrorBannerDismiss() {
            Timber.d("onErrorBannerDismiss")
        }
    }
}
