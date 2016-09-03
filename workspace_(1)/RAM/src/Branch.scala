case class Branch(cond:Int, label:Int)extends Instruction {
def execute()
{
  if(processor.reg(cond) != 0)
  {
    processor.pc = label
  }
    
}


}