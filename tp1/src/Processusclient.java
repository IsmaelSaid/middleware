import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;


public class Processusclient extends Thread {
    // """""""""""""""""""""""""""""constante"""""""""""""""""""""""""""""
    private static final String OUTPUT = "<html><head><title>Example</title></head><body><h1>Erreur : ressource non trouvée !</h1></body></html>";
    private static final String OUTPUT_END_OF_HEADERS = "\r\n\r\n";
    private static final String OUTPUT_HEADERS = "HTTP/1.0 404 OK!";
    private static final String CONTENT_TYPE = "Content-type: text/html";
    private static final String CONTENT_LENGTH = "Content-Length";
    
    
    // """""""""""""""""""""""""""""Attributs"""""""""""""""""""""""""""""
    Socket socket = null;
    
    // Flux 
    
    InputStream from_client = null;
    OutputStream to_client = null;
    BufferedWriter bwriter = null;
    BufferedReader breader = null;
    String ressource=null; 
    String type_requete =null;
    String ressourcefolder = "www";
    
    
    
    public Processusclient(Socket socket){
        this.socket = socket;
        
    }

   
    private String httpreponse(){
        return OUTPUT_HEADERS+"\r\n"+
        CONTENT_TYPE+
        "\r\n"+
        CONTENT_LENGTH+OUTPUT.length()+
        "\r\n"+
        OUTPUT_END_OF_HEADERS+
        OUTPUT;
        
    }
    
    private void AfficheInformations(){
        System.out.printf("Port: %d \n",socket.getPort());
        System.out.printf("IP:  %s \n",socket.getInetAddress().getHostName());
        
    }
    
    private void close(){
        try {
            bwriter.close();
            breader.close();
            from_client.close();
            to_client.close();
        } catch (IOException e) {
            System.exit(-1);
            e.printStackTrace();
        }
        
        
    }

    private String httpresponsespecific(){

        return "";

    }
    
    
    @Override
    public void run() {
        AfficheInformations();
        // """"""""""""""""""""""""""""""Ouverture des flux""""""""""""""""""""""""""""""
        try {
            from_client = this.socket.getInputStream();
            to_client = this.socket.getOutputStream();
            bwriter = new BufferedWriter(new OutputStreamWriter(to_client));
            breader = new BufferedReader(new InputStreamReader(from_client));
        } catch (IOException e) {
            
            return;
            
        }
        
        // """"""""""""""""""""""""""""Affichage de l'entete"""""""""""""""""""""""""""" 
        try {
            String line;
            boolean firsline = true;
            
            do {
                line = breader.readLine();
                if(firsline){
                    String[] result = line.split(" ");
                    ressource = result[1];
                    type_requete = result[0];
                    firsline = false;
                }            
                // System.out.println(line);    
            } while (!line.equals(""));
            
        } catch (Exception e) {
            return;
        }
        
        
        // """""""""""""""""""""""""""""""""Reponse http"""""""""""""""""""""""""""""""""
        try {
            System.out.println("-------------ressource--------------");
            System.out.println(ressourcefolder+ressource);
            if("GET".equals(type_requete)){
                String path = System.getProperty("user.dir");
                // System.out.println(path+"\\tp1\\"+"\\src"+ressource);


            }
            // default: renvoyer la page d'erreur
            bwriter.write(httpreponse());
            bwriter.flush();
            close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        // """""""""""""""""""""""""""""""""Fermeture flux"""""""""""""""""""""""""""""""""
    }
    
}
