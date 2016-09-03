package expressions
import ui._
import values._

case class Block(locals:List[Expression]) extends SpecialForm {

  def execute(env: Environment) =
	{
	  val temp:Environment = new Environment(env)
	  for(i<-0 to locals.length-1)
	  {
	    locals(i).execute(temp)
	  }
  	  locals(locals.length-1).execute(temp)
	  
	}
}