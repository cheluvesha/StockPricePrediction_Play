package controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc.{
  AbstractController,
  Action,
  AnyContent,
  ControllerComponents
}
import service.PricePredictionService.{
  convertToJson,
  convertToXML,
  splitTheRequestToPredict
}

/*
The objective of the class is to get the input from the presentation layer pass values to business layer and
return responses in XML or JSON.
 */
@Singleton
class PricePredictionController @Inject() (
    controllerComponents: ControllerComponents
) extends AbstractController(controllerComponents) {

  /*
  This function redirects to index page i.e. homePage
   */

  def homePage(): Action[AnyContent] = {
    Action { implicit request =>
      Ok(views.html.index())
    }
  }

  def predictPriceJson: Action[AnyContent] = {
    Action { implicit request =>
      val dataFromRequest = splitTheRequestToPredict(request)
      val jsonFormat = convertToJson(dataFromRequest)
      Ok(jsonFormat)
    }
  }

  def predictPriceXML: Action[AnyContent] = {
    Action { implicit request =>
      val dataFromRequest = splitTheRequestToPredict(request)
      val XMLFormat = convertToXML(dataFromRequest)
      Ok(XMLFormat).as("text/xml")
    }
  }
}
