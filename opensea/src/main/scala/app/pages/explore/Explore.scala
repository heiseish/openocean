package mui.pages.explore

import mui.StyleBuilder
import mui.layout.MainLayout
import mui.components.Footer
import org.scalablytyped.runtime.StringDictionary
import slinky.core.FunctionalComponent
import slinky.core.annotations.react
import slinky.core.facade.Hooks
import slinky.core.facade.Fragment
import slinky.web.html._

import typings.react.mod

import typings.classnames.mod.^.{default => classNames}
import typings.csstype.csstypeStrings.{auto, column, flex, relative}
import typings.materialUiCore.components._
import typings.materialUiCore.createMuiThemeMod.Theme
import typings.materialUiCore.materialUiCoreNumbers._
import typings.materialUiCore.mod.PropTypes.Color
import typings.materialUiCore.typographyTypographyMod.Style
import typings.materialUiCore.{materialUiCoreStrings => strings}
import typings.materialUiIcons.{components => Icons}
import typings.materialUiStyles.makeStylesMod.StylesHook
import typings.materialUiStyles.withStylesMod.{CSSProperties, Styles}
import typings.materialUiCore.materialUiCoreStrings.secondary

import model.NFTItem
import mock.SampleData

import typings.reactRouter.mod.RouteProps
import typings.reactRouterDom.{components => router}

import scala.scalajs.js

@react object Explore {
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
            .setMaxWidth(600)
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
      .add("cardMedia", CSSProperties().setPaddingTop("56.25%")) // 16:9
      .add("cardContent", CSSProperties().setFlexGrow(1))
      .add("routerWithoutLink", CSSProperties().setTextDecoration("none"))
      .hook


  val items = new SampleData().getSampleProducts()
  case class Props(allItems: Seq[NFTItem], addItemToCart: (NFTItem) => Unit, setSelectedItem: (NFTItem) => Unit)

  val component: FunctionalComponent[Props] = FunctionalComponent[Props] { (props: Props) =>
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
        div(className := classes("heroUnit"))(
          div(className := classes("heroContent"))(
            Typography
              .variant(Style.h2)
              .align(strings.center)
              .color(strings.textSecondary)
              .gutterBottom(true)
              .component("h1")(
                "Explore collections"
              ),
            Typography
              .variant(Style.h6)
              .align(strings.center)
              .color(strings.textSecondary)
              .paragraph(true)(
                """Discover, collect, and sell extraordinary NFTS on Open Ocean. The world's first 
                and largest NFT marketplace.""".stripMargin
              ),
            div(className := classes("heroButtons"))(
              Grid
                .container(true)
                .spacing(`16`)
                .justify(strings.center)(
                  Grid.item(true)(
                    router.Link[String](to = "/trending").style(mod.CSSProperties().setTextDecoration("none"))(
                      Button
                        .variant(strings.contained)
                        .size(strings.large)
                        .color(Color.primary)("Latest NFT trends")
                    )
                  ),
                  Grid.item(true)(
                    // Tooltip(
                      Button
                        .variant(strings.outlined)
                        .color(Color.primary)
                        .size(strings.large)
                        .disabled(true)("Create")
                    // ).placement(strings.top)("Coming Soon"),
                  )
                )
            )
          )
        ),
        div(
          className := classNames(
            StringDictionary[js.Any](
              classes("layout") -> true,
              classes("cardGrid") -> true
            )
          )
        )(
          Grid
            .container(true)
            .spacing(`16`)(props.allItems.map { item =>
              Grid
                .item(true)
                .withKey(item.Id.toString())
                .sm(`4`)
                .md(`4`)
                .lg(`4`)(
                  Card.className(classes("card"))(
                    CardMedia
                      .className(classes("cardMedia"))
                      .image(item.Image)
                      .title(item.Id.toString()),
                    CardContent
                      .className("cardContent")(
                        Typography
                          .gutterBottom(true)
                          .variant(Style.h4)
                          .component("h2")(item.Title),
                        Grid
                          .container(true)(
                            Typography
                              .gutterBottom(true)
                              .color(strings.textSecondary)
                              .variant(Style.h6)
                              .component("h2")("by\u00A0\u00A0"),
                            Typography
                              .gutterBottom(true)
                              .variant(Style.h6)
                              .component("h2")(item.Author)),
                        Typography.gutterBottom(true)(
                          s"${item.Description.substring(0, 80)}..."
                        ),
                        Typography
                          .color(secondary)
                          .variant(Style.h6)
                          .component("body1")(s"${item.PriceSol} SOL")
                      ),
                    CardActions(
                      router.Link[String](to = "/items/"+s"${item.Id}").style(mod.CSSProperties().setTextDecoration("none"))(
                        Button.size(strings.small)
                          .variant(strings.contained)
                          .color(Color.primary)("View")
                          .onClick(_ => props.setSelectedItem(item))
                      ),
                      router.Link[String](to = "/orders").style(mod.CSSProperties().setTextDecoration("none"))(
                        Button.size(strings.small)
                          .variant(strings.outlined)
                          .color(Color.primary)("Add to Cart")
                          .onClick(_ => props.addItemToCart(item))
                      )
                    )
                  )
                )
            })
        )
      ),
      Footer()
    )
  }
}
