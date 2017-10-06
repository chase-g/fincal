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
  val presentV = new RadioButton("Present Value")
  val futureV = new RadioButton("Future Value") 
  presentV.selected = true
  val valueGroup = new ButtonGroup(presentV, futureV)
  val answerSpace = new Label("") 
  val buttonCalc = new Button("Calculate")
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
      contents += buttonCalc 
    }
     contents += new BoxPanel(Orientation.Horizontal) {
      contents += Swing.HStrut(5)
      contents += answerSpace
      contents += Swing.HStrut(10)
    }
     contents += new BoxPanel(Orientation.Horizontal) {
      contents += Swing.HGlue
      contents += presentV
      contents += Swing.HStrut(10)
      contents += futureV
      contents += Swing.HStrut(10)
    }
    for (e <- contents)
      e.xLayoutAlignment = 0.0
    border = Swing.EmptyBorder(20, 20, 20, 20)
  }

  listenTo(amount)
  listenTo(years)
  listenTo(interestRate)
  listenTo(presentV)
  listenTo(futureV)
  listenTo(buttonCalc)
  
  reactions += {
    case ButtonClicked(buttonCalc) => {
      if(presentV.selected){
    val napier = 2.71828
    val amt = amount.text.toDouble
    val yrs = years.text.toDouble
    val rate = interestRate.text.toDouble * .01
    val answer = {amt * Math.pow(napier, yrs * rate)}
    println("Answer: $" + answer)
    answerSpace.text = "Present Value: $" + answer.toString
      } else {
    val napier = 2.71828
    val amt = amount.text.toDouble
    val yrs = years.text.toDouble
    val rate = interestRate.text.toDouble * .01
    val answer = {amt / Math.pow(napier, yrs * rate)}
    println("Answer: $" + answer)
    answerSpace.text = "Future Value: $" + answer.toString
      }
    }
  }
}

object fincal {
  def main(args: Array[String]) {
    val ui = new UI
    ui.visible = true
  }
}
