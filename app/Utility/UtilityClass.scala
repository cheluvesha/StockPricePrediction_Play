package Utility

import org.apache.spark.sql.SparkSession

object UtilityClass {

  def createSparkSession(): SparkSession = {
    val sparkSession = SparkSession
      .builder()
      .appName("Stock price prediction")
      .master("local[*]")
      .getOrCreate()
    sparkSession
  }
}
