package shanu;
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
import org.bouncycastle.crypto.prng.RandomGenerator;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
 
public class ControlServer
{
	private static Socket socket;
	private static ServerSocket serverSocket;
	
    private static String sendmessage1, sendmessage2;
    private static String pi,qi,ri,si,bi;
    private static String a,str,id,pin ;
    private static int x,c,b;
    Random random=new Random();
	
	 public static String getXor(String num1, String num2)
	    {
	    		String result="";
	    		
	    		BigInteger int1=new BigInteger(num1);
	    		BigInteger int2=new BigInteger(num2);
	    		BigInteger resu=int1.xor(int2);
	    		result=""+resu;
	    		return result;
	    }
	    
	    public static String getXor(String num1, int num2)
	    {
	    		String result="";
	    		if (null == num1)
	    			num1="0";
	    		BigInteger int1=new BigInteger(num1);
	    		
	    		BigInteger int2 = new BigInteger(Integer.toString(num2));
	     		BigInteger resu=int1.xor(int2);
	    		result=""+resu;
	    		return result;
	    }
	    
	    public static final int getRandomInt(){
			
			 int result=0;
			 Random random=new Random();
			 int a=random.nextInt(255);
			 System.out.println(a);
			 
			 return result;
			 
		 } 
 
    public static void main(String[] args)
    {
        try
        {
 
            int port = 25000;
            serverSocket = new ServerSocket(port);
            System.out.println("Server Started and listening to the port 25000");
 
            //Server is running always. This is done using this while(true) loop
            while(true)
            {
                //Reading the message from the client
                socket = serverSocket.accept();
                InputStream is = socket.getInputStream();
                ObjectInputStream isr = new ObjectInputStream(is);
               HashMap hmap=(HashMap)isr.readObject();
               Iterator iterator = hmap.keySet().iterator();
               while(iterator.hasNext()) {
               	
                  String key = (String)iterator.next();
                  String value=(String)hmap.get(key);
                  System.out.println("Server :"+ "key is: "+ key + " & Value is: "+ value);
                  
               }
            
               String bi= id+""+a+""+b;
               Security.addProvider(new BouncyCastleProvider());
               MessageDigest mdbi = MessageDigest.getInstance("SHA-512", "BC");
           	   byte [] digestbi = mdbi.digest(bi.getBytes()); 
               
           	   x=getRandomInt();
           	   String pi= bi+""+x;
               MessageDigest mdpi = MessageDigest.getInstance("SHA-512", "BC");
           		byte [] digestpi = mdbi.digest(pi.getBytes());
           	
           		c= getRandomInt();
           		String qi= pi+""+c;
           		MessageDigest mdqi = MessageDigest.getInstance("SHA-512", "BC");
           		byte [] digestqi = mdqi.digest(qi.getBytes());
           	
           		String ri= getXor(si,c);
           		MessageDigest mdri = MessageDigest.getInstance("SHA-512", "BC");
           		byte [] digestri = mdri.digest(ri.getBytes());
           	
           		
           		/* To print values at server */
           		System.out.println("Value at server for B: "+new String(digestbi));
           		System.out.println("Value at server for P: "+new String(digestpi));
           		System.out.println("Value at server for Q: "+new String(digestqi));
           		System.out.println("Value at server for R: "+new String(digestri));
           		
           	                                
               //stores the parameters on card and then sends card to the user.
            
            HashMap<String, byte[]> mapByte=new HashMap<String, byte[]>();
            mapByte.put("B", digestbi);
            mapByte.put("P", digestpi);
            mapByte.put("Q", digestqi);
            mapByte.put("R", digestri);
            
            //Sending the response back to the client.
            

            OutputStream os = socket.getOutputStream();
            ObjectOutputStream obj = new ObjectOutputStream(os);
            obj.writeObject(mapByte);
            System.out.println("Message sent to the client ");
            //os.flush();
         
             }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                socket.close();
            }
            catch(Exception e){}
        }
    }
}


