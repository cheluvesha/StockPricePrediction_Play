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

  /***
    * This function accepts request and calls respective methods to response the json output
    * @return Json value
    */
  def predictPriceJson: Action[AnyContent] = {
    Action { implicit request =>
      val dataFromRequest = splitTheRequestToPredict(request)
      val jsonFormat = convertToJson(dataFromRequest)
      Ok(jsonFormat)
    }
  }

  /***
    * This function accepts request and calls respective methods to response the XML output
    * @return text/XML
    */
  def predictPriceXML: Action[AnyContent] = {
    Action { implicit request =>
      val dataFromRequest = splitTheRequestToPredict(request)
      val XMLFormat = convertToXML(dataFromRequest)
      Ok(XMLFormat).as("text/xml")
    }
  }
}
