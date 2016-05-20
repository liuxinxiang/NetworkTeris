package UI;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import Teris.*;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
/**
 * ������Ϸ����
 * @author PfC
 *
 */

public class SingleGameFrame extends JFrame{
	//�������
	private Player player;//��Ϸ������
	private JPanel jp1;//�������
	private JPanel jp2;//������ʾ���
	//������������
	private JLabel l1;
	private JLabel l2;
	private JLabel l3;
	//��һ�����鴰��
	private JLabel l4;
	//����
	private JLabel l5;
	//��ͣ��ť
	JButton pauseButton;
	
	public SingleGameFrame(){
		Layout();
		this.setBackground(Color.WHITE);
		this.setVisible(true);
		this.setLocation(100, 100);
		this.setSize(400, 625);
		this.setTitle("����˹����");
		this.setResizable(false);
		this.requestFocus();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void Layout(){
		this.setLayout(null);
		//����Ϸ���沼��
		player=new Player(false);
		player.setLayout(null);
		player.setLocation(0, 100);
		player.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		this.add(player);
		
		/* ���̼��� */
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				player.onKeyDown(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
	}


}
