package app;

import core.parser.FormulaParserBase;
import core.parser.LaTexFormulaParser;
import core.parser.MathMLFormulaParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
/**
 * Created by waps12b on 2016. 11. 23..
 */
public class Indexer {
    public static int PORT_NUMBER = 2016;
    public static int NUMBER_OF_THREAD = 2016;

    public static void main(String[] args)
    {
        if(args.length == 1)
        {
            PORT_NUMBER = Integer.parseInt(args[0]);
        }

        System.out.println("[Indexer] started on port " + PORT_NUMBER);
        try {
            ServerSocket server = new ServerSocket(PORT_NUMBER);   // 서버소켓을 생성하는데 포튼넘버는 1234다.
            int id = 0;
            while(true) {
                Socket client = server.accept();     // 클라이언트를 기다린다.
                System.out.println("Spawning client " + id);   //클라이언트가 접속한 것을 화면에 출력한다.

                Thread clientThread = new Thread(new EchoServerHandler(client, id));
                clientThread.start();    //위에서 생성한 스레드를 시작한다.
                id ++;
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static class  EchoServerHandler extends Thread {
        private int id;
        private Socket client;

        public EchoServerHandler(Socket socket, int id) {
            this.client = socket;
            this.id = id;
        }

        public void run() {
            try {
                BufferedInputStream reader =  new BufferedInputStream( client.getInputStream() );
                Scanner sc = new Scanner(reader);

                PrintWriter writer = new PrintWriter(client.getOutputStream());

                String latex = null;
                while( (latex = sc.nextLine()) != null )
                {
                    LaTexFormulaParser parser = new LaTexFormulaParser(latex);
                    try
                    {
                        writer.println( parser.generateFormula().toLatex() );
                        writer.flush();
                    }catch (Exception ex)
                    {
                        writer.println("err");
                        writer.flush();
                        ex.printStackTrace();
                    }
                }

                writer.close();
                client.close();
                sc.close();
                reader.close();
            }
            catch(IOException e) {
                e.printStackTrace();
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }finally {

            }
        }
    }


}
