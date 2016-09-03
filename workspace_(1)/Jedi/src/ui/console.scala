package ui

import values._
import expressions._
import scala.util.parsing.combinator.RegexParsers
import scala.util.parsing.combinator.Parsers
object console {
   val parsers = new SithParsers // for now
   val globalEnv = new Environment()

   def execute(cmmd: String): String = {
      val tree = parsers.parseAll(parsers.expression, cmmd)
      tree match {
         case t: parsers.Failure => throw new SyntaxException(t)
         case _ => "result = " + tree.get.execute(globalEnv)
      }
   }
   
    def repl {
      // declare locals
      var more = true
      var cmmd:String = ""
      while(more) {
         try {
            cmmd = readLine("---> ")
            if(cmmd != "quit")
            {
              
              println(execute(cmmd))
              
            }
            else more = false
         } 
         catch {
            case e: SyntaxException => {
               println(e.msg)
               println(e.result.msg)
               println("line # = " + e.result.next.pos.line)
               println("column # = " + e.result.next.pos.column)
               println("token = " + e.result.next.first)
            }
            case e: UndefinedException =>{
               println(e.msg)


            }
            case e: TypeException =>{
               println(e.msg)
            }
            
              
         } finally {
            Console.flush 
         }
      }
   }
    
   def main(args: Array[String]): Unit = { repl }
}