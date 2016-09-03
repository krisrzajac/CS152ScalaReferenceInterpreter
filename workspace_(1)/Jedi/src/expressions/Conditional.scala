package expressions
import ui._
import values._
case class Conditional(condition: Expression, consequent: Expression, alternative: Expression = null) extends SpecialForm {
   def execute(env: Environment): Value = {
     def c = condition.execute(env);
     if (!c.isInstanceOf[Boole]) throw new TypeException("Condition must be a Boole")
     if (c.asInstanceOf[Boole].boole) consequent.execute(env)
     else if (alternative != null) alternative.execute(env)
     else Notification.UNKNOWN
   }
}