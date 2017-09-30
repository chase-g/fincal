import scala.swing._
import scala.swing.event._

class UI extends MainFrame {
  def restrictHeight(s: Component) {
    s.maximumSize = new Dimension(Short.MaxValue, s.preferredSize.height)
  }

  title = "Financial Calculator"
  
  val amount = new TextField { columns = 32 }
  val years = new TextField { columns = 32 }
  val interestRate = new TextField { columns = 32 }
  val button = new Button 
  val answerSpace = new Label("") 
  restrictHeight(amount)
  restrictHeight(years)
  restrictHeight(interestRate)

  contents = new BoxPanel(Orientation.Vertical) {
    contents += new BoxPanel(Orientation.Horizontal) {
      contents += new Label("Amount")
      contents += Swing.HStrut(5)
      contents += amount
    }
    contents += new BoxPanel(Orientation.Horizontal) {
      contents += new Label("Interest rate as %")
      contents += Swing.HStrut(5)
      contents += interestRate
    }
    contents += new BoxPanel(Orientation.Horizontal) {
      contents += new Label("Years")
      contents += Swing.HStrut(5)
      contents += years
    }
    contents += new BoxPanel(Orientation.Horizontal) {
      contents += Swing.HGlue
      contents += Button("Calculate") { submit() }
    }
     contents += new BoxPanel(Orientation.Horizontal) {
      contents += Swing.HStrut(5)
      contents += answerSpace
      contents += Swing.HStrut(10)
    }
    for (e <- contents)
      e.xLayoutAlignment = 0.0
    border = Swing.EmptyBorder(20, 20, 20, 20)
  }

  listenTo(amount)
  listenTo(years)
  listenTo(interestRate)
  
  private def submit() {
    val napier = 2.71828
    val amt = amount.text.toDouble
    val yrs = years.text.toDouble
    val rate = interestRate.text.toDouble * .01
    val answer = {amt * Math.pow(napier, yrs * rate)}
    println("Answer: " + answer)
    answerSpace.text = "Present Value: " + answer.toString
  }
}

object fincal {
  def main(args: Array[String]) {
    val ui = new UI
    ui.visible = true
  }
}