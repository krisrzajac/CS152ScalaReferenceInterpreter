package test

object HelloWorld {
  def main(args: Array[String]) {
    println("Hello, world!")
    def echo {
      var response: String = "";
      val prompt = "=> ";
      while (response != "quit") {
        print(prompt);
        response = readLine();
        println("you typed: " + response)
      }
    }
    
    val str1 = "      lalalalala     "
    def trimmer(str:String) = {str.trim}
    trimmer(str1)
    println(str1)
    str1(10)
  }
}	
