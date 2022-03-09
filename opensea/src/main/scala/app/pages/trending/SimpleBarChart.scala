package mui.trending

import org.scalablytyped.runtime.StringDictionary
import slinky.core.FunctionalComponent
import slinky.core.annotations.react
import typings.recharts.components._
import typings.recharts.rechartsStrings.monotone

import scala.scalajs.js
import typings.std.JSON

@react object SimpleBarChart {

  class Data(val Name: String, val Visits: Int) extends js.Object

  val data: js.Array[js.Object] = js.Array(
    new Data("S", 200),
    new Data("O", 128),
    new Data("N", 5000),
    new Data("D", 4780),
    new Data("J", 5890),
    new Data("F", 4390),
    new Data("M", 4490)
  )
  type Props = Unit

  val component: FunctionalComponent[Props] = FunctionalComponent[Props] {
    case () =>
      ResponsiveContainer
        .width("99%")
        .height(320)(
          BarChart.data(data)(
            XAxis.dataKey("Name"),
            YAxis(),
            CartesianGrid.vertical(false).strokeDasharray("3 3"),
            Tooltip(),
            Legend(),
            Bar("Visits").fill("#8884d8")
          )
        )
  }
}
