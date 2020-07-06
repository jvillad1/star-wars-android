package com.jvillad.starwars.android.commons.presentation.ui

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * Base Activity with common functions for all application activities.
 *
 * @author juan.villada
 */
@AndroidEntryPoint
abstract class BaseActivity(@LayoutRes contentLayoutId: Int) : AppCompatActivity(contentLayoutId)
