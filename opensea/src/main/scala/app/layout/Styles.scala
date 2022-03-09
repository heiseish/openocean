package mui.layout

import mui.StyleBuilder
import org.scalablytyped.runtime.StringDictionary
import slinky.core.FunctionalComponent
import slinky.core.annotations.react
import slinky.core.facade.Hooks
import slinky.web.html._
import typings.classnames.mod.^.{default => classNames}
import typings.csstype.csstypeStrings._
import typings.csstype.mod.OverflowXProperty
import typings.materialUiCore.anon.{PartialClassNameMapDrawer, Partialdurationnumberstri}
import typings.materialUiCore.components._
import typings.materialUiCore.createBreakpointsMod.Breakpoint
import typings.materialUiCore.createMuiThemeMod.Theme
import typings.materialUiCore.materialUiCoreStrings.{absolute, permanent}
import typings.materialUiCore.mod.PropTypes
import typings.materialUiCore.typographyTypographyMod.Style
import typings.materialUiIcons.{components => Icons}
import typings.materialUiStyles.makeStylesMod.StylesHook
import typings.materialUiStyles.withStylesMod.{CSSProperties, Styles}

import scala.scalajs.js

object LayoutStyles {
    val drawerWidth = 240

    lazy val styles: StylesHook[Styles[Theme, js.Object, String]] =
        StyleBuilder[Theme, js.Object]
        .add("root", CSSProperties().setDisplay(flex))
        .add("toolbar", CSSProperties().setPaddingRight(24)) // keep right padding when drawer closed
        .add(
            "toolbarIcon",
            theme =>
            CSSProperties()
                .setDisplay(flex)
                .setAlignItems(center)
                .setJustifyContent(`flex-end`)
                .setPadding("0 8px")
                .combineWith(theme.mixins.toolbar)
        )
        .add(
            "appBar",
            theme =>
            CSSProperties()
                .setZIndex(theme.zIndex.drawer + 1)
                .setTransition(
                theme.transitions.create(
                    js.Array("width", "margin"),
                    Partialdurationnumberstri()
                    .setEasing(theme.transitions.easing.sharp)
                    .setDuration(theme.transitions.duration.enteringScreen)
                )
            )
        )
        .add(
            "appBarShift",
            theme =>
            CSSProperties()
                .setMarginLeft(drawerWidth)
                .setWidth(s"calc(100% - ${drawerWidth}px)")
                .setTransition(
                theme.transitions.create(
                    js.Array("width", "margin"),
                    Partialdurationnumberstri()
                    .setEasing(theme.transitions.easing.sharp)
                    .setDuration(theme.transitions.duration.enteringScreen)
                )
            )
        )
        .add("menuButton", CSSProperties().setMarginLeft(12).setMarginRight(36))
        .add("menuButtonHidden", CSSProperties().setDisplay(none))
        .add("title", CSSProperties().setFlexGrow(1))
        .add(
            "drawerPaper",
            theme =>
            CSSProperties()
                .setPosition(relative)
                .setWhiteSpace(nowrap)
                .setWidth(drawerWidth)
                .setTransition(
                theme.transitions.create(
                    "width",
                    Partialdurationnumberstri()
                    .setEasing(theme.transitions.easing.sharp)
                    .setDuration(theme.transitions.duration.enteringScreen)
                )
            )
        )
        .add(
            "drawerPaperClose",
            theme =>
            CSSProperties()
                .setOverflowX(OverflowXProperty.hidden)
                .setTransition(
                theme.transitions.create(
                    "width",
                    Partialdurationnumberstri()
                    .setEasing(theme.transitions.easing.sharp)
                    .setDuration(theme.transitions.duration.leavingScreen)
                )
                )
                .setWidth(theme.spacing.unit * 7)
                .set(theme.breakpoints.up(Breakpoint.sm), CSSProperties().setWidth(theme.spacing.unit * 9))
        )
        .add("appBarSpacer", theme => CSSProperties().combineWith(theme.mixins.toolbar))
        .add(
            "content",
            theme =>
            CSSProperties()
                .setFlexGrow(1)
                .setPadding(theme.spacing.unit * 3)
                .setHeight("100vh")
                .setOverflow(auto)
        )
        .add("chartContainer", CSSProperties().setMarginLeft(-22))
        .add("tableContainer", CSSProperties().setHeight(320))
        .add("h5", theme => CSSProperties().setMarginBottom(theme.spacing.unit * 2))
        .add("disableButton", CSSProperties().setPointerEvents(none))
        .hook
}
