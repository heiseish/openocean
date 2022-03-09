package mock

import scala.scalajs.js.annotation.{JSExportTopLevel, JSImport}
import scala.scalajs.js
import model.MoreItemsFromSameAuthor

@JSImport("./images/ape.png", JSImport.Default)
@js.native
object Ape extends js.Object

@JSImport("./images/azuki.jpg", JSImport.Default)
@js.native
object Azu extends js.Object

@JSImport("./images/crypto_punk.png", JSImport.Default)
@js.native
object Cryp extends js.Object

class MoreItemsData() extends js.Object {
  def get(): Seq[MoreItemsFromSameAuthor] = {
    Seq(
      new MoreItemsFromSameAuthor(
        1,
        Ape.asInstanceOf[String],
        "Azuki#9999",
        1029,
        "January 28, 2022",
        "1.4 WETH",
      ),
      new MoreItemsFromSameAuthor(
        2,
        Azu.asInstanceOf[String],
        "Azuki#9998",
        577,
        "February 5, 2022",
        "1.6 WETH",
      ),
      new MoreItemsFromSameAuthor(
        3,
        Cryp.asInstanceOf[String],
        "Azuki#9997",
        796,
        "January 20, 2022",
        "2 WETH",
      )
    )
  }

}
