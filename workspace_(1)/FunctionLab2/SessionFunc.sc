object Session {

  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet



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
    }                                             //> recControlLoop: [T](state: T, cycle: Int, halt: (T, Int) => Boolean, update:
                                                  //|  (T, Int) => T)T
  
  
  
  def practiceHalt(x: Int, y: Int): Boolean =
    {
      if (x >= 15) true else false
    }                                             //> practiceHalt: (x: Int, y: Int)Boolean

  def practiceUpdate(x: Int, y: Int):Int =
    {
      x + 1

    }                                             //> practiceUpdate: (x: Int, y: Int)Int
  recControlLoop(0, 0, practiceHalt, practiceUpdate)
                                                  //> res0: Int = 15
	
	
	
	//
	// Problem 6 : Population Growth
	// population :131072
	import scala.math
	def popHalt(state: Int, cycle:Int)=
		if(state>=100000) true else false //> popHalt: (state: Int, cycle: Int)Boolean
		
	def popUpdate(state:Int, cycle:Int)=
	{
		state*2
	}                                         //> popUpdate: (state: Int, cycle: Int)Int
	
	recControlLoop(1,0,popHalt,popUpdate)     //> res1: Int = 131072
	
	
	
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
	}                                         //> solve: (f: Double => Double)Double
	
	def practiceEq(x:Double) = x*x-16         //> practiceEq: (x: Double)Double
	solve(practiceEq)                         //> res2: Double = 4.168067226890757
	
	
	//
	//Problem 8: Finding square root
	//I wasn't able to find what to put into solve. My solve function gets roughly the correct root to the example i put above, but I didn't know what to put into solve
	def squareApprox(x:Double) =.5*(x+(1/x))  //> squareApprox: (x: Double)Double
	
	def squareRoot(x:Double) = solve(squareApprox)
                                                  //> squareRoot: (x: Double)Double
	squareRoot(4)                             //> res3: Double = 2.0

	
	
	//
	//Problem 9: Finding cube root
	//
	def cube(x:Double) = x*x*x                //> cube: (x: Double)Double
	cube(3)                                   //> res4: Double = 27.0
	def cubeRoot(x:Double) = solve(cube)      //> cubeRoot: (x: Double)Double
	cubeRoot(27)                              //> res5: Double = 0.8571428571428572
	
	
	//
	//No Problem 10
	//
}