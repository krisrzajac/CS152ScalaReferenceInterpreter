case class Load(dest:Int, value:Int) extends Instruction {
def execute()
{
  processor.reg(dest) = value
}
}