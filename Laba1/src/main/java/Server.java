import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Server {

    public static void main(String[] args) throws IOException {
        System.out.println("Server is starting");
        BufferedReader in=null;
        PrintWriter out=null;

        ServerSocket server=null;
        Socket client=null;

        try {
            server=new ServerSocket(1234);

        } catch (IOException e) {
            System.out.println("Error connetcting to socket 1234");
            System.exit(-1);
        }

        try {
            System.out.println("Waiting connection");
            client=server.accept();
            System.out.println("Client connected");
        } catch (IOException e) {
            System.out.println("No connection");
            System.exit(-1);
        }

        out = new PrintWriter(client.getOutputStream(),true);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        out.println( sdf.format(cal.getTime()) );

        out.close();
        client.close();

    }
}
