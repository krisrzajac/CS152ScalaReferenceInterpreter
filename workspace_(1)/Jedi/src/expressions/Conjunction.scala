package expressions
import ui._
import values._
case class Conjunction(exp:List[Expression]) extends SpecialForm {
def execute(env: Environment) : Value = 
	{
	  def helper(exp1:List[Expression]):Boolean =
	  {
	    if(exp1 == Nil) true
	    val a = exp1.head.execute(env)
	  	if(exp1.head.execute(env).isInstanceOf[Boole])
	  	{
	  		
	  		if(a.toString == "true") helper(exp1.tail)
	  		else false
	  	}
	  	else throw new TypeException("Conjunction has part that doesn't evaluate to boole")
	  	
	  }
	  new Boole(helper(exp))
	}
}