import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String host = "36.50.135.242";
        int port = 2208;
        String sCode = "B23DCCN569";
        String qCode = "ara121LW";
        try (Socket socket = new Socket(host, port);
             BufferedReader reader = new BufferedReader(
                         new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             BufferedWriter writer = new BufferedWriter(
                     new OutputStreamWriter(socket.getOutputStream(),StandardCharsets.UTF_8));) {
            socket.setSoTimeout(5000);
            writer.write(sCode + ";" + qCode);
            writer.newLine();
            writer.flush();

            String received = reader.readLine();
            if(received == null){
                throw new IllegalStateException("Server dong ket noi truoc khi nhan du lieu");
            }
            System.out.println("server gui " + received);

            List<String> eduDomains = new ArrayList<>();
            for (String part : received.split(",")){
                String domain = part.trim();
                if (domain.endsWith(".edu")){
                    eduDomains.add(domain);
                }
            }
            String res = String.join( ", ", eduDomains);
            writer.write(res);
            writer.newLine();
            writer.flush();
            System.out.println("da gui:" + res);
        } catch (Exception e) {
            System.err.println("loi" + e.getMessage());
        }
    }
}