package open.geosolve.geosolve.view.fragments

import kotlinx.android.synthetic.main.fragment_example_figure.view.*
import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.canvas.tools.Tool

class ExampleFigureFragment : MvpFragmentX(R.layout.fragment_example_figure) {
    override fun setupLayout() {
        val args = ExampleFigureFragmentArgs.fromBundle(requireArguments())
        layout.fullExampleCanvasView.dataCanvas = args.dataCanvas
        layout.fullExampleCanvasView.canvasPresenter.tool = args.tool

        val updateForString = args.updateForString as () -> CharSequence

        layout.fullExampleCanvasView.dataCanvas.selectIt()
        layout.exampleText.text = updateForString()

        layout.fullExampleCanvasView.canvasPresenter.customTool = object : Tool {
            override fun onTouchMove(x: Float, y: Float) {
                layout.exampleText.text = updateForString()
            }
        }
    }
}