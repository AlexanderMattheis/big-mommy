package app.panels.main_panel

import tornadofx.*

class MainPanelView : View("Big Mommy") {
    private val controller: MainPanelController by inject()

    override val root = borderpane {
        importStylesheet(MainPanelStyle::class)

        top = menubar {
            menu("File") {
                item("Open", "Shortcut+O").action { controller.openFileDialog(super.currentWindow) }
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
