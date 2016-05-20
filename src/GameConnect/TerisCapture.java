package GameConnect;
import java.net.*;
import java.io.*;

import javax.swing.JOptionPane;

import Teris.Player;
import UI.DoubleGameFrame;
import UI.MenuFrame;
import UI.TerisCaptureFrame;

/**
 * ����������
 * @author PfC
 *
 */
public class TerisCapture implements Runnable{
	public static DoubleGameFrame dgf=null;
	
	public static Socket cs=null;
	public static String datain;
	public static char[] ein=new char[7];
	public static char[] eout;
	public static ServerSocket serverSocket;
	//����
	public static InputStream in;
	public static DataInputStream din;
	public static BufferedReader brin;
	//д��
	public static OutputStream out;
	public static DataOutputStream dOut;
	public static PrintWriter pw;
	
	//������
	public TerisCapture(){
		try {
			serverSocket=new ServerSocket();
			serverSocket.bind(new InetSocketAddress(InetAddress.getLocalHost(),55555));
			System.out.println(""+serverSocket.getInetAddress()+":"+serverSocket.getLocalPort());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//MenuFrame.terisCaptureFrame1.dispose();
		Thread tr=new Thread(this);
		tr.start();
	}
	//�ͻ���
	public TerisCapture(String ip,int port){
		/**
		 * @param args
		 * @throws IOException 
		 * @throws UnknownHostException 
		 */
			// TODO Auto-generated method stub
			try {
				cs=new Socket(ip,port);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				JOptionPane.showMessageDialog(MenuFrame.terisCaptureFrame2, "����ʧ�ܣ����� ip�����Ƿ���ȷ��");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			
			MenuFrame.terisCaptureFrame2.dispose();
			dgf=new DoubleGameFrame();
			Thread tr=new Thread(this);
			tr.start();
		}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(true){
				if(cs==null){
					cs=serverSocket.accept();
					dgf=new DoubleGameFrame();
				}
					in=cs.getInputStream();
					brin=new BufferedReader(new InputStreamReader(in));
				    //�ж�ȡ�ͻ�������
				    datain=new String(brin.readLine());
				    ein=datain.toCharArray();
//				    System.out.println(ein[0]);
				    dgf.enemy.teris.action(ein[0]);
//					TerisCapture.ein[0]='.';
				    Thread.sleep(50);
				//}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//������ݺ���
	public static void outstream(String ss){
		try {
			out = cs.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//DataOutputStream dOut=new DataOutputStream(out);
		pw=new PrintWriter(out);
		pw.println(ss);
		pw.flush();
	}
	public void close(){
		try {
			if(cs!=null){
				cs.close();
			}
			if(serverSocket!=null){
				serverSocket.close();
			}
			if(din!=null){
				din.close();
			}
			if(dOut!=null){
				dOut.close();
			}
			if(brin!=null){
				brin.close();
			}
			if(pw!=null){
				pw.close();
			}
			if(in!=null){
				in.close();
			}
			if(out!=null){
				out.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
