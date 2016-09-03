package ui
import scala.util.parsing.combinator._
import expressions. _
import values. _

class Parser extends RegexParsers {
  
  val number = """[0-9]+(.[0-9]+)?""".r
  val trueFalse = """true|false""".r
  val alphaNum = """[a-zA-Z][a-zA-Z0-9]*""".r
  val op0 = """\+|-""".r
  val op1 = """\*|/""".r
  
  
  def infix: Parser[Expression] = sum2 //diff|sum|dis
  
  def sum: Parser[Expression] = term~rep(op0~>term)^^ {
    case t~Nil=>t
    case t~terms=>FunCall(Identifier("add"), t::terms)
  }
  
  def sum2: Parser[Expression] = prod2~opt(op0~infix)^^ {
    case t~None=>t
    case t~Some(op~rest)=>
      {
        op match {
          case "+" => FunCall(Identifier("add"), t::rest::Nil)
          case "-" => FunCall(Identifier("sub"), t::rest::Nil)
        }
      }
  }
  
  def prod2: Parser[Expression] = expression~opt(op1~infix)^^ {
    case t~None=>t
    case t~Some(op~rest)=>
      {
        op match {
          case "*" => FunCall(Identifier("mul"), t::rest::Nil)
          case "/" => FunCall(Identifier("div"), t::rest::Nil)
        }
      }
  }

  
  def diff: Parser[Expression] = term~rep("-"~>term)^^ {
    case t~Nil=>t
    case t~terms=>FunCall(Identifier("sub"), t::terms)
  }
  
  def dis: Parser[Expression] = term~rep("||"~>term)^^ {
    case t~Nil=>t
    case t~terms=>Disjunction(t::terms)
  }
  
  def term: Parser[Expression] = product|quotient|con
  
   def quotient: Parser[Expression] = expression~rep("/"~>expression)^^ {
    case e~Nil=>e
    case e~exps=>FunCall(Identifier("div"), e::exps)
  }
  
  def con: Parser[Expression] = expression~rep("&&"~>expression)^^ {
    case e~Nil=>e
    case e~exps=>Conjunction(e::exps)
  }
  
  def product: Parser[Expression] = expression~rep("*"~>expression)^^ {
    case e~Nil=>e
    case e~exps=>FunCall(Identifier("mul"), e::exps)
  }
 
  def expression: Parser[Expression] = 
    literal | special | funCall | identifier | failure("Invalid expression")
  def literal: Parser[Literal] = truthVal | numeral
  def numeral: Parser[Number] = number ^^ 
  {
    case e => Number(e.toDouble)
  }
  
  def truthVal: Parser[Boole] = trueFalse ^^ 
  {
    case e => Boole(e.toBoolean)
  }
  
  def identifier: Parser[Identifier] = alphaNum ^^ 
  {
    case e => Identifier(e)
  }
  
  def funCall: Parser[FunCall] = (identifier~operands) ^^
  {
    case f ~ ops => FunCall(f, ops)
    
  }
  
  def operands: Parser[List[Expression]] = "(" ~> opt(infix ~ rep(","~>infix))<~")" ^^
  {
    case None => Nil 
    case Some(e ~ Nil) => List(e) 
    case Some(e ~ exps) => e::exps 
    case _ => Nil
  }
  
  def block: Parser[Block] = "{" ~> expression ~ rep(";"~>expression)<~"}" ^^
  {
    case e~Nil => Block(List(e))
    case e~exps => Block(e::exps)
  }
  
  def obj: Parser[Object] = ("object"~> "{" ~> expression ~ rep(";"~>expression)<~"}") ^^
  {
    case e~Nil => Object(List(e))
    case e~exps => Object(e::exps)
  }
  
  
  def special: Parser[SpecialForm] = 
    declaration | lambda | block | obj | assignment | iteration | conditional
  
 /* def access: Parser[Access] = "get"~"("~expression~","~identifier~")" ^^
  {
    case _~_~e~_~id~_ => Access(e, id)
  }
  * *
  */
  
  def declaration: Parser[Declaration] = ("def"~>identifier~"="~infix) ^^
  {
     case id~_~exp => Declaration(id, exp)
  }
  
  def parameters: Parser[List[Identifier]] =  "(" ~> opt(identifier ~ rep(","~>identifier))<~")" ^^
  {
    case None => Nil 
    case Some(e ~ Nil) => List(e) 
    case Some(e ~ exps) => e::exps 
    case _ => Nil
  }
  
  def lambda: Parser[Lambda] = ("lambda"~>"["~>parameters~infix<~"]") ^^
  {
    case ids~bod => Lambda(ids, bod)
    
  }
  
   def conditional: Parser[Conditional] = "if"~"("~infix~")"~infix~opt("else"~infix) ^^
  {
    case "if"~"("~e1~")"~e2~None => Conditional(e1, e2)
    case "if"~"("~e1~")"~e2~Some("else"~e3) => Conditional(e1, e2, e3)
  }
   
   def iteration: Parser[Iteration] = "while"~"("~infix~")"~infix ^^
  {
    case "while"~"("~e1~")"~e2=> Iteration(e1, e2)
  }
   
   def assignment: Parser[Assignment] = "assign"~identifier~infix ^^
   {
     case "assign"~id~e => Assignment(id, e)
   }
  
}