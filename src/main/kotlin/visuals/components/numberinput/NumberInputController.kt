package visuals.components.numberinput

import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.TextFormatter
import system.defaults.Internal
import tornadofx.Controller
import tornadofx.isInt

class NumberInputController(private val minimumValue: Int?, defaultValue: Int?, private val maximumValue: Int?) :
    Controller() {
    val valueProperty = SimpleStringProperty(defaultValue?.toString() ?: "0")

    fun increase() {
        if (valueProperty.value.isEmpty() || valueProperty.value == "-") {
            valueProperty.value = "-1"
        }

        val newValue = valueProperty.value.toInt() + 1

        if (maximumValue != null && newValue > maximumValue) {
            valueProperty.value = maximumValue.toString()
        } else {
            valueProperty.value = newValue.toString()
        }
    }

    fun decrease() {
        if (valueProperty.value.isEmpty() || valueProperty.value == "-") {
            valueProperty.value = "-1"
        }

        val newValue = valueProperty.value.toInt() - 1

        if (minimumValue != null && newValue < minimumValue) {
            valueProperty.value = minimumValue.toString()
        } else {
            valueProperty.value = newValue.toString()
        }
    }

    fun getValue(): Int {
        if (valueProperty.value.isEmpty() || valueProperty.value == "-") {
            valueProperty.value = "0"
        }

        return valueProperty.value.toInt()
    }

    fun filter(textChange: TextFormatter.Change): Boolean {
        return textChange.controlNewText.length < Internal.MAXIMUM_NUMBER_INPUT_LENGTH
                && textChange.controlNewText.matches(Regex("0|-|(-?[1-9]+[0-9]*)"))
                &&
                if (textChange.controlNewText.isInt()) {
                    when {
                        minimumValue != null -> textChange.controlNewText.toInt() >= minimumValue
                        maximumValue != null -> textChange.controlNewText.toInt() <= maximumValue
                        else -> true
                    }
                } else
                    true
    }
}
