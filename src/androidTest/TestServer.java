package androidTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.google.gson.Gson;

import Test.Guest;
import Test.UserVO;
import Test.LatteMessage;

public class TestServer {

	public static void main(String[] args) {
		Gson gson = new Gson();

		// TODO Auto-generated method stub
		try {
			ServerSocket server = new ServerSocket(55566);
			System.out.println("서버시작");
			while (true) {
				Socket s = server.accept();
				System.out.println("유저접속" + s.getInetAddress());
				BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
				PrintWriter pr = new PrintWriter(s.getOutputStream());

				Thread t = new Thread(() -> {
					System.out.println("" + s.hashCode());
					String msg = "";
					String id = "";
					try {
						while (true) {
							msg = br.readLine();

							System.out.println(msg);

							if (msg.contains("jsonData") && msg.contains("LOGIN")) {
								LatteMessage message = gson.fromJson(msg, LatteMessage.class);
								String clinetno = "A0000001";

								message.setCode2("SUCCESS");
								message.setClientNo(clinetno);
								Guest guest = gson.fromJson(message.getJsonData(), Guest.class);
								guest.setUserNo(clinetno);
								guest.setRole("user");
								message.setJsonData(gson.toJson(guest, Guest.class));
								msg = gson.toJson(message);

								pr.println();
								System.out.println(message.toString());

							}

							if (msg == null || msg.equals("@exit")) {
								break;
							}

							if ("disconnect".equals(msg)) {
								break;
							}

							if (msg.contains("Light:")) {
								msg = "lightPower" + msg;
							}

							if (msg.contains("Blind:")) {
								StringBuilder sb = new StringBuilder(msg);
								sb.delete(0, 6);
								msg = "BlindState:" + sb;
							}

							if (msg.contains("loginID")) {
//								UserVO vo = gson.fromJson(msg, UserVO.class);
								Guest vo = gson.fromJson(msg, Guest.class);
								id = vo.getLoginID();
							}

							if ("RoomCurrentSetting".equals(msg)) {
								msg = "userId:" + id;
							}

//							if(msg.contains("authCode")) {
//								Guest vo =gson.fromJson(msg, Guest.class);
////								vo.setAuthCode("000000000000");
//								msg = gson.toJson(vo);
//							}

							System.out.println("from Client: " + msg);
							pr.println(msg);
							pr.flush();

						}
					} catch (IOException e2) {
						System.out.println(e2.toString());
					}
					System.out.println("유저 로그아웃");
				});
				t.start();

			}
		} catch (IOException e) {

		}

	}

}
