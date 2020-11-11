package modelTest

import org.apache.spark.sql.SparkSession
import org.scalatest.FunSuite

import scala.math.BigDecimal.RoundingMode

class PricePredictionModelSpec extends FunSuite {
  val spark: SparkSession = SparkSession
    .builder()
    .master("local")
    .appName("Model Test")
    .getOrCreate()
  val openPrice: Double = 1466.80
  val highPrice: Double = 1489.75
  val lowPrice: Double = 1458.81
  val volume: Double = 978200
  val script = "./PythonScript/StockPricePrediction.py"
  val externalApp: String = "python3 " + script

  test("Checking the Predicted value equal or not") {
    val predictedPriceRDD = spark.sparkContext
      .makeRDD(List(openPrice, highPrice, lowPrice, volume))
      .coalesce(1)
      .pipe(externalApp)
    val closePrice = predictedPriceRDD.collect().apply(0)
    val predictedClosePrice = BigDecimal(closePrice)
      .setScale(2, RoundingMode.HALF_UP)
      .toDouble
    val resultFromModel = model.PricePredictionModel.predictPrice(
      openPrice,
      highPrice,
      lowPrice,
      volume,
      spark
    )
    assert(predictedClosePrice === resultFromModel)
  }

  test("givenValuesShouldPredictValueAndShouldNotEqualToZero ") {
    val closePrice = model.PricePredictionModel.predictPrice(
      openPrice,
      highPrice,
      lowPrice,
      volume,
      spark
    )
    assert(closePrice != 0)
  }

  test("givenScriptFilePathWhenCorrectShouldReturnTrue") {
    val fileExist = model.PricePredictionModel.checkPythonScriptExist(script)
    assert(true === fileExist)
  }

  test("givenWrongScriptFilePathWhenWrongShouldReturnFalse") {
    val fileExist =
      model.PricePredictionModel.checkPythonScriptExist("/home/abc.txt")
    assert(false === fileExist)
  }
}
