
case class Block(ins:List[Instruction])extends Instruction {
def execute()
{
  processor.run(ins)
  
}
}