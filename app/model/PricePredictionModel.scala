package model

import java.io.FileNotFoundException

import org.apache.spark.sql.SparkSession

import scala.math.BigDecimal.RoundingMode
import scala.reflect.io.File

object PricePredictionModel {

  val script: String = "./PythonScript/StockPricePrediction.py"

  /***
    * checks whether file exist or not
    * @param script String
    * @return Boolean
    */
  def checkPythonScriptExist(script: String): Boolean = {
    val file = File(script)
    file.exists
  }

  /*
    The aim is to create a rdd with the given inputs and call the python machine learning algorithm
    and return the calculated Close Price
    @params  OpenPrice[Double],highPrice[Double],lowPrice[Double],Volume[Double]
    @return PredictedPrice[Double]
   */
  def predictPrice(
      openPrice: Double,
      highPrice: Double,
      lowPrice: Double,
      volume: Double,
      sparkSessionObj: SparkSession
  ): Double = {
    try {
      val status = checkPythonScriptExist(script)
      if (status) {
        val externalApp =
          "python3 " + script
        val predictedPriceRDD = sparkSessionObj.sparkContext
          .makeRDD(List(openPrice, highPrice, lowPrice, volume))
          .coalesce(1)
          .pipe(externalApp)
        val predictedClosePrice = predictedPriceRDD.collect().apply(0)

        //Scaling the Predicted Close Price to 2 decimal places and returning it
        BigDecimal(predictedClosePrice)
          .setScale(2, RoundingMode.HALF_UP)
          .toDouble
      } else {
        throw new FileNotFoundException()
      }
    } catch {
      case fileNotFound: FileNotFoundException =>
        throw new FileNotFoundException("python script is not available")
    }
  }
}
