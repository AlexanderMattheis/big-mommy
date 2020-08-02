package app.panels.main_panel

import javafx.scene.paint.Color
import javafx.scene.paint.CycleMethod
import javafx.scene.paint.LinearGradient
import javafx.scene.paint.Stop
import tornadofx.*

class MainPanelStyle : Stylesheet() {

    companion object {
        val statusBarClass by cssclass()
    }

    init {
        val border = Dimension(0.0, Dimension.LinearUnits.px)
        val borderTop = Dimension(1.0, Dimension.LinearUnits.px)

        val paddingTop = Dimension(3.0, Dimension.LinearUnits.px)
        val paddingRight = Dimension(17.0, Dimension.LinearUnits.px)
        val paddingBottom = Dimension(3.0, Dimension.LinearUnits.px)
        val paddingLeft = Dimension(17.0, Dimension.LinearUnits.px)

        // ELEMENTS
        menuBar {
            minWidth = 1024.px
        }

        // CLASSES
        statusBarClass {
            backgroundColor += LinearGradient(0.0, 0.0, 0.0, 25.0,
                false, CycleMethod.REFLECT,
                Stop(0.0, Color.WHITE),
                Stop(25.0, Color.LIGHTGRAY)
            )
            borderInsets += box(borderTop, border, border, border)
            borderColor += box(c("#B5B5B5"))

            minHeight = 25.px
            padding = box(paddingTop, paddingRight, paddingBottom, paddingLeft)
        }
    }
}