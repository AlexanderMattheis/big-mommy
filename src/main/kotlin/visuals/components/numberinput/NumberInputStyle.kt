package visuals.components.numberinput

import tornadofx.*

class NumberInputStyle : Stylesheet() {
    companion object {
        val numberInputButtonClass by cssclass()
        val numberInputClass by cssclass()
    }

    init {
        // CLASSES
        numberInputButtonClass {
            backgroundRadius = multi(box(0.percent))
            fontSize = 6.px
            minWidth = 16.px
        }

        numberInputClass {
            backgroundRadius = multi(box(0.percent))
        }
    }
}