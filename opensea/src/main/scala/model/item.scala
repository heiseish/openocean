package model
import scala.scalajs.js

class NFTItem(
    val Title:String,
    val Id: Int,
    val Price_Id: String,
    val Image: String,
    val Author: String,
    val Description: String,
    val Favourites: Int,
    val PriceSol: Double,
    val PriceDollar: Double
) extends js.Object 
