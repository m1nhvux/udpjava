
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetAddress;

public class udp1 {
    public static void main(String[] args) {
        String host = "36.50.135.242";
        int port = 2207;
        String sCode = "B23DCCN569";
        String qCode = "Kmr2eLCZ";
        try(DatagramSocket socket = new DatagramSocket()) {
            InetAddress sSocket = InetAddress.getByName(host);
            String req = ";" + sCode + ";" + qCode;
            System.out.println(req);
            byte[] sendR = req.getBytes();
            DatagramPacket sendP = new DatagramPacket(sendR,sendR.length, sSocket, port);
            socket.send(sendP);
            byte[] buffer = new byte[4096];
            DatagramPacket reci = new DatagramPacket(buffer, buffer.length);
            socket.receive(reci);
            String tmp = new String(reci.getData(),reci.getOffset(), reci.getLength());
            System.out.println(tmp);
            String[] part = tmp.split(";");
            String resq = part[0].trim();
            int n = Integer.parseInt(part[1].trim());
            StringBuilder bd = new StringBuilder();
            String[] s = part[2].split(",");
            boolean[] appeared = new boolean[n + 1];
            for(String x: s){
                int v = Integer.parseInt(x);
                appeared[v] = true;
            }
            for(int i =1; i <= n;i++){
                if(!appeared[i]){
                    if(bd.isEmpty()){
                        bd.append(i);
                    }
                    else bd.append(",").append(i);
                }
            }
            String res = resq + ";" + bd.toString();
            System.out.println(res);
            DatagramPacket sendRe = new DatagramPacket(res.getBytes(), res.getBytes().length, sSocket,port);
            socket.send(sendRe);



        } catch (Exception e) {
        }
    }
}
