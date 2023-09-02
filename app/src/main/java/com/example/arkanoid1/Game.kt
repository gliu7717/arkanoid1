package com.example.arkanoid1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.core.content.ContextCompat

class Game(context: Context) : SurfaceView(context), SurfaceHolder.Callback {
    private var surfaceHolder : SurfaceHolder = holder
    private var gameLoop= GameLoop(this, surfaceHolder)
    private var joystick : Joystick
    private var ball: Ball

    init{
        holder.addCallback(this)
        //gameLoop = GameLoop(this, surfaceHolder)
        val width = resources.displayMetrics.widthPixels
        val height = resources.displayMetrics.heightPixels
        joystick = Joystick(width*9f/10f, height*9f/10f,70f, 40f)
        ball = Ball(context, width, height, 30f)
    }

    override fun surfaceCreated(p0: SurfaceHolder) {
        gameLoop.startLoop()
    }

    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {
    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {
    }

    fun drawUPS(canvas: Canvas?){
        var averageUPS : String
        averageUPS =gameLoop.averageUPS.toString()
        var paint = Paint()
        var color = ContextCompat.getColor(context, R.color.magenta)
        paint.color = color
        paint.textSize = 60F
        if (canvas != null) {
            canvas.drawText("UPS: " + averageUPS,100f, 100f,paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event != null) {
            if (event.action == MotionEvent.ACTION_DOWN)
            {
                if(joystick.isPressed(event.getX(), event.getY())){
                    joystick.isPressed = true
                }
                return true
                // player.setPosition( event.getX()  ,  event.getY())
            }
            else if (event.action == MotionEvent.ACTION_MOVE)
            {
                if(joystick.isPressed){
                    joystick.setActuator(event.getX() ,  event.getY())
                }
                return true
            }
            else if (event.action == MotionEvent.ACTION_UP){
                joystick.isPressed = false
                joystick.resetActuator()
                return true
            }
        }
        return super.onTouchEvent(event)
    }


    public fun drawFPS(canvas: Canvas?){
        var averageFPS : String
        averageFPS =gameLoop.averageFPS.toString()
        var paint = Paint()
        var color = ContextCompat.getColor(context, R.color.magenta)
        paint.setColor(color)
        paint.textSize = 60F
        if (canvas != null) {
            canvas.drawText("FPS: " + averageFPS,100f, 200f,paint)
        }
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        drawUPS(canvas)
        drawFPS(canvas)
        joystick.draw(canvas)
        ball.draw(canvas)
    }

    fun update()
    {
        joystick.update()
        ball.update()
    }
}