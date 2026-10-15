
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class udp2 {
    public static void main(String[] args) {
        String host = "36.50.135.242";
        int port = 2207;
        String sCode = "B23DCCN569";
        String qCode = "53y1z781";
        try(DatagramSocket socket = new DatagramSocket()) {
            InetAddress sAddress = InetAddress.getByName(host);
            String res1 = ";" + sCode + ";" + qCode;
            DatagramPacket sendR = new DatagramPacket(res1.getBytes(), res1.getBytes().length, sAddress, port);
            socket.send(sendR);
            byte[] buff = new byte[4096];
            DatagramPacket reci = new DatagramPacket(buff, buff.length);
            socket.receive(reci);
            String tmp = new String(reci.getData(),reci.getOffset(),reci.getLength());
            String[] part = tmp.split(";");
            String resI = part[0].trim();
            String[] s = part[1].split(",");
            int[] num = new int[s.length];
            for(int i = 0; i< s.length;i++){
                num[i] = Integer.parseInt(s[i]);
            }
            Arrays.sort(num);
            int maxx = num[num.length -1];
            int minn = num[0];

            String res = resI + ";" + maxx + "," + minn;
            DatagramPacket sendRQ = new DatagramPacket(res.getBytes(), res.getBytes().length, sAddress, port);
            socket.send(sendRQ);
        } catch (Exception e) {
        }
    }
}
