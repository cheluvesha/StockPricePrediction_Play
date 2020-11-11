package serviceTest

import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.libs.json.{JsValue, Json}
import play.api.test.FakeRequest
import play.api.test.Helpers._
import service.PricePredictionService

class PricePredictionServiceSpec extends PlaySpec with GuiceOneAppPerTest {
  val message = "The predicted result is"
  val close: Double = 1479.15
  val wrongMessage = "wrong"
  val wrongClose = 1.00
  val mappedValue =
    Map(
      "message" -> message,
      "value" -> close.toString,
      "status" -> "200"
    )
  val jsonOutput: JsValue = Json.toJson(mappedValue)
  val xmlOutput: String =
    "<xml><data><message>" + message + "</message><value>" + close + "</value></data><status>200</status></xml>"

  "PricePredictionService #Generating JSON Output by sending tuple data" should {

    "return json format output by considering tuple data to validate service class method" in {
      val output =
        PricePredictionService.convertToJson(message, close)
      assert(jsonOutput === output)
    }

    "return json values for passed parameters and compare with actual" in {
      val output =
        PricePredictionService.convertToJson(wrongMessage, wrongClose)
      assert(jsonOutput != output)
    }
  }

  "PricePredictionService #Generating XML Output by sending tuple data" should {

    "return xml value for passed parameter to validate service class method" in {
      val output = PricePredictionService.convertToXML(message, close)
      assert(xmlOutput === output)
    }

    "return wrong value and compare with actual" in {
      val output = PricePredictionService.convertToXML(wrongMessage, wrongClose)
      assert(xmlOutput != output)
    }
  }

  "PricePredictionService #Validation for splitTheRequestToPredict method" should {

    "return tuple calling upon request " in {
      val tupleData = PricePredictionService.splitTheRequestToPredict(
        FakeRequest(POST, "/predict/Json")
          .withFormUrlEncodedBody(
            "OpenPrice" -> "1466.80",
            "HighPrice" -> "1489.75",
            "LowPrice" -> "1458.81",
            "Volume" -> "978200"
          )
      )
      assert(tupleData._1 === message)
      assert(tupleData._2 === close)
    }

    "return tuple calling upon request with swapping value fields" in {
      val tupleData = PricePredictionService.splitTheRequestToPredict(
        FakeRequest(POST, "/predict/Json")
          .withFormUrlEncodedBody(
            "OpenPrice" -> "1466.80",
            "HighPrice" -> "1489.75",
            "Volume" -> "978200",
            "LowPrice" -> "1458.81"
          )
      )
      assert(tupleData._1 === message)
      assert(tupleData._2 === close)
    }

    "return wrong tuple calling upon request to validate data" in {
      val tupleData = PricePredictionService.splitTheRequestToPredict(
        FakeRequest(POST, "/predict/Json")
          .withFormUrlEncodedBody(
            "OpenPrice" -> "14.80",
            "HighPrice" -> "149.75",
            "LowPrice" -> "14581",
            "Volume" -> "9200"
          )
      )
      assert(tupleData._1 === message && tupleData._2 != close)
    }

    "return exception calling upon with empty body request to validate" in {
      val result = intercept[Exception] {
        PricePredictionService.splitTheRequestToPredict(
          FakeRequest(POST, "/predict/Json")
            .withFormUrlEncodedBody()
        )
      }
      assert(result.getMessage === "Empty Key fields")
    }
  }
}
