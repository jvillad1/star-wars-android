package com.jvillad.starwars.android.commons.presentation.ui

import android.os.Bundle
import android.view.View
import android.view.ViewStub
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.jvillad.starwars.android.R
import com.jvillad.starwars.android.commons.extensions.gone
import com.jvillad.starwars.android.commons.extensions.visible
import com.jvillad.starwars.android.commons.presentation.widget.ErrorBanner
import com.jvillad.starwars.android.commons.presentation.widget.ErrorBannerView
import com.jvillad.starwars.android.commons.presentation.widget.ErrorBannerView.ErrorBannerListener
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 * Base Fragment with common functions for all application fragments.
 *
 * @author juan.villada
 */
@AndroidEntryPoint
abstract class BaseFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {

    // Loading
    private var viewStub: ViewStub? = null
    private var inflated: View? = null

    // ErrorBanner
    var errorBanner: ErrorBanner? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewStub = view.findViewById(R.id.loadingViewStub)
    }

    override fun onResume() {
        super.onResume()

        errorBanner?.let {
            if (!it.isDismissed) {
                it.show()
            }
        }
    }

    protected fun showLoading(message: String? = null) {
        Timber.d("showLoading $message")
        if (inflated == null) {
            inflated = viewStub?.inflate()
        }

        inflated?.visible()
    }

    protected fun hideLoading() {
        inflated?.gone()
    }

    protected fun showErrorBanner(
        @StringRes titleResId: Int,
        @StringRes messageResId: Int,
        withRetry: Boolean = false,
        withDismiss: Boolean = false,
        errorBannerListener: ErrorBannerListener
    ) {
        Timber.d("showError")

        hideLoading()
        view?.let {
            errorBanner = ErrorBanner.make(
                view = it,
                titleResId = titleResId,
                messageResId = messageResId,
                withRetry = withRetry,
                withDismiss = withDismiss,
                errorBannerListener = errorBannerListener
            )
            errorBanner?.show()
        }
    }

    private fun hideErrorBanner() {
        Timber.d("hideErrorBanner")
        errorBanner?.dismiss()
    }

    protected fun dismissErrorBanner() {
        Timber.d("dismissErrorBanner")
        hideErrorBanner()
        errorBanner?.isDismissed = true
    }
}
