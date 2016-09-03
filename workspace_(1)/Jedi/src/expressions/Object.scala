package expressions
import ui._
import values._

case class Object(locals: List[Expression], ext: Identifier = null) extends SpecialForm {

  def execute(env: Environment): Value =
    {
      if (ext != null && ext.execute(env).isInstanceOf[Environment]) {
        val temp: Environment = new Environment(ext.execute(env).asInstanceOf[Environment])
        for (i <- 0 to locals.length - 1) {
          locals(i).execute(temp)
        }
        temp
      } else {
        val temp: Environment = new Environment(env)

        for (i <- 0 to locals.length - 1) {
          locals(i).execute(temp)
        }
        temp
      }
    }
}

