package expressions
import ui._
import values._
trait Expression {
	def execute(env: Environment): Value
}