package values

import ui._
import expressions._

class Number(val value: Double) extends Literal with Value {
  def +(other: Number): Number =
    {
      new Number(value + other.value)

    }
  def -(other: Number): Number =
    {
      new Number(value - other.value)

    }
  def *(other: Number): Number =
    {
      new Number(value * other.value)

    }
  def /(other: Number): Number =
    {
		  if(other.value != 0)
			  new Number(value / other.value)
		  else throw new Exception ("DIVIDE BY ZERO")

    }
  def ==(other: Number): Boole =
    {
      if (value == other.value) new Boole(true)
      else new Boole(false)
    }

  def <(other: Number): Boole =
    {
      if (value < other.value) new Boole(true)
      else new Boole(false)
    }

  override def toString = value.toString

  def this(str: String) {
    this(str.toDouble)
  }
  
}
object Number
{
  def test()
  {
    val num0 = new Number(0)
    val num1 = new Number(1)
    val num2 = new Number(2)
    val num3 = new Number(3)
    val num4 = new Number(4)
    val num5 = new Number(5)
    val num6 = new Number(6)
    println("testing Number class")

    println("testing add, expected 3 through 1 + 2, got ::: " + (num1+num2))
    println("testing add, expected 5 through 2 + 5, got ::: " + (num2+num3))
    println("testing add, expected 6 through 3 + 3, got ::: " + (num3+num3))
    println("testing sub, expected 0 through 3 - 3, got ::: " + (num3-num3))
    println("testing sub, expected 1 through 3 - 2, got ::: " + (num3-num2))
    println("testing sub, expected -1 through 2 - 3, got ::: " + (num2-num3))
    println("testing mul, expected 12 through 3 * 4, got ::: " + (num3*num4))
    println("testing mul, expected 0 through 3 * 0, got ::: " + (num3*num0))
    println("testing mul, expected 1 through 1 * 1, got ::: " + (num3*num4))
    println("testing div, expected 1 through 5 / 5, got ::: " + (num5/num5))
    println("testing div, expected 5 through 5 / 1, got ::: " + (num5/num1))
    println("testing eq , expected t through 5 ==5, got ::: " + (num5==num5))
    println("testing eq , expected f through 5 ==1, got ::: " + (num5==num1))
    println("testing gr , expected f through 5 < 1, got ::: " + (num5<num1))
    println("testing gr , expected t through 1 < 5, got ::: " + (num1<num5))
  }
}