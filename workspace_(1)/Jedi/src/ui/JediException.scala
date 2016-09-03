package ui
import values._
import expressions._
import scala.util.parsing.combinator.RegexParsers

class JediException(val msg:String) extends Throwable {

}