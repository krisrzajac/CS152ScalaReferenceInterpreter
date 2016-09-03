

case class Add(dest:Int, src1:Int, src2:Int) extends Instruction {
def execute()
{
  
  processor.reg(dest) = processor.reg(src1)+processor.reg(src2)
}
}