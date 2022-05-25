public class BankDatabase  
 {  
   private Account[] accounts;   // array of Accounts  
   // no-argument BankDatabase constructor initializes accounts  
   public BankDatabase(){  
     accounts = new Account[2];   // just 2 accounts for testing  
     accounts[0] = new Account(123456789, 1234, 800, 200);  
     accounts[1] = new Account(987654321, 4321, 1230, 150);   
   }  // end no-argument BankDatabase constructor  
   // retrieve Account object containing specified account number  
   private Account getAccount(int accountNumber){  
     // loop through accounts searching for matching account number  
     for(Account currentAccount : accounts){  
       // return current account if match found  
       if(currentAccount.getAccountNumber() == accountNumber) return currentAccount;  
     }  // end for  
     return null;  // if no matching account was found, return null  
   }  // end method  
   // determine whether user-specified account number and PIN match  
   // those of an account in the database  
   public boolean authenticateUser(int userAccountNumber, int userPIN){  
     // attempt to retrieve the account with the account number  
     Account userAccount = getAccount(userAccountNumber);  
     if(userAccount != null){  
       return userAccount.validatePIN(userPIN);  
     }  
     else{  
       return false;  // account number not found, so return false  
     }  
   }  // end method  
   // return available balance of Account with specified account number  
   public double getAvailableBalance(int userAccountNumber){  
     return getAccount(userAccountNumber).getAvailableBalance();  
   }  // end method  
   public double getTotalBalance(int userAccountNumber){  
     return getAccount(userAccountNumber).getTotalBalance();  
   }  // end method  
   public void credit(int userAccountNumber, double amount){  
     getAccount(userAccountNumber).credit(amount);  
   }  // end method  
   public void debit(int userAccountNumber, double amount){  
     getAccount(userAccountNumber).debit(amount);  
   }  // end method  
   }  // end class BankDatabase
