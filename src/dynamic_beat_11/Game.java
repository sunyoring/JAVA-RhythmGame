package dynamic_beat_11;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;

import dynamic_beat_3.Main;

public class Game extends Thread { //스레드는 하나의 프로그램 안에서 실행되는 작은 프로그램
	
	private Image notRouteLineImage = new ImageIcon(Main.class.getResource("../image/notRouteLine.png")).getImage();
	private Image notRouteSImage = new ImageIcon(Main.class.getResource("../image/notRoute.png")).getImage();
	private Image notRouteDImage = new ImageIcon(Main.class.getResource("../image/notRoute.png")).getImage();
	private Image notRouteFImage = new ImageIcon(Main.class.getResource("../image/notRoute.png")).getImage();
	private Image notRouteSpace1Image = new ImageIcon(Main.class.getResource("../image/notRoute.png")).getImage();
	private Image notRouteSpace2Image = new ImageIcon(Main.class.getResource("../image/notRoute.png")).getImage();
	private Image notRouteJImage = new ImageIcon(Main.class.getResource("../image/notRoute.png")).getImage();
	private Image notRouteKImage = new ImageIcon(Main.class.getResource("../image/notRoute.png")).getImage();
	private Image notRouteLImage = new ImageIcon(Main.class.getResource("../image/notRoute.png")).getImage();

	
	private Image notBasicImage = new ImageIcon(Main.class.getResource("../image/notBasic.png")).getImage();
	private Image judgementLineImage = new ImageIcon(Main.class.getResource("../image/judgementLine.png")).getImage();
	private Image gameInfoImage = new ImageIcon(Main.class.getResource("../image/gameInfo.png")).getImage();
	
	public void screenDraw (Graphics2D g) {
		g.drawImage(gameInfoImage, 0, 660, null);
		g.drawImage(notRouteSImage,228,30,null);
		g.drawImage(notRouteDImage,332,30,null);
		g.drawImage(notRouteFImage,436,30,null);
		g.drawImage(notRouteSpace1Image,540,30,null);
		g.drawImage(notRouteSpace2Image,640,30,null);
		g.drawImage(notRouteJImage,744,30,null);
		g.drawImage(notRouteKImage,848,30,null);
		g.drawImage(notRouteLImage,952,30,null);

		g.drawImage(notRouteLineImage,224,30,null);
		g.drawImage(notRouteLineImage,328,30,null);
		g.drawImage(notRouteLineImage,432,30,null);
		g.drawImage(notRouteLineImage,536,30,null);
		g.drawImage(notRouteLineImage,740,30,null);
		g.drawImage(notRouteLineImage,844,30,null);
		g.drawImage(notRouteLineImage,948,30,null);
		g.drawImage(notRouteLineImage,1052,30,null);

		g.drawImage(judgementLineImage, 0, 580, null);

		
		g.drawImage(notBasicImage,228,120,null);
		g.drawImage(notBasicImage,332,580,null);
		g.drawImage(notBasicImage,436,500,null);
		g.drawImage(notBasicImage,540,340,null);
		g.drawImage(notBasicImage,640,340,null);
		g.drawImage(notBasicImage,744,325,null);
		g.drawImage(notBasicImage,848,305,null);
		g.drawImage(notBasicImage,952,305,null);

		g.setColor(Color.white);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setColor(Color.white);
		g.setFont(new Font("Arial",Font.BOLD,30));
		g.drawString("Blue - BIGBANG", 20, 702);
		//글자 깨짐 현상 해결 방법 : Graphics2D 형변환
		//g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.drawString("Easy",1190, 702);
		g.setFont(new Font("Arial",Font.PLAIN,26));
		g.setColor(Color.DARK_GRAY);
		g.drawString("S",270,609);
		g.drawString("D",374,609);
		g.drawString("F",478,609);
		g.drawString("Space Bar",580,609);
		g.drawString("J",784,609);
		g.drawString("K",869,609);
		g.drawString("L",993,609);
		g.setColor(Color.LIGHT_GRAY);
	}
	
	public void pressedS(){
		notRouteSImage = new ImageIcon(Main.class.getResource("../image/notRoutePressed.png")).getImage();
		new Music("drumSmall1.mp3",false).start();
	}
	public void releasedS(){
		notRouteSImage = new ImageIcon(Main.class.getResource("../image/notRoute.png")).getImage();
		
	}
	public void pressedD(){
		notRouteDImage = new ImageIcon(Main.class.getResource("../image/notRoutePressed.png")).getImage();
		new Music("drumSmall2.mp3",false).start();

	}
	public void releasedD(){
		notRouteDImage = new ImageIcon(Main.class.getResource("../image/notRoute.png")).getImage();
		
	}
	public void pressedF(){
		notRouteFImage = new ImageIcon(Main.class.getResource("../image/notRoutePressed.png")).getImage();
		new Music("drumBig2.mp3",false).start();

	}
	public void releasedF(){
		notRouteFImage = new ImageIcon(Main.class.getResource("../image/notRoute.png")).getImage();
		
	}
	public void pressedSpace(){
		notRouteSpace1Image = new ImageIcon(Main.class.getResource("../image/notRoutePressed.png")).getImage();
		notRouteSpace2Image = new ImageIcon(Main.class.getResource("../image/notRoutePressed.png")).getImage();
		new Music("drumBig3.mp3",false).start();
	
	}
	public void releasedSpace(){
		notRouteSpace1Image = new ImageIcon(Main.class.getResource("../image/notRoute.png")).getImage();
		notRouteSpace2Image = new ImageIcon(Main.class.getResource("../image/notRoute.png")).getImage();
		
	}
	public void pressedJ(){
		notRouteJImage = new ImageIcon(Main.class.getResource("../image/notRoutePressed.png")).getImage();
		new Music("drumSmall1.mp3",false).start();

	}
	public void releasedJ(){
		notRouteJImage = new ImageIcon(Main.class.getResource("../image/notRoute.png")).getImage();
		
	}
	public void pressedK(){
		notRouteKImage = new ImageIcon(Main.class.getResource("../image/notRoutePressed.png")).getImage();
		new Music("drumSmall3.mp3",false).start();

	}
	public void releasedK(){
		notRouteKImage = new ImageIcon(Main.class.getResource("../image/notRoute.png")).getImage();
		
	}
	public void pressedL(){
		notRouteLImage = new ImageIcon(Main.class.getResource("../image/notRoutePressed.png")).getImage();
		new Music("drumBig1.mp3",false).start();

	}
	public void releasedL(){
		notRouteLImage = new ImageIcon(Main.class.getResource("../image/notRoute.png")).getImage();
		
	}
	
	@Override
	public void run() {//스레드라는 프로그램 안에서는 런메소드를 실행해주어야한다.
		
	}

}
