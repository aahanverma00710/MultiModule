package com.avcoding.core_utils.ext.view

import android.R
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.MotionEvent
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.EditText
import android.widget.TextView
import androidx.core.animation.doOnEnd
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar

/* ---------------------------------------------------- */
/* > ANIMATION EXTENSIONS                               */
/* ---------------------------------------------------- */

/**
 * Slides the view upward while fading it in.
 *
 * - Sets visibility to VISIBLE before animation
 * - Starts animation only after view is measured
 *
 * @param duration Animation duration in milliseconds
 */
fun View.slideUpAndShow(duration: Long = 500) {
    if (visibility == View.VISIBLE) return

    visibility = View.VISIBLE

    val alpha = ObjectAnimator.ofFloat(this, "alpha", 0f, 1f)
    val translate =
        ObjectAnimator.ofFloat(this, "translationY", height.toFloat(), 0f)

    AnimatorSet().apply {
        playTogether(alpha, translate)
        this.duration = duration
        interpolator = AccelerateInterpolator()
    }.also { animator ->
        viewTreeObserver.addOnPreDrawListener(object :
            ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                viewTreeObserver.removeOnPreDrawListener(this)
                animator.start()
                return true
            }
        })
    }
}

/**
 * Slides the view downward while fading it out.
 *
 * - Sets visibility to GONE after animation ends
 */
fun View.slideDownAndHide(duration: Long = 500) {
    if (visibility != View.VISIBLE) return

    val alpha = ObjectAnimator.ofFloat(this, "alpha", 1f, 0f)
    val translate =
        ObjectAnimator.ofFloat(this, "translationY", 0f, height.toFloat())

    AnimatorSet().apply {
        playTogether(alpha, translate)
        this.duration = duration
        interpolator = AccelerateInterpolator()
        doOnEnd { visibility = View.GONE }
    }.also { animator ->
        viewTreeObserver.addOnPreDrawListener(object :
            ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                viewTreeObserver.removeOnPreDrawListener(this)
                animator.start()
                return true
            }
        })
    }
}

/**
 * Rotates the view to the given angle.
 */
fun View.rotateTheView(angle: Float) {
    animate().rotation(angle).start()
}

/**
 * Animates the view sliding in from the left.
 */
fun View.animateSlideInFromLeft(duration: Long = 300) {
    alpha = 0f
    translationX = -width.toFloat()
    show()

    animate()
        .alpha(1f)
        .translationX(0f)
        .setInterpolator(DecelerateInterpolator())
        .setDuration(duration)
        .start()
}

/**
 * Animates the view sliding out to the left.
 */
fun View.animateSlideOutToLeft(
    duration: Long = 300,
    onEnd: (() -> Unit)? = null
) {
    animate()
        .alpha(0f)
        .translationX(-width.toFloat())
        .setInterpolator(AccelerateInterpolator())
        .setDuration(duration)
        .withEndAction {
            hide()
            onEnd?.invoke()
        }
        .start()
}

/* ---------------------------------------------------- */
/* > VISIBILITY HELPERS                                 */
/* ---------------------------------------------------- */

/** Sets visibility to VISIBLE */
fun View.show() {
    visibility = View.VISIBLE
}

/** Sets visibility to GONE */
fun View.hide() {
    visibility = View.GONE
}

/**
 * Fades view in/out while changing visibility.
 *
 * @param visibility Target visibility
 * @param duration Animation duration
 */
fun View.animateAlpha(visibility: Int, duration: Long = 200) {
    if (this.visibility == visibility) return

    if (visibility == View.VISIBLE) show()

    val targetAlpha = if (visibility == View.VISIBLE) 1f else 0f

    animate()
        .alpha(targetAlpha)
        .setDuration(duration)
        .withEndAction { this.visibility = visibility }
}

/* ---------------------------------------------------- */
/* > SNACKBAR                                           */
/* ---------------------------------------------------- */

/**
 * Shows a Snackbar with a text message.
 */
fun View.snack(message: String?, length: Int = Snackbar.LENGTH_SHORT) {
    message?.let { Snackbar.make(this, it, length).show() }
}

/* ---------------------------------------------------- */
/* > BITMAP / CANVAS                                    */
/* ---------------------------------------------------- */

/**
 * Captures the View into a Bitmap.
 *
 * ⚠️ Does not work with SurfaceView.
 */
fun View.getBitmap(width: Int, height: Int): Bitmap {
    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)

    background?.draw(canvas) ?: canvas.drawColor(Color.WHITE)
    draw(canvas)

    return bitmap
}

/**
 * Safely captures full content bitmap even if the view is not measured.
 */
fun View.captureBitmapSafely(): Bitmap? {
    val oldBounds = intArrayOf(left, top, right, bottom)

    measure(
        View.MeasureSpec.UNSPECIFIED,
        View.MeasureSpec.UNSPECIFIED
    )

    if (measuredWidth <= 0 || measuredHeight <= 0) return null

    layout(left, top, left + measuredWidth, top + measuredHeight)

    val bitmap =
        Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)

    canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
    draw(canvas)

    layout(oldBounds[0], oldBounds[1], oldBounds[2], oldBounds[3])

    return bitmap
}

/* ---------------------------------------------------- */
/* > TEXT / INPUT                                       */
/* ---------------------------------------------------- */

/**
 * Sets a clickable span within a TextView.
 */
fun TextView.setSpanString(
    message: String?,
    color: Int,
    startPos: Int,
    endPos: Int = message?.length ?: 0,
    isBold: Boolean = false,
    onClick: () -> Unit
) {
    val span = SpannableString(message)

    val clickable = object : ClickableSpan() {
        override fun onClick(widget: View) = onClick()
        override fun updateDrawState(ds: TextPaint) {
            ds.isUnderlineText = false
            ds.color = ContextCompat.getColor(context, color)
            if (isBold) ds.typeface = Typeface.DEFAULT_BOLD
        }
    }

    span.setSpan(clickable, startPos, endPos, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

    text = span
    movementMethod = LinkMovementMethod.getInstance()
    highlightColor = Color.TRANSPARENT
}

/**
 * Detects clicks on the END drawable of an EditText.
 */
@SuppressLint("ClickableViewAccessibility")
fun EditText.onEndDrawableClick(callback: () -> Unit) {
    setOnTouchListener { _, event ->
        if (event.action == MotionEvent.ACTION_UP) {
            compoundDrawables[2]?.let {
                if (event.rawX >= right - it.bounds.width() - paddingRight) {
                    callback()
                    return@setOnTouchListener true
                }
            }
        }
        false
    }
}

/* ---------------------------------------------------- */
/* > DIALOG                                             */
/* ---------------------------------------------------- */

/**
 * Makes BottomSheet dialog background transparent.
 */
fun BottomSheetDialogFragment.setTransparentBackground() {
    dialog?.setTransparentBackground()
}

/**
 * Makes DialogFragment background transparent.
 */
fun DialogFragment.setTransparentBackground() {
    dialog?.setTransparentBackground()
}

/**
 * Applies transparent background to Dialog.
 */
fun Dialog.setTransparentBackground() {
    setOnShowListener {
        findViewById<View>(
            com.google.android.material.R.id.design_bottom_sheet
        )?.setBackgroundResource(R.color.transparent)
    }
}

/* ---------------------------------------------------- */
/* > VIEWPAGER                                          */
/* ---------------------------------------------------- */

/**
 * Enables or disables user swipe interaction for ViewPager2.
 */
fun ViewPager2.disableUserInputTemporarily(disable: Boolean) {
    isUserInputEnabled = !disable
}
