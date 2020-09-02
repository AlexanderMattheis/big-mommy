package visuals.panels.mainpanel

import javafx.geometry.Orientation
import javafx.scene.control.TableView
import tornadofx.*
import visuals.components.numberinput.NumberInput

typealias Row = Map<String, String>

class MainPanelView : View("Big Mommy") {
    private val controller: MainPanelController by inject()

    private val table: TableView<Row> = TableView<Row>().apply {
        addClass(MainPanelStyle.tableClass)
    }

    private val minimumNumberInput: NumberInput = NumberInput(minimumValue = 0, defaultValue = 0)
    private val maximumNumberInput: NumberInput = NumberInput(minimumValue = 0, defaultValue = 1000)

    override val root = borderpane {
        importStylesheet(MainPanelStyle::class)

        top = menubar {
            menu("File") {
                item("Open", "Shortcut+O").action {
                    val firstLine = minimumNumberInput.getValue()
                    val lastLine = maximumNumberInput.getValue()
                    controller.openLoadingDialog(super.currentWindow, table, firstLine, lastLine)
                }
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
                    this += minimumNumberInput
                }

                field("Upper Bound") {
                    this += maximumNumberInput
                }

                field("Column Separator") {
                    textfield(",")
                }

                field {
                    button("Load").action {
                        val firstLine = minimumNumberInput.getValue()
                        val lastLine = maximumNumberInput.getValue()
                        controller.loadFileInto(table, firstLine, lastLine)
                    }
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
