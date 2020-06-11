package open.geosolve.geosolve.presentation.presenter

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpPresenter
import open.geosolve.geosolve.App
import open.geosolve.geosolve.GlobalFiguresController.figureList
import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.ElementUpdaters
import open.geosolve.geosolve.model.FigureController
import open.geosolve.geosolve.model.FigureController.figure
import open.geosolve.geosolve.model.data.Figure
import open.geosolve.geosolve.model.data.generalized.SolveGraph
import open.geosolve.geosolve.model.solve.CallBackSolveUi
import open.geosolve.geosolve.model.solve.SolveUtil
import open.geosolve.geosolve.model.tools.AddTool
import open.geosolve.geosolve.model.tools.AddTool.lastNode
import open.geosolve.geosolve.model.tools.BaseTool
import open.geosolve.geosolve.model.tools.SetValueTool
import open.geosolve.geosolve.model.tools.Tool
import open.geosolve.geosolve.presentation.view.CanvasScreenView
import open.geosolve.geosolve.view.screens.solveScreen.RecycleAdapter

@InjectViewState
class CanvasScreenPresenter(val app: App) : MvpPresenter<CanvasScreenView>() {

    var tool: Tool = AddTool

    init {
        SetValueTool.callBack = { message: Int, element: SolveGraph ->
            viewState.showDialog(message) {
                element.setValueDraw(it)
                solveAndCallBack()
            }
        }
    }

    fun onTouchDown(touchX: Float, touchY: Float) {
        tool.onTouchDown(touchX, touchY)
    }

    fun onTouchMove(touchX: Float, touchY: Float) {
        tool.onTouchMove(touchX, touchY)
    }

    fun onTouchUp(touchX: Float, touchY: Float) {
        tool.onTouchUp(touchX, touchY)

        if (BaseTool.movementWasNot)
            solveAndCallBack() // решать только когда было не передвижение
    }

    fun solveAndCallBack() {
        val solveUiCallBack = {
            viewState.showTypeFigure()
            viewState.updateCanvas()
        }

        GlobalScope.launch(Dispatchers.Main) {
            SolveUtil.solveGraphs()
            solveUiCallBack()
        }
    }

    fun showSolveClick() {
        SolveUtil.showStepSolveList(object : CallBackSolveUi {
            override fun findNotMark() {
                viewState.showMessage(R.string.find_not_mark)
            }

            override fun solveIsNotFound() {
                viewState.showMessage(R.string.solve_not_found)
            }

            override fun userInputValue() {
                viewState.showMessage(R.string.user_input_value)
            }

            override fun solveIsFound(list: List<SolveGraph>) {
                RecycleAdapter.addAll(list)
                viewState.goToSolveScreen()
            }
        })
    }

    fun clearButtonClick() {
        if (figureList.size > 1 && figure.isEmpty()) {
            FigureController.updateFind()
            figureList.remove(figure)
        }
        if (figureList.isNotEmpty()) {
            FigureController.updateFind()
            figureList.remove(figure)
        }

        lastNode = null

        figureList.add(Figure())

        viewState.showTypeFigure()
        viewState.updateCanvas()
    }
}