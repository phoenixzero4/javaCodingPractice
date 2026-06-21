package practice;
import java.net.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;

public class SystemMethods
{


    public static void main(String[] args) throws UnknownHostException
    {
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            System.out.println(inetAddress);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
