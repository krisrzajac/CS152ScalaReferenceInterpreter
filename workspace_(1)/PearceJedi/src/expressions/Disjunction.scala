package expressions

import values._

case class Disjunction(operands: List[Expression]) extends SpecialForm {
  
  def execute(env: Environment): Value = {
    def helper(result: Boole, unseen: List[Expression]): Boole =
      if (unseen.isEmpty) result
      else if (unseen.head.execute(env).asInstanceOf[Boole].value) Boole(true)
      else helper(result, unseen.tail)
      
   helper(Boole(false), operands)
  }

}