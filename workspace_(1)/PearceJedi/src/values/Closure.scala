package values
import  expressions._
class Closure(params: List[Identifier], body: Expression, defEnv: Environment) extends Value {
   def apply(args: List[Value]): Value = { 
     val callFrame = new Environment(defEnv) 
     callFrame.put(params, args)
     body.execute(callFrame)
   }
}