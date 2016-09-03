package ui

class UndefinedException(msg1:String) extends JediException("Undefined Identifier : " + msg1){
override def toString = msg1
}