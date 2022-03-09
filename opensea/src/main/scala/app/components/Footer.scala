package mui.components

import mui.StyleBuilder
import slinky.core.FunctionalComponent
import slinky.core.annotations.react
import slinky.web.html._
import typings.classnames.mod.^.{default => classNames}
import typings.materialUiCore.anon.{
  PartialClassNameMapDrawer,
}

import typings.react.mod

import typings.materialUiCore.components._
import typings.materialUiCore.createMuiThemeMod.Theme
import typings.materialUiStyles.makeStylesMod.StylesHook
import typings.materialUiStyles.withStylesMod.{CSSProperties, Styles}
import typings.materialUiCore.materialUiCoreStrings.{absolute, permanent}
import typings.materialUiCore.mod.PropTypes.Color
import typings.materialUiCore.typographyTypographyMod.Style
import typings.materialUiIcons.{components => Icons}
import typings.materialUiCore.{materialUiCoreStrings => strings}

import typings.reactRouter.mod.RouteProps
import typings.reactRouterDom.{components => router}

import scala.scalajs.js

@react object Footer {
   lazy val styles: StylesHook[Styles[Theme, js.Object, String]] =
    StyleBuilder[Theme, js.Object]
      .add(
        "footer",
        theme =>
          CSSProperties()
            .setBackgroundColor(theme.palette.background.paper)
            .setPadding(theme.spacing.unit * 6)
      )
      .hook

  val classes = styles(js.undefined)

  type Props = Unit

  val component: FunctionalComponent[Props] = FunctionalComponent[Props] {
    case () =>
      footer(className := classes("footer"))(
        Typography
          .variant(Style.h6)
          .align(strings.center)
          .gutterBottom(true)(
            "© 2014-2022 OpenOcean. All rights reserved."
          ),
        Typography
          .variant(Style.subtitle1)
          .align(strings.center)
          .color(strings.textSecondary)
          .component("p")(
            "Built with ",
          span(role := "img", aria - "label" := "Love")("❤️"),
          " by the ",
          Link.color(Color.inherit)("IT5100A OpenOcean"),
          " team."
          )
      )
  }
}