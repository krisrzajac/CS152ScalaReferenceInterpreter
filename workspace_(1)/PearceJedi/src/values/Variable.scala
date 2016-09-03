package values

case class Variable(var content: Value = Notification.UNKNOWN) extends Value {
  def setContent(v: Value) { content = v }
  def getContent = content
  override def toString = "Variable(" + content + ")"

}