// @GENERATOR:play-routes-compiler
// @SOURCE:/home/cheluvesha/IdeaProjects/PricePredictionPlay/conf/routes
// @DATE:Sun Nov 08 23:31:04 IST 2020

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:8
package controllers {

  // @LINE:8
  class ReversePricePredictionController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:12
    def predictPriceXML(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "predict/xml")
    }
  
    // @LINE:10
    def predictPriceJson(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "predict/json")
    }
  
    // @LINE:8
    def homePage(): Call = {
      
      Call("GET", _prefix)
    }
  
  }

  // @LINE:15
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:15
    def versioned(file:Asset): Call = {
      implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public"))); _rrc
      Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[play.api.mvc.PathBindable[Asset]].unbind("file", file))
    }
  
  }


}
