package dynamic_beat_4;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import dynamic_beat_3.Main;

public class DynamicBeat extends JFrame {

	private Image screenImage; // 더블버퍼링을 위한 인스턴스
	private Graphics screenGraphic;

	private Image introBackground = new ImageIcon(Main.class.getResource("../images/intro_Background(Title).jpg"))
			.getImage();
	// 이미지 변수 introBackground를 getResource("../images/introBackground.jpg")에서 가져온
	// 이미지로 초기화.
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));
	// menuBar라는 객체안에 해당 이미지를 담음.
	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButton_1.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButton_2.png"));
	private JButton exitButton = new JButton(exitButtonBasicImage);

	private int mouseX, mouseY;// 현재 프로그램안에서 마우스의 좌표

	public DynamicBeat() {
		setUndecorated(true); // 실행했을 때 기본적으로 존재하는 메뉴바가 보이지 않게 함.
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false); // 한 번 만들어진 게임장의 크기를 사용자가 임의로 수정하지 못하게 함.
		setLocationRelativeTo(null); // 게임창이 컴퓨터의 정중앙에 뜨게 함.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 게임창을 종료했을 때 프로그램이 같이 종료되게 함. (필수)
		setVisible(true); // 게임창이 눈에 보이게 해줌.
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null); // 버튼이나 JLabel같은 것을 넣을 때 제 위치에 들어가게 해줌.

		exitButton.setBounds(1245, 0, 30, 30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage); // 마우스가 올라갔을 때 이미지를 바꿔준다.
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 커서모양 변경
				Music buttonEnteredMusic = new Music("ButtonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage); // 마우스가 내려왔을 때 다시 이미지를 바꿔준다.
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonPressedMusic = new Music("ButtonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				try { // 버튼이 눌리고 소리가 나면 약 1초정도 뒤에 프로그램이 종료될 수 있게 구현.
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0); // 클릭했을 때 실행이 종료된다.

			}
		});
		add(exitButton);

		menuBar.setBounds(0, 0, 1280, 30); // 위치와 크기를 정해줌.
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) { // 마우스 클릭 이벤트
				mouseX = e.getX();
				mouseY = e.getY();
			} // 이벤트가 발생했을 때 x좌표와y좌표를 얻어온다.
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) { // 마우스 드래그 이벤트
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
				// 메뉴바를 마우스로 드래그할 때 현재 마우스의 위치로 JFram 즉, 게임창의 위치를 이동시킨다.
			}
		});
		add(menuBar); // JFrame에 메뉴바가 추가되며 paintComponents(g)를 이용해 그려지게 된다.

		Music introMusic = new Music("intro_music.mp3", true);
		introMusic.start();
	}

	public void paint(Graphics g) { // paint()는 JFrame을 상속받은 GUI게임에서 가장 앞 부분에 화면을 그려주는 메소드로써 약속된 부분이다.
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // 1280*720의 크기로 스크린 이미지를 만들어줌.
		screenGraphic = screenImage.getGraphics(); // 스크린 이미지를 통해 그래픽 객체를 얻어옴.
		screenDraw(screenGraphic); // 스크린 그래픽에 어떠한 그림을 그려준다.
		g.drawImage(screenImage, 0, 0, null); // 스크린 이미지를 (0,0)위치에 그려준다.
	}

	public void screenDraw(Graphics g) {
		g.drawImage(introBackground, 0, 0, null); // introBackground를 스크린 이미지에 그려준다.
		paintComponents(g); // 고정적인 이미지나 버튼을 그려줄 때 이용.
		this.repaint();
	} // 더블 버퍼링기법.

}