public class Account  
 {
   private int accountNumber;   // account number  
   private int pin;    // PIN for authentication  
   private double openingBalance;   
   private double overDraft; 
   
   public Account(int theAccountNumber, int thePIN, double theOpeningBalance, double theOverDraft){  
     accountNumber = theAccountNumber;  
     pin = thePIN;  
     openingBalance = theOpeningBalance;  
     overDraft = theOverDraft;  
   }
   
   // determines whether a user-specified PIN matches PIN in Account
   public boolean validatePIN(int userPIN){  
     if(userPIN == pin){  
       return true;  // means the PIN input is match with the user's PIN  
     }  
     else{  
       return false;  // means the PIN input is not match with the user's PIN  
     }  
   }
   
    // returns available balance  
   public double getAvailableBalance(){  
     return openingBalance;  
   }  
   // returns the total balance  
   public double getTotalBalance(){  
     return overDraft; 
   }
   
    // credits an amount to the account  
   public void credit(double amount){  
     overDraft += amount;   // add to total balance  
   }  
   
   // debits an amount from the account  
   public void debit(double amount){  
     openingBalance -= amount;   // substract from available balance  
     overDraft -= amount;     // substract from total balance  
   } 
   
   // returns account number  
   public int getAccountNumber(){  
     return accountNumber;  
   }
   
 }
