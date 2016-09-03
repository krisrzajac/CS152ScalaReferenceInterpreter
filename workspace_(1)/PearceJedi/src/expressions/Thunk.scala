package expressions

import values._

class Thunk(body: Expression) extends SpecialForm {
  
  def execute(env: Environment): Value = {
    val tempEnv = new Environment(env)
    val cache = new Variable(Number(0))
    val cacheUnready = new Variable(Boole(true))
    tempEnv.put(Identifier("cache"), cache)
    tempEnv.put(Identifier("cacheUnready"), cacheUnready)
    /*
     * if (cacheUnready) { cache = 
     */
    new Closure(Nil, body, tempEnv)
  }

}