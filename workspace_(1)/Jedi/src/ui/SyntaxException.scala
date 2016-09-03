package ui
import values._
import expressions._
import scala.util.parsing.combinator.RegexParsers
import scala.util.parsing.combinator.Parsers

class SyntaxException(val result: Parsers#Failure = null) extends JediException("Syntax error") {}
