import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.DrbgParameters.Reseed;

public class ServeurWeb {
    
    public static void main(String[] args) {
        ServerSocket socket_server = null;
        
        try {
            socket_server = new ServerSocket(8000);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        while(true){
            System.out.println("Attente d'un nouveau client...");
            InputStream from_client = null;
            BufferedReader breader = null;
            Socket socket_client = null;
            try {
                socket_client = socket_server.accept();
                System.out.println("Connexion d'un client");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            
            try {
                from_client = socket_client.getInputStream();
                breader = new BufferedReader(new InputStreamReader(from_client));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                continue;
            }
            // """""""""""""""""""""""""""""""""""Affichage port et ip"""""""""""""""""""""""""""""""""""
            System.out.printf("Port: %d \n",socket_client.getPort());
            System.out.printf("IP:  %s \n",socket_client.getInetAddress().getHostName());
            
            // """"""""""""""""""""""""""""""""""Affichage de l'en tête""""""""""""""""""""""""""""""""""
            
            String line=null;
            boolean firstline = true;
            
            do {
                try {
                    line = breader.readLine();
                    if(firstline){
                        System.out.println("''''''''''''''''''''''''''Commande requete''''''''''''''''''''''''''");
                        String[] result = line.split(" "); 
                        System.out.printf("command > %s\n",result[0]);
                        System.out.printf("url > %s\n",result[1]);
                        System.out.printf("version : %s\n",result[2]);
                        firstline = false;
                        System.out.println("''''''''''''''''''''''''''Fin Commande requete''''''''''''''''''''''''''");
                    }else{
                        
                        System.out.println(line);
                    }
                    
                    
                    
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            } while (!line.equals(""));    
            
            // """"""""""""""""""""""""""""""""""""Fermeture des flux""""""""""""""""""""""""""""""""""""
            try {
                breader.close();
                socket_client.close();
                from_client.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }
        
    }
    
    
}
