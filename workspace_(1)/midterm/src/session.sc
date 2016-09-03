object session {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  def dropRecursive[T](n: Int, vals: List[T]): List[T] =
    {
      if (n == 0 || vals == Nil) vals
      else dropRecursive(n - 1, vals.tail)

    }                                             //> dropRecursive: [T](n: Int, vals: List[T])List[T]
  dropRecursive(3, List(1, 2, 3, 4, 5))           //> res0: List[Int] = List(4, 5)
  dropRecursive(0, List(1, 2, 3, 4, 5))           //> res1: List[Int] = List(1, 2, 3, 4, 5)
  dropRecursive(6, List(1, 2, 3, 4, 5))           //> res2: List[Int] = List()
  dropRecursive(5, List(1,2,3,4,5,6))             //> res3: List[Int] = List(6)
	
		
	def dropTail[T](n:Int, vals: List[T]) =
	{
		def helper(count: Int, result:List[T]): List[T] =
		{
			if(count == 0 || result == Nil) result
			
			else helper(count-1, result.tail)
		}
		helper(n, vals)
	}                                         //> dropTail: [T](n: Int, vals: List[T])List[T]
	dropTail(3, List(1, 2, 3, 4, 5))          //> res4: List[Int] = List(4, 5)
  dropTail(0, List(1, 2, 3, 4, 5))                //> res5: List[Int] = List(1, 2, 3, 4, 5)
  dropTail(6, List(1, 2, 3, 4, 5))                //> res6: List[Int] = List()
  dropTail(5, List(1,2,3,4,5,6))                  //> res7: List[Int] = List(6)
	
	
  def avgRoot(nums: List[Double]) =
    {
      def sum(x:Double, y:Double) = x+y
      nums.filter(_ > 0).map(math.sqrt(_)).reduce(sum _) / nums.filter(_ > 0).map(math.sqrt(_)).length
    }                                             //> avgRoot: (nums: List[Double])Double
  avgRoot(List(9, -1, 25, 49))                    //> res8: Double = 5.0

  def exp[T](f: T => Double, g: T => Double) =
    {

      def h(x: T) =
        {
          math.pow(f(x), g(x))
        }
      h _
    }                                             //> exp: [T](f: T => Double, g: T => Double)T => Double

  def inc(x: Double) = x + 1                      //> inc: (x: Double)Double
  def square(x: Double) = x * x                   //> square: (x: Double)Double
  val mystery = exp(square _, inc _)              //> mystery  : Double => Double = <function1>
  mystery(3)                                      //> res9: Double = 6561.0
  mystery(2)                                      //> res10: Double = 64.0
  mystery(1)                                      //> res11: Double = 1.0
  mystery(4)                                      //> res12: Double = 1048576.0
}