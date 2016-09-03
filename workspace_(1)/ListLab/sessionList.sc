object session {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  
	//
	/// Problem 1: Sum of odd numbers cubed
	//
	
  def iterSumOfCubes(list: List[Int]) =
    {
      if (list == Nil) throw new Exception("Empty list")

      var badvar = 0
      for (i <- 0 to list.length - 1)
        if (list(i) % 2 != 0)
          badvar += list(i) * list(i) * list(i)

      println(badvar)
    }                                             //> iterSumOfCubes: (list: List[Int])Unit
  iterSumOfCubes(List(3, 5, 7, 9))                //> 1224
  iterSumOfCubes(List(3, 2, 2, 3, 4, 5, 5, 7, 9)) //> 1376
  (3 * 3 * 3) + (3 * 3 * 3) + (5 * 5 * 5) + (5 * 5 * 5) + (7 * 7 * 7) + (9 * 9 * 9)
                                                  //> res0: Int = 1376
  def recSumOfCubes(l: List[Int]): Int =
    {
      if (l == Nil) 0

      else if (l.head % 2 != 0) {
        l.head * l.head * l.head + recSumOfCubes(l.tail)
      } else
        recSumOfCubes(l.tail)

    }                                             //> recSumOfCubes: (l: List[Int])Int
  recSumOfCubes(List(3, 2, 2, 3, 4, 5, 5, 7, 9))  //> res1: Int = 1376

  def tailSumOfCubes(l: List[Int]) =
    {
      def helper(ll: List[Int], total: Int): Int =
        {
          if (ll == Nil) total
          else if (ll.head % 2 != 0) helper(ll.tail, total + ll.head * ll.head * ll.head)
          else helper(ll.tail, total)
        }
      helper(l, 0)
    }                                             //> tailSumOfCubes: (l: List[Int])Int

  tailSumOfCubes(List(3, 2, 2, 3, 4, 5, 5, 7, 9)) //> res2: Int = 1376
  def isTrip(x: Int) = x % 3 == 0                 //> isTrip: (x: Int)Boolean
  val ages = List(1, 2, 3, 4, 5, 6, 7, 8, 9)      //> ages  : List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9)
  ages.filter(isTrip _)                           //> res3: List[Int] = List(3, 6, 9)

  def trip(x: Int) = x * 3                        //> trip: (x: Int)Int
  ages.map(trip _)                                //> res4: List[Int] = List(3, 6, 9, 12, 15, 18, 21, 24, 27)

  def mfrSumOfCubes(l: List[Int]) =
    {
      def cube(x: Int) = x * x * x
      def sum(x: Int, y: Int) = x + y
      l.filter(_ % 2 != 0).map(cube _).reduce(sum _)
    }                                             //> mfrSumOfCubes: (l: List[Int])Int

  mfrSumOfCubes(List(3, 2, 2, 3, 4, 5, 5, 7, 9))  //> res5: Int = 1376

  //
  ///Problem 2
  //

  def iterSumOfSums(l: List[List[Int]]) =
    {
      var badvar = 0
      if (l == Nil) throw new Exception("List is NIL")

      else {
        for (i <- 0 to l.length - 1)
          for (j <- 0 to l.head.length - 1)
            badvar += l(i)(j)
      }

      badvar
    }                                             //> iterSumOfSums: (l: List[List[Int]])Int
  iterSumOfSums(List(List(1, 2, 3), List(4, 5, 6)))
                                                  //> res6: Int = 21

  //I didn't know how else to implement this without a helper function to add up the single list

  def recSumOfSums(l: List[List[Int]]): Int =
    {
      def singleListSum(ll: List[Int]): Int =
        {
          if (ll == Nil) 0
          else ll.head + singleListSum(ll.tail)
        }
      if (l == Nil) 0
      else singleListSum(l.head) + recSumOfSums(l.tail)

    }                                             //> recSumOfSums: (l: List[List[Int]])Int
  recSumOfSums(List(List(1, 2, 3, 4), List(1, 2, 3), List(1)))
                                                  //> res7: Int = 17
  recSumOfSums(List(List(1, 2, 3, 4), List(1, 2, 3), List(1)))
                                                  //> res8: Int = 17
  recSumOfSums(List(List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), List(1, 2, 3), List(1)))
                                                  //> res9: Int = 62
  recSumOfSums(List(List(1, 2), List(1, 2), List(1)))
                                                  //> res10: Int = 7

  def tailSumOfSums(l: List[List[Int]]) =
    {

      def helper(ll: List[List[Int]], total: Int): Int =
        {

          def listSum(lll: List[Int]): Int =
            {
              if (lll == Nil) 0
              else lll.head + listSum(lll.tail)
            }

          if (ll == Nil) total

          else {
            helper(ll.tail, listSum(ll.head) + total)
          }
        }
      helper(l, 0)
    }                                             //> tailSumOfSums: (l: List[List[Int]])Int

  tailSumOfSums(List(List(1, 2), List(1, 2), List(1)))
                                                  //> res11: Int = 7
  tailSumOfSums(List(List(1, 2, 3, 4, 5, 6), List(1, 2), List(1)))
                                                  //> res12: Int = 25

  def mfrSumOfSums(l: List[List[Int]]): Int =
    {
      def sum(x: Int, y: Int) = x + y
      if (l == Nil) 0
      else l.head.reduce(sum _) + mfrSumOfSums(l.tail)

    }                                             //> mfrSumOfSums: (l: List[List[Int]])Int
  mfrSumOfSums(List(List(1, 2, 3, 4, 5, 6), List(1, 2), List(1)))
                                                  //> res13: Int = 25
  mfrSumOfSums(List(List(1, 2, 3, 4, 5, 6), List(1, 2), List(1)))
                                                  //> res14: Int = 25
  mfrSumOfSums(List(List(1, 2, 3, 4, 5, 6), List(1, 2), List(1)))
                                                  //> res15: Int = 25

  //
  /// Problem 3 : Depth
  //

  def depth(x: Any): Int =
    {
      x match {
        case Nil => 1
        case v: List[Any] => depth(v.head) + depth(v.tail)
        case _ => 0
      }
    }                                             //> depth: (x: Any)Int
  depth(List(1))                                  //> res16: Int = 1
  depth(List(List(List(1))))                      //> res17: Int = 3
  depth(List(List(List(List(List(List(List(List(1)))))))))
                                                  //> res18: Int = 8
  depth(List(1, 2, List(1, 2, List(1, 2, 3))))    //> res19: Int = 3

  //
  /// Problem 6 : Number of elements in a list that satisfy a given predicate
  //

  def iterNumPred[T](pred: T => Boolean, l: List[T]) =
    {
      var badvar = 0
      for (i <- 0 to l.length - 1)
        if (pred(l(i)))
          badvar += 1
      badvar
    }                                             //> iterNumPred: [T](pred: T => Boolean, l: List[T])Int
  def isEven(x: Int) = x % 2 == 0                 //> isEven: (x: Int)Boolean
  iterNumPred(isEven _, List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13))
                                                  //> res20: Int = 6
  iterNumPred(isEven _, List(2, 4, 6, 8, 1))      //> res21: Int = 4

  def recNumPred[T](pred: T => Boolean, l: List[T]): Int =
    {
      if (l == Nil) 0
      else if (pred(l.head)) 1 + recNumPred(pred, l.tail)
      else recNumPred(pred, l.tail)
    }                                             //> recNumPred: [T](pred: T => Boolean, l: List[T])Int
  recNumPred(isEven _, List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13))
                                                  //> res22: Int = 6
  recNumPred(isEven _, List(2, 4, 6, 8, 1))       //> res23: Int = 4

  def tailNumPred[T](pred: T => Boolean, l: List[T]) =
    {
      def helper(predH: T => Boolean, ll: List[T], total: Int): Int =
        {
          if (ll == Nil) total
          else if (predH(ll.head)) helper(predH, ll.tail, total + 1)
          else helper(predH, ll.tail, total)
        }
      helper(pred, l, 0)
    }                                             //> tailNumPred: [T](pred: T => Boolean, l: List[T])Int
  tailNumPred(isEven _, List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13))
                                                  //> res24: Int = 6
  tailNumPred(isEven _, List(2, 4, 6, 0, 2, 4, 6, 8, 10, 12))
                                                  //> res25: Int = 10

  def mfrNumPred[T](pred: T => Boolean, l: List[T]) =
    {
      l.filter(pred).length
    }                                             //> mfrNumPred: [T](pred: T => Boolean, l: List[T])Int
  mfrNumPred(isEven _, List(2, 4, 6, 0, 2, 4, 6, 8, 10, 12))
                                                  //> res26: Int = 10
  mfrNumPred(isEven _, List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13))
                                                  //> res27: Int = 6
  mfrNumPred(isEven _, List(2))                   //> res28: Int = 1

  //
  ///Problem 7: True if all elements in the list satisfy the given predicate
  //

  def iterAllPred[T](pred: T => Boolean, l: List[T]) =
    {
      var badBool = true;
      if (l == Nil) throw new Exception("List is empty!")
      for (i <- 0 to l.length - 1) {
        if (!pred(l(i)))
          badBool = false;
      }
      badBool
    }                                             //> iterAllPred: [T](pred: T => Boolean, l: List[T])Boolean
  iterAllPred(isEven _, List(2, 4, 6, 8, 10))     //> res29: Boolean = true
  iterAllPred(isEven _, List(1, 2, 4, 6, 8, 10))  //> res30: Boolean = false

  def recAllPred[T](pred: T => Boolean, l: List[T]): Boolean =
    {
      if (l == Nil) true
      else if (pred(l.head)) recAllPred(pred, l.tail)
      else false
    }                                             //> recAllPred: [T](pred: T => Boolean, l: List[T])Boolean
  recAllPred(isEven _, List(1, 2, 4, 6, 8, 10))   //> res31: Boolean = false
  recAllPred(isEven _, List(2, 4, 6, 8, 10, 12, 14))
                                                  //> res32: Boolean = true

  def tailAllPred[T](pred: T => Boolean, l: List[T]) =
    {
      def helper[T](predd: T => Boolean, ll: List[T], result: Boolean): Boolean =
        {
          if (ll == Nil) result
          else if (predd(ll.head)) helper(predd, ll.tail, true)
          else false
        }
      helper(pred, l, true)
    }                                             //> tailAllPred: [T](pred: T => Boolean, l: List[T])Boolean
  tailAllPred(isEven _, List(1, 2, 4, 6, 8, 10))  //> res33: Boolean = false
  tailAllPred(isEven _, List(2))                  //> res34: Boolean = true
  tailAllPred(isEven _, List(2, 4, 6, 8, 10,11,12))
                                                  //> res35: Boolean = false
  tailAllPred(isEven _, List(2, 2, 2, 2, 2, 2, 2, 2))
                                                  //> res36: Boolean = true

  def mfrAllPred[T](pred: T => Boolean, l: List[T]): Boolean =
    {

      if (l.filter(pred).length == l.length) true
      else false

    }                                             //> mfrAllPred: [T](pred: T => Boolean, l: List[T])Boolean
  mfrAllPred(isEven _, List(2, 2, 2, 2, 2, 2, 2, 2))
                                                  //> res37: Boolean = true
  mfrAllPred(isEven _, List(1, 2, 2, 2, 2, 2, 2, 2))
                                                  //> res38: Boolean = false
  mfrAllPred(isEven _, List(2, 2, 2, 2, 2, 2, 111, 2))
                                                  //> res39: Boolean = false
  mfrAllPred(isEven _, List(2, 2, 5, 2, 3, 2, 2, 2))
                                                  //> res40: Boolean = false
  mfrAllPred(isEven _, List(2, 2, 2, 2, 4, 2, 2, 2))
                                                  //> res41: Boolean = true

  //
  /// Problem 8 : Returns true if any elenent satisfies the given predicate
  //
  
  
  def iterAnyPred[T](pred:T => Boolean, l: List[T]) =
  {
  	var badBool:Boolean = false
  	for(i<-0 to l.length-1)
  		if(pred(l(i))) badBool = true
  	badBool
  		
  }                                               //> iterAnyPred: [T](pred: T => Boolean, l: List[T])Boolean
  iterAnyPred(isEven _, List(1,3,5,7,9,11,13,15)) //> res42: Boolean = false
  iterAnyPred(isEven _, List(1,3,5,7,9,11,13,15, 2))
                                                  //> res43: Boolean = true
  iterAnyPred(isEven _, List(2))                  //> res44: Boolean = true
  
  def recAnyPred[T](pred:T => Boolean, l: List[T]):Boolean =
  {
  	if(l == Nil) false
  	else if(pred(l.head)) true
  	else recAnyPred(pred,l.tail)
  }                                               //> recAnyPred: [T](pred: T => Boolean, l: List[T])Boolean

	recAnyPred(isEven _, List(1,3,5,7,9,11,13,15))
                                                  //> res45: Boolean = false
  recAnyPred(isEven _, List(1,3,5,7,9,11,13,15, 2))
                                                  //> res46: Boolean = true
  recAnyPred(isEven _, List(2))                   //> res47: Boolean = true
  
  
  def tailAnyPred[T](pred:T => Boolean, l: List[T]) =
  {
  	def helper[T](predd: T => Boolean, ll: List[T], result: Boolean): Boolean =
  	{
  		if (ll == Nil) false
  		else if(predd(ll.head)) true
  		else helper(predd, ll.tail, false)
  	}
  	helper(pred, l, false)
  }                                               //> tailAnyPred: [T](pred: T => Boolean, l: List[T])Boolean
  
  tailAnyPred(isEven _, List(1,3,5,7,9,11,13,15)) //> res48: Boolean = false
  tailAnyPred(isEven _, List(1,3,5,7,9,11,13,15, 2))
                                                  //> res49: Boolean = true
  tailAnyPred(isEven _, List(2))                  //> res50: Boolean = true
  
  def mfrAnyPred[T](pred:T =>Boolean, l:List[T])=
  {
  	if(l.filter(pred).length >=1) true
  	else false
  }                                               //> mfrAnyPred: [T](pred: T => Boolean, l: List[T])Boolean
  mfrAnyPred(isEven _, List(1,3,5,7,9,11,13,15))  //> res51: Boolean = false
  mfrAnyPred(isEven _, List(1,3,5,7,9,11,13,15,2))//> res52: Boolean = true
  mfrAnyPred(isEven _, List(2,1))                 //> res53: Boolean = true
}