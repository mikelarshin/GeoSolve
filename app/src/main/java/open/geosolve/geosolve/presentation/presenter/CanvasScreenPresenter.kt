package open.geosolve.geosolve.presentation.presenter

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpPresenter
import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.canvas.FigureList
import open.geosolve.geosolve.model.canvas.canvasData
import open.geosolve.geosolve.model.canvas.data.generalized.SolveGraph
import open.geosolve.geosolve.model.solve.CallBackSolveUi
import open.geosolve.geosolve.model.solve.SolveUtil
import open.geosolve.geosolve.model.canvas.tools.AddTool.lastNode
import open.geosolve.geosolve.model.canvas.tools.SetValueTool
import open.geosolve.geosolve.presentation.view.CanvasScreenView

@InjectViewState
class CanvasScreenPresenter : MvpPresenter<CanvasScreenView>() {

    init {
        SetValueTool.callBack = { message: Int, element: SolveGraph ->
            viewState.showDialog(message, element.toString()) {
                element.setValueDraw(it)
                solveAndCallBack()
            }
        }
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

            override fun solveIsFound(solveList: List<SolveGraph>) {
                viewState.goToSolveScreen(solveList)
            }
        })
    }

    fun clearButtonClick() {
        lastNode = null

        FigureList.clear()
        canvasData.find = null

        viewState.showTypeFigure()
        viewState.updateCanvas()
    }
}