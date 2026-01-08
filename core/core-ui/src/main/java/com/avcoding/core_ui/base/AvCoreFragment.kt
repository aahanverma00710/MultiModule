package com.avcoding.core_ui.base

import android.content.Context
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AvCoreFragment : Fragment() {
    private lateinit var baseActivity: AvCoreActivity


    override fun onAttach(context: Context) {
        super.onAttach(context)
        baseActivity = context as AvCoreActivity
    }
}