import tornadofx.App
import tornadofx.launch
import app.panels.main_panel.MainPanelView

class BigMommy : App(MainPanelView::class)

fun main(args: Array<String>) {
    launch<BigMommy>(args)
}