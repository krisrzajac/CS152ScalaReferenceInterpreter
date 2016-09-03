object session {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(60); 
  println("Welcome to the Scala worksheet");$skip(155); 
  
  def dropRecursive[T](n: Int, vals: List[T]): List[T] =
    {
      if (n == 0 || vals == Nil) vals
      else dropRecursive(n - 1, vals.tail)

    };System.out.println("""dropRecursive: [T](n: Int, vals: List[T])List[T]""");$skip(40); val res$0 = 
  dropRecursive(3, List(1, 2, 3, 4, 5));System.out.println("""res0: List[Int] = """ + $show(res$0));$skip(40); val res$1 = 
  dropRecursive(0, List(1, 2, 3, 4, 5));System.out.println("""res1: List[Int] = """ + $show(res$1));$skip(40); val res$2 = 
  dropRecursive(6, List(1, 2, 3, 4, 5));System.out.println("""res2: List[Int] = """ + $show(res$2));$skip(38); val res$3 = 
  dropRecursive(5, List(1,2,3,4,5,6));System.out.println("""res3: List[Int] = """ + $show(res$3));$skip(213); 
	
		
	def dropTail[T](n:Int, vals: List[T]) =
	{
		def helper(count: Int, result:List[T]): List[T] =
		{
			if(count == 0 || result == Nil) result
			
			else helper(count-1, result.tail)
		}
		helper(n, vals)
	};System.out.println("""dropTail: [T](n: Int, vals: List[T])List[T]""");$skip(34); val res$4 = 
	dropTail(3, List(1, 2, 3, 4, 5));System.out.println("""res4: List[Int] = """ + $show(res$4));$skip(35); val res$5 = 
  dropTail(0, List(1, 2, 3, 4, 5));System.out.println("""res5: List[Int] = """ + $show(res$5));$skip(35); val res$6 = 
  dropTail(6, List(1, 2, 3, 4, 5));System.out.println("""res6: List[Int] = """ + $show(res$6));$skip(33); val res$7 = 
  dropTail(5, List(1,2,3,4,5,6));System.out.println("""res7: List[Int] = """ + $show(res$7));$skip(195); 
	
	
  def avgRoot(nums: List[Double]) =
    {
      def sum(x:Double, y:Double) = x+y
      nums.filter(_ > 0).map(math.sqrt(_)).reduce(sum _) / nums.filter(_ > 0).map(math.sqrt(_)).length
    };System.out.println("""avgRoot: (nums: List[Double])Double""");$skip(31); val res$8 = 
  avgRoot(List(9, -1, 25, 49));System.out.println("""res8: Double = """ + $show(res$8));$skip(142); 

  def exp[T](f: T => Double, g: T => Double) =
    {

      def h(x: T) =
        {
          math.pow(f(x), g(x))
        }
      h _
    };System.out.println("""exp: [T](f: T => Double, g: T => Double)T => Double""");$skip(30); 

  def inc(x: Double) = x + 1;System.out.println("""inc: (x: Double)Double""");$skip(32); 
  def square(x: Double) = x * x;System.out.println("""square: (x: Double)Double""");$skip(37); 
  val mystery = exp(square _, inc _);System.out.println("""mystery  : Double => Double = """ + $show(mystery ));$skip(13); val res$9 = 
  mystery(3);System.out.println("""res9: Double = """ + $show(res$9));$skip(13); val res$10 = 
  mystery(2);System.out.println("""res10: Double = """ + $show(res$10));$skip(13); val res$11 = 
  mystery(1);System.out.println("""res11: Double = """ + $show(res$11));$skip(13); val res$12 = 
  mystery(4);System.out.println("""res12: Double = """ + $show(res$12))}
}
