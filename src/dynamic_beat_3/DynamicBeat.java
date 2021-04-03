package dynamic_beat_3;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import dynamic_beat_1.Main;

public class DynamicBeat extends JFrame {
	
	private Image screenImage; //더블버퍼링을 위한 인스턴스
	private Graphics screenGraphic;
	
	private Image introBackground;
	
	public DynamicBeat() {
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
		setResizable(false); //한 번 만들어진 게임장의 크기를 사용자가 임의로 수정하지 못하게 함.
		setLocationRelativeTo(null); //게임창이 컴퓨터의 정중앙에 뜨게 함.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //게임창을 종료했을 때 프로그램이 같이 종료되게 함. (필수)
		setVisible(true); // 게임창이 눈에 보이게 해줌.
		
		introBackground = new ImageIcon(Main.class.getResource("../images/intro_Background(Title).jpg")).getImage();
		//이미지 변수 introBackground를 getResource("../images/introBackground.jpg")에서 가져온 이미지로 초기화.
		Music introMusic = new Music("intro_music.mp3",true); 
		introMusic.start();
	}
	
	public void paint(Graphics g) { //paint()는 JFrame을 상속받은 GUI게임에서 가장 앞 부분에 화면을 그려주는 메소드로써 약속된 부분이다.
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); //1280*720의 크기로 스크린 이미지를 만들어줌.
		screenGraphic = screenImage.getGraphics(); //스크린 이미지를 통해 그래픽 객체를 얻어옴.
		screenDraw(screenGraphic); // 스크린 그래픽에 어떠한 그림을 그려준다.
		g.drawImage(screenImage,0,0,null); // 스크린 이미지를 (0,0)위치에 그려준다.
	}
	
	public void screenDraw(Graphics g) {
		g.drawImage(introBackground,0,0,null); //introBackground를 스크린 이미지에 그려준다.
		this.repaint();
	} //더블 버퍼링기법.
	

}
