package expressions

import values._
import ui._

case class Assignment(id: Identifier, c: Expression) extends SpecialForm {
   def execute(env: Environment): Value = {
     val v = id.execute(env)
     if (!v.isInstanceOf[Variable]) throw new TypeException("Variable expected in assignment")
     v.asInstanceOf[Variable].setContent(c.execute(env))
     Notification.DONE
   }
}