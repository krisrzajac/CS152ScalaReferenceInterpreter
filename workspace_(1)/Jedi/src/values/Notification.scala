package values

import ui._
import expressions._


class Notification(msg:String) extends Value{
	override def toString = msg
}
object Notification {
  def UPDATED = new Notification("Variable updated")
  def BINDING = new Notification("Binding created")
  def UNKNOWN = new Notification("Unknown")
  def ERROR = new Notification("Error")
  def OK = new Notification("OK")
}