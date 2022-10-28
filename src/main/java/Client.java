import com.sun.security.jgss.GSSUtil;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

class Client
{
    public static void main(String data[]) throws IOException {//data is taken as command line argument
        String ipAddress=data[0];
        int portNumber=Integer.parseInt(data[1]);
        int rollNumber=Integer.parseInt(data[2]);
        String name=data[3];
        String gender=data[4];
        String request=rollNumber+","+name+","+gender+"#";
//”#” acts as a terminator
        try
            {
                Socket socket=new Socket(ipAddress , portNumber);
    // Socket is initialized and attempt is made for connecting to the         server// Declaring other properties and streams
                OutputStream outputStream=socket.getOutputStream();
                OutputStreamWriter outputStreamWriter=new OutputStreamWriter(outputStream);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                StringBuffer stringBuffer;
                String response;
                int x;
                int y = 0;// retrieving output Stream and its writer, for sending request or acknowledgement
                 // request is sent// retrieving input stream and its reader, for receiving    acknowledgement or response
                ArrayList<Integer> buff = new ArrayList<>();
                while(true)
                {
                    System.out.println("z");
                    x=in.read();
                    System.out.println(x);
                    if(x==-1) break;
                    buff.add(x);
                    y++;
//                    System.out.println(x);

                }
                System.out.println(y);
                System.out.println(buff.size());
                outputStreamWriter.flush();
                socket.close();
                System.out.println(0);//closing the connection
        }catch(Exception exception)
        {
// Raised in case, connection is refused or some other technical issue
            System.out.println(exception);
        }
    }
}