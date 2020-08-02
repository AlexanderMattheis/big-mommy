package app.panels.main_panel

import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.TableColumn
import tornadofx.*

class MainPanelView : View("Big Mommy") {
    private val controller : MainPanelController = MainPanelController(this)

    override val root = borderpane {
        importStylesheet(MainPanelStyle::class)

        top = menubar {
            menu("File") {
                item("Open", "Shortcut+O").action { controller.openFile() }
            }

            menu("Edit") {
                item("Copy", "Shortcut+C")
                item("Paste", "Shortcut+V")
            }
        }

        center = tableview(controller.rows) {
            addClass(MainPanelStyle.tableClass)
        }

        bottom = hbox {
            addClass(MainPanelStyle.statusBarClass)

            label("Lines: ")
            label(controller.linesProperty)
        }
    }
}
