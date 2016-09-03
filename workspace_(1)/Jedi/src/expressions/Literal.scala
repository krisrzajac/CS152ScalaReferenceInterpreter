package expressions
import ui._
import values._
case class Literal() extends Expression with Value {
	def execute(env: Environment) = this
}