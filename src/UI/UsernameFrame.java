package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Sql.UserSql;

public class UsernameFrame extends JFrame{
	private JButton b1;
	private JTextField nameField;
	private JLabel l1;
	private JLabel l2;
	private JLabel l3;
	private int rank;
	private int scores;
	private UsernameFrame usernameFrame=this;
	public UsernameFrame(SingleGameFrame frame,int rank,int scores) {
		this.rank=rank;
		this.scores=scores;
		Layout();
		this.setVisible(true);
		this.setLocation(frame.getLocation().x+frame.getWidth()/2-100, frame.getLocation().y+frame.getHeight()/3);
		this.setSize(220, 180);
		this.setTitle("��ʾ");
		this.setResizable(false);
		this.requestFocus();
	}
	
	public void Layout(){
		this.setLayout(null);
//		userPanel=new JPanel();
//		userPanel.setBounds(0, 0, 200, 180);
//		g=(Graphics)userPanel.getGraphics();
		//paint(g);
		l1=new JLabel("��ϲ���������ư�������:");
		l1.setBounds(5, 10, 200, 20);
		this.add(l1);
		
		l2=new JLabel(""+rank);
		l2.setBounds(95,35,30,20);
		this.add(l2);
		
		l3=new JLabel("����������������");
		l3.setBounds(5, 60, 200, 20);
		this.add(l3);
		
		nameField=new JTextField();
		nameField.setBounds(5, 85, 150, 30);
		this.add(nameField);
		
		b1=new JButton("ȷ��");
		b1.setBounds(130,120,80,30);
		this.add(b1);
	
		//this.add(userPanel);
		b1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!nameField.getText().equals("")){
					UserSql sqlcon=new UserSql();
					PreparedStatement ps;
					String name=new String(nameField.getText());
					String sqlStr=new String("INSERT INTO Player values('"+name+"',"+scores+","+rank+")");
					try {
						sqlcon.stmt.execute(sqlStr);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					usernameFrame.dispose();
					JOptionPane.showMessageDialog(usernameFrame, "����ɹ�");
				}
				else{
					JOptionPane.showMessageDialog(usernameFrame, "����������������");
				}
			}
		});
	}
	
//	public void paint(Graphics g) {
//		g.setColor(Color.black);
//		g.drawString("��ϲ���������ư�������:", 5, 20);
//		g.drawString("", 95, 40);
//		g.drawString("����������������", 5, 60);
//	}

}
