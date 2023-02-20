package kz.jusan.singularityhomeworks

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout

class PinCompoundView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    var entryBtn1: PinCustomView
    var entryBtn2: PinCustomView
    var entryBtn3: PinCustomView
    var entryBtn4: PinCustomView

    init {
        val view = inflate(context, R.layout.item_pin_compound, this)

        with(view) {
            entryBtn1 = findViewById(R.id.cv_pin_1)
            entryBtn2 = findViewById(R.id.cv_pin_2)
            entryBtn3 = findViewById(R.id.cv_pin_3)
            entryBtn4 = findViewById(R.id.cv_pin_4)
        }

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.PinCompoundView,
            0, 0
        ).apply {
            try {
                entryBtn1.text = this.getString(R.styleable.PinCompoundView_pinEntry1)
                entryBtn2.text = this.getString(R.styleable.PinCompoundView_pinEntry2)
                entryBtn3.text = this.getString(R.styleable.PinCompoundView_pinEntry3)
                entryBtn4.text = this.getString(R.styleable.PinCompoundView_pinEntry4)
            } finally {
                recycle()
            }
        }
    }

    fun setEntryPin1(value: String) {
        entryBtn1.text = value
    }

    fun setEntryPin2(value: String) {
        entryBtn2.text = value
    }

    fun setEntryPin3(value: String) {
        entryBtn3.text = value
    }

    fun setEntryPin4(value: String) {
        entryBtn4.text = value
    }

    fun clearAllEntries() {
        entryBtn1.setBorderInvisible()
        entryBtn1.text = ""
        invalidate()

        entryBtn2.setBorderInvisible()
        entryBtn2.text = ""
        invalidate()

        entryBtn3.setBorderInvisible()
        entryBtn3.text = ""
        invalidate()

        entryBtn4.setBorderInvisible()
        entryBtn4.text = ""
        invalidate()
    }

    fun showError(errorColor: Int) {
        entryBtn1.setBorderVisible(errorColor)
        entryBtn1.setTextColor(errorColor)
        invalidate()

        entryBtn2.setBorderVisible(errorColor)
        entryBtn2.setTextColor(errorColor)
        invalidate()

        entryBtn3.setBorderVisible(errorColor)
        entryBtn3.setTextColor(errorColor)
        invalidate()

        entryBtn4.setBorderVisible(errorColor)
        entryBtn4.setTextColor(errorColor)
        invalidate()
    }
}