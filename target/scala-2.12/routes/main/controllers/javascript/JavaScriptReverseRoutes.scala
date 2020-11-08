// @GENERATOR:play-routes-compiler
// @SOURCE:/home/cheluvesha/IdeaProjects/PricePredictionPlay/conf/routes
// @DATE:Sun Nov 08 23:31:04 IST 2020

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset

// @LINE:8
package controllers.javascript {

  // @LINE:8
  class ReversePricePredictionController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:12
    def predictPriceXML: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.PricePredictionController.predictPriceXML",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "predict/xml"})
        }
      """
    )
  
    // @LINE:10
    def predictPriceJson: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.PricePredictionController.predictPriceJson",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "predict/json"})
        }
      """
    )
  
    // @LINE:8
    def homePage: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.PricePredictionController.homePage",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + """"})
        }
      """
    )
  
  }

  // @LINE:15
  class ReverseAssets(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:15
    def versioned: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Assets.versioned",
      """
        function(file1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[play.api.mvc.PathBindable[Asset]].javascriptUnbind + """)("file", file1)})
        }
      """
    )
  
  }


}
