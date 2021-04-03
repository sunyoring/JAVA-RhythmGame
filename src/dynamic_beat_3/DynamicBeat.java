package dynamic_beat_3;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import dynamic_beat_1.Main;

public class DynamicBeat extends JFrame {
	
	private Image screenImage; //������۸��� ���� �ν��Ͻ�
	private Graphics screenGraphic;
	
	private Image introBackground;
	
	public DynamicBeat() {
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
		setResizable(false); //�� �� ������� �������� ũ�⸦ ����ڰ� ���Ƿ� �������� ���ϰ� ��.
		setLocationRelativeTo(null); //����â�� ��ǻ���� ���߾ӿ� �߰� ��.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //����â�� �������� �� ���α׷��� ���� ����ǰ� ��. (�ʼ�)
		setVisible(true); // ����â�� ���� ���̰� ����.
		
		introBackground = new ImageIcon(Main.class.getResource("../images/intro_Background(Title).jpg")).getImage();
		//�̹��� ���� introBackground�� getResource("../images/introBackground.jpg")���� ������ �̹����� �ʱ�ȭ.
		Music introMusic = new Music("intro_music.mp3",true); 
		introMusic.start();
	}
	
	public void paint(Graphics g) { //paint()�� JFrame�� ��ӹ��� GUI���ӿ��� ���� �� �κп� ȭ���� �׷��ִ� �޼ҵ�ν� ��ӵ� �κ��̴�.
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); //1280*720�� ũ��� ��ũ�� �̹����� �������.
		screenGraphic = screenImage.getGraphics(); //��ũ�� �̹����� ���� �׷��� ��ü�� ����.
		screenDraw(screenGraphic); // ��ũ�� �׷��ȿ� ��� �׸��� �׷��ش�.
		g.drawImage(screenImage,0,0,null); // ��ũ�� �̹����� (0,0)��ġ�� �׷��ش�.
	}
	
	public void screenDraw(Graphics g) {
		g.drawImage(introBackground,0,0,null); //introBackground�� ��ũ�� �̹����� �׷��ش�.
		this.repaint();
	} //���� ���۸����.
	

}
