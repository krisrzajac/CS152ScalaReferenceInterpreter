object Session {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(62); 

  println("Welcome to the Scala worksheet");$skip(392); 



//
/// Problem 5: Control Loop
//

  def recControlLoop[T](state: T,
    cycle: Int,
    halt: (T, Int) => Boolean,
    update: (T, Int) => T): T =
    {
      def helper(s: T, cyc: Int, ht: (T, Int) => Boolean,
        u: (T, Int) => T): T =
        {
          if (ht(s, cyc))s
          else helper(u(s,cyc), cyc+1, ht, u)

        }
      helper(state, cycle, halt, update)
    };System.out.println("""recControlLoop: [T](state: T, cycle: Int, halt: (T, Int) => Boolean, update: (T, Int) => T)T""");$skip(105); 
  
  
  
  def practiceHalt(x: Int, y: Int): Boolean =
    {
      if (x >= 15) true else false
    };System.out.println("""practiceHalt: (x: Int, y: Int)Boolean""");$skip(71); 

  def practiceUpdate(x: Int, y: Int):Int =
    {
      x + 1

    };System.out.println("""practiceUpdate: (x: Int, y: Int)Int""");$skip(53); val res$0 = 
  recControlLoop(0, 0, practiceHalt, practiceUpdate)
	
	
	
	//
	// Problem 6 : Population Growth
	// population :131072
	import scala.math;System.out.println("""res0: Int = """ + $show(res$0));$skip(162); 
	def popHalt(state: Int, cycle:Int)=
		if(state>=100000) true else false;System.out.println("""popHalt: (state: Int, cycle: Int)Boolean""");$skip(58); 
		
	def popUpdate(state:Int, cycle:Int)=
	{
		state*2
	};System.out.println("""popUpdate: (state: Int, cycle: Int)Int""");$skip(42); val res$1 = 
	
	recControlLoop(1,0,popHalt,popUpdate);System.out.println("""res1: Int = """ + $show(res$1));$skip(384); 
	
	
	
	//
	//Problem 7: Finding Roots of Functions
	//
	
	def solve(f:Double=>Double) =
	{
		def fPrime(y:Double, cycle:Int) =
		{
			(f(y+cycle)-f(y))/cycle
		}
		def solveUpdate(s:Double, cycle:Int) =
		{
			s -(f(s)/fPrime(s, cycle+1))
		}
		def solveHalt(s:Double, cycle:Int):Boolean =
			if(f(s).abs<=cycle) true else false
		recControlLoop(1.0,0, solveHalt, solveUpdate)
	};System.out.println("""solve: (f: Double => Double)Double""");$skip(38); 
	
	def practiceEq(x:Double) = x*x-16;System.out.println("""practiceEq: (x: Double)Double""");$skip(19); val res$2 = 
	solve(practiceEq);System.out.println("""res2: Double = """ + $show(res$2));$skip(252); 
	
	
	//
	//Problem 8: Finding square root
	//I wasn't able to find what to put into solve. My solve function gets roughly the correct root to the example i put above, but I didn't know what to put into solve
	def squareApprox(x:Double) =.5*(x+(1/x));System.out.println("""squareApprox: (x: Double)Double""");$skip(54); 
	
	def squareRoot(x:Double) = solve(squareApprox _ );System.out.println("""squareRoot: (x: Double)Double""");$skip(15); val res$3 = 
	squareRoot(4);System.out.println("""res3: Double = """ + $show(res$3));$skip(76); 

	
	
	//
	//Problem 9: Finding cube root
	//
	def cube(x:Double) = x*x*x;System.out.println("""cube: (x: Double)Double""");$skip(9); val res$4 = 
	cube(3);System.out.println("""res4: Double = """ + $show(res$4));$skip(38); 
	def cubeRoot(x:Double) = solve(cube);System.out.println("""cubeRoot: (x: Double)Double""");$skip(14); val res$5 = 
	cubeRoot(27);System.out.println("""res5: Double = """ + $show(res$5))}
	
	
	//
	//No Problem 10
	//
}
