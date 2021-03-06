package visuals.components.numberinput

import javafx.scene.layout.BorderPane
import tornadofx.*

class NumberInput(minimumValue: Int? = null, defaultValue: Int? = 0, maximumValue: Int? = null) : BorderPane() {
    private val controller = NumberInputController(minimumValue, defaultValue, maximumValue)

    init {
        importStylesheet(NumberInputStyle::class)

        center = textfield(controller.valueProperty) {
            filterInput { controller.filter(it) }
            addClass(NumberInputStyle.numberInputClass)
        }

        right = vbox {
            button("\u25B2") {
                addClass(NumberInputStyle.numberInputButtonClass)
            }.action { controller.increase() }

            button("\u25BC") {
                addClass(NumberInputStyle.numberInputButtonClass)
            }.action { controller.decrease() }
        }
    }

    fun getValue(): Int {
        return controller.getValue()
    }
}