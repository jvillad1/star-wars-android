package com.jvillad.starwars.android.commons.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

/**
 * Extension functions for LiveData class.
 *
 * @author juan.villada
 */

fun <T> MutableLiveData<T>.asLiveData() = this as LiveData<T>

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, body: (T) -> Unit = {}) =
    liveData.observe(this, Observer { it?.let { t -> body(t) } })

fun <T> Fragment.observeFragment(liveData: LiveData<T>, body: (T) -> Unit = {}) =
    liveData.observe(this.viewLifecycleOwner, Observer { it?.let { t -> body(t) } })
