
package views.html

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

object index extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.4*/("""

"""),_display_(/*3.2*/main("Stock Price Prediction")/*3.32*/ {_display_(Seq[Any](format.raw/*3.34*/("""
"""),_display_(/*4.2*/defining(play.core.PlayVersion.current)/*4.41*/ { version =>_display_(Seq[Any](format.raw/*4.54*/("""

"""),format.raw/*6.1*/("""<section id="content">
    <h1>Welcome to Stock Prediction</h1>
    <form method="post" action=""""),_display_(/*8.34*/routes/*8.40*/.PricePredictionController.predictPriceJson()),format.raw/*8.85*/("""">
      <div class = "inputFields">
        OpenPrice:<input type = "text"  name = "OpenPrice" placeholder="OpenPrice..">
        HighPrice:<input type = "text"  name = "HighPrice" placeholder="HighPrice..">
        LowPrice:<input type = "text"  name = "LowPrice" placeholder="LowPrice..">
        Volume:<input type = "text"  name = "Volume" placeholder="Volume..">
      </div>
      <br>
      <button type="submit" class = "buttonType">JsonOutput</button>
      <br>
      <br>
      <button type = "submit" class = "buttonType" formaction=""""),_display_(/*19.65*/routes/*19.71*/.PricePredictionController.predictPriceXML()),format.raw/*19.115*/("""">XmlOutput</button>
    </form>
</section>
""")))}),format.raw/*22.2*/("""
""")))}))
      }
    }
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: 2020-11-08T23:31:04.443
                  SOURCE: /home/cheluvesha/IdeaProjects/PricePredictionPlay/app/views/index.scala.html
                  HASH: 2283564f57b31094fcce9c4b01e973cb279d7ba8
                  MATRIX: 722->1|818->3|848->8|886->38|925->40|953->43|1000->82|1050->95|1080->99|1205->198|1219->204|1284->249|1870->808|1885->814|1951->858|2029->906
                  LINES: 21->1|26->1|28->3|28->3|28->3|29->4|29->4|29->4|31->6|33->8|33->8|33->8|44->19|44->19|44->19|47->22
                  -- GENERATED --
              */
          