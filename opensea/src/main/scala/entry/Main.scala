package entry

import mui.pages.trending.Trending
import mui.pages.cart.Cart
import mui.pages.explore.Explore
import mui.pages.items.Items
import mui.pages.login.Login
import mui.global.{AppTheme,DarkTheme, Palette}
import mui.layout.MainLayout
import org.scalajs.dom
import slinky.core.FunctionalComponent
import slinky.core.facade.Fragment
import slinky.hot
import slinky.web.ReactDOM
import typings.materialUiCore.createMuiThemeMod.{Theme, ThemeOptions}
import typings.materialUiCore.createTypographyMod.TypographyOptions
import typings.materialUiCore.stylesMod.createMuiTheme
import typings.materialUiStyles.components.ThemeProvider

import typings.reactRouter.mod.RouteProps
import typings.reactRouterDom.{components => router}

import scala.scalajs.LinkingInfo
import model.NFTItem
import mock.SampleData

object App {

  val theme: Theme = createMuiTheme(
    ThemeOptions()
      .setTypography(
        TypographyOptions().setUseNextVariants(true)
      ) // https://v3.material-ui.com/style/typography/#migration-to-typography-v2
  )

  type Props = Unit

  /* the production build is deployed at github pages under /material-ui , while dev build is server from root of webpack-dev-server */
  val basename =
    if (scala.scalajs.runtime.linkingInfo.productionMode)
      "/it5100a/opensea/"
    else ""
  var allItems: Seq[NFTItem] = new SampleData().getSampleProducts()
  var cartItems: Seq[NFTItem] = allItems.take(3)
  allItems = allItems.drop(3)
  var selectedItem: NFTItem = allItems(0)

  def addItemToCart(item: NFTItem): Unit = {
    cartItems = cartItems.appended(item)
    allItems = allItems diff Seq(item)
  }
  def removeItemFromCart(item: NFTItem): Unit = {
    cartItems = cartItems diff Seq(item)
    allItems = allItems.appended(item)
  }

  def setSelectedItem(item:NFTItem):Unit = {
    selectedItem = item
  }

  val Main: FunctionalComponent[Props] = FunctionalComponent[Props] { case () =>
    ThemeProvider(theme)(
      router.BrowserRouter.basename(basename)(
        router.Switch(
          router.Route(
            RouteProps()
              .setPath("/login")
              .setRender(_ =>
                Fragment(
                  AppTheme(
                    title = "Login page",
                    description = "Login into user account"
                  )(Login())
                )
              )
          ),
          router.Route(
            RouteProps()
              .setPath("/explore")
              .setRender(_ =>
                Fragment(
                  MainLayout(),
                  AppTheme(
                    title = "Explore page",
                    description = "Find all the NFTs here."
                  )(
                    Explore(
                      allItems = allItems,
                      addItemToCart = addItemToCart,
                      setSelectedItem = setSelectedItem
                    )
                  )
                )
              )
          ),
          router.Route(
            RouteProps()
              .setPath("/trending")
              .setRender(_ =>
                Fragment(
                  MainLayout(),
                  AppTheme(
                    title = "Trending page",
                    description = "Get data on the trending NFTs in the market."
                  )(Trending())
                )
              )
          ),
          (allItems++cartItems).map {
            item => 
              router.Route(
              RouteProps()
              .setExact(true)
              .setPath("/items/"+s"${item.Id}")
              .setRender(_ =>
                Fragment(
                  MainLayout(),
                  AppTheme(
                    title = "Items page",
                    description = "Get more details about the item."
                  )(Items(
                      item = selectedItem,
                      addItemToCart = addItemToCart
                    )
                  )
                )
              )
          )
          },
          router.Route(
            RouteProps()
              .setPath("/orders")
              .setRender(_ =>
                Fragment(
                  MainLayout(),
                  AppTheme(
                    title = "Orders page",
                    description = "Your NFT shopping cart"
                  )(
                    Cart(
                      cartItems = cartItems,
                      removeItemFromCart = removeItemFromCart
                    )
                  )
                )
              )
          ),
          router.Route(
            RouteProps()
              .setPath("/order-history")
              .setRender(_ => MainLayout())
          ),
          router.Route(
            RouteProps()
              .setPath("/create")
              .setRender(_ => MainLayout())
          ),
          router.Route(
            RouteProps()
              .setPath("/settings")
              .setRender(_ => MainLayout())
          ),
          router.Route(
            RouteProps()
              .setPath("/")
          )(router.Redirect("/explore"))
        )
      )
    )
  }

  def main(argv: Array[String]): Unit = {
    if (LinkingInfo.developmentMode)
      hot.initialize()
    println("starting")
    ReactDOM.render(
      Main(()),
      dom.document.getElementById("container")
    )
  }
}
