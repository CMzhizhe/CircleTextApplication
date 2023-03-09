package com.gxx.circletextviewlibrary

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat

/**
 * @date 创建时间: 2023/3/9
 * @author gaoxiaoxiong
 * @description 自定义，圆圈中心绘制文字
 */
class CircleTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var mOvlPaint = Paint()//圆形画笔
    var mOvlRectF = RectF(0.0f, 0.0f, 0.0f, 0.0f)
    val mTextPaint = Paint()//文字画笔

    //圆形宽度
     var mOvalStrokeWidth = dip2px(1.0f).toFloat()

    //圆形颜色
    @ColorInt
    var mOvalStrokeColor: Int = -1

    //文字大小
     var mTextSize = sp2px(16.0f)

    //文字颜色
    @ColorInt
     var mTextColor: Int = -1

    //文字内容
     var mText: String? = ""

    init {
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.CircleTextView, defStyleAttr, 0)
        mOvalStrokeWidth =
            typedArray.getDimension(R.styleable.CircleTextView_circle_stroke_width, dip2px(1.0f).toFloat())
        mOvalStrokeColor = typedArray.getColor(
            R.styleable.CircleTextView_circle_stroke_color,
            ContextCompat.getColor(this.context, R.color.circle_purple_200)
        )
        mTextSize = typedArray.getDimension(R.styleable.CircleTextView_circle_text_size, sp2px(16.0f))
        mTextColor = typedArray.getColor(R.styleable.CircleTextView_circle_stroke_color, Color.BLACK)
        mText = typedArray.getString(R.styleable.CircleTextView_circle_text)
        typedArray.recycle()

        mOvlPaint.style = Paint.Style.STROKE
        mOvlPaint.isAntiAlias = true;//取消锯齿
        mTextPaint.isAntiAlias = true;//取消锯齿
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //设置圆形的颜色 + 描边
        mOvlPaint.color = mOvalStrokeColor
        mOvlPaint.strokeWidth = mOvalStrokeWidth

        mOvlRectF.set(
            mOvalStrokeWidth / 2,
            mOvalStrokeWidth / 2,
            width.toFloat() - mOvalStrokeWidth / 2,
            height.toFloat() - mOvalStrokeWidth / 2
        )
        canvas.drawArc(mOvlRectF, 0.0f, 360.0f, false, mOvlPaint)

        mTextPaint.style = Paint.Style.FILL
        mTextPaint.textSize = mTextSize
        mTextPaint.color = mTextColor
        mTextPaint.textAlign = Paint.Align.CENTER

        //计算baseline
        if (!mText.isNullOrEmpty()) {
            val fontMetrics = mTextPaint.fontMetrics
            val distance = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom
            val baseline: Float = height / 2 + distance
            canvas.drawText(mText!!, (width / 2).toFloat(), baseline, mTextPaint)
        }
    }


    private fun sp2px(spValue: Float): Float {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5f)
    }


    private fun dip2px(dpValue: Float): Int {
        val scale = context.getResources().getDisplayMetrics().density;
        return (dpValue * scale + 0.5f).toInt();
    }


}