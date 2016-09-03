package values

class Notification(msg: String = "notification") extends Value {
   override def toString = msg
}

object Notification {
  val OK = new Notification("ok")
  val DONE = new Notification("done")
  val UNKNOWN = new Notification("unknown")
  val ERROR = new Notification("error")
  val VOID = new Notification("void")
  val EMPTY = new Notification("")
  
}