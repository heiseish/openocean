package mui.pages.cart

import mui.components.Footer
import mui.StyleBuilder
import org.scalablytyped.runtime.StringDictionary
import slinky.core.FunctionalComponent
import slinky.core.annotations.react
import slinky.core.facade.Fragment
import slinky.core.facade.Hooks._
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
import mui.trending.SimpleBarChart
import mui.trending.SimpleTable
import mui.components.CheckoutButton
import scala.io.Source

import typings.reactRouterDom.{components => router}

object Image extends js.Object

// https://v3.material-ui.com/getting-started/page-layout-examples/album/
// https://github.com/mui-org/material-ui/blob/v3.x/docs/src/pages/getting-started/page-layout-examples/album/Album.js
@react object Cart {

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
            .setMaxWidth(800)
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
        theme =>
          CSSProperties()
            .setHeight("100%")
            .setMargin("0 auto")
            .setPadding(10)
      )
      .add(
        "flexRow",
        theme =>
          CSSProperties()
            .setDisplay(flex)
            .setFlexGrow(1)
            .setMargin("0 auto")
            .setPadding(
              s"${theme.spacing.unit * 2}px 0 ${theme.spacing.unit * 1}px"
            )
            .setJustifyContent("space-between")
      )
      .add(
        "button",
        theme =>
          CSSProperties()
            .setDisplay(flex)
            .setFlexGrow(1)
            .setMargin("0 auto")
            .setPadding(45)
            .setJustifyContent("center")
      )
      .add(
        "flexColumn",
        theme =>
          CSSProperties()
            .setDisplay(flex)
            .setMargin("0 auto")
            .setPadding(
              s"${theme.spacing.unit * 2}px 0 ${theme.spacing.unit * 1}px"
            )
            .setFlexDirection(column)
            .setJustifyContent("space-between")
      )
      .add(
        "cardMedia",
        theme =>
          CSSProperties()
            .setHeight("100%")
            .setWidth(150)
        // .setPadding(20)
        // .setMargin(20)
      )
      .add(
        "cardContent",
        theme =>
          CSSProperties()
            .setWidth(600)
            .setMargin("0 auto")
            .setPadding(
              s"${theme.spacing.unit * 2}px 0 ${theme.spacing.unit * 1}px"
            )
      )
      .add(
        "footer",
        theme =>
          CSSProperties()
            .setBackgroundColor(theme.palette.background.paper)
            .setPadding(theme.spacing.unit * 6)
      )
      .hook

  case class Props(
      cartItems: Seq[NFTItem],
      removeItemFromCart: (NFTItem) => Unit
  )

  val component: FunctionalComponent[Props] = FunctionalComponent[Props] {
    (props: Props) =>
      val (cartItems, updateCartItems) = useState(props.cartItems)
      //   useEffect(
      //     () => { updateCartItems(props.cartItems) },
      //     Seq(props.cartItems)
      //   )

      def removeItemFromCart(item: NFTItem): Unit = {
        // HACK need to familiarize with slinky useEffect 
        // but doesnt seem to get it to work
        updateCartItems(cartItems diff Seq(item))
        props.removeItemFromCart(item)
      }
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
                  "Orders"
                ),
              Typography
                .variant(Style.h6)
                .align(strings.center)
                .color(strings.textSecondary)
                .paragraph(true)(
                  """You are very close to owning these NFTs right now!""".stripMargin
                )
            )
          },
          div(className := classes("area"))(
            Grid
              .container(true)
              .spacing(`16`)(
                cartItems.map { item =>
                  Grid
                    .item(true)
                    .withKey(item.Id.toString())
                    .sm(`4`)
                    .md(`4`)
                    .lg(`12`)(
                      Card.className(classes("card"))(
                        Grid
                          .container(true)
                          .spacing(`16`)(
                            Grid
                              .item(true)
                              .withKey("0")
                              .xs(`3`)(
                                CardMedia
                                  .className(classes("cardMedia"))
                                  .image(item.Image)
                                  .title(item.Id.toString())
                              ),
                            Grid
                              .item(true)
                              .withKey("1")
                              .xs(`6`)(
                                div(className := classes("flexColumn"))(
                                  Typography
                                    .gutterBottom(true)
                                    .variant(Style.h4)
                                    .component("h2")(item.Title),
                                  Typography.gutterBottom(true)(
                                    s"${item.Description.substring(0, 80)}..."
                                  ),
                                  Typography
                                    .color(Color.secondary)
                                    .variant(Style.h6)
                                    .component("body1")(s"${item.PriceSol} SOL")
                                )
                              ),
                            Grid
                              .item(true)
                              .withKey("2")
                              .xs(`3`)(
                                div(className := classes("button"))(
                                  Button
                                    .variant(strings.outlined)
                                    .color(Color.secondary)("Remove")
                                    .onClick(_ =>
                                      removeItemFromCart(item)
                                    )
                                )
                              )
                          )
                      )
                    )
                },
                Grid
                  .item(true)
                  .withKey("99999999")
                  .sm(`4`)
                  .md(`4`)
                  .lg(`12`)(
                    div(className := classes("button"))(
                        CheckoutButton()
                    )
                  )
              )
          )
        ),
        footer(className := classes("footer"))(
          Footer()
        )
      )
  }
}
