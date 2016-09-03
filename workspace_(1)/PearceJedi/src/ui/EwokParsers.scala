package ui

import scala.util.parsing.combinator._
import expressions._
import values._

class EwokParsers extends RegexParsers {

  
//  val number = """(\+|-)?[0-9]+(\.[0-9]+)?""".r
//  val trueFalse = """true|false""".r
//  val alphaNum = """[a-zA-Z][a-zA-Z0-9]*""".r  
  
  def expression: Parser[Expression] = declaration | conditional | disjunction | failure("Invalid expression")
  
  def declaration: Parser[Expression] = "def"~identifier~"="~expression ^^
  {
     case "def"~id~"="~exp => Declaration(id, exp)
  }
  
  def conditional: Parser[Conditional] = "if"~"("~expression~")"~expression~opt("else"~expression) ^^
  {
    case "if"~"("~e1~")"~e2~None => Conditional(e1, e2)
    case "if"~"("~e1~")"~e2~Some("else"~e3) => Conditional(e1, e2, e3)
  }
  
  def disjunction: Parser[Expression] = conjunction ~ rep("||" ~> conjunction) ^^ 
  {
    case t~Nil=> t
    case t~rest=> Disjunction(t::rest)
  }
  
  def conjunction: Parser[Expression] = equality ~ rep("&&" ~> equality) ^^ 
  {
    case t~Nil=> t
    case t~rest=> Conjunction(t::rest)
  }
  
  
  
  def equality: Parser[Expression] = inequality ~ rep("==" ~> inequality) ^^
  {
    case t~Nil=> t
    case t~rest=> FunCall(Identifier("equals"), t::rest)
  }
  
  def inequality: Parser[Expression] = sum ~ rep("<" ~> sum) ^^
  {
    case t~Nil=> t
    case t~rest=> FunCall(Identifier("less"), t::rest)
  }
  
  // exp -> 0 - exp
  def negate(exp: Expression): Expression = {
    val sub = Identifier("sub")
    val zero = Number(0)
    FunCall(sub, List(zero, exp))
  }
    
    
  def sum: Parser[Expression] = product ~ rep(("+"|"-") ~ product ^^ {
    case "+"~s=>s
    case "-"~s=> negate(s)
    })^^{
    case p~Nil=> p
    case p~rest=>FunCall(Identifier("add"), p::rest)
    }
      
 def invert(exp: Expression): Expression = {
    val div = Identifier("div")
    val one = Number(1)
    FunCall(div, List(one, exp))
  }
 
 def product: Parser[Expression] = funcall ~ rep(("*"|"/") ~ funcall ^^ {
    case "*"~s=>s
    case "/"~s=>invert(s)
    })^^{
    case p~Nil=> p
    case p~rest=>FunCall(Identifier("mul"), p::rest)
    }
 
  
  /*
  def mkFunCall(f: Expression, ops: List[List[Expression]]): Expression = {
    if (ops.isEmpty) f
    else mkFunCall(FunCall(f, ops.head), ops.tail)
  }
  * 
  */
  
  
  def operands: Parser[List[Expression]] = "(" ~> opt(expression ~ rep(","~>expression))<~")" ^^
  {
     case None => Nil 
     case Some(e ~ Nil) => List(e) 
     case Some(e ~ exps) => e::exps 
     case _ => Nil
  }
  
  def funcall: Parser[Expression] = term ~ opt(operands) ^^
  {
    case t~None => t
    case t ~ Some(Nil) => FunCall(t.asInstanceOf[Identifier], Nil)
    case t~Some(ops) => FunCall(t.asInstanceOf[Identifier], ops)
  }
  
  /*
   def funcall2: Parser[Expression] = term ~ rep(operands) ^^
  {
    case t~Nil => t
    case t~ops => mkFunCall(t, ops)
  }
  * 
  */
  
  
  def term: Parser[Expression] = literal | identifier | "("~>expression<~")" 
  
  def literal: Parser[Literal] = boole | numeral
  
  def numeral: Parser[Number] = """(\+|-)?[0-9]+(\.[0-9]+)?""".r ^^ 
  {
    case e => Number(e.toDouble)
  }
  
  def boole: Parser[Boole] = """true|false""".r ^^ 
  {
    case e => Boole(e.toBoolean)
  }
  
  def identifier: Parser[Identifier] = """[a-zA-Z][a-zA-Z0-9]*""".r ^^ 
  {
    case e => Identifier(e)
  }
  

  
 
}