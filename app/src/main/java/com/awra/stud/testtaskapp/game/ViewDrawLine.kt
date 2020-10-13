package com.awra.stud.testtaskapp.game

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import com.awra.stud.testtaskapp.R

class ViewDrawLine(context: Context) : Drawable() {
    var pointFrom = PointF(0f, 0f)
    var pointTo = PointF(0f, 0f)
    val paint = Paint().apply {
        color = Color.RED;strokeWidth = context.resources.getDimension(R.dimen.width_line)
    }
    private var width: Int = 0
    private var height: Int = 0


    override fun draw(canvas: Canvas) {
        width = canvas.clipBounds.width()
        height = canvas.clipBounds.height()
        if (!pointFrom.equals(pointTo))
            canvas.drawLine(pointFrom.x, pointFrom.y, pointTo.x, pointTo.y, paint)
    }

    fun drawLine(from: Int, to: Int) {
        pointFrom.x = (width / 3 * (from % 3) + width / 6).toFloat()
        pointFrom.y = (height / 3 * (from / 3) + height / 6).toFloat()
        pointTo.x = (width / 3 * (to % 3) + width / 6).toFloat()
        pointTo.y = (height / 3 * (to / 3) + height / 6).toFloat()
        invalidateSelf()
    }

    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        paint.colorFilter = colorFilter
    }

    override fun getOpacity() = PixelFormat.OPAQUE
}