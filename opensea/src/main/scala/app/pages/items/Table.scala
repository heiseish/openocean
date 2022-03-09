package mui.pages.items

import mui.StyleBuilder
import slinky.core.FunctionalComponent
import slinky.core.annotations.react
import slinky.web.html._
import typings.csstype.csstypeStrings.auto
import typings.materialUiCore.components._
import typings.materialUiCore.createMuiThemeMod.Theme
import typings.materialUiCore.materialUiCoreStrings.right
import typings.materialUiStyles.withStylesMod.CSSProperties
import mock.MoreItemsData
import typings.materialUiCore.{materialUiCoreStrings => strings}
import scala.scalajs.js
import typings.materialUiCore.mod.PropTypes.Color

// https://github.com/mui-org/material-ui/blob/v3.x/docs/src/pages/getting-started/page-layout-examples/dashboard/SimpleTable.js
@react object SimpleTable {

  lazy val styles =
    StyleBuilder[Theme, js.Object]
      .add("root", CSSProperties().setWidth("100%").setOverflowX(auto))
      .add("table", CSSProperties().setMinWidth(400))
      .hook

  type Props = Unit

  val component: FunctionalComponent[Props] = FunctionalComponent[Props] {
    case () =>
      val classes = styles(js.undefined)
      Paper.className(classes("root"))(
        Table(className := classes("table"))(
          TableHead(
            TableRow(
              TableCell(""),
              TableCell.align(right)("Collection"),
              TableCell.align(right)("Likes"),
              TableCell.align(right)("Order Date"),
              TableCell.align(right)("Price"),
              TableCell.align(right)("")
            )
          ),
          TableBody(
            new MoreItemsData().get().map { n =>
              TableRow.withKey(n.id.toString)(
                TableCell
                  .set("component", "th")
                  .scope("row")(Avatar.srcSet("../" + n.image)),
                TableCell.align(right)(n.collection),
                TableCell.align(right)(n.Favourites.toString()),
                TableCell.align(right)(n.OrderDate),
                TableCell.align(right)(n.Price),
                TableCell.align(right)(
                  Button
                    .variant(strings.outlined)
                    .color(Color.secondary)("More Details")
                    .disabled(true)
                    .fullWidth(true)
                )
              )
            }
          )
        )
      )
  }
}

