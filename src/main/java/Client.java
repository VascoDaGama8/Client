import com.sun.security.jgss.GSSUtil;

import java.io.*;
import java.net.*;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.ArrayList;

class Client
{
    public static int mod(int a, int deg, int p){
        int b = 1;
        while(deg != 0){
            b*=a;
            b%=p;
            deg--;
        }
        return b;
    }
    public static int shag(int a, int b, int p){
        int m, k;
        m = k = (int)(Math.sqrt(p))+1;
        int x;
        ArrayList<Integer> a_deg = new ArrayList<>();
        ArrayList<Integer> b_deg = new ArrayList<>();
        for(int i = 1; i < k+1; i++){;
            a_deg.add(mod(a, i*m, p));
        }
        for(int i = 0; i < m; i++){;
            b_deg.add(b*mod(a, i, p)%p);
        }
        int i = 0;
        int j = 0;
        for(i = 0; i < k; i++){
            for(j = 0; j < m; j++){
                if(a_deg.get(i) == b_deg.get(j)){
                    break;
                }
            }
            if(j != m && a_deg.get(i) == b_deg.get(j)){
                i++;
                break;
            }
        }
        x = i*m-j;
        return x;
    }
    public static int solve(String equation){
        int a;
        int b;
        int p;
        int x = 0;
        String ch = "";
        int i = 4;
        for(; equation.charAt(i) != ' '; i++){
            ch += equation.charAt(i);
        }
        a = Integer.parseInt(ch);
        ch = "";
        i+=5;
        for(;equation.charAt(i) != ' '; i++){
            ch += equation.charAt(i);
        }
        b = Integer.parseInt(ch);
        ch = "";
        i+=5;
        for(;i<equation.length(); i++){
            ch += equation.charAt(i);
        }
        p = Integer.parseInt(ch);
        x = shag(a, b, p);
        return x;
    }
    public static void main(String data[]) throws IOException {
        String ipAddress=data[0];
        int portNumber=Integer.parseInt(data[1]);
        try
            {
                Socket socket=new Socket(ipAddress , portNumber);
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String x;
                int y = 0;
                int n = 0;
                while(true)
                {
                    if((x = in.readLine()) != null){
                        System.out.println(x);
                        if(n != 0) {
                            y = solve(x);
                            out.write(y+ "\n");
                            out.flush();
                        }
                        n++;

                    }
                }
            }catch(Exception exception)
        {
            System.out.println(exception);
        }
    }
}