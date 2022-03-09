package mock
import scala.scalajs.js.annotation.{JSExportTopLevel, JSImport}
import scala.scalajs.js
import model.NFTItem

@JSImport("./images/image1.jpg", JSImport.Default)
@js.native
object Image1 extends js.Object

@JSImport("./images/image2.jpg", JSImport.Default)
@js.native
object Image2 extends js.Object

@JSImport("./images/image3.jpg", JSImport.Default)
@js.native
object Image3 extends js.Object

@JSImport("./images/image4.jpg", JSImport.Default)
@js.native
object Image4 extends js.Object

@JSImport("./images/image5.jpg", JSImport.Default)
@js.native
object Image5 extends js.Object

@JSImport("./images/image6.jpg", JSImport.Default)
@js.native
object Image6 extends js.Object

@JSImport("./images/image7.jpg", JSImport.Default)
@js.native
object Image7 extends js.Object

@JSImport("./images/image8.jpg", JSImport.Default)
@js.native
object Image8 extends js.Object

@JSImport("./images/image9.jpg", JSImport.Default)
@js.native
object Image9 extends js.Object

@JSImport("./images/image10.jpg", JSImport.Default)
@js.native
object Image10 extends js.Object

@JSImport("./images/image11.jpg", JSImport.Default)
@js.native
object Image11 extends js.Object

@JSImport("./images/image12.jpg", JSImport.Default)
@js.native
object Image12 extends js.Object

// @JSImport("./products.json", JSImport.Default)
// @js.native
// object JSONRaw extends js.Any

class SampleData() extends js.Object {
  def getSampleProducts(): Seq[NFTItem] = {
    Seq(
      new NFTItem(
        "Never Sleep City",
        1,
        "price_1KXqRNAWtFQYqwKyDcL4CDIa",
        Image1.asInstanceOf[String],
        "ElonGrimaceMuck",
        "A brand for the metaverse. Built by the community. View the collection at azuki.com/gallery.Azuki starts with a collection of 10,000 avatars that give you membership access to The Garden: a corner of the internet where artists, builders, and web3 enthusiasts meet to create a decentralized future.",
        1226,
        10.124,
        5248.04
      ),
      new NFTItem(
        "Cloud and Star",
        2,
        "price_1KXqRtAWtFQYqwKyE4h7RWms",
        Image2.asInstanceOf[String],
        "FishyFam",
        "A collection of 62,500 genesis Plots of land. Get a Plot to access the Critterz metaverse in Minecraft. Each Plot of land is 64 by 64 Minecraft blocks in size. Only owners of the Plots can build or destroy any blocks on it. You will have to stake Plots before building on them. Plots can also be minted using $BLOCK tokens.",
        1131,
        6.126,
        3204.12
      ),
      new NFTItem(
        "Red Tree",
        3,
        "price_1KXqSPAWtFQYqwKyRXogDGO6",
        Image3.asInstanceOf[String],
        "nabovisuals",
        "Lab notes: Unstable regulation of $RWASTE lead to all subjects of KaijuKingz Collection 001 to become dangerously unstable. As a result, a new collection has been created utilizing better calibration calculations.",
        889,
        8.246,
        4120.98
      ),
      new NFTItem(
        "Light",
        4,
        "price_1KXqTBAWtFQYqwKypyB088Lb",
        Image4.asInstanceOf[String],
        "williswaverls",
        "Starcatchers is a collection of 10,000 fun loving space explorers. We are a community-driven brand that aims to push the boundaries of web3.",
        678,
        0.0287,
        77.24
      ),
      new NFTItem(
        "That Day",
        5,
        "price_1KXqTaAWtFQYqwKyeHEYfvVL",
        Image5.asInstanceOf[String],
        "wterly",
        "Angels and Devils have agreed to put aside their differences and enter the Metaverse together, as friends! A hand-drawn collection, 5,000 Angels and 5,000 Devils to complete a full collection of 10,000 NFTs.",
        115,
        0.0165,
        45.97
      ),
      new NFTItem(
        "8030",
        6,
        "price_1KXqTyAWtFQYqwKyWqjzWscI",
        Image6.asInstanceOf[String],
        "mixphix",
        "10KTF is a limited collection of items carefully crafted by Wagmi-san to go along with your generative art NFTs. It gives collectors the ability to curate unique digital assets by creating one-of-a-kind shoes, apparel, or other goods in the spirit of their avatar. Only the owners of certain existing NFTs can mint their corresponding 10KTF item. Visit 10ktf.com for more details.",
        333,
        0.27,
        750.52
      ),
      new NFTItem(
        "Fog",
        7,
        "price_1KXqUOAWtFQYqwKyXOS5TGMa",
        Image7.asInstanceOf[String],
        "holygrailer",
        "We've created unique \" Quantum AI Data Paintings \" using quantum bit strings generated from Google Quantum AI team's beyond classical experiment together with a Generative Adversarial Network machine learning algorithm that was developed during the production of Quantum Memories.",
        258,
        2.235,
        6212.61
      ),
      new NFTItem(
        "HaHa",
        8,
        "price_1KXqUmAWtFQYqwKyK4HysomJ",
        Image8.asInstanceOf[String],
        "Arowana",
        "Wickens is a limited collection of 6666 unique NFTs living on the Ethereum network. They are randomly assembled from an assortment of over 160 traits, making each Wicken uniquely yours. Ownership of a Wicken grants you ongoing benefits in the form of airdrops, physical redemptions, as well as entry into various community events, games and giveaways.",
        732,
        0.06,
        166.78
      ),
      new NFTItem(
        "Pixel Bear",
        9,
        "price_1KXqVMAWtFQYqwKyVLpswwwX",
        Image9.asInstanceOf[String],
        "TheR0d",
        "Bears Deluxe is a collection of 6,900 algorithmically generated bears. All art drawn by hand in 24px format. Enjoy!",
        1678,
        1.2,
        3335.63
      ),
      new NFTItem(
        "Robotic12138",
        10,
        "price_1KXqViAWtFQYqwKydrBV1qRc",
        Image10.asInstanceOf[String],
        "E67184",
        "Ultraminers are synthesized from the burning of two BMCs. They are hand drawn rare and unique artworks inspired by Bitcoin miners (AntminerS19j ASIC Pro).",
        77,
        0.575,
        1598.32
      ),
      new NFTItem(
        "Phanta Bear",
        11,
        "price_1KXqW3AWtFQYqwKyXMPyvv8f",
        Image11.asInstanceOf[String],
        "zyxa9527",
        "Phanta Bear is a collection of 10,000 algorithmically generated digital collectibles that double as memebership cards for the Ezek Club.",
        372,
        1.05,
        2918.67
      ),
      new NFTItem(
        "little devil",
        12,
        "price_1KXqWTAWtFQYqwKyg0FqlIT8",
        Image12.asInstanceOf[String],
        "turkeyman",
        "We are the ghost, your nightmare, your daydream.",
        903,
        0.249,
        692.14
      )
    )
  }

//   def getJsonRaw(): String = JSONRaw.asInstanceOf[String]
}
