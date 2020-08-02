package app.panels.main_panel

import javafx.beans.property.SimpleLongProperty
import javafx.stage.FileChooser
import tornadofx.*
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.function.Supplier
import java.util.stream.Stream

class MainPanelController(val view: View) : Controller() {

    val rows = mutableListOf<Map<String, String>>().asObservable()
    val separator = ","

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
        val linesStreamSupplier: Supplier<Stream<String>> = Supplier { Files.lines(Paths.get(file.path)) }

        val header = getHeaderElements(linesStreamSupplier.get(), separator)
        val rows = mutableListOf<Map<String, String>>()

        linesStreamSupplier.get().forEach { line ->
            val lineElements = getLineElements(line, separator)
            val mapping = mutableMapOf<String, String>()

            for ((heading, lineElement) in header zip lineElements) {
                mapping[heading] = lineElement
            }

            rows += mapping
        }

        this.rows.setAll(rows)
        linesProperty.value = linesStreamSupplier.get().count()
    }

    private fun getHeaderElements(linesStream: Stream<String>, separator: String): List<String> {
        val firstLine = linesStream.findFirst()
        return if (firstLine.isPresent) firstLine.get().split(separator) else emptyList()
    }

    private fun getLineElements(line: String, separator: String): List<String> {
        return line.split(separator)
    }
}
