package visuals.panels.mainpanel

import javafx.geometry.Orientation
import javafx.scene.control.TableView
import tornadofx.*
import visuals.components.numberinput.numberInput

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

        left = form {
            addClass(MainPanelStyle.sidebarClass)

            fieldset("Lines", labelPosition = Orientation.VERTICAL) {
                field("Lower Bound") {
                    numberInput(minimumValue = 0, defaultValue = 0)
                }

                field("Upper Bound") {
                    numberInput(minimumValue = 0, defaultValue = 1000)
                }

                field {
                    button("Load")
                }
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
