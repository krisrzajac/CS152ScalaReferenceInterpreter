package expressions
import values._
case class Declaration(symbol: Identifier, exp: Expression) extends SpecialForm {
  def execute(env: Environment): Value = {
    env.put(symbol, exp.execute(env))
    Notification.OK
  }
}