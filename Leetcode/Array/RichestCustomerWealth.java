import java.util.ArrayList; 
import java.lang.Math;
import java.lang.reflect.Array;
import java.util.Scanner;

// 1672
public class RichestCustomerWealth {
    public static void main(String[] args){

    int[][] accounts = {{2,8,7}, {7,1,3}, {1,9,5}}; //[2,8,7],[7,1,3],[1,9,5]
                                                    //17      11      15
    int max = 0;

    
       for (int i =0 ; i < accounts.length; i++){
        int value = 0; // should be inside the frist loop because, after the innner loop ends, it sets the value back to 0
                        // so that we can add the value together in the next index separetly
                        // if you put it globally above the loops, it wont reset back to 0, it will just keep on adding together 
                        // all the values inside the index 
        for(int j = 0; j < accounts[i].length; j++){
            
            value = value + accounts[i][j];
        
        }
        if ( value > max){
            max = value;
        }
       
       }
        
        System.out.println(max);
     
        
}}
