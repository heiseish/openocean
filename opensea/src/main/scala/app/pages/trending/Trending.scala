package mui.pages.trending

import mui.components.Footer
import mui.StyleBuilder
import org.scalablytyped.runtime.StringDictionary
import slinky.core.FunctionalComponent
import slinky.core.annotations.react
import slinky.core.facade.Fragment
import slinky.web.html._
import typings.classnames.mod.^.{default => classNames}
import typings.csstype.csstypeStrings.{auto, column, row, flex, relative}
import typings.materialUiCore.components._
import typings.materialUiCore.createMuiThemeMod.Theme
import typings.materialUiCore.materialUiCoreNumbers._
import typings.materialUiCore.mod.PropTypes.Color
import typings.materialUiCore.typographyTypographyMod.Style
import typings.materialUiCore.{materialUiCoreStrings => strings}
import typings.materialUiIcons.{components => Icons}
import typings.materialUiStyles.makeStylesMod.StylesHook
import typings.materialUiStyles.withStylesMod.{CSSProperties, Styles}
import model.NFTItem
import mock.SampleData
import scala.scalajs.js.annotation.{JSExportTopLevel, JSImport}
import scala.scalajs.js
import mui.trending.SimpleLineChart
import mui.trending.SimpleBarChart
import mui.trending.SimpleTable
import scala.io.Source

object Image extends js.Object

// https://v3.material-ui.com/getting-started/page-layout-examples/album/
// https://github.com/mui-org/material-ui/blob/v3.x/docs/src/pages/getting-started/page-layout-examples/album/Album.js
@react object Trending {

  lazy val styles: StylesHook[Styles[Theme, js.Object, String]] =
    StyleBuilder[Theme, js.Object]
      .add("appBar", CSSProperties().setPosition(relative))
      .add(
        "icon",
        theme => CSSProperties().setMarginRight(theme.spacing.unit * 2)
      )
      .add(
        "heroUnit",
        theme =>
          CSSProperties().setBackgroundColor(theme.palette.background.paper)
      )
      .add(
        "heroContent",
        theme =>
          CSSProperties()
            .setMaxWidth(800)
            .setMargin("0 auto")
            .setPadding(
              s"${theme.spacing.unit * 8}px 0 ${theme.spacing.unit * 6}px"
            )
      )
      .add(
        "heroButtons",
        theme => CSSProperties().setMarginTop(theme.spacing.unit * 4)
      )
      .add(
        "area",
        theme =>
          CSSProperties()
            .setAlignContent("center")
            .setMaxWidth(1100)
            .setMargin("0 auto")
            .setPadding(
              s"${theme.spacing.unit * 2}px 0 ${theme.spacing.unit * 1}px"
            )
      )
      .add(
        "layout",
        theme =>
          CSSProperties()
            .setWidth("auto")
            .setMarginLeft(theme.spacing.unit * 3)
            .setMarginRight(theme.spacing.unit * 3)
            .set(
              theme.breakpoints.up(1100 + theme.spacing.unit * 3 * 2),
              CSSProperties()
                .setWidth(1100)
                .setMarginLeft(auto.asInstanceOf[String])
                .setMarginRight(auto.asInstanceOf[String])
            )
      )
      .add(
        "avatar",
        theme =>
          CSSProperties()
            .setMargin(theme.spacing.unit)
            .setHeight(300)
            .setWidth(300)
            .setBackgroundColor(theme.palette.secondary.main)
      )
      .add(
        "cardGrid",
        theme => CSSProperties().setPadding(s"${theme.spacing.unit * 8}px 0")
      )
      .add(
        "card",
        CSSProperties()
          .setHeight("100%")
          .setDisplay(flex)
          .setFlexDirection(column)
      )
      .add(
        "flexRow",
        CSSProperties()
          .setDisplay(flex)
          .setMargin("0 auto")
          .setPadding(10)
          .setFlexDirection(row)
      )
      .add(
        "flexColumn",
        CSSProperties()
          .setDisplay(flex)
          .setMargin("0 auto")
          .setPadding(10)
          .setFlexDirection(column)
      )
      .add("cardMedia", CSSProperties().setPaddingTop("56.25%")) // 16:9
      .add("cardContent", CSSProperties().setFlexGrow(1))
      .add(
        "footer",
        theme =>
          CSSProperties()
            .setBackgroundColor(theme.palette.background.paper)
            .setPadding(theme.spacing.unit * 6)
      )
      .hook

  val items = new SampleData().getSampleProducts()
  type Props = Unit

  val component: FunctionalComponent[Unit] = FunctionalComponent[Unit] { _ =>
    val classes = styles(js.undefined)

    Fragment(
      CssBaseline(),
      AppBar
        .position(strings.static)
        .className(classes("appBar"))(
          Toolbar(
            Icons.PhotoCamera.className(classes("icon"))(),
            Typography.variant(Style.h6).color(Color.inherit)("Album layout")
          )
        ),
      main(
        div(className := classes("heroUnit")) {
          div(className := classes("heroContent"))(
            Typography
              .variant(Style.h2)
              .align(strings.center)
              .color(strings.textSecondary)
              .gutterBottom(true)
              .component("h1")(
                "Trending NFTs"
              ),
            Typography
              .variant(Style.h6)
              .align(strings.center)
              .color(strings.textSecondary)
              .paragraph(true)(
                """Most popular NFT in the marketplace right now!""".stripMargin
              )
          )
        },
        div(className := classes("area"))(
          Grid
            .container(true)
            .spacing(`16`)(
              Grid
                .item(true)
                .withKey("0")
                .xs(`3`)(
                  Card.className(classes("card"))(
                    CardContent(
                      div(className := classes("flexRow"))(
                        Avatar.className(classes("avatar"))(Icons.TableChart()),
                        div(className := classes("flexColumn"))(
                          Typography
                            .variant(Style.h5)
                            .align(strings.left)
                            .color(strings.textSecondary)
                            .gutterBottom(true)
                            .component("h5")(
                              "Trade Volume"
                            ),
                          Typography
                            .variant(Style.subheading)
                            .align(strings.left)
                            .color(strings.textSecondary)
                            .gutterBottom(true)
                            .component("h4")(
                              "524.4k USD"
                            )
                        )
                      )
                    )
                  )
                ),
              Grid
                .item(true)
                .withKey("1")
                .xs(`3`)(
                  Card.className(classes("card"))(
                    CardContent(
                      div(className := classes("flexRow"))(
                        Avatar
                          .className(classes("avatar"))(Icons.MonetizationOn()),
                        div(className := classes("flexColumn"))(
                          Typography
                            .variant(Style.h5)
                            .align(strings.left)
                            .color(strings.textSecondary)
                            .gutterBottom(true)
                            .component("h5")(
                              "Top purchase"
                            ),
                          Typography
                            .variant(Style.subheading)
                            .align(strings.left)
                            .color(strings.textSecondary)
                            .gutterBottom(true)
                            .component("h4")(
                              "$21245.2"
                            )
                        )
                      )
                    )
                  )
                ),
              Grid
                .item(true)
                .withKey("0")
                .xs(`3`)(
                  Card.className(classes("card"))(
                    CardContent(
                      div(className := classes("flexRow"))(
                        Avatar
                          .className(classes("avatar"))(Icons.Loyalty()),
                        div(className := classes("flexColumn"))(
                          Typography
                            .variant(Style.h5)
                            .align(strings.left)
                            .color(strings.textSecondary)
                            .gutterBottom(true)
                            .component("h5")(
                              "NFTs sold"
                            ),
                          Typography
                            .variant(Style.subheading)
                            .align(strings.left)
                            .color(strings.textSecondary)
                            .gutterBottom(true)
                            .component("h4")(
                              "321"
                            )
                        )
                      )
                    )
                  )
                ),
              Grid
                .item(true)
                .withKey("0")
                .xs(`3`)(
                  Card.className(classes("card"))(
                    CardContent(
                      div(className := classes("flexRow"))(
                        Avatar.className(classes("avatar"))(Icons.People()),
                        div(className := classes("flexColumn"))(
                          Typography
                            .variant(Style.h5)
                            .align(strings.left)
                            .color(strings.textSecondary)
                            .gutterBottom(true)
                            .component("h5")(
                              "User active"
                            ),
                          Typography
                            .variant(Style.subheading)
                            .align(strings.left)
                            .color(strings.textSecondary)
                            .gutterBottom(true)
                            .component("h4")(
                              "23,123"
                            )
                        )
                      )
                    )
                  )
                )
            )
        ),
        div(className := classes("area"))(
          Grid
            .container(true)
            .spacing(`16`)(
              Grid
                .item(true)
                .withKey("0")
                .xs(`8`)(
                  Card.className(classes("card"))(
                    CardContent(
                      Typography
                        .variant(Style.title)
                        .align(strings.left)
                        .color(strings.textSecondary)
                        .gutterBottom(true)
                        .component("h4")(
                          "Reports"
                        ),
                      SimpleLineChart()
                    )
                  )
                ),
              Grid
                .item(true)
                .withKey("1")
                .xs(`4`)(
                  Card.className(classes("card"))(
                    CardContent(
                      Typography
                        .variant(Style.title)
                        .align(strings.left)
                        .color(strings.textSecondary)
                        .gutterBottom(true)
                        .component("h4")(
                          "Visits"
                        ),
                      SimpleBarChart()
                    )
                  )
                )
            )
        ),
        div(className := classes("area"))(
          Typography
            .variant(Style.title)
            .align(strings.left)
            .color(strings.textSecondary)
            .gutterBottom(true)
            .component("h4")(
              "Top NFTs"
            ),
          SimpleTable()
        )
      ),
      footer(className := classes("footer"))(
        Footer()
      )
    )
  }
}
