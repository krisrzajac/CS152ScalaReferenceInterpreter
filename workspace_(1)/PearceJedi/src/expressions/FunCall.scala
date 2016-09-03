package expressions
import values._
import ui._

case class FunCall(val operator: Expression, val operands: List[Expression] = Nil) extends Expression {
  
  def execute(env: Environment): Value = {
    val args = operands.map(_.execute(env))
    try {
      val fun = operator.execute(env)
      if (fun.isInstanceOf[Closure]) fun.asInstanceOf[Closure](args)
      else throw new TypeException("Only functions can be called")
    } catch {
        case e: UndefinedException => system.execute(operator.asInstanceOf[Identifier], args)
    }
   
  }

    
}