object session {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(344); 

  
  
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
    };System.out.println("""iterSumOfCubes: (list: List[Int])Unit""");$skip(35); 
  iterSumOfCubes(List(3, 5, 7, 9));$skip(50); 
  iterSumOfCubes(List(3, 2, 2, 3, 4, 5, 5, 7, 9));$skip(84); val res$0 = 
  (3 * 3 * 3) + (3 * 3 * 3) + (5 * 5 * 5) + (5 * 5 * 5) + (7 * 7 * 7) + (9 * 9 * 9);System.out.println("""res0: Int = """ + $show(res$0));$skip(211); 
  def recSumOfCubes(l: List[Int]): Int =
    {
      if (l == Nil) 0

      else if (l.head % 2 != 0) {
        l.head * l.head * l.head + recSumOfCubes(l.tail)
      } else
        recSumOfCubes(l.tail)

    };System.out.println("""recSumOfCubes: (l: List[Int])Int""");$skip(49); val res$1 = 
  recSumOfCubes(List(3, 2, 2, 3, 4, 5, 5, 7, 9));System.out.println("""res1: Int = """ + $show(res$1));$skip(299); 

  def tailSumOfCubes(l: List[Int]) =
    {
      def helper(ll: List[Int], total: Int): Int =
        {
          if (ll == Nil) total
          else if (ll.head % 2 != 0) helper(ll.tail, total + ll.head * ll.head * ll.head)
          else helper(ll.tail, total)
        }
      helper(l, 0)
    };System.out.println("""tailSumOfCubes: (l: List[Int])Int""");$skip(51); val res$2 = 

  tailSumOfCubes(List(3, 2, 2, 3, 4, 5, 5, 7, 9));System.out.println("""res2: Int = """ + $show(res$2));$skip(34); 
  def isTrip(x: Int) = x % 3 == 0;System.out.println("""isTrip: (x: Int)Boolean""");$skip(45); 
  val ages = List(1, 2, 3, 4, 5, 6, 7, 8, 9);System.out.println("""ages  : List[Int] = """ + $show(ages ));$skip(24); val res$3 = 
  ages.filter(isTrip _);System.out.println("""res3: List[Int] = """ + $show(res$3));$skip(28); 

  def trip(x: Int) = x * 3;System.out.println("""trip: (x: Int)Int""");$skip(19); val res$4 = 
  ages.map(trip _);System.out.println("""res4: List[Int] = """ + $show(res$4));$skip(175); 

  def mfrSumOfCubes(l: List[Int]) =
    {
      def cube(x: Int) = x * x * x
      def sum(x: Int, y: Int) = x + y
      l.filter(_ % 2 != 0).map(cube _).reduce(sum _)
    };System.out.println("""mfrSumOfCubes: (l: List[Int])Int""");$skip(50); val res$5 = 

  mfrSumOfCubes(List(3, 2, 2, 3, 4, 5, 5, 7, 9));System.out.println("""res5: Int = """ + $show(res$5));$skip(304); 

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
    };System.out.println("""iterSumOfSums: (l: List[List[Int]])Int""");$skip(52); val res$6 = 
  iterSumOfSums(List(List(1, 2, 3), List(4, 5, 6)));System.out.println("""res6: Int = """ + $show(res$6));$skip(377); 

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

    };System.out.println("""recSumOfSums: (l: List[List[Int]])Int""");$skip(63); val res$7 = 
  recSumOfSums(List(List(1, 2, 3, 4), List(1, 2, 3), List(1)));System.out.println("""res7: Int = """ + $show(res$7));$skip(63); val res$8 = 
  recSumOfSums(List(List(1, 2, 3, 4), List(1, 2, 3), List(1)));System.out.println("""res8: Int = """ + $show(res$8));$skip(82); val res$9 = 
  recSumOfSums(List(List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), List(1, 2, 3), List(1)));System.out.println("""res9: Int = """ + $show(res$9));$skip(54); val res$10 = 
  recSumOfSums(List(List(1, 2), List(1, 2), List(1)));System.out.println("""res10: Int = """ + $show(res$10));$skip(422); 

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
    };System.out.println("""tailSumOfSums: (l: List[List[Int]])Int""");$skip(56); val res$11 = 

  tailSumOfSums(List(List(1, 2), List(1, 2), List(1)));System.out.println("""res11: Int = """ + $show(res$11));$skip(67); val res$12 = 
  tailSumOfSums(List(List(1, 2, 3, 4, 5, 6), List(1, 2), List(1)));System.out.println("""res12: Int = """ + $show(res$12));$skip(175); 

  def mfrSumOfSums(l: List[List[Int]]): Int =
    {
      def sum(x: Int, y: Int) = x + y
      if (l == Nil) 0
      else l.head.reduce(sum _) + mfrSumOfSums(l.tail)

    };System.out.println("""mfrSumOfSums: (l: List[List[Int]])Int""");$skip(66); val res$13 = 
  mfrSumOfSums(List(List(1, 2, 3, 4, 5, 6), List(1, 2), List(1)));System.out.println("""res13: Int = """ + $show(res$13));$skip(66); val res$14 = 
  mfrSumOfSums(List(List(1, 2, 3, 4, 5, 6), List(1, 2), List(1)));System.out.println("""res14: Int = """ + $show(res$14));$skip(66); val res$15 = 
  mfrSumOfSums(List(List(1, 2, 3, 4, 5, 6), List(1, 2), List(1)));System.out.println("""res15: Int = """ + $show(res$15));$skip(200); 

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
    };System.out.println("""depth: (x: Any)Int""");$skip(17); val res$16 = 
  depth(List(1));System.out.println("""res16: Int = """ + $show(res$16));$skip(29); val res$17 = 
  depth(List(List(List(1))));System.out.println("""res17: Int = """ + $show(res$17));$skip(59); val res$18 = 
  depth(List(List(List(List(List(List(List(List(1)))))))));System.out.println("""res18: Int = """ + $show(res$18));$skip(47); val res$19 = 
  depth(List(1, 2, List(1, 2, List(1, 2, 3))));System.out.println("""res19: Int = """ + $show(res$19));$skip(272); 

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
    };System.out.println("""iterNumPred: [T](pred: T => Boolean, l: List[T])Int""");$skip(34); 
  def isEven(x: Int) = x % 2 == 0;System.out.println("""isEven: (x: Int)Boolean""");$skip(73); val res$20 = 
  iterNumPred(isEven _, List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13));System.out.println("""res20: Int = """ + $show(res$20));$skip(45); val res$21 = 
  iterNumPred(isEven _, List(2, 4, 6, 8, 1));System.out.println("""res21: Int = """ + $show(res$21));$skip(188); 

  def recNumPred[T](pred: T => Boolean, l: List[T]): Int =
    {
      if (l == Nil) 0
      else if (pred(l.head)) 1 + recNumPred(pred, l.tail)
      else recNumPred(pred, l.tail)
    };System.out.println("""recNumPred: [T](pred: T => Boolean, l: List[T])Int""");$skip(72); val res$22 = 
  recNumPred(isEven _, List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13));System.out.println("""res22: Int = """ + $show(res$22));$skip(44); val res$23 = 
  recNumPred(isEven _, List(2, 4, 6, 8, 1));System.out.println("""res23: Int = """ + $show(res$23));$skip(328); 

  def tailNumPred[T](pred: T => Boolean, l: List[T]) =
    {
      def helper(predH: T => Boolean, ll: List[T], total: Int): Int =
        {
          if (ll == Nil) total
          else if (predH(ll.head)) helper(predH, ll.tail, total + 1)
          else helper(predH, ll.tail, total)
        }
      helper(pred, l, 0)
    };System.out.println("""tailNumPred: [T](pred: T => Boolean, l: List[T])Int""");$skip(73); val res$24 = 
  tailNumPred(isEven _, List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13));System.out.println("""res24: Int = """ + $show(res$24));$skip(62); val res$25 = 
  tailNumPred(isEven _, List(2, 4, 6, 0, 2, 4, 6, 8, 10, 12));System.out.println("""res25: Int = """ + $show(res$25));$skip(95); 

  def mfrNumPred[T](pred: T => Boolean, l: List[T]) =
    {
      l.filter(pred).length
    };System.out.println("""mfrNumPred: [T](pred: T => Boolean, l: List[T])Int""");$skip(61); val res$26 = 
  mfrNumPred(isEven _, List(2, 4, 6, 0, 2, 4, 6, 8, 10, 12));System.out.println("""res26: Int = """ + $show(res$26));$skip(72); val res$27 = 
  mfrNumPred(isEven _, List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13));System.out.println("""res27: Int = """ + $show(res$27));$skip(32); val res$28 = 
  mfrNumPred(isEven _, List(2));System.out.println("""res28: Int = """ + $show(res$28));$skip(351); 

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
    };System.out.println("""iterAllPred: [T](pred: T => Boolean, l: List[T])Boolean""");$skip(46); val res$29 = 
  iterAllPred(isEven _, List(2, 4, 6, 8, 10));System.out.println("""res29: Boolean = """ + $show(res$29));$skip(49); val res$30 = 
  iterAllPred(isEven _, List(1, 2, 4, 6, 8, 10));System.out.println("""res30: Boolean = """ + $show(res$30));$skip(172); 

  def recAllPred[T](pred: T => Boolean, l: List[T]): Boolean =
    {
      if (l == Nil) true
      else if (pred(l.head)) recAllPred(pred, l.tail)
      else false
    };System.out.println("""recAllPred: [T](pred: T => Boolean, l: List[T])Boolean""");$skip(48); val res$31 = 
  recAllPred(isEven _, List(1, 2, 4, 6, 8, 10));System.out.println("""res31: Boolean = """ + $show(res$31));$skip(53); val res$32 = 
  recAllPred(isEven _, List(2, 4, 6, 8, 10, 12, 14));System.out.println("""res32: Boolean = """ + $show(res$32));$skip(315); 

  def tailAllPred[T](pred: T => Boolean, l: List[T]) =
    {
      def helper[T](predd: T => Boolean, ll: List[T], result: Boolean): Boolean =
        {
          if (ll == Nil) result
          else if (predd(ll.head)) helper(predd, ll.tail, true)
          else false
        }
      helper(pred, l, true)
    };System.out.println("""tailAllPred: [T](pred: T => Boolean, l: List[T])Boolean""");$skip(49); val res$33 = 
  tailAllPred(isEven _, List(1, 2, 4, 6, 8, 10));System.out.println("""res33: Boolean = """ + $show(res$33));$skip(33); val res$34 = 
  tailAllPred(isEven _, List(2));System.out.println("""res34: Boolean = """ + $show(res$34));$skip(52); val res$35 = 
  tailAllPred(isEven _, List(2, 4, 6, 8, 10,11,12));System.out.println("""res35: Boolean = """ + $show(res$35));$skip(54); val res$36 = 
  tailAllPred(isEven _, List(2, 2, 2, 2, 2, 2, 2, 2));System.out.println("""res36: Boolean = """ + $show(res$36));$skip(145); 

  def mfrAllPred[T](pred: T => Boolean, l: List[T]): Boolean =
    {

      if (l.filter(pred).length == l.length) true
      else false

    };System.out.println("""mfrAllPred: [T](pred: T => Boolean, l: List[T])Boolean""");$skip(53); val res$37 = 
  mfrAllPred(isEven _, List(2, 2, 2, 2, 2, 2, 2, 2));System.out.println("""res37: Boolean = """ + $show(res$37));$skip(53); val res$38 = 
  mfrAllPred(isEven _, List(1, 2, 2, 2, 2, 2, 2, 2));System.out.println("""res38: Boolean = """ + $show(res$38));$skip(55); val res$39 = 
  mfrAllPred(isEven _, List(2, 2, 2, 2, 2, 2, 111, 2));System.out.println("""res39: Boolean = """ + $show(res$39));$skip(53); val res$40 = 
  mfrAllPred(isEven _, List(2, 2, 5, 2, 3, 2, 2, 2));System.out.println("""res40: Boolean = """ + $show(res$40));$skip(53); val res$41 = 
  mfrAllPred(isEven _, List(2, 2, 2, 2, 4, 2, 2, 2));System.out.println("""res41: Boolean = """ + $show(res$41));$skip(263); 

  //
  /// Problem 8 : Returns true if any elenent satisfies the given predicate
  //
  
  
  def iterAnyPred[T](pred:T => Boolean, l: List[T]) =
  {
  	var badBool:Boolean = false
  	for(i<-0 to l.length-1)
  		if(pred(l(i))) badBool = true
  	badBool
  		
  };System.out.println("""iterAnyPred: [T](pred: T => Boolean, l: List[T])Boolean""");$skip(50); val res$42 = 
  iterAnyPred(isEven _, List(1,3,5,7,9,11,13,15));System.out.println("""res42: Boolean = """ + $show(res$42));$skip(53); val res$43 = 
  iterAnyPred(isEven _, List(1,3,5,7,9,11,13,15, 2));System.out.println("""res43: Boolean = """ + $show(res$43));$skip(33); val res$44 = 
  iterAnyPred(isEven _, List(2));System.out.println("""res44: Boolean = """ + $show(res$44));$skip(156); 
  
  def recAnyPred[T](pred:T => Boolean, l: List[T]):Boolean =
  {
  	if(l == Nil) false
  	else if(pred(l.head)) true
  	else recAnyPred(pred,l.tail)
  };System.out.println("""recAnyPred: [T](pred: T => Boolean, l: List[T])Boolean""");$skip(49); val res$45 = 

	recAnyPred(isEven _, List(1,3,5,7,9,11,13,15));System.out.println("""res45: Boolean = """ + $show(res$45));$skip(52); val res$46 = 
  recAnyPred(isEven _, List(1,3,5,7,9,11,13,15, 2));System.out.println("""res46: Boolean = """ + $show(res$46));$skip(32); val res$47 = 
  recAnyPred(isEven _, List(2));System.out.println("""res47: Boolean = """ + $show(res$47));$skip(280); 
  
  
  def tailAnyPred[T](pred:T => Boolean, l: List[T]) =
  {
  	def helper[T](predd: T => Boolean, ll: List[T], result: Boolean): Boolean =
  	{
  		if (ll == Nil) false
  		else if(predd(ll.head)) true
  		else helper(predd, ll.tail, false)
  	}
  	helper(pred, l, false)
  };System.out.println("""tailAnyPred: [T](pred: T => Boolean, l: List[T])Boolean""");$skip(53); val res$48 = 
  
  tailAnyPred(isEven _, List(1,3,5,7,9,11,13,15));System.out.println("""res48: Boolean = """ + $show(res$48));$skip(53); val res$49 = 
  tailAnyPred(isEven _, List(1,3,5,7,9,11,13,15, 2));System.out.println("""res49: Boolean = """ + $show(res$49));$skip(33); val res$50 = 
  tailAnyPred(isEven _, List(2));System.out.println("""res50: Boolean = """ + $show(res$50));$skip(113); 
  
  def mfrAnyPred[T](pred:T =>Boolean, l:List[T])=
  {
  	if(l.filter(pred).length >=1) true
  	else false
  };System.out.println("""mfrAnyPred: [T](pred: T => Boolean, l: List[T])Boolean""");$skip(49); val res$51 = 
  mfrAnyPred(isEven _, List(1,3,5,7,9,11,13,15));System.out.println("""res51: Boolean = """ + $show(res$51));$skip(51); val res$52 = 
  mfrAnyPred(isEven _, List(1,3,5,7,9,11,13,15,2));System.out.println("""res52: Boolean = """ + $show(res$52));$skip(34); val res$53 = 
  mfrAnyPred(isEven _, List(2,1));System.out.println("""res53: Boolean = """ + $show(res$53))}
}
