package com.example.geosolve

import android.content.Context
import android.graphics.Canvas
import android.view.MotionEvent
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.geosolve.const_enum.Mode
import com.example.geosolve.model.DrawControl
import com.example.geosolve.model.Figure
import com.example.geosolve.view.CanvasView
import com.example.geosolve.view.RecycleAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Presenter {
    // CanvasFragment
    lateinit var calcButton: FloatingActionButton
    lateinit var clearButton: FloatingActionButton
    lateinit var editModeButton: FloatingActionButton
    lateinit var deleteModeButton: FloatingActionButton
    lateinit var canvasView: CanvasView

    // CanvasView
    private var figure: Figure = Figure()
    private var drawControl: DrawControl = DrawControl(figure)

    // SolveFragment
    lateinit var backButton: FloatingActionButton
    lateinit var recyclerView: RecyclerView

    lateinit var navController: NavController

    fun onDraw(canvas: Canvas){
        drawControl.onDraw(canvas)
    }

    fun onTouchDown(x: Float, y: Float){
        drawControl.setState(x, y)
    }

    fun onTouchMove(x: Float, y: Float){
        drawControl.moveNode(x, y)
    }

    fun onTouchUp(x: Float, y: Float){
        drawControl.addNodeIf(x, y)
    }

    fun clearButtonClick() {
        drawControl.clearCanvas(canvasView)
    }

    fun setCanvasButton(){
        calcButton.setOnClickListener {
            navController.navigate(R.id.ToSolveAction)
        }

        clearButton.setOnClickListener {
            clearButtonClick()
        }

        editModeButton.setOnClickListener {
            drawControl.mode = Mode.ADDMOVE
        }

        deleteModeButton.setOnClickListener {
            drawControl.mode = Mode.DELMOVE
        }
    }

    fun setSolveButton(){
        backButton.setOnClickListener {
            navController.popBackStack()
        }
    }

    fun setRecyclerParam(context: Context){
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)

        recyclerView.adapter = RecycleAdapter(context)

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0 || dy < 0 && backButton.isShown)
                    backButton.hide()
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE)
                    backButton.show();

                super.onScrollStateChanged(recyclerView, newState)
            }
        })
    }
}