package expressions
import values._
case class Block(locals: List[Expression]) extends SpecialForm {
  
   def execute(env: Environment) = {
     var result: Value = Notification.UNKNOWN
     val tempEnv = new Environment(env)
     for(e<-locals) result = e.execute(tempEnv)
     result
   }
}