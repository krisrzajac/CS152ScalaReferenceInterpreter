package values


import ui._
import expressions._

class Closure(params:List[Identifier], body:Expression, defEnv:Environment) extends Value {
	  def apply(args: List[Value]): Value = 
	  {
	    val tem = new Environment(defEnv)
	    defEnv.put(params,args)
	    body.execute(tem)
	  }
}