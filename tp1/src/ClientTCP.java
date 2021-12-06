import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientTCP {

    public static void main(String[] args) {
        Socket socket_to_server = null;

        try {
            socket_to_server = new Socket("localhost",8000);
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            socket_to_server.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
