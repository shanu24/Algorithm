package Thesis;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.Security;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class Server {


    private static String id,pin;

    
    static Random random=new Random();
 
   
    //public static void main(String args[]) throws Exception
    public HashMap<String, String> getClientDetails() throws Exception
    
    {
            
            /* This is how to declare HashMap */
            HashMap<String, String> hmap = new HashMap<String, String>();    
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

            /*Adding elements to HashMap*/
            System.out.print("Enter your ID: ");
            id=br.readLine();
            hmap.put("id", id);
            
            System.out.print("Enter your ID: " +id);
            
            System.out.print("Enter your PIN: ");
           
           pin=br.readLine();
            hmap.put("pin", pin);

            hmap.put("b", ""+random.nextInt(255)+"");

            /* Display content using Iterator*/
            //Set set = hmap.entrySet();
            Iterator<String> iterator = hmap.keySet().iterator();
            while(iterator.hasNext()) {
            	
               String key = (String)iterator.next();
               String value=(String)hmap.get(key);
               System.out.println("key is: "+ key + " & Value is: "+ value);
               
            }
            
         return hmap;   
    }
}
