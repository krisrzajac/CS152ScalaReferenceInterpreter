package values
import ui._
import expressions._

// we can do better

object system {
  
  def execute(opcode: Identifier, args: List[Value]): Value = {
    opcode.name match {
      case "add" => add(args)
      case "mul" => mul(args)
      case "sub" => sub(args)
      case "div" => div(args)
      case "less" => less(args)
      case "equals" => equals(args)
      case "not" => not(args)
      case "get" => get(args(0).asInstanceOf[Environment], args(1).asInstanceOf[Identifier])
      case "var" => makeVar(args)
      case "val" => getContent(args)
      case "write" => write(args)
      case "prompt" => prompt(args)
      case "read" => read(args)
      case _ => throw new UndefinedException(opcode.name)
    }
  }
  
  def getContent(args: List[Value]) = {
    if (args.length != 1) throw new TypeException("content needs a single argument")
    if (!args(0).isInstanceOf[Variable])  throw new TypeException("content needs a variable argument")
    args(0).asInstanceOf[Variable].getContent
  }
  
  def makeVar(args: List[Value]) = Variable(args(0))
  
  def get(obj: Environment, member: Identifier): Value = {
    //println("obj = " + obj + " member = " + member)
    obj.find(member)
  }
  
  def get(env: Environment, members: List[Identifier]): Value = {
    var result = members.head.execute(env) 
    for (field <- members.tail) {
      result = get(result.asInstanceOf[Environment], field.asInstanceOf[Identifier])
    }
    result
  }
  
  private def add2(vals: List[Value]): Value = {
    if (vals.isEmpty) throw new TypeException("add expected > 0 inputs")
     vals match {
       case nums: List[Number] => nums.reduce(_+_)
       case _ => throw new TypeException("add expected args: List[Number]")
     }
  }
  
  private def add(vals: List[Value]): Value = {
    if (vals.isEmpty) throw new TypeException("add expected > 0 inputs")
    val ok = vals.filter(_.isInstanceOf[Number])
    if (ok.length < vals.length) throw new TypeException("add inputs must be numbers")
    val args2 = vals.map(_.asInstanceOf[Number])
    args2.reduce(_+_)
  }
  
  private def mul(vals: List[Value]): Value = {
     if (vals.isEmpty) throw new TypeException("mul expected > 0 inputs")
     vals match {
       case nums: List[Number] => nums.reduce(_*_)
       case _ => throw new TypeException("mul expected args: List[Number]")
     }
  }
  
  def sub(vals: List[Value]): Value = {
     if (vals.isEmpty) throw new TypeException("sub expected > 0 inputs")
     vals match {
       case nums: List[Number] => nums.reduce(_-_)
       case _ => throw new TypeException("sub expected args: List[Number]")
     }
  }
  
  def div(vals: List[Value]): Value = {
     if (vals.isEmpty) throw new TypeException("div expected > 0 inputs")
     vals match {
       case nums: List[Number] => nums.reduce(_/_)
       case _ => throw new TypeException("sub expected args: List[Number]")
     }
  }
  
  def less(vals: List[Value]): Value = {
    if (vals.isEmpty) throw new TypeException("less expected > 0 inputs")
    val ok = vals.filter(_.isInstanceOf[Number])
    if (ok.length < vals.length) throw new TypeException("add inputs must be numbers")
    val args2 = vals.map(_.asInstanceOf[Number])
    var result = true
    for(i <- 0 until args2.length - 1) if (!((args2(i) < args2(i + 1))).value) result = false
    Boole(result)
     //new Boole((vals(0).asInstanceOf[Number] < vals(1).asInstanceOf[Number]).value)
  }
  
  def equals(vals: List[Value]): Value = {
     if (vals.isEmpty) throw new TypeException("equals expected > 0 inputs")
     var result = true
     for(i <- 1 until vals.length) if (vals(i) != vals(0)) result = false
     Boole(result)
     //Boole(vals.reduce(_==_))
     //new Boole(vals(0).equals(vals(1)))
  }
  
   def not(vals: List[Value]): Value = {
     if (vals.length != 1) throw new TypeException("not expected 1 input")
     if (!vals(0).isInstanceOf[Boole]) throw new TypeException("input to not must be Boole")
     (vals(0).asInstanceOf[Boole]).not
  }
   
   def write(vals: List[Value]): Value = { println(vals(0)); Notification.DONE }
   def read(vals: List[Value]): Value = { val result = readDouble(); Number(result)}
   def prompt(vals: List[Value]): Value = { print("=> "); Notification.DONE }

}