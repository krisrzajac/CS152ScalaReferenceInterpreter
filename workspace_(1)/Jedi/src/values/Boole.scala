package values


import ui._
import expressions._
class Boole(val boole:Boolean)extends Literal with Value {
	def &&(other:Boole):Boole =
	if(boole&&other.boole) new Boole(true)
	else new Boole(false)
	
	def ||(other:Boole):Boole =
	  if(boole||other.boole) new Boole(true)
	  else new Boole(false)
	
	
	def !():Boole =
	  if(boole) new Boole(false) else new Boole(true)
	
	override def toString = boole.toString
	
	def this(str:String)
	{
	    this(str.toBoolean)
	}
	
}
object Boole extends Literal {
  def test(){
    val testT = new Boole(true)
    val testF = new Boole(false)
    println()
    println("testing Boole")
    println("testing && , expected t through t&&t, got ::: " + (testT&&testT))
    println("testing && , expected f through t&&f, got ::: " + (testT&&testF))
    println("testing || , expected t through t&&t, got ::: " + (testT&&testT))
    println("testing || , expected t through t&&f, got ::: " + (testT&&testF))
    println("testing || , expected f through f&&f, got ::: " + (testF&&testF))
    println("testing ! , expected t through !f, got ::: " + testF.!)
    println("testing ! , expected f through !t, got ::: " + testT.!)
  }
  
  
}