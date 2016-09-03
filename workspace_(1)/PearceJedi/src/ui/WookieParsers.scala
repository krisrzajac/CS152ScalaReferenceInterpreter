package ui

import scala.util.parsing.combinator._
import expressions._
import values._

class WookieParsers extends RegexParsers {

//  
//  val number = """(\+|-)?[0-9]+(\.[0-9]+)?""".r
//  val trueFalse = """true|false""".r
//  val alphaNum = """[a-zA-Z][a-zA-Z0-9]*""".r  
//  
//  def expression: Parser[Expression] = declaration | conditional | disjunction | failure("Invalid expression")
//  
//  def declaration: Parser[Declaration] = ("def"~>identifier~"="~expression) ^^
//  {
//     case id~"="~exp => Declaration(id, exp)
//  }
//  
//  def conditional: Parser[Conditional] = "if"~"("~expression~")"~expression~opt("else"~expression) ^^
//  {
//    case "if"~"("~e1~")"~e2~None => Conditional(e1, e2)
//    case "if"~"("~e1~")"~e2~Some("else"~e3) => Conditional(e1, e2, e3)
//  }
//  
//  def disjunction: Parser[Expression] = conjunction ~ rep("||" ~> conjunction) ^^ 
//  {
//    case t~Nil=> t
//    case t~rest=> Disjunction(t::rest)
//  }
//  
//  def conjunction: Parser[Expression] = equality ~ rep("&&" ~> equality) ^^ 
//  {
//    case t~Nil=> t
//    case t~rest=> Conjunction(t::rest)
//  }
//  
//  
//  
//  def equality: Parser[Expression] = inequality ~ rep("==" ~> inequality) ^^
//  {
//    case t~Nil=> t
//    case t~rest=> FunCall(Identifier("equals"), t::rest)
//  }
//  
//  def inequality: Parser[Expression] = sum ~ rep("<" ~> sum) ^^
//  {
//    case t~Nil=> t
//    case t~rest=> FunCall(Identifier("less"), t::rest)
//  }
//  
//  // exp -> 0 - exp
//  def negate(exp: Expression): Expression = {
//    val sub = Identifier("sub")
//    val zero = Number(0)
//    FunCall(sub, List(zero, exp))
//  }
//    
//    
//  def sum: Parser[Expression] = product ~ rep(("+"|"-") ~ product ^^ {
//    case "+"~s=>s
//    case "-"~s=> negate(s)
//    })^^{
//    case p~Nil=> p
//    case p~rest=>FunCall(Identifier("add"), p::rest)
//    }
//      
// def invert(exp: Expression): Expression = {
//    val div = Identifier("div")
//    val one = Number(1)
//    FunCall(div, List(one, exp))
//  }
// 
// def product: Parser[Expression] = funcall ~ rep(("*"|"/") ~ funcall ^^ {
//    case "*"~s=>s
//    case "/"~s=>invert(s)
//    })^^{
//    case p~Nil=> p
//    case p~rest=>FunCall(Identifier("mul"), p::rest)
//    }
// 
//  
//  
//  def mkFunCall(f: Expression, ops: List[List[Expression]]): Expression = {
//    if (ops.isEmpty) f
//    else mkFunCall(FunCall(f, ops.head), ops.tail)
//  }
//  
//  
//  def operands: Parser[List[Expression]] = "(" ~> opt(expression ~ rep(","~>expression))<~")" ^^
//  {
//     case None => Nil 
//     case Some(e ~ Nil) => List(e) 
//     case Some(e ~ exps) => e::exps 
//     case _ => Nil
//  }
//  
//  
//   def funcall: Parser[Expression] = term ~ rep(operands) ^^
//  {
//    case t~Nil => t
//    case t~ops => mkFunCall(t, ops)
//  }
//  
//  
//  def term: Parser[Expression] = lambda | block | literal | identifier | "("~>expression<~")" | failure("Invalid expression")
//  
//  def block: Parser[Expression] = "{"~>expression ~ rep(";" ~> expression) <~ "}" ^^ {
//    case e~Nil => Block(List(e))
//    case e ~ more => Block(e::more)
//  }
//  
//  def parameters: Parser[List[Identifier]] = "("~>opt(identifier~rep("," ~> identifier))<~")" ^^ {
//    case None => Nil
//    case Some(id~Nil) => List(id)
//    case Some(id~more) => id::more
//  }
//  def lambda: Parser[Expression] = "lambda" ~> parameters ~ expression ^^ {
//    case params ~ e => Lambda(params, e)
//  }
//  
//  def literal: Parser[Literal] = boole | numeral
//  
//  def numeral: Parser[Number] = number ^^ 
//  {
//    case e => Number(e.toDouble)
//  }
//  
//  def boole: Parser[Boole] = trueFalse ^^ 
//  {
//    case e => Boole(e.toBoolean)
//  }
//  
//  def identifier: Parser[Identifier] = alphaNum ^^ 
//  {
//    case e => Identifier(e)
//  }
//  

  
 
}