package com.matheus.testioasys.ui.common

import android.app.ActionBar
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.updatePadding
import com.matheus.testioasys.R
import com.matheus.testioasys.extensions.actionBarHeight
import com.matheus.testioasys.extensions.doOnApplyWindowInsets

class DefaultToolbar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val containerView = ConstraintLayout(context, attrs, defStyleAttr)

    init {
        doOnApplyWindowInsets { view, windowInsets, initialPadding ->
            view.updatePadding(top = initialPadding.top + windowInsets.systemWindowInsetTop)
        }

        setupContainer()
        setupBackground()
        setupMinHeight()
    }

    private fun setupBackground() {
        setBackgroundResource(R.drawable.toolbar_background)
    }

    private fun setupMinHeight() {
        minHeight = context.actionBarHeight
    }

    private fun setupContainer() {
        addView(
                containerView,
                LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, context.actionBarHeight)
        )

        ConstraintSet().apply {
            clone(this@DefaultToolbar)
            connect(
                    containerView.id,
                    ConstraintSet.START,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.START
            )
            connect(containerView.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
            connect(
                    containerView.id,
                    ConstraintSet.BOTTOM,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.BOTTOM
            )
        }.applyTo(this)
    }

    override fun addView(child: View?, params: ViewGroup.LayoutParams?) {
        if (child != containerView) {
            containerView.addView(child, params)
        } else {
            super.addView(child, params)
        }
    }
}