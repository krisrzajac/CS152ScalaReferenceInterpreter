package ui
import expressions._
import values._

object testDriver {
  def tester(){
    Number.test
    Boole.test
    Environment.test
  }
	 def main(args: Array[String]): Unit = {tester()}
}