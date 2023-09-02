package com.example.arkanoid1

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class Joystick (val outCircleCenterPositionX: Float,
                val outCircleCenterPositionY: Float,
                var outCircleRadius:Float,
                var innerCircleRadius:Float
)
{
    private var innerCircleCenterPositionX = outCircleCenterPositionX
    private var innerCircleCenterPositionY = outCircleCenterPositionY
    private var outerCirclePaint = Paint()
    private var innerCirclePaint = Paint()
    var actuatorX=0f
    var actuatorY=0f
    var isPressed = false

    init {
        outerCirclePaint.setColor(Color.GRAY)
        outerCirclePaint.style = Paint.Style.FILL_AND_STROKE
        innerCirclePaint.setColor(Color.BLUE)
        innerCirclePaint.style = Paint.Style.FILL_AND_STROKE
    }

    fun isPressed(x: Float, y: Float): Boolean {
        var joystickCentertoTouchDistance = Math.sqrt(
            Math.pow((outCircleCenterPositionX -x).toDouble(), 2.0) +
                    Math.pow((outCircleCenterPositionY -y).toDouble(), 2.0)
        )
        return joystickCentertoTouchDistance < outCircleRadius

    }

    fun draw(canvas: Canvas?) {
        if (canvas != null) {
            canvas.drawCircle(outCircleCenterPositionX,
                outCircleCenterPositionY,
                outCircleRadius,
                outerCirclePaint)
            canvas.drawCircle(innerCircleCenterPositionX,
                innerCircleCenterPositionY,
                innerCircleRadius,
                innerCirclePaint)
        }
    }

    fun update() {
        updateInnerCirclePosition()
    }
    fun updateInnerCirclePosition()
    {
        innerCircleCenterPositionX = outCircleCenterPositionX +actuatorX * outCircleRadius
        innerCircleCenterPositionY = outCircleCenterPositionY +actuatorY * outCircleRadius
    }

    fun setActuator(x: Float, y: Float) {
        var deltaX = x - outCircleCenterPositionX
        var deltaY = y - outCircleCenterPositionY
        var deltaDistance = Math.sqrt( deltaX.toDouble() * deltaX.toDouble() +
                deltaY.toDouble()*deltaY.toDouble())
        if(deltaDistance < outCircleRadius){
            actuatorX = deltaX / outCircleRadius
            actuatorY = deltaY / outCircleRadius
        }else{
            actuatorX = (deltaX / deltaDistance).toFloat()
            actuatorY = (deltaY / deltaDistance).toFloat()
        }
    }

    fun resetActuator() {
        actuatorX = 0f
        actuatorY=0f
    }

}