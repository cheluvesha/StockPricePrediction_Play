package Utility

import org.apache.spark.sql.SparkSession

/***
  * Utility class to create SparkSession Object
  */
object UtilityClass {

  /***
    * Creates SparkSession Object
    * @return SparkSession
    */
  def createSparkSession(): SparkSession = {
    val sparkSession = SparkSession
      .builder()
      .appName("Stock price prediction")
      .master("local[*]")
      .getOrCreate()
    sparkSession
  }

}
