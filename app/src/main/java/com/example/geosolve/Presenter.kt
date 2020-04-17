package com.example.geosolve

import android.content.Context
import android.graphics.Canvas
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.FragmentNavigator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.geosolve.const_enum.Mode
import com.example.geosolve.model.DrawControl
import com.example.geosolve.model.Solve
import com.example.geosolve.model.figure.Figure
import com.example.geosolve.view.CanvasView
import com.example.geosolve.view.RecycleAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Presenter {
    // CanvasFragment
    lateinit var calcButton: FloatingActionButton
    lateinit var clearButton: FloatingActionButton
    lateinit var editModeButton: FloatingActionButton
    lateinit var deleteModeButton: FloatingActionButton
    lateinit var markModeButton: FloatingActionButton
    lateinit var canvasView: CanvasView

    // CanvasView
    var figure: Figure =
        Figure()
    private var drawControl: DrawControl = DrawControl(figure)

    // SolveFragment
    lateinit var backButton: FloatingActionButton
    lateinit var recyclerView: RecyclerView

    lateinit var navController: NavController

    fun onDraw(canvas: Canvas){
        drawControl.onDraw(canvas)
    }

    fun onTouchDown(x: Float, y: Float){
        drawControl.onTouchDown(x, y)
    }

    fun onTouchMove(x: Float, y: Float){
        drawControl.onTouchMove(x, y)
    }

    fun onTouchUp(x: Float, y: Float){
        drawControl.onTouchUp(x, y)
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
            drawControl.mode = Mode.ADD_MOVE_FIN
        }

        deleteModeButton.setOnClickListener {
            drawControl.mode = Mode.DEL_MOVE
        }

        markModeButton.setOnClickListener{
            drawControl.mode = Mode.MARK_FIND
        }
    }

    fun setSolveButton(){
        backButton.setOnClickListener {
            navController.popBackStack()
            RecycleAdapter.clear()
            Solve.stepList.clear()
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