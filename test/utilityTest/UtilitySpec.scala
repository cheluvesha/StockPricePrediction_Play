package utilityTest

import Utility.UtilityClass
import org.apache.spark.sql.SparkSession
import org.scalatestplus.play.PlaySpec

class UtilitySpec extends PlaySpec {
  "Create and check object type equals or not" should {
    "SparkSession type must be matched" in {
      val sparkSession = UtilityClass.createSparkSession()
      val createdSparkSession = SparkSession
        .builder()
        .master("local[*]")
        .appName("Test")
        .getOrCreate()
      assert(sparkSession === createdSparkSession)
    }
  }
}
