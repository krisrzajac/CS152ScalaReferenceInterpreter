object Session {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  def tripple(x: Int) = x * 3                     //> tripple: (x: Int)Int
  def inc(x: Int) = x + 1                         //> inc: (x: Int)Int

  def square(x: Int) = x * x                      //> square: (x: Int)Int

  def noisySquare(x: Int) = { println("squaring " + x); square(x) };
                                                  //> noisySquare: (x: Int)Int

  lazy val hundred = noisySquare(10)              //> hundred: => Int

  hundred                                         //> squaring 10
                                                  //| res0: Int = 100

  hundred                                         //> res1: Int = 100
  def id[T](x: T) = x                             //> id: [T](x: T)T
  id("Hello")                                     //> res2: String = Hello
  id(1)                                           //> res3: Int = 1
  id(2.0)                                         //> res4: Double = 2.0
  id(id _)                                        //> res5: Nothing => Nothing = <function1>
  def square2(x: Int) = x * x                     //> square2: (x: Int)Int
  val square3 = (x: Int) => x * x                 //> square3  : Int => Int = <function1>
  square3(2)                                      //> res6: Int = 4
  1.to(10).map(x => x * x)                        //> res7: scala.collection.immutable.IndexedSeq[Int] = Vector(1, 4, 9, 16, 25, 3
                                                  //| 6, 49, 64, 81, 100)

  if (false) 3 else "Hello"                       //> res8: Any = Hello
  1 :: 2 :: 3 :: Nil                              //> res9: List[Int] = List(1, 2, 3)
  1 :: "Hello" :: Nil                             //> res10: List[Any] = List(1, Hello)

  def map(lst: List[Int], fun: (Int) => Int): List[Int] =
    if (lst.isEmpty)
      Nil
    else
      fun(lst.head) :: map(lst.tail, fun)         //> map: (lst: List[Int], fun: Int => Int)List[Int]

  val isEven = (x: Int) => x % 2 == 0             //> isEven  : Int => Boolean = <function1>
  (1 to 10).filter(isEven)                        //> res11: scala.collection.immutable.IndexedSeq[Int] = Vector(2, 4, 6, 8, 10)
  def isOdd(x: Int) = x % 2 != 0                  //> isOdd: (x: Int)Boolean
  (1 to 10).filter(isOdd)                         //> res12: scala.collection.immutable.IndexedSeq[Int] = Vector(1, 3, 5, 7, 9)

  def tester(x: Int, y: Int): Int = x * y         //> tester: (x: Int, y: Int)Int
  tester(2, 3)                                    //> res13: Int = 6

  def mul2(x: Int)(y: Int) = x * y                //> mul2: (x: Int)(y: Int)Int
  mul2(3)(4)                                      //> res14: Int = 12

	  //
  //  Problem 1 : Compose
 	//

  def compose[T](f: T => T, g: T => T): T => T = {
    def r(x: T): T = f(g(x));
    r _
  }                                               //> compose: [T](f: T => T, g: T => T)T => T

  val compTest = compose(tripple _, inc _)        //> compTest  : Int => Int = <function1>

  compTest(3)                                     //> res15: Int = 12
  compTest(4)                                     //> res16: Int = 15
  compTest(5)                                     //> res17: Int = 18

	  //
  //  Problem 2 : SelfIter
 	//

  def selfIter[T](f: T => T, n: Int) =
    {
    	def helper(count:Int, resultF:T=>T): T=>T =
    	{
    		def r(x: T): T = resultF(resultF(x));
    		r _
    		
    		if (count == 0) resultF
    		else helper(count-1, compose(resultF, resultF))
    		
    	}
    	def id(x:T) =
    		x
    	if( n == 0 ) id(_)
    	else
    		helper (n-1, f)
    	
    }                                             //> selfIter: [T](f: T => T, n: Int)T => T

  def double(x: Double) = 2 * x                   //> double: (x: Double)Double

  val testSelfIterDouble2 = selfIter(double _, 1) //> testSelfIterDouble2  : Double => Double = <function1>
  testSelfIterDouble2(2)                          //> res18: Double = 4.0
  testSelfIterDouble2(3)                          //> res19: Double = 6.0
  testSelfIterDouble2(4)                          //> res20: Double = 8.0
  testSelfIterDouble2(5)                          //> res21: Double = 10.0
  testSelfIterDouble2(6)                          //> res22: Double = 12.0
  testSelfIterDouble2(7)                          //> res23: Double = 14.0
  testSelfIterDouble2(8)                          //> res24: Double = 16.0
  testSelfIterDouble2(9)                          //> res25: Double = 18.0
  val testSelfIterDouble4 = selfIter(double _, 0) //> testSelfIterDouble4  : Double => Double = <function1>
  testSelfIterDouble4(2)                          //> res26: Double = 2.0
  testSelfIterDouble4(3)                          //> res27: Double = 3.0
  testSelfIterDouble4(4)                          //> res28: Double = 4.0
  testSelfIterDouble4(5)                          //> res29: Double = 5.0
  testSelfIterDouble4(6)                          //> res30: Double = 6.0
  testSelfIterDouble4(7)                          //> res31: Double = 7.0
  testSelfIterDouble4(8)                          //> res32: Double = 8.0
  testSelfIterDouble4(9)                          //> res33: Double = 9.0
  //
  //  Problem 3 : Countpass
 	//
  def countPass[T](a:Array[T]) =
  {
  	def helper (count:Int, total:Int):Int =
  	{
  		if(count == a.length) return total
  		else
  			if(a(count) == (true)||a(count) == (false)) helper(count+1, total+1)
  			
  			else helper(count+1,total)
  	}
  			helper(0, 0)
  }                                               //> countPass: [T](a: Array[T])Int
  val a = Array(true,2,true,4,false,6,1,3,5,7,8,false)
                                                  //> a  : Array[AnyVal] = Array(true, 2, true, 4, false, 6, 1, 3, 5, 7, 8, false
                                                  //| )
  a.length                                        //> res34: Int = 12
  a(1)                                            //> res35: AnyVal = 2
  a(3)                                            //> res36: AnyVal = 4
  a(2)                                            //> res37: AnyVal = true
  countPass(a)                                    //> res38: Int = 4
    //
  //  Problem 4 : Objects as functions
 	//
  def makeAccount(initBalance: Double = 0.0) = {
   var balance:Double = initBalance
   var delegate: (String)=>String = (y: String) => "error"
   def dispatch(msg: String)(amt: Double): String =
   {
   		if(msg == "withdraw")
   		{
   		balance -= amt
   		"$" + balance.toString
   		}
   		else if (msg == "deposit")
   		{
   		balance += amt
   		"$" + balance.toString
   		}
   		else delegate(msg)
	 }
   dispatch _
}                                                 //> makeAccount: (initBalance: Double)String => (Double => String)

val savings = makeAccount(100)                    //> savings  : String => (Double => String) = <function1>
val checking = makeAccount(200)                   //> checking  : String => (Double => String) = <function1>
println(savings("withdraw")(30))  // prints $70   //> $70.0
println(checking("withdraw")(30)) // prints $170  //> $170.0
println(checking("transfer")(30)) // prints error //> error
  val baha = 2.0                                  //> baha  : Double = 2.0
}