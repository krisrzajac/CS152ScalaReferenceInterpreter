package expressions
import values._
import ui._

 
case class Access(rec: Expression, field: Expression) extends SpecialForm {
  
  def execute(env: Environment) = {
    //if (refs.length < 2) throw new TypeException("2 or more identifiers required in a compound identifier")
    val thing = rec.execute(env).asInstanceOf[Environment]
    system.get(thing, field.asInstanceOf[Identifier])
    
    //refs.reduce(combiner(_, _, env))
    
    // a.b.c.d = ((a.b).c).d
  }

}
