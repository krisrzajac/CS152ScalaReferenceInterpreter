package ui

import scala.util.parsing.combinator._
import expressions._
import values._

class WookieParsers extends RegexParsers {

  def expression: Parser[Expression] = declaration | conditional | disjunction | failure("Invalid expression")
  def declaration: Parser[Declaration] =
    "def" ~ identifier ~ "=" ~ expression ^^
      {
        case "def" ~ id ~ "=" ~ exp => Declaration(id, exp)
      }

  def conditional: Parser[Expression] =
    "if" ~ "(" ~ expression ~ ")" ~ expression ~ opt("else" ~> expression) ^^
      {
        case "if" ~ "(" ~ exp1 ~ ")" ~ exp2 ~ None => Conditional(exp1, exp2)
        case "if" ~ "(" ~ exp1 ~ ")" ~ exp2 ~ Some(exp3) => Conditional(exp1, exp2, exp3)
      }

  def disjunction: Parser[Expression] =
    conjunction ~ rep("||" ~> conjunction) ^^
      {
        case exp ~ Nil => exp
        case exp ~ exp2 => Disjunction(exp :: exp2)
      }

  def conjunction: Parser[Expression] =
    equality ~ rep("&&" ~> equality) ^^
      {
        case exp ~ Nil => exp
        case exp ~ exp2 => Conjunction(exp :: exp2)
      }

  def equality: Parser[Expression] =
    inequality ~ rep("==" ~> inequality) ^^
      {
        case exp ~ Nil => exp
        case exp ~ exp2 => FunCall(Identifier("equals"), exp :: exp2)
      }

  def inequality: Parser[Expression] =
    sum ~ rep(("<") ~> sum) ^^
      {
        case exp ~ Nil => exp
        case exp ~ exp2 => FunCall(Identifier("less"), exp :: exp2)
      }

  def sum: Parser[Expression] =
    product ~ rep(("+" | "-") ~ product ^^
      {
        case "+" ~ s => s
        case "-" ~ s => negate(s)
      }) ^^
      {
        case p ~ Nil => p
        case p ~ rest => FunCall(Identifier("add"), p :: rest)
      }

  def product: Parser[Expression] = funcall ~ rep(("*" | "/") ~ funcall ^^
    {
      case "*" ~ s => s
      case "/" ~ s => invert(s)
    }) ^^
    {
      case p ~ Nil => p
      case p ~ rest => FunCall(Identifier("mul"), p :: rest)
    }

  def funcall: Parser[Expression] =
    term ~ opt(operands) ^^
      {
        case id ~ None => id
        case id ~ Some(ops: List[Expression]) => FunCall(id, ops)
      }

  def operands: Parser[List[Expression]] =
    "(" ~> opt(expression ~ rep("," ~> expression)) <~ ")" ^^
      {
        case None => List()
        case Some(op ~ opList) => op :: opList
      }

  def term: Parser[Expression] = lambda | block | literal | identifier | "(" ~> expression <~ ")"

  /*def disjunction: Parser[Expression] =
    conjunction ~ rep("||" ~> conjunction) ^^
      {
        case exp ~ Nil => exp
        case exp ~ exp2 => Disjunction(exp :: exp2)
      }*/
  def lambda: Parser[Lambda] = "lambda" ~ parameters ~ expression ^^
    {
      case "lambda" ~ p ~ e => Lambda(p, e)
    }

  def parameters: Parser[List[Identifier]] =
    "(" ~> opt(identifier ~ rep("," ~> identifier)) <~ ")" ^^
      {
        case None => Nil
        case Some(e ~ Nil) => List(e)
        case Some(e ~ exps) => e :: exps
        case _ => Nil
      }

  def block: Parser[Block] =
    "{" ~> expression ~ rep(";" ~> expression) <~ "}" ^^
      {

        case exp ~ Nil => Block(List(exp))
        case exp ~ exp2 => Block(exp :: exp2)
        case _ => Block(Nil)
      }

  def literal: Parser[Literal] = boole | number

  def identifier: Parser[Identifier] = """[a-zA-Z][a-zA-Z0-9]*""".r ^^
    {
      case t => Identifier(t.toString)
    }

  def boole: Parser[Boole] = ("true" | "false") ^^
    {
      case "true" => new Boole(true)
      case "false" => new Boole(false)

    }

  def number: Parser[Number] = """-?[0-9]+\.?[0-9]*""".r ^^
    {
      case n => new Number(n)

    }

  def negate(exp: Expression): Expression = {
    val sub = Identifier("sub")
    val zero = new Number(0)
    FunCall(sub, List(zero, exp))
  }

  def invert(exp: Expression): Expression = {
    val div = Identifier("div")
    val one = new Number(1)
    FunCall(div, List(one, exp))
  }

}