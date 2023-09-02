package com.example.arkanoid1

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat

class Ball( context :Context, var width: Int, var height:Int, var r: Float) :
    Circle(ContextCompat.getColor(context, R.color.white), width/2f, height/3f, r) {
    var randomNumberx = (Math.random().toFloat() - 0.5f)*2.0f
    var randomNumbery = (Math.random().toFloat() - 0.5f)*2.0f
    init {
        if(randomNumberx<0f)
            randomNumberx = -1f
        else
            randomNumberx = 1f
        if(randomNumbery<0f)
            randomNumbery = -1f
        else
            randomNumbery = 1f
        velocityX = randomNumberx*BALL_MAX_SPEED
        velocityY = randomNumbery*BALL_MAX_SPEED
    }

    override fun update() {
        super.update()
        positionX += velocityX
        positionY += velocityY
        if(positionX<=r || positionX>= width - r)
            velocityX = -velocityX
        if(positionY<=r || positionY >= height -r )
            velocityY = -velocityY
    }
}
