import javafx.stage.FileChooser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

class ServerOne extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    static String strOut;
    static ArrayList<String> afors=new ArrayList<String>();

    static void loadAfor() throws IOException, ParseException {
        File f = new File("aforism.json");
        JSONParser parser = new JSONParser();
        FileReader fr = new FileReader(f);
        Object obj = parser.parse(fr);
        JSONObject js = (JSONObject) obj;
        JSONArray items = (JSONArray) js.get("afor");
        for (Object i : items) {
            JSONObject j=(JSONObject)i;
            afors.add(j.get("a").toString());
        }
    }

    public ServerOne(Socket s) throws IOException, ParseException {
        loadAfor();
        int r=(int)(Math.random()*100+1);
        strOut= afors.get(r);
        socket = s;
        //in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
        start();
    }

    public void run() {
        try {
            out.println(strOut);
            System.out.println(strOut);
            System.out.println("Connection is closed");

        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Socket doesn't close");
            }

        }
    }
}

public class Server {
    static final int PORT = 1234;




    public static void main(String args[]) throws IOException, ParseException {


        ServerSocket s = new ServerSocket(PORT);
        System.out.println("Multiserver is starting");
        try {
            while (true) {
                Socket socket = s.accept();
                System.out.println("New connection ");
                System.out.println("Client: " + socket.getInetAddress());
                new ServerOne(socket);
            }
        } finally {
            s.close();
        }
    }
}
