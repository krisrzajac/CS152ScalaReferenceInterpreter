package expressions

import values._


case class ForLoop(lcv: Identifier, limit: Number , body: Expression) extends SpecialForm {
   def execute(env: Environment): Value = {
     var tempEnv = new Environment(env)
     val counter = new Variable(Number(0))
     tempEnv.put(lcv, counter)
     var result: Value = null
     while(counter.content.asInstanceOf[Number].value < limit.value) {
       result = body.execute(tempEnv)
       counter.content = Number(counter.content.asInstanceOf[Number].value + 1)
     }
     result
   }
}