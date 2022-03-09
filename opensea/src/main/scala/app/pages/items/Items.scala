package mui.pages.items

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
import mui.pages.items.SimpleTable
import mui.components.CheckoutButton
import scala.io.Source
import typings.react.mod
import typings.reactRouter.mod.RouteProps
import typings.reactRouterDom.{components => router}

import scala.scalajs.LinkingInfo

object Image extends js.Object

// https://v3.material-ui.com/getting-started/page-layout-examples/album/
// https://github.com/mui-org/material-ui/blob/v3.x/docs/src/pages/getting-started/page-layout-examples/album/Album.js
@react object Items {

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
            .setMaxWidth(1100)
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
            .setHeight(400)
            .setWidth(500)
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
      item: NFTItem,
      addItemToCart: NFTItem => Unit
  )

  val component: FunctionalComponent[Props] = FunctionalComponent[Props] {
    (props: Props) =>
    //   val (item) = useState(props.item)
      //   useEffect(
      //     () => { updateCartItems(props.cartItems) },
      //     Seq(props.cartItems)
      //   )

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
              
                Grid.item(true)
                (
                    router.Link[String](to = "/explore").style(mod.CSSProperties().setTextDecoration("none"))(
                      Button
                        .variant(strings.contained)
                        .size(strings.large)
                        .color(Color.primary)("Back to Explore")
                    )
                  ),
            )
          },
          div(className := classes("area"))(
            Grid
              .container(true)
              .spacing(`16`)(
                  Grid
                    .item(true)
                    .withKey(props.item.Id.toString())
                    (
                      Card.className(classes("card"))(
                        Grid
                          .container(true)
                          .spacing(`16`)(
                            Grid
                              .item(true)
                              .withKey("0")
                              .xs(`6`)(
                                CardMedia
                                  .className(classes("cardMedia"))
                                  .image("../"+props.item.Image)
                                  .title(props.item.Id.toString())
                              ),
                            Grid
                              .item(true)
                              .withKey("1")
                              .xs(`6`)(
                                div(className := classes("flexColumn"))(
                                Typography
                                    .gutterBottom(true)
                                    .variant(Style.h4)
                                    .component("h2")(props.item.Title),
                                Grid
                                .container(true)(
                                    Typography
                                    .gutterBottom(true)
                                    .color(strings.textSecondary)
                                    .variant(Style.h5)
                                    .component("h2")("by\u00A0\u00A0"),
                                    Typography
                                    .gutterBottom(true)
                                    .variant(Style.h5)
                                    .component("h2")(props.item.Author),
                                    Typography
                                        .color(Color.secondary)
                                        .variant(Style.h5)
                                        .component("body1")("\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0"),
                                  Typography
                                    // .variant(Style.subtitle1)
                                    .color(strings.textSecondary)
                                    .align(strings.right)
                                    .variant(Style.h5)
                                    .component("h2")(
                                    span(role := "img", aria - "label" := "Love")("❤️"),
                                    s"${props.item.Favourites}"
                                    )
                                ),
                                  Typography.gutterBottom(true)
                                  .variant(Style.h6)(
                                    s"${props.item.Description}"
                                  ),
                                  Grid
                                    .container(true)
                                    (   
                                        Typography
                                        .variant(Style.h5)
                                        .color(strings.textSecondary)
                                        .component("body1")("Price \u00A0\u00A0\u00A0\u00A0")
                                        ,
                                        Typography
                                        .color(Color.secondary)
                                        .variant(Style.h5)
                                        .component("body1")(s"${props.item.PriceSol} SOL")
                                        ,
                                        Typography
                                        .color(Color.secondary)
                                        .variant(Style.h5)
                                        .component("body1")("\u00A0\u00A0")
                                        ,
                                        Typography
                                        .variant(Style.h6)
                                        .component("body1")(s"($$${props.item.PriceDollar})")
                                    
                                    ),
                                    CardActions(
                                        router.Link[String](to = "/orders").style(mod.CSSProperties().setTextDecoration("none"))(
                                            Button.size(strings.large)
                                            .variant(strings.contained)
                                            .color(Color.primary)("Add to Cart")
                                            .onClick(_ => props.addItemToCart(props.item))
                      )),
                               
                                  )
                              )
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
              "NFTs from this creator"
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
