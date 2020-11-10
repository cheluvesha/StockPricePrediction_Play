package controllerTest

import controllers.PricePredictionController
import org.scalatestplus.play.PlaySpec
import play.api.test.FakeRequest
import play.api.test.Helpers.{
  POST,
  contentAsString,
  contentType,
  defaultAwaitTimeout,
  status,
  stubControllerComponents
}

class PricePredictionControllerSpec extends PlaySpec {
  val jsonOutput =
    """{"message":"The predicted result is","value":"1479.15","status":"200"}"""
  val xmlOutput: String =
    """<xml><data><message>The predicted result is</message><value>1479.15</value></data><status>200</status></xml>"""
  val controller = new PricePredictionController(stubControllerComponents())

  "PricePredictionController POST JSON " should {
    "return json output as The predicted result is and value 1479.15 with status 200" in {

      val predictJSON = controller
        .predictPriceJson()
        .apply(
          FakeRequest(POST, "/predict/Json").withFormUrlEncodedBody(
            "OpenPrice" -> "1466.80",
            "HighPrice" -> "1489.75",
            "LowPrice" -> "1458.81",
            "Volume" -> "978200"
          )
        )
      status(predictJSON) mustBe 200
      contentType(predictJSON) mustBe Some("application/json")
      contentAsString(predictJSON) must include(jsonOutput)
    }
  }
  "PricePredictionController POST XML " should {
    "return xml output as The predicted result is and value 1479.15 with status 200" in {
      val predictPriceXML = controller
        .predictPriceXML()
        .apply(
          FakeRequest(POST, "/predict/xml").withFormUrlEncodedBody(
            "OpenPrice" -> "1466.80",
            "HighPrice" -> "1489.75",
            "LowPrice" -> "1458.81",
            "Volume" -> "978200"
          )
        )
      status(predictPriceXML) mustBe 200
      contentType(predictPriceXML) mustBe Some("text/xml")
      contentAsString(predictPriceXML) must include(xmlOutput)
    }
  }
}
