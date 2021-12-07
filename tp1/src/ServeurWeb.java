import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServeurWeb {
    public static String MimeForFileName (String str) {
        String[] result = str.split("\\.");
        return result.length ==2 ? result[1] : "application";
         


        
    }
    public static void main(String[] args) {
        ServerSocket socket_server = null;
        
        try {
            socket_server = new ServerSocket(8000);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.exit(-1);
            e.printStackTrace();
        }
        
        while(true){
            System.out.println("Attente d'un nouveau client...");
            try {
                Socket socket_client = socket_server.accept();
                System.out.println("Traitement d'un nouveau client");
                Processusclient ps = new Processusclient(socket_client);
                ps.run();
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
            
        }
        
    }
}
