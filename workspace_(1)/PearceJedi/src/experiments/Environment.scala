package experiments
import expressions._
import values._

class Environment (var callEnv: Environment = null, var defEnv: Environment = null)extends Value {
  val bindings = new scala.collection.mutable.HashMap[Identifier, Value]
  var ready = true 
  def add(id: Identifier, value: Value) { bindings.put(id, value)}
  def add(ids: List[Identifier], vals: List[Value]) {
    for(p<-ids.zip(vals)) bindings += p
  }
  override def toString = bindings.toString
  
  def find(id: Identifier): Value = {
    try {
      if (ready) bindings(id)
      else if (defEnv != null) defEnv.find(id)
      else null
    } catch {
       case e: Throwable =>
         {
           if (defEnv != null) defEnv.find(id)
           else if (callEnv != null) callEnv.find(id)
           else null
         }
    }
  }
}