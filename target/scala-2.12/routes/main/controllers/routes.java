// @GENERATOR:play-routes-compiler
// @SOURCE:/home/cheluvesha/IdeaProjects/PricePredictionPlay/conf/routes
// @DATE:Sun Nov 08 23:31:04 IST 2020

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReversePricePredictionController PricePredictionController = new controllers.ReversePricePredictionController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseAssets Assets = new controllers.ReverseAssets(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReversePricePredictionController PricePredictionController = new controllers.javascript.ReversePricePredictionController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseAssets Assets = new controllers.javascript.ReverseAssets(RoutesPrefix.byNamePrefix());
  }

}
