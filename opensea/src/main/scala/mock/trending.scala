package mock

import scala.scalajs.js.annotation.{JSExportTopLevel, JSImport}
import scala.scalajs.js
import model.TrendingItem

@JSImport("./images/azuki.jpg", JSImport.Default)
@js.native
object Azuki extends js.Object

@JSImport("./images/crypto_punk.png", JSImport.Default)
@js.native
object CryptoPunk extends js.Object

@JSImport("./images/ape.png", JSImport.Default)
@js.native
object BoredApeYatchClub extends js.Object

class TrendingData() extends js.Object {
  def get(): Seq[TrendingItem] = {
    Seq(
      new TrendingItem(
        1,
        Azuki.asInstanceOf[String],
        "Azuki",
        105,
        "January 20, 2022",
        "1.4 WETH",
        "Pending"
      ),
      new TrendingItem(
        2,
        CryptoPunk.asInstanceOf[String],
        "CryptoPunks",
        56,
        "January 20, 2022",
        "1.6 WETH",
        "Approved"
      ),
      new TrendingItem(
        3,
        BoredApeYatchClub.asInstanceOf[String],
        "Bored Ape Yatch Club",
        496,
        "January 20, 2022",
        "2 WETH",
        "Processing"
      )
    )
  }

}
