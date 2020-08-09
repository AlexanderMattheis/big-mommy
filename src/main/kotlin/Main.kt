import tornadofx.App
import tornadofx.launch
import visuals.panels.mainpanel.MainPanelView

class BigMommy : App(MainPanelView::class)

fun main(args: Array<String>) {
    launch<BigMommy>(args)
}