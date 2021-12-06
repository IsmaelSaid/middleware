import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.Charset;

public class udpprog2 {

    public static void main(String[] args) {
        System.out.println("programme d'écoute");
        DatagramSocket socket_udp = null;


        try {
            socket_udp = new DatagramSocket(8000);
        } catch (SocketException e) {
            System.out.println("impossible d'obtenit le socket");
            e.printStackTrace();
        }

        DatagramPacket packet = new DatagramPacket(new byte[512],512);
        
        while(true){
            try {
                System.out.println("Attente du prochain packet");
                socket_udp.receive(packet);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    
            byte[] content = packet.getData();
            String message = new String(content,0,packet.getLength(),Charset.forName("UTF-8"));
            System.out.println(message);

        }

        // socket_udp.close();
    }
    
}
