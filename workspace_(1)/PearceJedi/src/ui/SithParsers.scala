package ui

import scala.util.parsing.combinator._
import expressions._
import values._

class SithParsers extends RegexParsers {

  val number = """(\+|-)?[0-9]+(\.[0-9]+)?""".r
  val trueFalse = """true|false""".r
  val alphaNum = """[a-zA-Z][a-zA-Z0-9]*""".r

  def expression: Parser[Expression] = declaration | conditional | disjunction | failure("Invalid expression")

  def declaration: Parser[Declaration] = ("def" ~> identifier ~ "=" ~ expression) ^^
    {
      case id ~ "=" ~ exp => Declaration(id, exp)
    }

  def conditional: Parser[Conditional] = "if" ~ "(" ~ expression ~ ")" ~ expression ~ opt("else" ~ expression) ^^
    {
      case "if" ~ "(" ~ e1 ~ ")" ~ e2 ~ None => Conditional(e1, e2)
      case "if" ~ "(" ~ e1 ~ ")" ~ e2 ~ Some("else" ~ e3) => Conditional(e1, e2, e3)
    }

  def disjunction: Parser[Expression] = conjunction ~ rep("||" ~> conjunction) ^^
    {
      case t ~ Nil => t
      case t ~ rest => Disjunction(t :: rest)
    }

  def conjunction: Parser[Expression] = equality ~ rep("&&" ~> equality) ^^
    {
      case t ~ Nil => t
      case t ~ rest => Conjunction(t :: rest)
    }

  def equality: Parser[Expression] = inequality ~ rep("==" ~> inequality) ^^
    {
      case t ~ Nil => t
      case t ~ rest => new expressions.FunCall(Identifier("equals"), t :: rest)
    }

  def inequality: Parser[Expression] = sum ~ rep("<" ~> sum) ^^
    {
      case t ~ Nil => t
      case t ~ rest => FunCall(Identifier("less"), t :: rest)
    }

  // exp -> 0 - exp
  def negate(exp: Expression): Expression = {
    val sub = Identifier("sub")
    val zero = Number(0)
    FunCall(sub, List(zero, exp))
  }

  def sum: Parser[Expression] = product ~ rep(("+" | "-") ~ product ^^ {
    case "+" ~ s => s
    case "-" ~ s => negate(s)
  }) ^^ {
    case p ~ Nil => p
    case p ~ rest => FunCall(Identifier("add"), p :: rest)
  }

  def invert(exp: Expression): Expression = {
    val div = Identifier("div")
    val one = Number(1)
    FunCall(div, List(one, exp))
  }

  def product: Parser[Expression] = funcall ~ rep(("*" | "/") ~ funcall ^^ {
    case "*" ~ s => s
    case "/" ~ s => invert(s)
  }) ^^ {
    case p ~ Nil => p
    case p ~ rest => FunCall(Identifier("mul"), p :: rest)
  }

  def operands: Parser[List[Expression]] = "(" ~> opt(expression ~ rep("," ~> expression)) <~ ")" ^^
    {
      case None => Nil
      case Some(e ~ Nil) => List(e)
      case Some(e ~ exps) => e :: exps
      case _ => Nil
    }

  def funcall: Parser[Expression] = access ~ opt(operands) ^^
    {
      case t ~ None => t
      case t ~ Some(ops) => FunCall(t, ops)
    }

  def term: Parser[Expression] = obj | assignment | iteration|dowhile | deref | lambda | block | literal | identifier | "(" ~> expression <~ ")"

  def block: Parser[Expression] = "{" ~> expression ~ rep(";" ~> expression) <~ "}" ^^ {
    case e ~ Nil => Block(List(e))
    case e ~ more => Block(e :: more)
  }

  def parameters: Parser[List[Identifier]] = "(" ~> opt(identifier ~ rep("," ~> identifier)) <~ ")" ^^ {
    case None => Nil
    case Some(id ~ Nil) => List(id)
    case Some(id ~ more) => id :: more
  }
  def lambda: Parser[Expression] = "lambda" ~> parameters ~ expression ^^ {
    case params ~ e => Lambda(params, e)
  }

  def literal: Parser[Literal] = boole | numeral

  def numeral: Parser[Number] = number ^^
    {
      case e => Number(e.toDouble)
    }
  

  def boole: Parser[Boole] = trueFalse ^^
    {
      case e => Boole(e.toBoolean)
    }

  def identifier: Parser[Identifier] = alphaNum ^^
    {
      case e => Identifier(e)
    }

  def iteration: Parser[Expression] = "while" ~ "(" ~ expression ~ ")" ~ expression ^^
    {
      case "while" ~ "(" ~ e1 ~ ")" ~ e2 => Iteration(e1, e2)
    }
def dowhile: Parser[Expression] = "do" ~ expression ~ "while" ~ "(" ~ expression ~ ")" ^^
    {
      case "do" ~ body ~ "while" ~ "(" ~ condition ~ ")" => DoWhile(body, condition)
    }

  def assignment: Parser[Expression] = identifier ~ "=" ~ expression ^^
    {
      case id ~ _ ~ e => Assignment(id, e)
    }

  def deref: Parser[Expression] = "[" ~> expression <~ "]" ^^ {
    case e => FunCall(Identifier("val"), List(e))
  }

  //def obj: Parser[Expression] = "object"~ "{" ~> expression ~ rep(";"~>expression)<~"}"~opt("extends" ~> identifier) ^^

  def obj: Parser[Expression] = "object" ~ "{" ~ expression ~ rep(";" ~> expression) ~ "}" ~ opt("extends" ~> identifier) ^^
    {
      case "object" ~ "{" ~ e ~ exps ~ "}" ~ None => Object(e :: exps)
      case "object" ~ "{" ~ e ~ exps ~ "}" ~ Some(id) => Object(e :: exps, id)
      // case "object" ~ e~exps~Some(id) => Object(List(e::exps), id)
      //case e~exps~None => Object(e::exps)
      //case e~exps~Some(id) => Object(e::exps, id)
    }

  def access: Parser[Expression] = term ~ opt("." ~> identifier) ^^
    {
      case t ~ None => t
      case t ~ Some(e) => Access(t, e)
    }

  val thunkBody =
    "{def cache = var(0); def cacheReady = var(false); lambda() { if(not([cacheReady])) { cache = EXP; cacheReady = true }; [cache]}}"

  val body = """EXP""".r

}