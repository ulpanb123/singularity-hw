package kz.jusan.singularityhomeworks

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Canvas
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel

private const val COLOR_NAME = -1

class PinCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatButton(context, attrs, defStyleAttr) {
    private var radius = 0f
    var hasBorder = false
    private var borderColor = COLOR_NAME
    private var borderSize = 0f

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.PinCustomView,
            0, 0
        ).apply {
            try {
                radius = resources.getDimension(R.dimen.dimen_pin_radius)
                hasBorder = getBoolean(R.styleable.PinCustomView_hasBorder, false)
                borderColor = getColor(R.styleable.PinCustomView_borderColor, COLOR_NAME)
                borderSize = resources.getDimension(R.dimen.dimen_pin_border)
            } finally {
                recycle()
            }
        }

        setBackground()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        setBackground()
    }

    private fun setBackground() {
        val shapeAppearanceModel: ShapeAppearanceModel = ShapeAppearanceModel().toBuilder().apply {
            if (radius > 0) {
                setAllCorners(
                    CornerFamily.ROUNDED,
                    radius
                )
            }
        }.build()

        val shapeDrawable = MaterialShapeDrawable(shapeAppearanceModel).apply {

            if (hasBorder && borderColor != COLOR_NAME) {
                strokeWidth = borderSize
                strokeColor = ColorStateList.valueOf(borderColor)
            }

            fillColor = ColorStateList.valueOf(resources.getColor(R.color.color_btn_bg))
        }

        background = shapeDrawable
    }

    fun setBorderVisible(color: Int) {
        hasBorder = true
        borderColor = color
        invalidate()        //doesn't work:(
    }

    fun setBorderInvisible() {
        hasBorder = false
        invalidate()
    }

}