package ui

import scala.util.parsing.combinator._
import expressions. _
import values. _

class Parser3 extends RegexParsers {

  /*
  val number = """[0-9]+(.[0-9]+)?""".r
  val trueFalse = """true|false""".r
  val alphaNum = """[a-zA-Z][a-zA-Z0-9]*""".r  
  
  def expression: Parser[Expression] = disjunction | failure("Invalid expression")
  
  def disjunction: Parser[Expression] = conjunction ~ opt("||" ~ disjunction) ^^ 
  {
    case t~None=> t
    case t~Some(op~rest)=> Disjunction(List(t, rest))
  }
  

  def conjunction: Parser[Expression] = equality ~ opt("&&" ~ conjunction) ^^ 
  {
    case t~None=> t
    case t~Some(op~rest)=> Conjunction(List(t, rest))
  }
  
  def equality: Parser[Expression] = inequality ~ opt("==" ~ equality) ^^
  {
    case t~None=> t
    case t~Some(op~rest)=> FunCall(Identifier("equals"), List(t, rest))
  }
  def inequality: Parser[Expression] = sum ~ opt("<" ~ equality) ^^
  {
    case t~None=> t
    case t~Some(op~rest)=> FunCall(Identifier("less"), List(t, rest))
  }
  val plus = """\+|-""".r
  
  def negate(exps: List[Expression]): List[Expression] = {
    var result = List[Expression]()
    val sub = Identifier("sub")
    val zero = Number(0)
    for(e<-exps) {
      result = FunCall(sub, List(zero, e))::result
    }
    result.reverse
  }
  
  def negate(exp: Expression): Expression = {
    val sub = Identifier("sub")
    val zero = Number(0)
    FunCall(sub, List(zero, exp))
  }
    
    
  def sum: Parser[Expression] = product ~ opt(plus ~ sum) ^^
  {
    case t~None=> t
    case t~Some(op~rest)=>
      {
      op match {
        case "+" => FunCall(Identifier("add"), List(t, rest))
        case "-" => FunCall(Identifier("add"), List(t, negate(rest)))
        case _ => throw new JediException()
      }
      }
  }
  val times = """\*|/""".r
  def product: Parser[Expression] = field ~ opt(times ~ product) ^^
  {
    case t~None=> t
    case t~Some(op~rest)=> 
      {
      op match {
        case "*" => FunCall(Identifier("mul"), List(t, rest))
        case "/" => FunCall(Identifier("div"), List(t, rest))
      }
      }
  }
  
  val fld = """#""".r
  def field: Parser[Expression] = funcall ~ opt("." ~ field) ^^
  {
    case t~None=> t
    case t~Some(op~rest)=> Access(t, rest)
  }
  
  def mkFunCall(f: Expression, ops: List[List[Expression]]): Expression = {
   // if (!f.isInstanceOf[FunCall]) throw new JediException("operator must be a function")
    if (ops.isEmpty) f
    else mkFunCall(FunCall(f, ops.head), ops.tail)
  }
  
  def operands: Parser[List[Expression]] = "(" ~> opt(expression ~ rep(","~>expression))<~")" ^^
  {
     case None => Nil 
     case Some(e ~ Nil) => List(e) 
     case Some(e ~ exps) => e::exps 
     case _ => Nil
  }
  def funcall2: Parser[Expression] = term ~ opt(operands) ^^
  {
    case t~None => t
    case t~Some(ops) => FunCall(t, ops)
  }
  
   def funcall: Parser[Expression] = term ~ rep(operands) ^^
  {
    case t~Nil => t
    case t~ops => mkFunCall(t, ops)
  }
  
  
  def term: Parser[Expression] = literal | special | identifier | "("~>expression<~")" | failure("Invalid expression")
  
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
  

  
  def special: Parser[SpecialForm] = 
    declaration | lambda | block | obj | access | assignment | iteration | conditional 
  
  def access: Parser[Access] = "get"~"("~expression~","~identifier~")" ^^
  {
    case _~_~e~_~id~_ => Access(e, id)
  }

  
  //def access2: Parser[Access] = identifier~"#"~identifier~rep("#"~identifier) ^^
  //{
   // case None => Access(List[Identifier]())
   // case id1~ _ ~ id2 ~ Nil => Access(List(id1, id2)) 
   // case id1~_~id2 ~ Some(_~exps) => Access(id1::id2::exps) 
   // case _ => Access(List[Identifier]())
 // }
 
       
    def access3: Parser[Access] = identifier~"#"~expression ^^
    {
       case i~_~e => Access(i, e)
    }

  
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
    

  
  */
 
}