package service

import play.api.libs.json.{JsValue, Json}
import play.api.mvc.Results.Redirect
import play.api.mvc.{AnyContent, Request}
import controllers.routes
import org.apache.spark.sql.SparkSession
object PricePredictionService {

  val sparkSession: SparkSession = Utility.UtilityClass.createSparkSession()

  /***
    * This function converts Tuple collection to Json Format
    * @param tupleMessageAndValue (String, Double)
    * @return JSON
    */
  def convertToJson(tupleMessageAndValue: (String, Double)): JsValue = {
    try {
      val mappedValue =
        Map(
          "message" -> tupleMessageAndValue._1,
          "value" -> tupleMessageAndValue._2.toString,
          "status" -> "200"
        )
      Json.toJson(mappedValue)
    } catch {
      case exception: Exception =>
        exception.printStackTrace()
        Json.toJson("")
    }
  }

  /***
    * This function converts the Tuple collection to XML
    * @param tupleData (String, Double)
    * @return String
    */
  def convertToXML(tupleData: (String, Double)): String = {
    "<xml>" +
      "<data>" +
      "<message>" + tupleData._1 + "</message>" + "<value>" + tupleData._2 + "</value>" +
      "</data>" +
      "<status>Success!!!" +
      "</status>" +
      "</xml>"
  }

  /***
    * This function checks the body and extracts the Data to predict the price
    * @param request HTML Request
    * @return (String, Double)
    */
  def splitTheRequestToPredict(
      request: Request[AnyContent]
  ): (String, Double) = {
    var closePrice: Double = 0.0 // close price initialized to zero
    val message = "The predicted result is"
    val bodyValues = request.body.asFormUrlEncoded
    bodyValues
      .map { values =>
        val openPrice = values("OpenPrice").head.toDouble
        val highPrice = values("HighPrice").head.toDouble
        val lowPrice = values("LowPrice").head.toDouble
        val volume = values("Volume").head.toDouble

        closePrice = model.PricePredictionModel.predictPrice(
          openPrice,
          highPrice,
          lowPrice,
          volume,
          sparkSession
        )
      }
      .getOrElse(Redirect(routes.PricePredictionController.homePage()))
    (message, closePrice)
  }

}
