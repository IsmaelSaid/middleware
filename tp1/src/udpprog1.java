import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;

// Classe pour envoyer le packet 
public class udpprog1 {

    public static void main(String[] args) {
        System.out.println("prgramme d'envoi");

        DatagramSocket socket_udp = null;

        try {
            socket_udp = new DatagramSocket();
        } catch (SocketException e) {
            System.out.println("impossible d'obtenit le socket");
            e.printStackTrace();
        }
        
        
        // Preparation du packet
        DatagramPacket packet = new DatagramPacket(new byte[512], 512);
        


        String message = "fuck the world";
        try {
            packet.setAddress(InetAddress.getByName("localhost"));
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        }
        packet.setData(message.getBytes(Charset.forName("UTF-8")));
        packet.setPort(8000);
        
        // Transmission du packet
        try {
            socket_udp.send(packet);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("impossible d'envoyer le packet");
            e.printStackTrace();
        }
    }

    
}
