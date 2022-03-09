package mui.trending

import org.scalablytyped.runtime.StringDictionary
import slinky.core.FunctionalComponent
import slinky.core.annotations.react
import typings.recharts.components._
import typings.recharts.rechartsStrings.monotone

import scala.scalajs.js
import typings.std.JSON

// https://github.com/mui-org/material-ui/blob/v3.x/docs/src/pages/getting-started/page-layout-examples/dashboard/SimpleLineChart.js
@react object SimpleLineChart {

  class Data(val Name: String, val ItemsPurchased: Int, val ItemsListed: Int)
      extends js.Object

  val data: js.Array[js.Object] = js.Array(
    new Data("Sep", 200, 3400),
    new Data("Oct", 128, 2398),
    new Data("Nov", 5000, 4300),
    new Data("Dec", 4780, 2908),
    new Data("Jan", 5890, 4800),
    new Data("Feb", 4390, 3800),
    new Data("Mar", 4490, 4300)
  )
  type Props = Unit

  val component: FunctionalComponent[Props] = FunctionalComponent[Props] {
    case () =>
      ResponsiveContainer
        .width("99%")
        .height(320)(
          LineChart.data(data)(
            XAxis.dataKey("Name"),
            YAxis(),
            CartesianGrid.vertical(false).strokeDasharray("3 3"),
            Tooltip(),
            Legend(),
            Line("ItemsPurchased").`type`(monotone).stroke("#82ca9d"),
            Line("ItemsListed")
              .`type`(monotone)
              .stroke("#8884d8")
              .activeDot(StringDictionary("r" -> 8))
          )
        )
  }
}
