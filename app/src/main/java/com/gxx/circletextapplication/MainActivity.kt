package com.gxx.circletextapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val circleTextView = this.findViewById<com.gxx.circletextviewlibrary.CircleTextView>(R.id.auto_circle_text_view)

        circleTextView.mText = "你好"
        circleTextView.mTextSize = sp2px(20.0f)
        circleTextView.mTextColor = ContextCompat.getColor(this,R.color.red)

        circleTextView.mOvalStrokeWidth = dip2px(2.0f)
        circleTextView.mOvalStrokeColor = ContextCompat.getColor(this,R.color.black)

        circleTextView.invalidate()
    }


    private fun sp2px(spValue: Float): Float {
        val fontScale = this.resources.displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5f)
    }


    private fun dip2px(dpValue: Float): Float {
        val scale = this.getResources().getDisplayMetrics().density;
        return (dpValue * scale + 0.5f)
    }


}