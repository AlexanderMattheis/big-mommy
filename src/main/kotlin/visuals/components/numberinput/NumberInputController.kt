package visuals.components.numberinput

import javafx.beans.property.SimpleStringProperty
import tornadofx.Controller

class NumberInputController(private val minimumValue: Int?, private val defaultValue: Int, private val maximumValue: Int?) : Controller() {
    val valueProperty = SimpleStringProperty(defaultValue.toString())

    fun increase() {
        if (valueProperty.value.isEmpty()) {
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
        if (valueProperty.value.isEmpty()) {
            valueProperty.value = "-1"
        }

        val newValue = valueProperty.value.toInt() - 1

        if (minimumValue != null && newValue < minimumValue) {
            valueProperty.value = minimumValue.toString()
        } else {
            valueProperty.value = newValue.toString()
        }
    }
}