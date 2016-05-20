package UI;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 * С��Ч��
 * @author PfC
 *
 */
public class MinSound {
	public String musicName;//�������Ƽ�·��
	private InputStream in;
	private AudioStream ais;
	public MinSound(String name) throws IOException{
		musicName=name;
		in=new FileInputStream(musicName);
		ais=new AudioStream(in);
		AudioPlayer.player.start(ais);
	}
}
