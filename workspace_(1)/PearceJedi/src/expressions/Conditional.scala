package expressions
import ui._
import values._

case class Conditional(condition: Expression, thenClause: Expression, elseClause: Expression = null) extends SpecialForm {
   def execute(env: Environment): Value = {
     def c = condition.execute(env);
     if (!c.isInstanceOf[Boole]) throw new TypeException("Condition must be a Boole")
     if (c.asInstanceOf[Boole].value)
       thenClause.execute(env)
     else if (elseClause != null)
     elseClause.execute(env)
     else Notification.VOID
   }
}