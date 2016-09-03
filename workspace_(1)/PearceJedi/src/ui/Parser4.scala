package ui

import scala.util.parsing.combinator._
import expressions. _
import values. _

class Parser4 extends RegexParsers {

//  
//  val number = """(\+|-)?[0-9]+(\.[0-9]+)?""".r
//  val trueFalse = """true|false""".r
//  val alphaNum = """[a-zA-Z][a-zA-Z0-9]*""".r  
//  
//  def expression: Parser[Expression] = declaration | conditional | disjunction | failure("Invalid expression")
//  
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
//  // exp -> 1/exp
//  def negate(exp: Expression): Expression = {
//    val sub = Identifier("sub")
//    val zero = Number(0)
//    FunCall(sub, List(zero, exp))
//  }
//    
//    
//  def sum: Parser[Expression] = product ~ rep(("+"|"-") ~ product ^^ {
//    case "+"~s=>s
//    case "-"~s=>negate(s)
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
//  def field: Parser[Expression] = term ~ opt("." ~ field) ^^
//  {
//    case t~None=> t
//    case t~Some(op~rest)=> Access(t, rest)
//  }
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
//   def funcall: Parser[Expression] = field ~ rep(operands) ^^
//  {
//    case t~Nil => t
//    case t~ops => mkFunCall(t, ops)
//  }
//  
//  
//  def term: Parser[Expression] = literal | special | identifier | "("~>expression<~")" | failure("Invalid expression")
//  
//  def literal: Parser[Literal] = truthVal | numeral
//  
//  def numeral: Parser[Number] = number ^^ 
//  {
//    case e => Number(e.toDouble)
//  }
//  
//  def truthVal: Parser[Boole] = trueFalse ^^ 
//  {
//    case e => Boole(e.toBoolean)
//  }
//  
//  def identifier: Parser[Identifier] = alphaNum ^^ 
//  {
//    case e => Identifier(e)
//  }
//  
//
//  
//  def special: Parser[SpecialForm] = 
//    lambda | block | obj | access | assignment | iteration 
//  
//  def access: Parser[Access] = "get"~"("~expression~","~identifier~")" ^^
//  {
//    case _~_~e~_~id~_ => Access(e, id)
//  }
//  
//  def deref: Parser[Expression] = "["~>identifier<~"]" ^^ {
//    case id => FunCall(Identifier("val"), List(id))
//  }
//
//  
//  //def access2: Parser[Access] = identifier~"#"~identifier~rep("#"~identifier) ^^
//  //{
//   // case None => Access(List[Identifier]())
//   // case id1~ _ ~ id2 ~ Nil => Access(List(id1, id2)) 
//   // case id1~_~id2 ~ Some(_~exps) => Access(id1::id2::exps) 
//   // case _ => Access(List[Identifier]())
// // }
// 
//       
//    def access3: Parser[Access] = identifier~"#"~expression ^^
//    {
//       case i~_~e => Access(i, e)
//    }
//
//  
//  def declaration: Parser[Declaration] = ("def"~>identifier~"="~expression) ^^
//  {
//     case id~_~exp => Declaration(id, exp)
//  }
//  
//  def block: Parser[Block] = "{" ~> expression ~ rep(";"~>expression)<~"}" ^^
//  {
//    case e~Nil => Block(List(e))
//    case e~exps => Block(e::exps)
//  }
//  
//  def parameters: Parser[List[Identifier]] =  "(" ~> opt(identifier ~ rep(","~>identifier))<~")" ^^
//  {
//    case None => Nil 
//    case Some(e ~ Nil) => List(e) 
//    case Some(e ~ exps) => e::exps 
//    case _ => Nil
//  }
//  
//  def lambda: Parser[Lambda] = ("lambda"~>parameters~expression) ^^ //("lambda"~>"["~>parameters~expression<~"]") ^^
//  {
//    case ids~body => Lambda(ids, body)  
//  }
//  
//  def obj: Parser[Object] = ("object"~> "{" ~> expression ~ rep(";"~>expression)<~"}") ^^
//  {
//    case e~Nil => Object(List(e))
//    case e~exps => Object(e::exps)
//  }
//  
//  
//   def conditional: Parser[Conditional] = "if"~"("~expression~")"~expression~opt("else"~expression) ^^
//  {
//    case "if"~"("~e1~")"~e2~None => Conditional(e1, e2)
//    case "if"~"("~e1~")"~e2~Some("else"~e3) => Conditional(e1, e2, e3)
//  }
//   
//   def iteration: Parser[Iteration] = "while"~"("~expression~")"~expression ^^
//  {
//    case "while"~"("~e1~")"~e2=> Iteration(e1, e2)
//  }
//   
//   def assignment2: Parser[Assignment] = "assign"~identifier~expression ^^
//   {
//     case "assign"~id~e => Assignment(id, e)
//   }
//    
//   def assignment: Parser[Assignment] = identifier~"="~expression ^^
//   {
//     case id~_~e => Assignment(id, e)
//   }
//    
//
//  
 
}