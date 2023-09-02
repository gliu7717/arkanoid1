package com.example.arkanoid1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Drawable

open class Circle(var color:Int,
                  x:Float,
                  y:Float,
                  var radius:Float) :  GameObject(x,y){
    var paint = Paint()
    init {
        paint.setColor(color)
    }

    override fun draw(canvas: Canvas, d: Drawable?) {
        if (canvas != null) {
            canvas.drawCircle(positionX, positionY, radius, paint)
        }
    }

    override fun update() {
    }
}