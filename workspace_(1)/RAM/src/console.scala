object console {
  
  val parsers = new RAMParsers
    
  def repl() {
    var more = true
    var cmmd = ""
    while(more) {
      print("-> ")
      cmmd = readLine() 
      if (cmmd == "quit") {
        println("bye")
        more = false
      } else {
        val tree = parsers.parseAll(parsers.instruction, cmmd)
        tree match {
          case t: parsers.Failure => {
            println("line # = " + t.next.pos.line)
            println("column # = " + t.next.pos.column)
            println("token = " + t.next.first)
          }
          case _ => "" + tree.get.execute()
        }
        println(processor.toString)
      }
    }
  }

  def main(args: Array[String]): Unit = { repl() }

}