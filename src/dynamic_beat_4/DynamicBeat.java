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

	private Image screenImage; // ������۸��� ���� �ν��Ͻ�
	private Graphics screenGraphic;

	private Image introBackground = new ImageIcon(Main.class.getResource("../images/intro_Background(Title).jpg"))
			.getImage();
	// �̹��� ���� introBackground�� getResource("../images/introBackground.jpg")���� ������
	// �̹����� �ʱ�ȭ.
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));
	// menuBar��� ��ü�ȿ� �ش� �̹����� ����.
	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButton_1.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButton_2.png"));
	private JButton exitButton = new JButton(exitButtonBasicImage);

	private int mouseX, mouseY;// ���� ���α׷��ȿ��� ���콺�� ��ǥ

	public DynamicBeat() {
		setUndecorated(true); // �������� �� �⺻������ �����ϴ� �޴��ٰ� ������ �ʰ� ��.
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false); // �� �� ������� �������� ũ�⸦ ����ڰ� ���Ƿ� �������� ���ϰ� ��.
		setLocationRelativeTo(null); // ����â�� ��ǻ���� ���߾ӿ� �߰� ��.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ����â�� �������� �� ���α׷��� ���� ����ǰ� ��. (�ʼ�)
		setVisible(true); // ����â�� ���� ���̰� ����.
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null); // ��ư�̳� JLabel���� ���� ���� �� �� ��ġ�� ���� ����.

		exitButton.setBounds(1245, 0, 30, 30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage); // ���콺�� �ö��� �� �̹����� �ٲ��ش�.
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // ���콺 Ŀ����� ����
				Music buttonEnteredMusic = new Music("ButtonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage); // ���콺�� �������� �� �ٽ� �̹����� �ٲ��ش�.
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonPressedMusic = new Music("ButtonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				try { // ��ư�� ������ �Ҹ��� ���� �� 1������ �ڿ� ���α׷��� ����� �� �ְ� ����.
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0); // Ŭ������ �� ������ ����ȴ�.

			}
		});
		add(exitButton);

		menuBar.setBounds(0, 0, 1280, 30); // ��ġ�� ũ�⸦ ������.
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) { // ���콺 Ŭ�� �̺�Ʈ
				mouseX = e.getX();
				mouseY = e.getY();
			} // �̺�Ʈ�� �߻����� �� x��ǥ��y��ǥ�� ���´�.
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) { // ���콺 �巡�� �̺�Ʈ
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
				// �޴��ٸ� ���콺�� �巡���� �� ���� ���콺�� ��ġ�� JFram ��, ����â�� ��ġ�� �̵���Ų��.
			}
		});
		add(menuBar); // JFrame�� �޴��ٰ� �߰��Ǹ� paintComponents(g)�� �̿��� �׷����� �ȴ�.

		Music introMusic = new Music("intro_music.mp3", true);
		introMusic.start();
	}

	public void paint(Graphics g) { // paint()�� JFrame�� ��ӹ��� GUI���ӿ��� ���� �� �κп� ȭ���� �׷��ִ� �޼ҵ�ν� ��ӵ� �κ��̴�.
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // 1280*720�� ũ��� ��ũ�� �̹����� �������.
		screenGraphic = screenImage.getGraphics(); // ��ũ�� �̹����� ���� �׷��� ��ü�� ����.
		screenDraw(screenGraphic); // ��ũ�� �׷��ȿ� ��� �׸��� �׷��ش�.
		g.drawImage(screenImage, 0, 0, null); // ��ũ�� �̹����� (0,0)��ġ�� �׷��ش�.
	}

	public void screenDraw(Graphics g) {
		g.drawImage(introBackground, 0, 0, null); // introBackground�� ��ũ�� �̹����� �׷��ش�.
		paintComponents(g); // �������� �̹����� ��ư�� �׷��� �� �̿�.
		this.repaint();
	} // ���� ���۸����.

}