package UI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.swing.filechooser.FileNameExtensionFilter;

import sun.audio.AudioStream;

/**
 * ����������
 * @author PfC
 *
 */
public class BackgroungSound extends Thread{
	public String musicName;//�������Ƽ�·��
	private InputStream in;
	private AudioStream ais;
	public BackgroungSound(String name) throws IOException{
		musicName=name;
		in=new FileInputStream(musicName);
		ais=new AudioStream(in);
		this.start();
	}
	public void run(){
		  
		  //ѭ������
		  while(true){
			   sun.audio.AudioPlayer.player.start(ais);//��ʼ����
			   try{
			    Thread.sleep(5000);//��Ƶ����ʱ��
			   }catch(Exception e){
				   System.out.println(e);
			   }
		   }
	}
}
