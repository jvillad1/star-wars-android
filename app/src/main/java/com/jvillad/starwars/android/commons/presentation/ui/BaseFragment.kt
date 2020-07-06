package com.jvillad.starwars.android.commons.presentation.ui

import android.os.Bundle
import android.view.View
import android.view.ViewStub
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.jvillad.starwars.android.R
import com.jvillad.starwars.android.commons.extensions.gone
import com.jvillad.starwars.android.commons.extensions.visible
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewStub = view.findViewById(R.id.loadingViewStub)
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
}
