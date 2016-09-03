package expressions
import ui._
import values._

case class Assignment(vbl:Identifier, update:Expression) extends SpecialForm {
def execute(env: Environment): Value = 
	{
	  if(vbl.execute(env).isInstanceOf[Variable])
	  {
	   	vbl.execute(env).asInstanceOf[Variable].content = update.execute(env)
	   	Notification.OK
	  }else throw new TypeException("vbl isn't of type Variable")
	}
}