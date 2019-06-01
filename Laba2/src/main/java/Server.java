import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

class ServerOne extends Thread{
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public ServerOne(Socket s) throws IOException {
        socket=s;
        //in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out=new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
        start();
    }
    public void run(){
        
    }
}

public class Server {
    static final int PORT=1234;
    public static void main(String args[]) throws IOException {
        ServerSocket s=new ServerSocket(PORT);
        System.out.println("Multiserver is startin");
        try {
            while (true){
                Socket socket=s.accept();
                try{
                    System.out.println("New connection ");
                    System.out.println("Client: "+socket.getInetAddress());
                    new ServerOne(socket);
                }
            }
        }
    }
}
