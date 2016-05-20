package Teris;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * ������
 *@author PfC
 */
public class Tools {
	//ը������
	public int boom=1;//ը������
	public boolean isBoom=false;//ʹ�ÿ���
	public void boomLogic(Player self){//ը�����߼���������������3��
		self.teris.gamestate=Teris.STATE_TOOL;
		boom--;
		isBoom=false;
		for(int j=0;j<3;j++){
			for(int i=0;i<self.teris.body_W;i++){
				self.teris.body[i][self.teris.body_top]=0;
			}
			if(self.teris.body_top<Teris.body_H-1){
				self.teris.body_top++;
			}
		}
	}
	public void drawBoom(Graphics g,Player self){//����ը�������ͷ�Ч��
		
	}
	
	//�ӱ����ֵ���
	public int scores2=1;//����
	public boolean isScores2=false;//ʹ�ÿ���
	public boolean isNewScores2=false;//�ж��Ƿ� �Ǹ�ʹ�ô˵����Ծ����Ƿ�ʱ�����
	public int scores2Time;//�ӱ�ʱ��
	public void scores2Logic(Player self){//�߼�
		if(isNewScores2){
			scores2Time+=600;
			isNewScores2=false;
			scores2--;
			self.teris.score1=200;
			self.teris.score2=600;
			self.teris.score3=1400;
			self.teris.score4=3000;
		}else{
			scores2Time--;
			if(scores2Time==0){
				isScores2=false;
				self.teris.score1=100;
				self.teris.score2=300;
				self.teris.score3=700;
				self.teris.score4=1500;
			}
		}
		
	}
	public void drawScores2(Graphics g,Player self){
		
	}
	
	//����ը��
	public int pitiBoom;//����
	public boolean ispitiBoom=false;//����
	public void pitiBoomLogic(Player enemy){//�߼�
		enemy.teris.gamestate=Teris.STATE_TOOL;
		boom--;
		isBoom=false;
		for(int j=0;j<5;j++){
			for(int i=0;i<enemy.teris.body_W;i++){
				enemy.teris.body[i][enemy.teris.body_top]=0;
			}
			if(enemy.teris.body_top<Teris.body_H-1){
				enemy.teris.body_top++;
			}
		}
	}
	public void drawPitiBoom(Graphics g,Player enemy){
		
	}
	
	//���ٵ���
	public int speedUp;//����
	public boolean isSpeedUp=false;//����
	public int speedUpTime;
	public boolean isNewSpeedUp=false;
	public void speedUpLogic(Player enemy){//�߼�
		if(isNewSpeedUp){
			enemy.teris.isLevelJudge=false;
			speedUpTime+=600;
			isNewSpeedUp=false;
			speedUp--;
			enemy.teris.speedTime=2;
			enemy.teris.level=5;
		}else{
			speedUpTime--;
			if(speedUpTime==0){
				isSpeedUp=false;
				enemy.teris.isLevelJudge=true;//�ٶȻ�ԭ
			}
		}
	}
	public void drawSpeedUp(Graphics g,Player self){
		
	}
	
	//�������
	public int reverseControl;//����
	public boolean isReverseControl=false;//����
	public int reverseTime;//��ʱ��
	public boolean isNewReverseControl=false;
	public void reverseControlLogic(Player enemy){
		if(isNewReverseControl){
			reverseTime+=600;
			isNewSpeedUp=false;
			reverseControl--;
			enemy.teris.isReverseControl=true;
		}else{
			reverseTime--;
			if(reverseTime==0){
				isReverseControl=false;
				enemy.teris.isReverseControl=false;
			}
		}
	}
	public void drawReverseContorl(Graphics g,Player enemy){
		
	}
	//���е���
	public int linesAdd;//����
	public int linesAddCount=0;//��ʱ����ʵ�ֽ���Ч��
	public boolean isLinesAdd=false;//����
	public void linesAddLogic(Player enemy){//�߼�
		enemy.teris.gamestate=Teris.STATE_TOOL;
		linesAddCount++;
		for (int j = enemy.teris.body_top-1; j <Teris.body_H-1; j++) {
			for (int k = 0; k < Teris.body_W; k++) {
				enemy.teris.body[k][j] = enemy.teris.body[k][j + 1];
			}
		}
		for(int k=0;k<Teris.body_W;k++){
			enemy.teris.body[k][Teris.body_H-1] = (new Random()).nextInt(2);
		}
		enemy.teris.body[(new Random()).nextInt(11)][Teris.body_H-1]=0;
		if(linesAddCount==5){
			enemy.teris.gamestate=Teris.STATE_PLAY;
			isLinesAdd=false;
		}
	}
	public void drawlinesAdd(Graphics g,Player enemy){
		
	}
	
	public void drawSingle(Graphics g,Player self){//���Ƶ��˵���Ч��
		if(isBoom){
			
		}
		else if(isScores2){
			
		}
	}
	
	public void drawDouble(Graphics g,Player enemy){//����˫�˵���Ч��
		if(ispitiBoom){
			
		}
		else if(isSpeedUp){
			
		}
		else if(isReverseControl){
			
		}
		else if(isLinesAdd){
			
		}
	}
	public void singleLogic(Player self){//���˵����߼�
		if(isBoom){
			boomLogic(self);
		}
		else if(isScores2){
			scores2Logic(self);
		}
	}
	public void doubleLogic(Player enemy){//˫�˵����߼�
		if(ispitiBoom){
			pitiBoomLogic(enemy);
		}
		else if(isSpeedUp){
			speedUpLogic(enemy);
		}
		else if(isReverseControl){
			
		}
		else if(isLinesAdd){
			linesAddLogic(enemy);
		}
	}
	public void onKeyDown(KeyEvent e){
		switch(e.getKeyChar())
		   {  
			  case '1':
				  if(boom>0){
				  isBoom=true;
				  }
				  break; 
			  case '2':
				  if(scores2>0){
					  isNewScores2=true;
					  isScores2=true;
				  }
				  break;
			  case '3':
				  if(pitiBoom>0){
					  ispitiBoom=true;
				  }
				  break;
			  case '4':
				  if(speedUp>0){
					  isNewSpeedUp=true;
					  isSpeedUp=true;
				  }
				  break;  
			  case '5':
				  if(reverseControl>0){
					  isNewReverseControl=true;
					  isReverseControl=true;
				  }
				  break; 
			  case '6':
				  if(linesAdd>0){
					  isLinesAdd=true;
				  }
				  break;
			  default:return;//�û������ʱ��������
		   }
	}
}
