package app.panels.main_panel

import javafx.scene.control.TableView
import tornadofx.*

typealias Row = Map<String, String>

class MainPanelView : View("Big Mommy") {
    private val controller: MainPanelController by inject()

    private var table: TableView<Row> = TableView<Row>().apply {
        addClass(MainPanelStyle.tableClass)
    }

    override val root = borderpane {
        importStylesheet(MainPanelStyle::class)

        top = menubar {
            menu("File") {
                item("Open", "Shortcut+O").action { controller.openLoadingDialog(super.currentWindow, table) }
            }

            menu("Edit") {
                item("Copy", "Shortcut+C")
                item("Paste", "Shortcut+V")
            }
        }

        center = table

        bottom = hbox {
            addClass(MainPanelStyle.statusBarClass)

            label("Lines: ")
            label(controller.linesProperty)
        }
    }
}
