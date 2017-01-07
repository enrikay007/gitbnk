/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

/**
 *
 * @author eric
 */

import java.util.Scanner;

public class ATM {
    
       
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    // init Scanner
        Scanner sc = new Scanner(System.in);
        
    // init Bank
        Bank theBank = new Bank("Mina Tjanster Sverige Bank");
        
    // add a user which also creates a savings account
        User aUser = theBank.addUser("Peter", "Blom", "1234");
        
   // add a checking account for our user
        Account newAccount = new Account("Checking", aUser, theBank);
        aUser.addAccount(newAccount);
        theBank.addAccount(newAccount);
        
        User curUser;
        while(true){
        
            // Stay in this login prompt until successful login
            curUser = ATM.mainMenuPrompt(theBank, sc);
            
            // stay in main menu until user quits
            ATM.printUserMenu(curUser, sc);
        
        }
        
    }
 
    /**
     * 
     * @param theBank
     * @param sc
     * @return 
     */
    public static User mainMenuPrompt(Bank theBank, Scanner sc){
    
        //inits
        String userID;
        String pin;
        User authUser;
        
        //prompt the user for user ID/pin combo until a cprrect one is reached
        do{
            System.out.printf("\n\n Welcome to %s\n\n", theBank.getName());
            System.out.print("Enter user ID: ");
            userID = sc.nextLine();
            System.out.printf("Enter pin: ");
            pin = sc.nextLine();
            
            // try to get the user object corresponding to the ID and pin combo
            authUser = theBank.userLogin(userID, pin);
            if(authUser == null){
                System.out.println("Incorrect user ID/pin combination. "+ 
                        "Please try again.");
            }
        } while(authUser == null); // continue looping until successful login
            return authUser;
    }
 
    public static void printUserMenu(User theUser, Scanner sc){
    // print a summary of the user's accounts
        theUser.printAccountsSummary();
        
     //init
     int choice;
        
    
     //user menu
     do {
     System.out.printf("Welcome %s, what would like to do?\n",
             theUser.getFirstName());
     System.out.println("  1) Show account transaction history");
     System.out.println("  2) Withdraw");
     System.out.println("  3) Payment");
     System.out.println("  4) Transfer");
     System.out.println("  5) Quit");
     System.out.println();
     System.out.println("Enter choice: ");
     choice = sc.nextInt();
     
     if (choice < 1 || choice > 5){
     System.out.println("Invalid choice. Please choose 1-5");
     }
     }while(choice < 1 || choice > 5);
     
    // process the choice
     switch (choice){
         
         case 1:
             ATM.showTransHistory(theUser, sc);
             break;
         case 2:
             ATM.withdrawalFunds(theUser, sc);
             break;
         case 3:
             ATM.payment(theUser,sc);
             break;
         case 4:
             ATM.transferFunds(theUser, sc);
             break;
         case 5:
            //take up the rest of previous input
            sc.nextLine();
             break;
     }
        // redisplay this menu unless the user wants to quit
             if(choice != 5){
                 ATM.printUserMenu(theUser, sc);
             }
             
     }
     
     /**
      * Show the transaction history for an account
      * @param theUser the logged in User object
      * @param sc the Scanner object used for user input
      */
     public static void showTransHistory(User theUser, Scanner sc){  
         int theAcct;
         
         // get account whose transaction history to look at
         do {
             System.out.printf("Enter the number (1-%d) of the account" + 
                     "whose transaction you want to see:",
             theUser.numAccounts());
             theAcct = sc.nextInt()-1;
             if (theAcct < 0 || theAcct >= theUser.numAccounts()){
             System.out.println("Invalid account, Please try again.");
             }
             
             
         } while (theAcct < 0 || theAcct >= theUser.numAccounts());
     // print the transaction history
         theUser.printAcctTransHistory(theAcct);
     }

     
     /**
      * Processing transferring funds from one account to another
      * @param theUser the logged in User Object
      * @param sc  the Scanner object used for user input
      */
    public static void transferFunds(User theUser, Scanner sc){
      //inits
        int fromAcct;
        int toAcct;
        double amount;
        double acctBal;
        
        // get the account to transfer from
              do {
              System.out.printf("Enter the number (1-%d) of the account\n" +
                      "to transfer from: ", theUser.numAccounts());
              fromAcct = sc.nextInt()-1;
              if(fromAcct < 0 || fromAcct >= theUser.numAccounts()){
             System.out.println("Invalid account, Please try again.");
              }
              }while(fromAcct < 0 || fromAcct >= theUser.numAccounts());
              acctBal = theUser.getAcctBalance(fromAcct);
              
       //get the account to transfer to
              do {
              System.out.printf("Enter the number (1-%d) of the account\n" +
                      "to transfer to: ", theUser.numAccounts());
              toAcct = sc.nextInt()-1;
              if(toAcct < 0 || toAcct >= theUser.numAccounts()){
             System.out.println("Invalid account, Please try again.");
              }
              }while(toAcct < 0 || toAcct >= theUser.numAccounts());
             
      // get the amount to transfer
              do {
                System.out.printf("Enter the amount to transfer (max $%.02f): $",
                        acctBal);
                amount = sc.nextDouble();
                if (amount < 0){
                System.out.println("Amount must be greater than zero.");
                }else if(amount > acctBal){
                System.out.printf("Amount must not be greater than\n" +
                        "balance of $%.02f.\n", acctBal);
                }
              } while(amount < 0 || amount > acctBal);
              
    // Finally, do the transfer
              theUser.addAcctTransaction(fromAcct, -1*amount, String.format(
              "Transfer to account %s", theUser.getAcctUUID(toAcct)));
              
              theUser.addAcctTransaction(toAcct, amount, String.format(
              "Transfer to account %s", theUser.getAcctUUID(fromAcct)));
              
    } 
     
    
    /**
     * Process a fund withdrawal from an account
     * @param theUser the logged-in User object
     * @param sc the Scanner object for user input 
     */
    public static void withdrawalFunds(User theUser, Scanner sc){
        
        //inits
        int fromAcct;
        double amount;
        double acctBal;
        String memo;
        
        // get the account to transfer from
              do {
              System.out.printf("Enter the number (1-%d) of the account\n" +
                      "to withdraw from: ",theUser.numAccounts());
              fromAcct = sc.nextInt()-1;
              if(fromAcct < 0 || fromAcct >= theUser.numAccounts()){
             System.out.println("Invalid account, Please try again.");
              }
              }while(fromAcct < 0 || fromAcct >= theUser.numAccounts());
              acctBal = theUser.getAcctBalance(fromAcct);
     


        // get the amount to transfer
              do {
                System.out.printf("Enter the amount to withdraw (max $%.02f): $",
                        acctBal);
                amount = sc.nextDouble();
                if (amount < 0){
                System.out.println("Amount must be greater than zero.");
                }else if(amount > acctBal){
                System.out.printf("Amount must not be greater than\n" +
                        "balance of $%.02f.\n", acctBal);
                }
              } while(amount < 0 || amount > acctBal);
              
              //take up the rest of previous input
            sc.nextLine();
            
            //get a memo
            System.out.println("Enter a memo: ");
            memo = sc.nextLine();
            
            // do the withdraw
            theUser.addAcctTransaction(fromAcct, -1*amount, memo);
    }
 
    /**
     * Process a payment to an account
     * @param theUser the logged-in User object
     * @param sc the Scanner object used for user input
     */
    public static void payment(User theUser, Scanner sc){
    
        //inits
        int toAcct;
        double amount;
        double acctBal;
        String memo;
        
        // get the account to transfer from
              do {
              System.out.printf("Enter the number (1-%d) of the account\n" +
                      "to pay into: ", theUser.numAccounts());
              toAcct = sc.nextInt()-1;
              if(toAcct < 0 || toAcct >= theUser.numAccounts()){
             System.out.println("Invalid account, Please try again.");
              }
              }while(toAcct < 0 || toAcct >= theUser.numAccounts());
              acctBal = theUser.getAcctBalance(toAcct);
     


        // get the amount to transfer
              do {
                System.out.printf("Enter the amount to transfer (max $%.02f): $",
                        acctBal);
                amount = sc.nextDouble();
                if (amount < 0){
                System.out.println("Amount must be greater than zero.");
                }
              } while(amount < 0);
              
              //take up the rest of previous input
            sc.nextLine();
            
            //get a memo
            System.out.println("Enter a memo: ");
            memo = sc.nextLine();
            
            // do the withdraw
            theUser.addAcctTransaction(toAcct, amount, memo);

    }
}
