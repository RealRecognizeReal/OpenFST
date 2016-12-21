package app;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;

/**
 * Created by waps12b on 2016. 12. 7..
 */
public class IndexerClient {
    public static void main(String[] args)
    {
        try {
            String serverIP = "127.0.0.1"; // 127.0.0.1 & localhost 본인
            System.out.println("서버에 연결중입니다. 서버 IP : " + serverIP);

            // 소켓을 생성하여 연결을 요청한다.
            Socket socket = new Socket(serverIP, 2016);

            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.println("x^2");
            writer.flush();

            // 소켓의 입력스트림을 얻는다.
            InputStream in = socket.getInputStream();
            DataInputStream dis = new DataInputStream(in);  // 기본형 단위로 처리하는 보조스트림

            // 소켓으로 부터 받은 데이터를 출력한다.
            System.out.println("서버로부터 받은 메세지 : " + dis.readUTF());
            System.out.println("연결을 종료합니다.");

            // 스트림과 소켓을 닫는다.

            writer.close();
            dis.close();
            socket.close();
        } catch (ConnectException ce) {
            ce.printStackTrace();
        } catch (IOException ie) {
            ie.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } // try - catch

    }
}
