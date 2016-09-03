package expressions
import ui._
import values._
case class Disjunction(exp:List[Expression]) extends SpecialForm {
def execute(env: Environment) : Value = 
	{
	  def helper(exp1:List[Expression]):Boolean =
	  {
	    if(exp1 == Nil) false
	     val a = exp1.head.execute(env)
	  	if(exp1.head.execute(env).isInstanceOf[Boole])
	  	{
	  		if(a.toString == "true") true
	  		else helper(exp1.tail)
	  	}
	  	else throw new TypeException("Disjunction has part that doesn't evaluate to boole")
	  	
	  }
	  new Boole(helper(exp))
	}
}
	
