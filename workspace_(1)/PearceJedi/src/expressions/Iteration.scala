package expressions

import ui._
import values._

case class Iteration(condition: Expression, body: Expression) extends SpecialForm {
   def execute(env: Environment): Value = {
     def c = condition.execute(env);
     if (!c.isInstanceOf[Boole]) throw new TypeException("Condition must be a Boole")
     var result: Value = Notification.VOID 
     while (c.asInstanceOf[Boole].value) {
       result = body.execute(env)
     }
     result
   }
}