package visuals.components.numberinput

import javafx.event.EventTarget
import javafx.scene.layout.BorderPane
import tornadofx.*

class NumberInput(minimumValue: Int?, defaultValue: Int, maximumValue: Int?) : BorderPane() {
    private val controller = NumberInputController(minimumValue, defaultValue, maximumValue)

    init {
        importStylesheet(NumberInputStyle::class)

        center = textfield(controller.valueProperty) {
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
}

fun EventTarget.numberInput(
    minimumValue: Int? = null,
    defaultValue: Int = 0,
    maxiumumValue: Int? = null,
    op: NumberInput.() -> Unit = {}
) = opcr(this, NumberInput(minimumValue, defaultValue, maxiumumValue), op)