package Teris;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.Random;
import java.util.Vector;

import javax.swing.JOptionPane;

import GameConnect.TerisCapture;
import Sql.UserSql;
import UI.MenuFrame;
import UI.UsernameFrame;

import com.sun.org.apache.bcel.internal.generic.NEW;
/**
 * ��Ϸ����
 * @author pfc
 *
 */
public class Teris{
	//��¼�ǵ��˻����Լ�
	public boolean isEnemy=false;
	//��Ϸ������Ŀ�͸�
	public static int SCREEN_W = 275;
	public static int SCREEN_H = 500;
	
	public static int beginDrawX=0;
	public static int beginDrawY=0;
	public static final int caseWidth = 25; // С���ӱ߳�
	public static final int case_L = 4; // ����Ӹ�����4*4��
	public static int body_W = 11;//��Ϸ�ؿ���С����Ϊ��λ��
	public static int body_H = SCREEN_H / caseWidth+3;//��Ϸ�ظߣ���С����Ϊ��λ��
	
	//���л�÷���
	public int score1=100;
	public int score2=300;
	public int score3=700;
	public int score4=1500;
	
	public  int[][] body = new int[body_W][body_H]; // ��Ϸ��
	public  boolean[] isReCase = new boolean[body_H]; // ��ȥ����
	public int body_top=0;//��¼��Ϸ�ص� ����
	
	public int[][] currentCase = new int[case_L][case_L]; // ��ǰ�ͼ��
	public int[][] tempCase = new int[case_L][case_L]; // �м�ͼ�Σ��������ת���ͼ�Σ�
	public int[][] nextCase = new int[case_L][case_L]; // ��һ���ͼ��
	public int[] indexrnd=new int[]{5,9,4,0,2,5,7,1,0,0,27,5,5,6,24,0,0,26,15,17,9,0,0,15,10,13,17,25,24,0,26,1,5,6,4,12,19,18,17,26,27,14,13,15,16,2,8,7,1,14};
	public int[] nextColornd=new int[]{0,5,4,3,5,4,0,5,2,1,4,2,0,1,3,5,4,1,0,2,1,4,2,0,3,4,1,0,5,1,1,3,5,4,1,0,2,0,5,2};
	public int indexCount=0;
	public int nextColorCount=0;
	public int currentX; // ��ǰ�����꣨�����ڣ�
	public int currentY; // ��ǰ������
	
	public int currentColor; // ��ǰ����ɫ
	public int nextColor; // ��һ����ɫ
	
	public int index; // ���ɿ���±�
	public int nowIndex; // ��ǰ����±�
	
	public static int[][][] store = new int[][][] { {//�洢���еķ��飬���Ұ���˳ʱ�����򣬱�����ת
		{ 0, 0, 0, 1 }, { 0, 0, 0, 1 }, { 0, 0, 0, 1 }, { 0, 0, 0, 1 } }, {// I
		{ 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 1, 1, 1, 1 }, { 0, 0, 0, 0 } }, {// I
		{ 0, 0, 0, 1 }, { 0, 0, 0, 1 }, { 0, 0, 0, 1 }, { 0, 0, 0, 1 } }, {// I
		{ 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 1, 1, 1, 1 }, { 0, 0, 0, 0 } }, {// I
		{ 0, 0, 0, 0 }, { 0, 1, 1, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } }, {// O
		{ 0, 0, 0, 0 }, { 0, 1, 1, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } }, {// O
		{ 0, 0, 0, 0 }, { 0, 1, 1, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } }, {// O
		{ 0, 0, 0, 0 }, { 0, 1, 1, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } }, {// O
		{ 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } }, {// L
		{ 0, 0, 0, 0 }, { 1, 1, 1, 0 }, { 1, 0, 0, 0 }, { 0, 0, 0, 0 } }, {// L
		{ 0, 1, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 } }, {// L
		{ 0, 0, 0, 0 }, { 0, 0, 1, 0 }, { 1, 1, 1, 0 }, { 0, 0, 0, 0 } }, {// L
		{ 0, 0, 1, 0 }, { 0, 0, 1, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } }, {// J
		{ 0, 0, 0, 0 }, { 1, 0, 0, 0 }, { 1, 1, 1, 0 }, { 0, 0, 0, 0 } }, {// J
		{ 0, 1, 1, 0 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 } }, {// J
		{ 0, 0, 0, 0 }, { 1, 1, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 } }, {// J
		{ 0, 0, 0, 0 }, { 0, 1, 0, 0 }, { 1, 1, 1, 0 }, { 0, 0, 0, 0 } }, {// T
		{ 0, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 } }, {// T
		{ 0, 0, 0, 0 }, { 1, 1, 1, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 } }, {// T
		{ 0, 0, 1, 0 }, { 0, 1, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 } }, {// T
		{ 0, 0, 0, 0 }, { 0, 1, 1, 0 }, { 1, 1, 0, 0 }, { 0, 0, 0, 0 } }, {// S
		{ 0, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 } }, {// S
		{ 0, 0, 0, 0 }, { 0, 1, 1, 0 }, { 1, 1, 0, 0 }, { 0, 0, 0, 0 } }, {// S
		{ 0, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 } }, {// S
		{ 0, 0, 0, 0 }, { 1, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } }, {// Z
		{ 0, 0, 1, 0 }, { 0, 1, 1, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 } }, {// Z
		{ 0, 0, 0, 0 }, { 1, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } }, {// Z
		{ 0, 0, 1, 0 }, { 0, 1, 1, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 } } };// Z
	
	public int score = 0;//��¼����
	public int showScore = 0;//��ʾ����
	public int reLine = 0;//��ȥ������
	public int level=1;//�ȼ�
	public int speedTime=20;//�½��ٶȣ��½�һ����Ҫ��ʱ�䣩
	public int speedCount=0;//�½��ٶȼ�ʱ��
	
	public static int iStep = body_H; // �������� ����
	public static int frame = 0;
	public static int comboFrame = 0;
	public static int addFrame = 0;
	public static int frame5 = 5;
	public static int frame10 = 10;
	public static int frame20 = 20;
	public static int removeFrame = 5;
	public static int fastFrame = 1;
	public static int overFrame = 10;
	public static int createdFrame = 280;
	// ����
	public boolean isNewCase = false;
	public boolean isCombo = false;//�Ƿ񵽵���
	public boolean isRemove = false;
	public boolean isMoveDown=false;
	public boolean isLevelJudge=true;//�Ƿ����ȼ�����
	public boolean isReverseControl=false;//�Ƿ���������ƹ���
	
	public Random rand;
	
	public static final int STATE_START = 4;//��Ϸ׼����ʼ
	public static final int STATE_PLAY = 5;// ������
	public static final int STATE_GAMEOVER = 7;// ��Ϸ����
	public static final int STATE_REMOVE = 8;// ��ȥ
	public static final int STATE_PAUSE = 9;// ��ͣ
	public static final int STATE_TOOL=3;//ʹ�ü�ʱ����
	public static final int STATE_USERPRINT=6;//�û���������״̬
	public int gamestate = STATE_START;//��¼��Ϸ״̬
	
	public static Image[] blockImages=new Image[6];
	
	public Teris(boolean isEnemy){
		this.isEnemy=isEnemy;
	}
	public void startGame() {
		// �����Ϸ��
		for (int i = 0; i < body.length; i++) {
			for (int j = 0; j < body[i].length; j++) {
				if (body[i][j] != 0) {
					body[i][j] = 0;
				}
			}
		}
		rand=new Random();//ʵ�����������
		beginDrawY=SCREEN_H - caseWidth * body_H;
		// ������λ
		score = 0;
		score1=100;
		score2=300;
		score3=700;
		score4=1500;
		showScore = 0;
		// ��ȥ��������
		reLine = 0;
		//�ȼ���1
		level=1;

		// Ҫ��������2��
		newCase();
		isNewCase = true;
		gamestate = STATE_PLAY;
	}
	
	public void logic(){//��Ϸ�߼�
		switch (gamestate) {
		case STATE_START:
			startGame();
			break;
		case STATE_PLAY:
			//�жϷ�������ʵ�ַ����Ľ���
			if (showScore < score) {
				showScore += (score - showScore) >> 1;
			}
			if (showScore < score) {
				showScore++;
				if(isLevelJudge){
					if(showScore>5000){
						level=2;
					}else if(showScore>10000){
						level=3;
					}else if(showScore>20000){
						level=4;
					}
				}
			}
			//���ݵȼ�ȷ�������ٶ�
			switch(level){
			case 1:speedTime=20;break;
			case 2:speedTime=15;break;
			case 3:speedTime=10;break;
			case 4:speedTime=5;break;
			default:break;
			}
			speedCount++;
			if (speedCount > speedTime) {
				speedCount = 0;
				isMoveDown = true;
			}
			if (isNewCase) {
				newCase();
			} else if (isCombo) {
				comboFrame++;
				if (comboFrame > frame5) {
					comboFrame = 0;
					doCombo();
				}
			} else if (isRemove) {
				gamestate = STATE_REMOVE;
			} else if (isMoveDown) {
				moveDown();
			}
			break;
		case STATE_REMOVE:
			doRemove();
			gamestate=STATE_PLAY;
			break;
		case STATE_TOOL:
			isMoveDown=false;
			break;
		case STATE_GAMEOVER:
//			if(MenuFrame.gamemode==MenuFrame.MODE_SINGLE){
//				toGameOver();
//			}
			toGameOver();
			break;
		case STATE_PAUSE:
			break;
		case STATE_USERPRINT:
			break;
		}
	}
	
	public void draw(Graphics g){//��Ϸ��ͼ
		switch (gamestate) {
		case STATE_PLAY:
			drawBody(g);
			drawData(g);
			drawCurrentCase(g);
			drawNextCase(g);
			break;
		case STATE_REMOVE:
			break;
		case STATE_GAMEOVER:
			break;
		case STATE_TOOL:
			drawBody(g);
			drawData(g);
			drawCurrentCase(g);
			drawNextCase(g);
			break;
		default:
			break;
		}
	}
	private void drawData(Graphics g){//����Ϸ����
		g.setColor(Color.white);
		g.fillRect(SCREEN_W, 0, Player.panel_W-SCREEN_W, Player.panel_H);
		g.setColor(Color.RED);
		g.drawString("Level", caseWidth * body_W+20, 8 * caseWidth);
		g.drawString("Line", caseWidth * body_W+20, 12 * caseWidth);
		g.drawString("Score", caseWidth * body_W+20, 16 * caseWidth);
		g.drawString("" + level, caseWidth * body_W+20, 10 * caseWidth);
		g.drawString("" + reLine, caseWidth * body_W+20, 14 * caseWidth);
		g.drawString("" + showScore, caseWidth * body_W+20, 18 * caseWidth);
	}
	private void drawBody(Graphics g){//����Ϸ��
		for(int i=0;i<body_W;i++){
			for (int j = 0; j < body_H; j++) {
				if (body[i][j] != 0) {
					g.drawImage(blockImages[body[i][j]-1],beginDrawX + i
							* caseWidth, beginDrawY + j * caseWidth,null);
				}
			}
		}
	}
	private void drawCurrentCase(Graphics g){//����ǰ����
		for (int i = 0; i < currentCase.length; i++) {
			for (int j = 0; j < currentCase[i].length; j++) {
				if (currentCase[i][j] != 0) {
					g.drawImage(blockImages[currentColor], beginDrawX
							+ (i + currentX) * caseWidth, beginDrawY
							+ (j + currentY) * caseWidth, null);
				}
			}
		}
	}
	public void drawNextCase(Graphics g){//����һ������
		for (int i = 0; i < nextCase.length; i++) {
			for (int j = 0; j < nextCase[i].length; j++) {
				if (nextCase[i][j] != 0) {
					g.drawImage(blockImages[nextColor], SCREEN_W+ i
							* caseWidth + caseWidth / 2, j * caseWidth+caseWidth / 2, null);
				}
			}
		}
	}
	public void drawRemove(){
		for (int i = 0; i < body_H; i++) {
			if (isReCase[i]) {
				
			}
		}
	}
	public boolean isSpace(int x, int y) {//�ж��Ƿ�Ϊ��
		if (x < 0 || x > body.length - 1) {
			return false;
		}
		if (y < 0 || y > body[0].length - 1) {
			return false;
		}

		if (body[x][y] == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public void moveLeft() {//����
		for (int i = 0; i < currentCase.length; i++) {
			for (int j = 0; j < currentCase[i].length; j++) {
				if (currentCase[i][j] != 0) {
					if (!isSpace(currentX + i - 1, currentY + j)) {
						return;
					}
				}
			}
		}
		currentX--;
		isCombo = false;
		comboFrame = 0;
	}
	
	public void moveRight() {//����
		for (int i = 0; i < currentCase.length; i++) {
			for (int j = 0; j < currentCase[i].length; j++) {
				if (currentCase[i][j] != 0) {
					if (!isSpace(currentX + i + 1, currentY + j)) {
						return;
					}
				}
			}
		}
		currentX++;
		isCombo = false;
		comboFrame = 0;
	}
	
	public void moveDown() {//����
		speedCount = 0;
		isMoveDown = false;
		for (int i = 0; i < currentCase.length; i++) {
			for (int j = 0; j < currentCase[i].length; j++) {
				if (currentCase[i][j] != 0) {
					if (!isSpace(currentX + i, currentY + j + 1)) {
						isCombo = true;
						return;
					}
				}
			}
		}
		currentY++;
	}
	
	public void fastDrop() {//��������
		for (int i = 0; i < currentCase.length; i++) {
			for (int j = 0; j < currentCase[i].length; j++) {
				if (currentCase[i][j] != 0) {
					for (int k = currentY + j; k < body[0].length; k++) {
						if (!isSpace(currentX + i, k + 1)
								|| k == body[0].length - 1) {
							if (iStep > k - currentY - j) {
								iStep = k - currentY - j;
							}
						}
					}
				}
			}
		}
		currentY += iStep;
		if (iStep > 0) {
			comboFrame = frame5 >> 1;
			isCombo = true;
		}
		iStep = body_H;
	}
	
	public void doCombo() {//�ѻ��ӵ���Ϸ��
		isCombo = false;
		// ����
		for (int i = currentCase.length-1; i >=0; i--) {
			for (int j = currentCase.length-1; j >=0; j--) {
				if (currentCase[i][j] != 0) {
					body[currentX + i][currentY + j] = currentColor +1;
					body_top=currentY+j;
				}
			}
		}

		// ��������ȥ����
		boolean remove = false;
		for (int i = 0; i < body_H; i++) {
			remove = true;
			for (int j = 0; j < body_W; j++) {
				if (isSpace(j, i)) {
					remove = false;
					break;
				}
			}
			isReCase[i] = remove;
			if (isReCase[i]) {
				reLine++;
				isRemove = true;
			}
		}
		isNewCase = !isRemove; // ������ȥ �������¿�
	}
	
	public void doRemove() {//����
		int removeLine = 0;
		for (int i = 0; i < body_H; i++) {
			if (isReCase[i]) {
				removeLine++;
				for (int j = i; j > 0; j--) {
					for (int k = 0; k < body_W; k++) {
						body[k][j] = body[k][j - 1];
					}
				}
			}
		}
		// �Ƿ�
		switch (removeLine) {
		case 1:
			score += score1;
			break;
		case 2:
			score += score2;
			break;
		case 3:
			score += score3;
			break;
		case 4:
			score += score4;
			break;
		}
		isRemove = false;
		isNewCase = true; // ������ȥ �������¿�
	}
	
	public void turn() {//��ת
		boolean canTurn = false;
		int tempX = 0, tempY = 0;
		int tempIndex;

		// ˳ʱ��ת
		tempIndex = nowIndex;
		if (tempIndex % 4 > 0) {
			tempIndex--;
		} else {
			tempIndex += 3;
		}
		for (int i = 0; i < tempCase.length; i++) {
			for (int j = 0; j < tempCase[i].length; j++) {
				tempCase[i][j] = store[tempIndex][i][j];
			}
		}

		tempX = currentX;
		tempY = currentY;

		canTurn = turnCheck(tempX, tempY);
		// ��ǿ�ж�
		while (!canTurn) {
			// ����1
			canTurn = turnCheck(tempX, tempY + 1);
			if (canTurn) {
				tempY++;
				break;
			}
			// ������1
			canTurn = turnCheck(tempX - 1, tempY + 1);
			if (canTurn) {
				tempX--;
				tempY++;
				break;
			}
			// ������1
			canTurn = turnCheck(tempX + 1, tempY + 1);
			if (canTurn) {
				tempX++;
				tempY++;
				break;
			}
			// ����2
			canTurn = turnCheck(tempX, tempY + 2);
			if (canTurn) {
				tempY += 2;
				break;
			}
			// ����1
			canTurn = turnCheck(tempX - 1, tempY);
			if (canTurn) {
				tempX--;
				break;
			}
			// ����1
			canTurn = turnCheck(tempX + 1, tempY);
			if (canTurn) {
				tempX++;
				break;
			}
			// ����1
			canTurn = turnCheck(tempX, tempY - 1);
			if (canTurn) {
				tempY--;
				break;
			}
			// ������1
			canTurn = turnCheck(tempX - 1, tempY - 1);
			if (canTurn) {
				tempX--;
				tempY--;
				break;
			}
			// ������1
			canTurn = turnCheck(tempX + 1, tempY - 1);
			if (canTurn) {
				tempX++;
				tempY--;
				break;
			}
			// ����2
			canTurn = turnCheck(tempX - 2, tempY);
			if (canTurn) {
				tempX -= 2;
				break;
			}
			// ����2
			canTurn = turnCheck(tempX + 2, tempY);
			if (canTurn) {
				tempX += 2;
				break;
			}
			// ����2
			canTurn = turnCheck(tempX, tempY - 2);
			if (canTurn) {
				tempY -= 2;
				break;
			}
			break;
		}
		// ת����
		if (canTurn) {
			nowIndex = tempIndex;
			currentX = tempX;
			currentY = tempY;
			for (int i = 0; i < currentCase.length; i++) {
				for (int j = 0; j < currentCase[i].length; j++) {
					currentCase[i][j] = tempCase[i][j];
				}
			}

			isCombo = false;
			comboFrame = 0;
		}
	}
	
	public boolean turnCheck(int tempX, int tempY) {//����Ƿ������ת
		for (int i = 0; i < tempCase.length; i++) {
			for (int j = 0; j < tempCase[i].length; j++) {
				if (tempCase[i][j] != 0) {
					if (!isSpace(tempX + i, tempY + j)) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public void newCase() {//��������·���
		nowIndex = index;
		if(MenuFrame.gamemode==MenuFrame.MODE_DOUBLE_CON_CLIENT||MenuFrame.gamemode==MenuFrame.MODE_DOUBLE_CON_SERVER){
			index=indexrnd[indexCount];
			if(indexCount==49){
				indexCount=0;
			}else{
				indexCount++;
			}
		}else{
			index = Math.abs(rand.nextInt() % 28);
		}
		currentColor = nextColor;
		if(MenuFrame.gamemode==MenuFrame.MODE_DOUBLE_CON_CLIENT||MenuFrame.gamemode==MenuFrame.MODE_DOUBLE_CON_SERVER){
			nextColor=nextColornd[nextColorCount];
			if(nextColorCount==39){
				nextColorCount=0;
			}else{
				nextColorCount++;
			}
		}else{
			index = Math.abs(rand.nextInt() % 28);
		}
		for (int i = 0; i < nextCase.length; i++) {
			for (int j = 0; j < nextCase[i].length; j++) {
				// ����һ��������
				currentCase[i][j] = nextCase[i][j];

				// ��һ��ˢ��
				nextCase[i][j] = store[index][i][j];
			}
		}
		for (int j = 0; j < currentCase.length; j++) {
			for (int i = 0; i < currentCase[j].length; i++) {
				if (currentCase[i][j] != 0) {
					currentY = currentCase.length - 1 - j;
				}
			}
		}

		int width = 0;//���ڼ���currentX;
		int start = 0;
		int end = 0;
		boolean isBreak;//�����ж�����forѭ���Ƿ�����

		isBreak = false;
		for (int i = 0; i < currentCase.length; i++) {
			for (int j = 0; j < currentCase.length; j++) {
				if (currentCase[i][j] != 0) {
					start = i;
					isBreak = true;
					break;
				}
			}
			if (isBreak) {
				break;
			}
		}

		isBreak = false;
		for (int i = currentCase.length - 1; i >= 0; i--) {
			for (int j = 0; j < currentCase.length; j++) {

				if (currentCase[i][j] != 0) {
					end = i + 1;
					isBreak = true;
					break;
				}

			}

			if (isBreak) {
				break;
			}
		}

		width = end - start;

		currentX = (body_W - width) / 2 - start;

		isNewCase = false;
		isGameOver();
	}
	
	public void isGameOver() {
		for (int i = 0; i < currentCase.length; i++) {
			for (int j = 0; j < currentCase[i].length; j++) {
				if (currentCase[i][j] != 0) {
					if (!isSpace(currentX + i, currentY+j)) {
						// ��Ϸ����
						gamestate = STATE_GAMEOVER;
						//System.out.println(gamestate);
						// startGame();
						return;
					}
				}
			}
		}
	}
	
	public void toGameOver(){
		UserSql sqlcon=new UserSql();
		String sqlStr=new String("select *from Player where rank<11");
		sqlcon.executeQuery(sqlStr);
		int rank=1;
		try {
			while(sqlcon.rs.next()){
//				System.out.println(sqlcon.rs.getInt(2));
				if(showScore<sqlcon.rs.getInt(2)){
					rank++;
				}
//				Vector hang=new Vector();
//				hang.add(sqlcon.rs.getString(1));
//				hang.add(sqlcon.rs.getInt(2));
//				hang.add(sqlcon.rs.getInt(3));
//				//System.out.println(sqlcon.rs.getString(2));
//				jilu.add(hang);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(rank<=10){
			sqlcon.close();
			//System.out.println("����");
			UsernameFrame usernameFrame=new UsernameFrame(MenuFrame.sgf,rank,showScore);
			gamestate=STATE_USERPRINT;
		}else{
			sqlcon.close();
			JOptionPane.showMessageDialog(MenuFrame.sgf, "�Բ��𣬳�����ư�ʧ�ܣ����ٽ�������");
			
		}
	}
	
	public void action(char e){//������������������AI�Ŀ���
		switch(e){
		case 'L':
		case 'l':
			moveLeft();
			break;
		case 'R':
		case 'r':
			moveRight();
			break;
		case 'D':
		case 'd':
			moveDown();
			break;
		case 'f':
		case 'F':
			fastDrop();
			break;
		case 'T':
		case 't':
			turn();
			break;
//		case 'i':
//			nextColor=TerisCapture.ein[1]-48;
//			index=TerisCapture.ein[2]-48;
//			int t=TerisCapture.ein[3]-48;
//			if(t>=0&&t<=9){
//				index=index*10+t;
//			}
//			break;
		}
	}
	
	public void onKeyDown(KeyEvent e){
		switch(e.getKeyChar())
		   {  
			  case 'w':
			  case 'W':
				  turn(); 
				  if(MenuFrame.gamemode > MenuFrame.MODE_DOUBLE_AI)
				  TerisCapture.outstream("t");
				  break; 
			  case 's':
			  case 'S':
				  if(MenuFrame.gamemode > MenuFrame.MODE_DOUBLE_AI)
				  TerisCapture.outstream("f");
				  fastDrop();break;  
			  case 'a':
			  case 'A':
				  if(MenuFrame.gamemode > MenuFrame.MODE_DOUBLE_AI)
				  TerisCapture.outstream("l");
				  moveLeft();break;  
			  case 'd':
			  case 'D':
				  if(MenuFrame.gamemode > MenuFrame.MODE_DOUBLE_AI)
				  TerisCapture.outstream("r");
				  moveRight();break;
			  default:return;//�û������ʱ��������
		   }
	}
	
	public void onReverseKeyDown(KeyEvent e){
		switch(e.getKeyChar())
		   {  
			  case 's':
			  case 'S':
				  if(MenuFrame.gamemode > MenuFrame.MODE_DOUBLE_AI)
				  TerisCapture.outstream("t");
				  turn(); break; 
			  case 'w':
			  case 'W':
				  if(MenuFrame.gamemode > MenuFrame.MODE_DOUBLE_AI)
				  TerisCapture.outstream("f");
				  fastDrop();break;  
			  case 'd':
			  case 'D':
				  if(MenuFrame.gamemode > MenuFrame.MODE_DOUBLE_AI)
				  TerisCapture.outstream("l");
				  moveLeft();break;  
			  case 'a':
			  case 'A':
				  if(MenuFrame.gamemode > MenuFrame.MODE_DOUBLE_AI)
				  TerisCapture.outstream("r");
				  moveRight();break;
			  default:return;//�û������ʱ��������
		   }
	}

}
