package values

import expressions.Literal

case class Number(value: Double) extends Literal {
  //def this(s: String) { this(s.toDouble)}
  def +(other: Number) = Number(this.value + other.value)
  def *(other: Number) = Number(this.value * other.value)
  def -(other: Number) = Number(this.value - other.value)
  def /(other: Number) = Number(this.value / other.value)
  def <(other: Number) = Boole(this.value <  other.value)
  def ==(other: Number) = Boole(this.value == other.value)
  override def toString() = "" + value
}