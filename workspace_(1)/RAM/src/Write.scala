case class Write(src:Int) extends Instruction {
def execute() {
     println("reg[" + src + "] = " + processor.reg(src))
  }
}