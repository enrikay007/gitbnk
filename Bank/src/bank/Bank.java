/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author eric
 */
public class Bank {

    private String name;
    
    private ArrayList<User> users;
    
    private ArrayList<Account> accounts;
    
    
    /**
     * Create a new bank object with empty lists of users and accounts
     * @param name  the name of the bank
     */
    public Bank(String name){
    this.name = name;
    this.users = new ArrayList<User>();
    this.accounts = new ArrayList<Account>();
    }
    
    /**
     * Generate a new universally unique ID for a user.
     * @return the uuid
     */
    public String getNewUserUUID(){
        
        // inits
        String uuid;
        Random rng = new Random();
        int len = 6;
        boolean nonUnique;
        
        // continue looping until we get a unique ID
        do {   
            // generate the number
            uuid = "";
            for (int c=0; c <len; c++){
                uuid +=((Integer)rng.nextInt(10)).toString();
                
            }
            
            nonUnique = false;
            // check to make sure its unique
            for(User u: this.users){
                if (uuid.compareTo(u.getUUID())==0){
                    nonUnique = true;
                    break;
                }
            }
        } while (nonUnique);
          return uuid;
    }
    
    
    /**
     * Generate a new universally unique ID for an account
     * @return the uuid
     */
    public String getNewAccountUUID(){
        // inits
        String uuid;
        Random rng = new Random();
        int len = 10;
        boolean nonUnique;
        
        // continue looping until we get a unique ID
        do {   
            // generate the number
            uuid = "";
            for (int c=0; c <len; c++){
                uuid +=((Integer)rng.nextInt(10)).toString();
                
            }
            
            nonUnique = false;
            // check to make sure its unique
            for(Account u: this.accounts){
                if (uuid.compareTo(u.getUUID())==0){
                    nonUnique = true;
                    break;
                }
            }
        } while (nonUnique);
          return uuid;
        
    }
    
    
    
    
     /**
     * Add an account for the user
     * @param anAcct  the account to add
     */
    public void addAccount(Account anAcct){
    this.accounts.add(anAcct);
    }
    
    /**
     * Create a new user of the bank
     * @param firstName the users first name
     * @param lastName the users last name
     * @param pin the user's pin
     * @return  the new User object
     */
  
    
    public User addUser(String firstName, String lastName, String pin){
    
    // create a new user object and add it to out list
        User newUser = new User(firstName, lastName, pin, this);
        this.users.add(newUser);
        
    // creat a savings account for the user
    Account newAccount = new Account("Savings", newUser, this);
    //add to holder and bank lists
    newUser.addAccount(newAccount);
    this.addAccount(newAccount);
    return newUser;    
    }
    
    /**
     * 
     * @param userID
     * @param pin
     * @return 
     */
    public User userLogin(String userID, String pin){
    
        // search through list of users
        for(User u: this.users){
        
        // check user ID is correct
        if (u.getUUID().compareTo(userID) == 0 && u.validatePin(pin)){
            return u;
        }
        }
        
        // if we haven't found the user or have an incorrect pin
        return null;
        
    }
    
    /**
     * 
     * @return 
     */
    public String getName(){
    return this.name;
    }
}
