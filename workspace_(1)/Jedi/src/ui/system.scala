package ui
import values._
import expressions._

object system {
  def execute(opcode: Identifier, args: List[Value]): Value = {
    opcode.name match {
      case "add" => add(args)
      case "sub" => sub(args)
      case "mul" => mul(args)
      case "div" => div(args)
      case "equals" => equals(args)
      case "less" => less(args)
      case "not" => not(args)
      case "var" => makeVar(args)
      case "content" => contentdd(args)
      // mul, sub, div, equals, less, etc.
      case _ => throw new UndefinedException(opcode.toString)
    }
  }

  private def add(vals: List[Value]): Value = {
    if (vals.isEmpty) throw new TypeException("addition expects > 0 inputs")
    val ok = vals.filter(_.isInstanceOf[Number])
    if (ok.length < vals.length) throw new TypeException("all addition inputs must be numbers")
    val args2 = vals.map(_.asInstanceOf[Number])
    args2.reduce(_ + _)
  }

  private def mul(vals: List[Value]): Value =
    {
      if (vals.isEmpty) throw new TypeException("addition expects > 0 inputs")
      val ok = vals.filter(_.isInstanceOf[Number])
      if (ok.length < vals.length) throw new TypeException("all multipliation inputs must be numbers")
      val args2 = vals.map(_.asInstanceOf[Number])
      args2.reduce(_ * _)
    }
  private def div(vals: List[Value]): Value =
    {
      if (vals.isEmpty) throw new TypeException("division expects > 0 inputs")
      val ok = vals.filter(_.isInstanceOf[Number])
      if (ok.length < vals.length) throw new TypeException("all division inputs must be numbers")
      val args2 = vals.map(_.asInstanceOf[Number])

      args2.reduce(_ / _)
    }
  private def sub(vals: List[Value]): Value =
    {
      if (vals.isEmpty) throw new TypeException("subtraction expects > 0 inputs")
      val ok = vals.filter(_.isInstanceOf[Number])
      if (ok.length < vals.length) throw new TypeException("all subtraction inputs must be numbers")
      val args2 = vals.map(_.asInstanceOf[Number])
      args2.reduce(_ - _)
    }

  private def less(vals: List[Value]): Value =
    {
      if (vals.isEmpty) throw new TypeException("less then expects at least 2 inputs")
      else if (vals.length == 1) throw new TypeException("less then excepts at least 2 inputs")
      val ok = vals.filter(_.isInstanceOf[Number])
      if (ok.length < vals.length) throw new TypeException("all less then inputs must be numbers")
      val args2 = vals.map(_.asInstanceOf[Number])
      var isLess = true;
      for (i <- 0 to args2.length - 2) {
        if ((args2(i).asInstanceOf[Number] < args2(i + 1).asInstanceOf[Number]).boole && isLess == true)
          isLess = true;
        else
          isLess = false
      }
      new Boole(isLess)
    }

  private def equals(vals: List[Value]): Value =
    {
      if (vals.isEmpty) throw new TypeException("equals expects > 0 inputs")
      val ok = vals.filter(_.isInstanceOf[Number])
      if (ok.length < vals.length) throw new TypeException("all equals inputs must be numbers")
      val args2 = vals.map(_.asInstanceOf[Number])
      var isEqual = true;
      for (i <- 0 to args2.length - 2) {
        if ((args2(i).asInstanceOf[Number] == args2(i + 1).asInstanceOf[Number]).boole && isEqual == true)
          isEqual = true;
        else
          isEqual = false
      }
      new Boole(isEqual)
    }

  private def not(vals: List[Value]): Value =
    {
      if (vals.isEmpty) throw new TypeException("not expects > 0 inputs")

      if (vals.head.asInstanceOf[Boole].boole == true)
        new Boole(false)
      else new Boole(true)
    }

  private def contentdd(args: List[Value]) =
    {
      if (args.head.isInstanceOf[Environment])
        args.head.asInstanceOf[Environment]
      
      else if (args.head.isInstanceOf[Variable])
        args.head.asInstanceOf[Variable].content
        
      else if (args.head.isInstanceOf[Notification])
        args.head.asInstanceOf[Notification]
      
      else throw new TypeException("cant deref var")
    }

  private def makeVar(args: List[Value]) =
    {

      if (args.head.isInstanceOf[Number])
        new Variable(args.head.asInstanceOf[Number])

      else if (args.head.isInstanceOf[Closure])
        new Variable(args.head.asInstanceOf[Closure])

      else if (args.head.isInstanceOf[Boole])
        new Variable(args.head.asInstanceOf[Boole])

      else if (args.head.isInstanceOf[Variable])
        new Variable(args.head.asInstanceOf[Variable])

      else throw new TypeException("cant make var")
    }

}