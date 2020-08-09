package visuals.panels.mainpanel

import javafx.beans.property.ReadOnlyObjectWrapper
import javafx.beans.property.SimpleLongProperty
import javafx.collections.ObservableList
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.stage.FileChooser
import javafx.stage.Window
import javafx.util.Callback
import tornadofx.Controller
import tornadofx.asObservable
import tornadofx.runLater
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.function.Supplier
import java.util.stream.Stream
import kotlin.collections.set


class MainPanelController : Controller() {

    val separator = ","
    val linesProperty = SimpleLongProperty(0L)

    fun openLoadingDialog(parent: Window?, table: TableView<Row>) {
        val fileChooser = FileChooser();
        val file = fileChooser.showOpenDialog(parent)

        if (file != null) {
            runLater {
                loadFileInto(file, table)
            }
        }
    }

    private fun loadFileInto(file: File, table: TableView<Row>) {
        val linesStreamSupplier: Supplier<Stream<String>> = Supplier { Files.lines(Paths.get(file.path)) }

        val header = getHeaderElements(linesStreamSupplier.get(), separator)
        val rows = mutableListOf<Row>().asObservable()

        linesStreamSupplier.get().forEach { line ->
            val lineElements = getLineElements(line, separator)
            val mapping = mutableMapOf<String, String>()

            for ((heading, lineElement) in header zip lineElements) {
                mapping[heading] = lineElement
            }

            rows += mapping
        }

        linesProperty.value = linesStreamSupplier.get().count()
        populateToTable(rows, table)
    }

    private fun getHeaderElements(linesStream: Stream<String>, separator: String): List<String> {
        val firstLine = linesStream.findFirst()
        return if (firstLine.isPresent) firstLine.get().split(separator) else emptyList()
    }

    private fun getLineElements(line: String, separator: String): List<String> {
        return line.split(separator)
    }

    private fun populateToTable(rows: ObservableList<Row>, table: TableView<Row>) {
        table.columns.clear()
        table.items.clear()

        createHeadings(rows, table)
        createContents(rows, table)
    }

    private fun createHeadings(rows: ObservableList<Row>, table: TableView<Row>) {
        val columns = ArrayList<TableColumn<Row, Any>>()
        val keys = rows.first().keys

        for (key in keys) {
            val column = TableColumn<Row, Any>(key)
            column.cellValueFactory = Callback { param ->
                ReadOnlyObjectWrapper(param.value.get(key))
            }
            columns.add(column)
        }

        table.columns.addAll(columns)
    }

    private fun createContents(rows: ObservableList<Row>, table: TableView<Row>) {
        val contents = rows.minus(rows.first())  // header should not be added
        table.items.addAll(contents)
    }
}
