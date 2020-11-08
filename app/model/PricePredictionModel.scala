package model

import org.apache.spark.sql.SparkSession

import scala.math.BigDecimal.RoundingMode

object PricePredictionModel {

  /*
  The aim is to create a rdd with the given inputs and call the python machine learning algorithm
  and return the calculated Close Price
  @params  OpenPrice[Double],highPrice[Double],lowPrice[Double],Volume[Double]
  @return PredictedPrice[Double]
   */
  def predictPrice(
      openPrice: Double,
      HighPrice: Double,
      lowPrice: Double,
      Volume: Double,
      sparkSessionObj: SparkSession
  ): Double = {
    val externalApp =
      "python3 ./PythonFile/StockPricePrediction.py"
    val predictedPriceRDD = sparkSessionObj.sparkContext
      .makeRDD(List(openPrice, HighPrice, lowPrice, Volume))
      .repartition(1)
      .pipe(externalApp)
    val predictedClosePrice = predictedPriceRDD.collect().apply(0)

    //Scaling the Predicted Close Price to 2 decimal places and returning it
    BigDecimal(predictedClosePrice)
      .setScale(2, RoundingMode.HALF_UP)
      .toDouble
  }
}
