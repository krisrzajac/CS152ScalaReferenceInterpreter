package expressions
import values._
import ui._
case class Identifier(name: String) extends Expression with Serializable { 
   def execute(env: Environment) = {
     val result = env.find(this)
     //if (result == null) throw new UndefinedException(name)
     result
   }
   
   override def toString = name
}

