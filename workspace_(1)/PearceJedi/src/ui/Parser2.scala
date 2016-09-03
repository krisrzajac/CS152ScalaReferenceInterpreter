package ui

import scala.util.parsing.combinator._
import expressions. _
import values. _

class Parser2 extends RegexParsers {
  
  val number = """[0-9]+(.[0-9]+)?""".r
  val trueFalse = """true|false""".r
  val alphaNum = """[a-zA-Z][a-zA-Z0-9]*""".r
  val op0 = """\+|-|\|~|<|\=|\|""".r
  val op1 = """\*|/|&""".r
  
  def expression: Parser[Expression] = (term ~ opt(op0 ~ expression)) ^^ // | failure("Invalid expression") //(failure("Invalid expression")) ^^
  {
    case t~None=>t
    case t~Some(op~rest)=>
      {
        op match {
          case "+" => FunCall(Identifier("add"), t::rest::Nil)
          case "-" => FunCall(Identifier("sub"), t::rest::Nil)
          case "=" => FunCall(Identifier("equals"), t::rest::Nil)
          case "<" => FunCall(Identifier("less"), t::rest::Nil)
          case "|" => Disjunction(t::rest::Nil)
          
        }
      }
  }
  
  def term: Parser[Expression] = factor ~ opt(op1 ~ expression) ^^
  {
     case t~None=>t
     case t~Some(op~rest)=>
      {
        op match {
          case "*" => FunCall(Identifier("mul"), t::rest::Nil)
          case "/" => FunCall(Identifier("div"), t::rest::Nil)
          case "&" => Conjunction(t::rest::Nil)
          //case "#" => Access(t, rest)
        }
      }
  }
  
  def factor: Parser[Expression] = literal | special | funCall | identifier | "("~>expression<~")" | failure("Invalid expression")
  
  def literal: Parser[Literal] = truthVal | numeral
  
  def numeral: Parser[Number] = number ^^ 
  {
    case e => Number(e.toDouble)
  }
  
  def truthVal: Parser[Boole] = trueFalse ^^ 
  {
    case e => Boole(e.toBoolean)
  }
  
  /*def identifier: Parser[Identifier] = alphaNum ^^ 
  {
    case e => Identifier(e)
  }
  * 
  */
  
  def identifier: Parser[Identifier] = alphaNum ^^ 
  {
    case e => Identifier(e)
  }
  
  
  def funCall: Parser[FunCall] = (identifier~operands) ^^
  {
    case f ~ ops => FunCall(f, ops)
  }
  
  def operands: Parser[List[Expression]] = "(" ~> opt(expression ~ rep(","~>expression))<~")" ^^
  {
    case None => Nil 
    case Some(e ~ Nil) => List(e) 
    case Some(e ~ exps) => e::exps 
    case _ => Nil
  }
  
  def special: Parser[SpecialForm] = 
    declaration | lambda | block | obj | access | assignment | iteration | conditional |  conjunction | disjunction
  
  def access: Parser[Access] = "get"~"("~expression~","~identifier~")" ^^
  {
    case _~_~e~_~id~_ => Access(e, id)
  }
 /*
  
  def access2: Parser[Access] = identifier~"#"~identifier~rep("#"~identifier) ^^
  {
   // case None => Access(List[Identifier]())
    case id1~ _ ~ id2 ~ Nil => Access(List(id1, id2)) 
   // case id1~_~id2 ~ Some(_~exps) => Access(id1::id2::exps) 
    case _ => Access(List[Identifier]())
  }
 
       
    def access3: Parser[Access] = identifier~"#"~expression ^^
    {
       case i~_~e => Access(i, e)
    }
    * *
    */
  
  def declaration: Parser[Declaration] = ("def"~>identifier~"="~expression) ^^
  {
     case id~_~exp => Declaration(id, exp)
  }
  
  def block: Parser[Block] = "{" ~> expression ~ rep(";"~>expression)<~"}" ^^
  {
    case e~Nil => Block(List(e))
    case e~exps => Block(e::exps)
  }
  
  def parameters: Parser[List[Identifier]] =  "(" ~> opt(identifier ~ rep(","~>identifier))<~")" ^^
  {
    case None => Nil 
    case Some(e ~ Nil) => List(e) 
    case Some(e ~ exps) => e::exps 
    case _ => Nil
  }
  
  def lambda: Parser[Lambda] = ("lambda"~>parameters~expression) ^^ //("lambda"~>"["~>parameters~expression<~"]") ^^
  {
    case ids~body => Lambda(ids, body)  
  }
  
  def obj: Parser[Object] = ("object"~> "{" ~> expression ~ rep(";"~>expression)<~"}") ^^
  {
    case e~Nil => Object(List(e))
    case e~exps => Object(e::exps)
  }
  
  
   def conditional: Parser[Conditional] = "if"~"("~expression~")"~expression~opt("else"~expression) ^^
  {
    case "if"~"("~e1~")"~e2~None => Conditional(e1, e2)
    case "if"~"("~e1~")"~e2~Some("else"~e3) => Conditional(e1, e2, e3)
  }
   
   def iteration: Parser[Iteration] = "while"~"("~expression~")"~expression ^^
  {
    case "while"~"("~e1~")"~e2=> Iteration(e1, e2)
  }
   
   def assignment: Parser[Assignment] = "assign"~identifier~expression ^^
   {
     case "assign"~id~e => Assignment(id, e)
   }
    
   def disjunction: Parser[Disjunction] = ("or" ~> operands) ^^ {
    case exps=>Disjunction(exps)
  }
  
 
  
  def conjunction: Parser[Conjunction] = ("and" ~> operands) ^^ {
    case exps=>Conjunction(exps)
  }
  
 
}