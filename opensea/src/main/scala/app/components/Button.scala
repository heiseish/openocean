package mui.components

import org.scalablytyped.runtime.StringDictionary
import org.scalajs.dom
import slinky.core._
import slinky.core.annotations.react
import slinky.core.facade.Hooks
import slinky.web.ReactDOM
import slinky.web.html._
import typings.csstype.mod.{ColorProperty, NamedColor}
import typings.materialUiCore.createMuiThemeMod.{Theme, ThemeOptions}
import typings.materialUiCore.spacingMod.SpacingOptions
import typings.materialUiCore.{stylesMod, components => Mui}
import typings.materialUiStyles.components.ThemeProvider
import typings.materialUiIcons.{components => Icons}
import typings.std.global.window

import scala.scalajs.js

@react
object LoginButton {
  import typings.materialUiStyles.makeStylesMod.StylesHook
  import typings.materialUiStyles.mod.makeStyles
  import typings.materialUiStyles.withStylesMod.{
    CSSProperties,
    StyleRulesCallback,
    Styles,
    WithStylesOptions
  }

  type Props = Unit

  class StyleProps(val favouriteColor: ColorProperty) extends js.Object

  val useStyles: StylesHook[Styles[Theme, StyleProps, String]] = {
    val root: js.Function1[StyleProps, CSSProperties] = props =>
      CSSProperties()
        .setBackground("linear-gradient(45deg, #2196F3 30%, #21CBF3 90%)")
        .setBorder(0)
        .setBorderRadius(3)
        .setColor(props.favouriteColor)
        .setHeight(30)
        .setPadding("0 30px")
        .setTextDecoration("none")

    /* If you don't need direct access to theme, this could be `StyleRules[Props, String]` */
    val stylesCallback: StyleRulesCallback[Theme, StyleProps, String] = theme =>
      StringDictionary(
        "root" -> root,
        "outer" -> CSSProperties().setPadding(s"${theme.spacing.unit * 3}px")
      )

    makeStyles(stylesCallback, WithStylesOptions())
  }

  val component: FunctionalComponent[Props] = FunctionalComponent[Props] { _ =>
    val classes = useStyles(new StyleProps(NamedColor.green))
    div(
      className := classes("outer"),
      Mui.Button
        .className(classes("root"))
        ("LOGIN")
    )
  }
}

@react
object CheckoutButton {
  import typings.materialUiStyles.makeStylesMod.StylesHook
  import typings.materialUiStyles.mod.makeStyles
  import typings.materialUiStyles.withStylesMod.{
    CSSProperties,
    StyleRulesCallback,
    Styles,
    WithStylesOptions
  }

  type Props = Unit

  class StyleProps(val favouriteColor: ColorProperty) extends js.Object

  val useStyles: StylesHook[Styles[Theme, StyleProps, String]] = {
    val root: js.Function1[StyleProps, CSSProperties] = props =>
      CSSProperties()
        .setBackground("linear-gradient(45deg, #2196F3 30%, #21CBF3 90%)")
        .setBorder(0)
        .setColor(props.favouriteColor)
        .setWidth(300)
        .setBorderRadius(15)
        .setHeight(50)
        .setPadding("0 30px")
        .setTextDecoration("none")
    val text: js.Function1[StyleProps, CSSProperties] = props =>
      CSSProperties()
        .setColor("#FFFFFF")

    /* If you don't need direct access to theme, this could be `StyleRules[Props, String]` */
    val stylesCallback: StyleRulesCallback[Theme, StyleProps, String] = theme =>
      StringDictionary(
        "root" -> root,
        "text" -> text,
        "outer" -> CSSProperties().setPadding(s"${theme.spacing.unit * 3}px")
      )

    makeStyles(stylesCallback, WithStylesOptions())
  }

  val component: FunctionalComponent[Props] = FunctionalComponent[Props] { _ =>
    val classes = useStyles(new StyleProps(NamedColor.green))
    div(
      className := classes("outer"),
      Mui.Button
        .className(classes("root"))
        (
        Icons.ShoppingCart(),
        Mui.Typography.className(classes("text"))("CHECK OUT ITEMS")
        )
        .onClick(_ =>
          window.open("https://checkout.stripe.com/pay/ppage_1Kb3HXLI8H7tqkr6RnIj2QAO#fidkdWxOYHwnPyd1blpxYHZxWjA0Tk5wfU1JTD1NMnF0bnczNUkzcUw9aEt3S0FhYnc8b1FIMzJfUjZyT2pTVXRETE88S1NtTHJzYmBIQ1xgM3M8MzZfQ1wzcld1MHZDQE5JMG9ETXBrVkBkNTVSRGE0XUx8PCcpJ2hsYXYnP34nYnBsYSc%2FJzc1MWNkPDNmKD00PWcoMWY1MChnZjBnKDc9MDczYzMzNDUzNTVgZjUxNScpJ2hwbGEnPyc0MDVkZDQ8ZihkMjEzKDFjMjEoZGc1ZigzZ2E8MWM1ZmRnMzNmPGEzY2YnKSd2bGEnPyc8N2E8MjY8MChnMjUzKDFjMjwoZzQxYCgxNDBmPGcyNGM0NjUxMDUyNDYneCknZ2BxZHYnP15YKSdpZHxqcHFRfHVgJz8naHBpcWxabHFgaCcpJ3dgY2B3d2B3SndsYmxrJz8nbXFxdT8qKmlqZmRpbWp2cT82NTU1JyknaWpmZGlgJz9rcGlpeCUl")
        )
    )
  }
}
