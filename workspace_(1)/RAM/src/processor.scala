object processor {
  val reg = new Array[Int](8)
  var pc = 0

  override def toString = {
    var result = "pc = " + pc
    for(i <- 0 until 8) result = result + " reg[" + i + "] = " + reg(i)
    result
  }
  
  def run(program: List[Instruction]) {
    pc = 0
    var next: Instruction = null
    while(pc < program.length) {
      next = program(pc)
      pc = pc + 1
      next.execute()
    }
    println("Program halted")
  }
}