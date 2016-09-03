package values
import expressions._
import ui._
import scala.collection.mutable.HashMap

class Environment (var nextEnv: Environment = null) extends HashMap[Identifier, Value] with Value {
  
  def put(ids: List[Identifier], vals: List[Value]) {
    if (ids.length != vals.length) throw new TypeException("too few identifiers or values")
    for(i <- 0 until ids.length) put(ids(i), vals(i))
  }
  
  def find(id: Identifier): Value = {
    if (this.contains(id)) this(id)
    else if (nextEnv != null) nextEnv.find(id)
    else throw new UndefinedException("undefined symbol: " + id)
  }
}