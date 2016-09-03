package expressions

import values._

case class Conjunction(operands: List[Expression]) extends SpecialForm {
  
  def execute(env: Environment): Value = {
    def helper(result: Boole, unseen: List[Expression]): Boole =
      if (unseen.isEmpty) result
      else if (!unseen.head.execute(env).asInstanceOf[Boole].value) Boole(false)
      else helper(result, unseen.tail)
      
   helper(Boole(true), operands)
  }

}