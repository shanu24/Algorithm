package Thesis;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

import shanu.ControlServer;

public class Client {
	 private static Socket socket;
	 
	    public static void main(String args[])
	    {
	        try
	        {
	            String host = "localhost";
	            int port = 25000;
	            InetAddress address = InetAddress.getByName(host);
	            socket = new Socket(address, port);
	            
	         
	            OutputStream os = socket.getOutputStream();
	            ObjectOutputStream obj = new ObjectOutputStream(os);
	            obj.writeObject(new Server().getClientDetails());
	 
	          
	            
	        }
	        catch (Exception exception)
	        {
	            exception.printStackTrace();
	        }
	        finally
	        {
	            //Closing the socket
	            try
	            {
	                socket.close();
	            }
	            catch(Exception e)
	            {
	                e.printStackTrace();
	            }
	        }
	        
	    }

}
