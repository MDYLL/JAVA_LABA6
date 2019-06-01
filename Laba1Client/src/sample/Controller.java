package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Controller {

    @FXML
    Label time1;

    @FXML
    public void request() throws IOException {
        Socket server = null;
        server=new Socket("127.0.0.1",1234);
        BufferedReader in=new BufferedReader(new InputStreamReader(server.getInputStream()));
        String fserver=in.readLine();
        System.out.println(fserver);
        time1.setText(fserver);
        in.close();
        server.close();
    }
}
