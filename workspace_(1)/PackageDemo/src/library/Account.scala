package library


package banking {
   import hr._
   class Account { }
   class Transaction { }
   class AccountHolder extends hr.Person {}
}


package hr {
  
  class Person{ }
  class Employee extends Person{ }
  class Manager extends Employee
  class Benefit{}
  
}
    
package business {
  import banking._
  class Customer extends AccountHolder{ }
}