object Session {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(60); 
  println("Welcome to the Scala worksheet");$skip(32); 

  def tripple(x: Int) = x * 3;System.out.println("""tripple: (x: Int)Int""");$skip(26); 
  def inc(x: Int) = x + 1;System.out.println("""inc: (x: Int)Int""");$skip(31); 

  def square(x: Int) = x * x;System.out.println("""square: (x: Int)Int""");$skip(71); 

  def noisySquare(x: Int) = { println("squaring " + x); square(x) };System.out.println("""noisySquare: (x: Int)Int""");$skip(39); ;

  lazy val hundred = noisySquare(10);System.out.println("""hundred: => Int""");$skip(12); val res$0 = 

  hundred;System.out.println("""res0: Int = """ + $show(res$0));$skip(12); val res$1 = 

  hundred;System.out.println("""res1: Int = """ + $show(res$1));$skip(22); 
  def id[T](x: T) = x;System.out.println("""id: [T](x: T)T""");$skip(14); val res$2 = 
  id("Hello");System.out.println("""res2: String = """ + $show(res$2));$skip(8); val res$3 = 
  id(1);System.out.println("""res3: Int = """ + $show(res$3));$skip(10); val res$4 = 
  id(2.0);System.out.println("""res4: Double = """ + $show(res$4));$skip(11); val res$5 = 
  id(id _);System.out.println("""res5: Nothing => Nothing = """ + $show(res$5));$skip(30); 
  def square2(x: Int) = x * x;System.out.println("""square2: (x: Int)Int""");$skip(34); 
  val square3 = (x: Int) => x * x;System.out.println("""square3  : Int => Int = """ + $show(square3 ));$skip(13); val res$6 = 
  square3(2);System.out.println("""res6: Int = """ + $show(res$6));$skip(27); val res$7 = 
  1.to(10).map(x => x * x);System.out.println("""res7: scala.collection.immutable.IndexedSeq[Int] = """ + $show(res$7));$skip(30); val res$8 = 

  if (false) 3 else "Hello";System.out.println("""res8: Any = """ + $show(res$8));$skip(21); val res$9 = 
  1 :: 2 :: 3 :: Nil;System.out.println("""res9: List[Int] = """ + $show(res$9));$skip(22); val res$10 = 
  1 :: "Hello" :: Nil;System.out.println("""res10: List[Any] = """ + $show(res$10));$skip(142); 

  def map(lst: List[Int], fun: (Int) => Int): List[Int] =
    if (lst.isEmpty)
      Nil
    else
      fun(lst.head) :: map(lst.tail, fun);System.out.println("""map: (lst: List[Int], fun: Int => Int)List[Int]""");$skip(40); 

  val isEven = (x: Int) => x % 2 == 0;System.out.println("""isEven  : Int => Boolean = """ + $show(isEven ));$skip(27); val res$11 = 
  (1 to 10).filter(isEven);System.out.println("""res11: scala.collection.immutable.IndexedSeq[Int] = """ + $show(res$11));$skip(33); 
  def isOdd(x: Int) = x % 2 != 0;System.out.println("""isOdd: (x: Int)Boolean""");$skip(26); val res$12 = 
  (1 to 10).filter(isOdd);System.out.println("""res12: scala.collection.immutable.IndexedSeq[Int] = """ + $show(res$12));$skip(44); 

  def tester(x: Int, y: Int): Int = x * y;System.out.println("""tester: (x: Int, y: Int)Int""");$skip(15); val res$13 = 
  tester(2, 3);System.out.println("""res13: Int = """ + $show(res$13));$skip(37); 

  def mul2(x: Int)(y: Int) = x * y;System.out.println("""mul2: (x: Int)(y: Int)Int""");$skip(13); val res$14 = 
  mul2(3)(4);System.out.println("""res14: Int = """ + $show(res$14));$skip(133); 

	  //
  //  Problem 1 : Compose
 	//

  def compose[T](f: T => T, g: T => T): T => T = {
    def r(x: T): T = f(g(x));
    r _
  };System.out.println("""compose: [T](f: T => T, g: T => T)T => T""");$skip(45); 

  val compTest = compose(tripple _, inc _);System.out.println("""compTest  : Int => Int = """ + $show(compTest ));$skip(16); val res$15 = 

  compTest(3);System.out.println("""res15: Int = """ + $show(res$15));$skip(14); val res$16 = 
  compTest(4);System.out.println("""res16: Int = """ + $show(res$16));$skip(14); val res$17 = 
  compTest(5);System.out.println("""res17: Int = """ + $show(res$17));$skip(396); 

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
    	
    };System.out.println("""selfIter: [T](f: T => T, n: Int)T => T""");$skip(34); 

  def double(x: Double) = 2 * x;System.out.println("""double: (x: Double)Double""");$skip(52); 

  val testSelfIterDouble2 = selfIter(double _, 1);System.out.println("""testSelfIterDouble2  : Double => Double = """ + $show(testSelfIterDouble2 ));$skip(25); val res$18 = 
  testSelfIterDouble2(2);System.out.println("""res18: Double = """ + $show(res$18));$skip(25); val res$19 = 
  testSelfIterDouble2(3);System.out.println("""res19: Double = """ + $show(res$19));$skip(25); val res$20 = 
  testSelfIterDouble2(4);System.out.println("""res20: Double = """ + $show(res$20));$skip(25); val res$21 = 
  testSelfIterDouble2(5);System.out.println("""res21: Double = """ + $show(res$21));$skip(25); val res$22 = 
  testSelfIterDouble2(6);System.out.println("""res22: Double = """ + $show(res$22));$skip(25); val res$23 = 
  testSelfIterDouble2(7);System.out.println("""res23: Double = """ + $show(res$23));$skip(25); val res$24 = 
  testSelfIterDouble2(8);System.out.println("""res24: Double = """ + $show(res$24));$skip(25); val res$25 = 
  testSelfIterDouble2(9);System.out.println("""res25: Double = """ + $show(res$25));$skip(50); 
  val testSelfIterDouble4 = selfIter(double _, 0);System.out.println("""testSelfIterDouble4  : Double => Double = """ + $show(testSelfIterDouble4 ));$skip(25); val res$26 = 
  testSelfIterDouble4(2);System.out.println("""res26: Double = """ + $show(res$26));$skip(25); val res$27 = 
  testSelfIterDouble4(3);System.out.println("""res27: Double = """ + $show(res$27));$skip(25); val res$28 = 
  testSelfIterDouble4(4);System.out.println("""res28: Double = """ + $show(res$28));$skip(25); val res$29 = 
  testSelfIterDouble4(5);System.out.println("""res29: Double = """ + $show(res$29));$skip(25); val res$30 = 
  testSelfIterDouble4(6);System.out.println("""res30: Double = """ + $show(res$30));$skip(25); val res$31 = 
  testSelfIterDouble4(7);System.out.println("""res31: Double = """ + $show(res$31));$skip(25); val res$32 = 
  testSelfIterDouble4(8);System.out.println("""res32: Double = """ + $show(res$32));$skip(25); val res$33 = 
  testSelfIterDouble4(9);System.out.println("""res33: Double = """ + $show(res$33));$skip(310); 
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
  };System.out.println("""countPass: [T](a: Array[T])Int""");$skip(55); 
  val a = Array(true,2,true,4,false,6,1,3,5,7,8,false);System.out.println("""a  : Array[AnyVal] = """ + $show(a ));$skip(11); val res$34 = 
  a.length;System.out.println("""res34: Int = """ + $show(res$34));$skip(7); val res$35 = 
  a(1);System.out.println("""res35: AnyVal = """ + $show(res$35));$skip(7); val res$36 = 
  a(3);System.out.println("""res36: AnyVal = """ + $show(res$36));$skip(7); val res$37 = 
  a(2);System.out.println("""res37: AnyVal = """ + $show(res$37));$skip(15); val res$38 = 
  countPass(a);System.out.println("""res38: Int = """ + $show(res$38));$skip(479); 
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
};System.out.println("""makeAccount: (initBalance: Double)String => (Double => String)""");$skip(32); 

val savings = makeAccount(100);System.out.println("""savings  : String => (Double => String) = """ + $show(savings ));$skip(32); 
val checking = makeAccount(200);System.out.println("""checking  : String => (Double => String) = """ + $show(checking ));$skip(48); 
println(savings("withdraw")(30));$skip(49);   // prints $70
println(checking("withdraw")(30));$skip(50);  // prints $170
println(checking("transfer")(30));$skip(17);  // prints error
  val baha = 2.0;System.out.println("""baha  : Double = """ + $show(baha ))}
}
