package expressions
import ui._
import values._
case class Identifier(name:String) extends Expression with Serializable{
	def execute(env: Environment): Value = 
	{
	  val temp =env.find(this)
	  if(temp.toString =="Unknown") throw new UndefinedException(name)
	  else temp
	}
	override def toString = name
}