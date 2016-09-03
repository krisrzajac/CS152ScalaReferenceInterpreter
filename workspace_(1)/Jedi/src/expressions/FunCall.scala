package expressions
import ui._
import values._
case class FunCall(val operator: Expression, val operands: List[Expression] = Nil) extends Expression {
  def execute(env: Environment): Value =
    {
	  val args = operands.map(_.execute(env))
      try {
        val a = operator.execute(env)
    	  if(a.isInstanceOf[Closure])
    	  {
    	    a.asInstanceOf[Closure].apply(args)
    	    
    	  }
    	  else Notification.ERROR
      } 
      
      
      catch {
        case e: UndefinedException => {
          if(operator.isInstanceOf[Identifier])
            system.execute(operator.asInstanceOf[Identifier], args)
            else Notification.ERROR
        }

      }
    }

}