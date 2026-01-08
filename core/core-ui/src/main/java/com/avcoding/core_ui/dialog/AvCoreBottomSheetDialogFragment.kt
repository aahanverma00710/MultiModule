package com.avcoding.core_ui.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.viewbinding.ViewBinding
import androidx.core.graphics.drawable.toDrawable

abstract class AvCoreBottomSheetDialogFragment<VB : ViewBinding> :
    BottomSheetDialogFragment() {

    /**
     * Generate view binding
     */
    abstract fun getBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    /**
     * Access UI views
     */
    protected lateinit var views: VB

    /**
     * Dialog cancelable or not
     */
    abstract fun isDialogCancelable(): Boolean

    /**
     * Cancel on outside touch
     */
    abstract fun isCanceledOnTouchOutside(): Boolean

    /**
     * Transparent background or not
     */
    abstract fun setDialogBackgroundTransparent(): Boolean

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = isDialogCancelable()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        views = getBinding(inflater, container)
        return views.root
    }

    override fun onStart() {
        super.onStart()

        dialog?.apply {
            setCanceledOnTouchOutside(isCanceledOnTouchOutside())

            if (setDialogBackgroundTransparent()) {
                window?.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
            }
        }
    }
}
