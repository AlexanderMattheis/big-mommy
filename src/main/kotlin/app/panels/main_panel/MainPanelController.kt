package app.panels.main_panel

import javafx.beans.property.SimpleLongProperty
import javafx.stage.FileChooser
import tornadofx.Controller
import tornadofx.View
import tornadofx.runLater
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

class MainPanelController(val view: View) : Controller() {

    val linesProperty = SimpleLongProperty(0L)

    fun openFile() {
        val fileChooser = FileChooser();
        val file = fileChooser.showOpenDialog(view.currentWindow)

        if (file != null) {
            runLater {
                loadData(file)
            }
        }
    }

    private fun loadData(file: File) {
        val linesStream = Files.lines(Paths.get(file.path))
        linesProperty.setValue(linesStream.count())
    }
}
