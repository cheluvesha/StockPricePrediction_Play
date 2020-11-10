package controllerTest

import controllers.HomeController
import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test.Helpers._
import play.api.test._

class HomeControllerSpec
    extends PlaySpec
    with GuiceOneAppPerTest
    with Injecting {

  "HomeController page #index" should {

    "render the index page calling upon with controller object request" in {
      val controller = new HomeController(Helpers.stubControllerComponents())
      val indexPage = controller.index().apply(FakeRequest(GET, "/"))
      status(indexPage) mustBe 200
      contentType(indexPage) mustBe Some("text/html")
      contentAsString(indexPage) must include("Welcome to Stock Prediction")
    }

    "render the index page from the application" in {
      val controller = inject[HomeController]
      val indexPage = controller.index().apply(FakeRequest(GET, "/"))

      status(indexPage) mustBe 200
      contentType(indexPage) mustBe Some("text/html")
      contentAsString(indexPage) must include("Welcome to Stock Prediction")
    }

    "render the index page from the routes" in {
      val indexPage = route(app, FakeRequest(GET, "/")).get
      status(indexPage) mustBe 200
      contentType(indexPage) mustBe Some("text/html")
      contentAsString(indexPage) must include("Welcome to Stock Prediction")
    }
  }
}
