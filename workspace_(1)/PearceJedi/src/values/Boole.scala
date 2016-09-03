package values
import expressions.Literal
case class Boole(value: Boolean) extends Literal { 
  //def this(s: String) { this(s.toBoolean)}
  def and(other: Boole) = Boole(this.value && other.value)
  def &&(other: Boole) = Boole(this.value && other.value)
  def ||(other: Boole) = Boole(this.value || other.value)
  def not() = Boole(!this.value)
  override def toString() = "" + value
}