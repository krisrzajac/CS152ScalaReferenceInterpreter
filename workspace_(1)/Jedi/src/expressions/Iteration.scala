package expressions
import ui._
import values._

case class Iteration(condition: Expression, body: Expression) extends SpecialForm {
  def execute(env: Environment): Value =
    {
      if (condition.execute(env).isInstanceOf[Boole]) {
        while(condition.execute(env).asInstanceOf[Boole].boole == true)
        {
          body.execute(env)
        }
        Notification.OK
      } else throw new TypeException("Condition should execute to Boole type")
    }
}