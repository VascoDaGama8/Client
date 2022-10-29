import com.sun.security.jgss.GSSUtil;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

class Client
{
    public static void main(String data[]) throws IOException {//data is taken as command line argument
        String ipAddress=data[0];
        int portNumber=Integer.parseInt(data[1]);
        try
            {
                Socket socket=new Socket(ipAddress , portNumber);
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String x;
                String y;
                long time;
                while(true)
                {
                    if((x = in.readLine()) != null){
                        System.out.println(x);
                        out.write(x + "\n");
                        out.flush();
                    }
                }
            }catch(Exception exception)
        {
            System.out.println(exception);
        }
    }
}