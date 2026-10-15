import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.*;

public class udp4 {
    public static void main(String[] args) {
        String host = "36.50.135.242";
        int port = 2208;
        try(DatagramSocket socket = new DatagramSocket()) {
            InetAddress sAddress = InetAddress.getByName(host);
            String req = ";B23DCCN569;8F6vQZpx";
            DatagramPacket sendR = new DatagramPacket(req.getBytes(), req.getBytes().length, sAddress, port);
            socket.send(sendR);
            byte[] buf = new byte[4096];
            DatagramPacket rec = new DatagramPacket(buf, buf.length);
            socket.receive(rec);
            String reci = new String(rec.getData(), rec.getOffset(), rec.getLength());
            String part[] = reci.split(";");
            String z = part[0];
            String tmp = part[1];
            System.out.println(tmp);
            Map<Character, Integer> mp = new HashMap<>();
            for(char x : tmp.toCharArray()){
                if (Character.isWhitespace(x)) {
                    continue; // Không đếm khoảng trắng
                }
                mp.put(x, mp.getOrDefault(x, 0) + 1);
            }
            int maxx = 0;
            char x = '\0';
            for(Map.Entry<Character, Integer> entry: mp.entrySet()){
                if(entry.getValue() > maxx){
                    maxx = entry.getValue();
                    x = entry.getKey();
                }
            }
            System.out.println(x);
            StringBuilder bd = new StringBuilder();
            bd.append(x).append(":");
            for(int i= 0; i < tmp.length();i++){
                if(x == tmp.charAt(i)){
                    bd.append(i + 1).append(",");
                }
            }
            String res = z + ";" + bd.toString().trim();
            DatagramPacket sendP = new DatagramPacket(res.getBytes(),res.getBytes().length, sAddress, port);
            socket.send(sendP);
        } catch (Exception e) {
        }
    }
}
