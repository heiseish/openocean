package mui.layout

import mui.StyleBuilder
import mui.layout.LayoutStyles.styles
import mui.components.{LoginButton}
import org.scalablytyped.runtime.StringDictionary
import slinky.core.FunctionalComponent
import slinky.core.annotations.react
import slinky.core.facade.Hooks
import slinky.web.html._
import typings.classnames.mod.^.{default => classNames}
import typings.materialUiCore.anon.{
  PartialClassNameMapDrawer,
}

import typings.react.mod

import typings.materialUiCore.components._
import typings.materialUiCore.materialUiCoreStrings.{absolute, permanent}
import typings.materialUiCore.mod.PropTypes
import typings.materialUiCore.typographyTypographyMod.Style
import typings.materialUiIcons.{components => Icons}

import typings.reactRouter.mod.RouteProps
import typings.reactRouterDom.{components => router}

import scala.scalajs.js

@react object MainLayout {
  type Props = Unit

  val component: FunctionalComponent[Props] = FunctionalComponent[Props] {
    case () =>
      val classes = styles(js.undefined)
      val (isOpen, setIsOpen) = Hooks.useState(false)
      def toggleDrawer(): Unit = setIsOpen(!isOpen)

      div(
        className := classes("root"),
        CssBaseline(),
        AppBar
          .position(absolute)
          .className(
            classNames(
              StringDictionary[js.Any](
                classes("appBar") -> true,
                classes("appBarShift") -> isOpen
              )
            )
          )(
            Toolbar
              .disableGutters(!isOpen)
              .className(classes("toolbar"))(
                IconButton
                  .color(PropTypes.Color.inherit)
                  .`aria-label`("Toggle drawer")
                  .onClick(_ => toggleDrawer())
                  .className(
                    classNames(
                      StringDictionary[js.Any](
                        classes("menuButton") -> true,
                        classes("menuButtonHidden") -> isOpen
                      )
                    )
                  )(Icons.Menu()),
                Typography()
                  .component("h1")
                  .variant(Style.h6)
                  .color(PropTypes.Color.inherit)
                  .noWrap(true)
                  .className(classes("title"))(
                    ""
                  ),
                router.Link[String](to = "/Login").style(mod.CSSProperties().setTextDecoration("none"))(
                  LoginButton()
                ),
              )
          ),
        Drawer
          .variant(permanent)
          .classes(
            PartialClassNameMapDrawer().setPaper(
              classNames(
                StringDictionary[js.Any](
                  classes("drawerPaper") -> true,
                  classes("drawerPaperClose") -> !isOpen
                )
              )
            )
          )
          .open(isOpen)(
            div(className := classes("toolbarIcon"))(
              IconButton.onClick(_ => toggleDrawer())(Icons.Menu())
            ),
            List(
                  ListSubheader.inset(true)(""),
                  router.Link[String](to = "/")(
                    ListItem.button(true)(
                      ListItemIcon(Icons.Dashboard()),
                      ListItemText.primary("Explore")
                    )
                  ),
                  router.Link[String](to = "/trending")(
                    ListItem.button(true)(
                      ListItemIcon(Icons.BarChart()),
                      ListItemText.primary("Trending")
                    )
                  ),
                  // router.Link[String](to = "/items")(
                  //   ListItem.button(true)(
                  //     ListItemIcon(Icons.Assignment()),
                  //     ListItemText.primary("Items")
                  //   )
                  // ),
                  router.Link[String](to = "/orders")(
                    ListItem.button(true)(
                      ListItemIcon(Icons.ShoppingCart()),
                      ListItemText.primary("Orders")
                    )
                  ),
                  div(className := classes("disableButton"))(
                    router.Link[String](to = "/order-history")(
                      ListItem.button(true).disabled(true)(
                        ListItemIcon(Icons.History()),
                        ListItemText.primary("Order History")
                      )
                    )
                  ),
                  div(className := classes("disableButton"))(
                    router.Link[String](to = "/create")(
                      ListItem.button(false).disabled(true)(
                        ListItemIcon(Icons.AddCircle()),
                        ListItemText.primary("Create")
                      )
                    )
                  ),
                  Divider(),
                  div(className := classes("disableButton"))(
                    router.Link[String](to = "/settings")(
                      ListItem.button(false).disabled(true)(
                        ListItemIcon(Icons.Settings()),
                        ListItemText.primary("Settings")
                      )
                    )
                  )
                )
          ),
      )
  }
}