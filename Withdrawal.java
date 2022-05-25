public class Withdrawal extends Transaction  
 {  
   private int amount;   // amount to withdraw  
   private Keypad keypad; // references to keypad  
   private CashDispenser cashDispenser;  // references to cash dispenser
   private BankDatabase bankDatabase;
   // constant corresponding to menu option to cancel  
   private final static int CANCELED = 6;  
   // Withdrawal constructor  
   public Withdrawal(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase, Keypad atmKeypad, CashDispenser atmCashDispenser){  
     // initializes superclass variables  
     super(userAccountNumber, atmScreen, atmBankDatabase);  
     // initializes references to keypad and cash dispenser  
     keypad = atmKeypad;  
     cashDispenser = atmCashDispenser;  
   }  // end Withdrawal constructor 
   
   
   // perform transaction  
   @Override  
   public void execute(){  
     boolean cashDispensed = false; // cash was not dispensed yet  
     double availableBalance;    // amount available for withdrawal  
     // get references to bank database and screen  
     BankDatabase bankDatabase = getBankDatabase();  
     Screen screen = getScreen();  
    
     // loop until cash is dispensed or the user cancels  
     do{  
       // obtain a chosen withdrawal amount from the user  
       amount = displayMenuOfAmounts();  
       // check whether user choose a withdrawal amount or canceled  
       if(amount != CANCELED){  
         // get available balance of account involved  
         availableBalance = bankDatabase.getAvailableBalance(getAccountNumber());  
         // check whether the user has enough money in the account  
         if(amount <= availableBalance){  
           // check whether the cash dispenser has enough money  
           if(cashDispenser.isSufficientCashAvailable(amount)){  
             // update the account involved to reflect the withdrawal  
             bankDatabase.debit(getAccountNumber(), amount);  
             cashDispenser.dispenseCash(amount);   // dispense cash  
             cashDispensed = true;  // cash was dispensed  
             // instructs user to take cash  
             screen.displayMessageLine("\nYour cash has been dispensed. Please take your cash now.");  
           }  // end if  
           else{  
             // cash dispenser does not have enough cash  
             screen.displayMessageLine("\nInsufficient cash available in the ATM.");  
             screen.displayMessageLine("\nPlease choose a smaller amount.");  
           }  // end if  
         }  // end if  
         else{  
           // not enough money available in user's account  
           screen.displayMessageLine("\nInsufficient funds in your account.");  
           screen.displayMessageLine("\nPlease choose a smaller amount.");  
         }  // end if  
       }  // end if  
       else{  
         // user choose cancel menu option  
         screen.displayMessageLine("\nCancelling transactions...");  
         return;   // return to main menu because user canceled  
       }  // end if  
     }  while(!cashDispensed);  
   }  // end method execute  
   // display a menu of withdrawal amounts and the options to cancel  
   // return the chosen amount or 0 if the user chooses to cancel  
   private int displayMenuOfAmounts(){  
     int userChoice = 0;   // local variable to store return value  
     Screen screen = getScreen();  // get screen references  
     // array of amounts to correspond to menu numbers  
     int[] amounts = {0, 20, 40, 60, 100, 200};  
     // loop while no valid choice has been made  
     while(userChoice == 0){  
       // display the withdrawal menu  
       screen.displayMessageLine("\nWithdrawal Menu : ");  
       screen.displayMessageLine("1 - €20");  
       screen.displayMessageLine("2 - €40");  
       screen.displayMessageLine("3 - €60");  
       screen.displayMessageLine("4 - €100");  
       screen.displayMessageLine("5 - €200");  
       screen.displayMessageLine("6 - Cancel Transaction");  
       screen.displayMessage("\nChoose a withdrawal amount : ");  
       int input = keypad.getInput();   // get user input through keypad  
       // determine how to proceed based on the input value  
       switch(input){  
         // if the user choose a withdrawal amount  
         // i.e choose option 1, 2, 3, 4 or 5  
         // return the corresponding amount from amounts's array  
         case 1 :  
         case 2 :  
         case 3 :  
         case 4 :  
         case 5 :  
           userChoice = amounts[input];    // save user's choice  
           break;  
         case CANCELED :  
           // the user choose to cancel  
           userChoice = CANCELED;   // save user's choice  
           break;  
         default :  
           // the user did not enter value between 1-6  
           screen.displayMessageLine("\nInvalid selection.");  
           screen.displayMessageLine("Try again.");  
       }  // end switch  
     }  // end while  
     return userChoice;   // return withdrawal amount or CANCELED  
   }  // end method displayMenuOfAmounts  
   }  // end class Withdrawal
