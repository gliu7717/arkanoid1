package com.example.arkanoid1

import android.graphics.Canvas
import android.graphics.drawable.Drawable

abstract class GameObject(var positionX:Float, var positionY:Float) {
    var velocityX = 0f
    var velocityY = 0f

    abstract fun  draw(canvas: Canvas, d: Drawable? = null)
    abstract fun  update()

    companion object {
        val  SPEED_PIXELS_PER_SECOND = 400f
        val MAX_SPEED = SPEED_PIXELS_PER_SECOND / 30f  // GameLoop.MAX_UPS
        val BALL_MAX_SPEED = SPEED_PIXELS_PER_SECOND / 30f *0.6f
    }

}