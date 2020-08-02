import tornadofx.App
import tornadofx.launch
import app.panels.main_panel.MainPanelView
import java.nio.file.Files
import java.nio.file.Paths

class BigMommy : App(MainPanelView::class)

fun main(args: Array<String>) {
    launch<BigMommy>(args)
}