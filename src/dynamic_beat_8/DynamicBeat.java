package dynamic_beat_8;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import dynamic_beat_3.Main;

public class DynamicBeat extends JFrame {

	private Image screenImage; // �������۸��� ���� �ν��Ͻ�
	private Graphics screenGraphic;

	private Image background = new ImageIcon(Main.class.getResource("../image/intro_background(Title).jpg")).getImage();
	// �̹��� ���� background�� getResource("../images/background.jpg")���� ������
	// �̹����� �ʱ�ȭ.
	private boolean isMainScreen = false;

	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../image/menuBar.png")));
	// menuBar��� ��ü�ȿ� �ش� �̹����� ����.
	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../image/exitButton_1.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../image/exitButton_2.png"));
	private ImageIcon startButtonEnteredImage = new ImageIcon(Main.class.getResource("../image/StartButton00.png"));
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../image/StartButton0.png"));
	private ImageIcon endButtonEnteredImage = new ImageIcon(Main.class.getResource("../image/ExitButton00.png"));
	private ImageIcon endButtonBasicdImage = new ImageIcon(Main.class.getResource("../image/ExitButton0.png"));
	private ImageIcon leftButtonEnteredImage = new ImageIcon(Main.class.getResource("../image/LeftButton2.png"));
	private ImageIcon leftButtonBasicImage = new ImageIcon(Main.class.getResource("../image/LeftButton.png"));
	private ImageIcon rightButtonEnteredImage = new ImageIcon(Main.class.getResource("../image/RightButton2.png"));
	private ImageIcon rightButtonBasicImage = new ImageIcon(Main.class.getResource("../image/RightButton.png"));
	
	private ImageIcon easyButtonEnteredImage = new ImageIcon(Main.class.getResource("../image/easyButtonEntered.png"));
	private ImageIcon easyButtonBasicImage = new ImageIcon(Main.class.getResource("../image/easyButtonBasic.png"));
	private ImageIcon hardButtonEnteredImage = new ImageIcon(Main.class.getResource("../image/hardButtonEntered.png"));
	private ImageIcon hardButtonBasicImage = new ImageIcon(Main.class.getResource("../image/hardButtonBasic.png"));

	private JButton exitButton = new JButton(exitButtonBasicImage);
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton quitButton = new JButton(endButtonBasicdImage);
	private JButton leftButton = new JButton(leftButtonBasicImage);
	private JButton rightButton = new JButton(rightButtonBasicImage);
	private JButton easyButton = new JButton(easyButtonBasicImage);
	private JButton hardButton = new JButton(hardButtonBasicImage);

	private int mouseX, mouseY;// ���� ���α׷��ȿ��� ���콺�� ��ǥ

	// ���� ���õǴ� �κ�
	ArrayList<Track> trackList = new ArrayList<Track>();
	private Image selectedImage;
	private Image titleImage;
	private Music selectedMusic;

	private int nowSelected = 0;

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

		Music introMusic = new Music("intro_music.mp3", true);
		introMusic.start();

		trackList.add(new Track("Blue TitleImage.png", "BlueMainImage.jpg", "BlueSky.jpg", "BlueHigh.mp3",
				"Blue - BIGBANG.mp3"));
		trackList.add(new Track("Rollin TitleImage.png", "RollinMainImage.jpg", "image1.jpg", "RollinHigh.mp3",
				"Rollin - BraveGirls.mp3"));
		trackList.add(new Track("Dynamite TitleImage.png", "DynamiteMainImage.jpg", "image2.jpg", "DynamiteHigh.mp3",
				"Dynamite - BTS.mp3"));

		startButton.setBounds(540, 200, 200, 100);
		startButton.setBorderPainted(false);
		;
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonEnteredImage); // ���콺�� �ö��� �� �̹����� �ٲ��ش�.
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // ���콺 Ŀ����� ����
				Music buttonEnteredMusic = new Music("ButtonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage); // ���콺�� �������� �� �ٽ� �̹����� �ٲ��ش�.
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonPressedMusic = new Music("ButtonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				introMusic.close();

				selectTrack(0);

				isMainScreen = true;
				startButton.setVisible(false); // ��ư�� �������ʰ� ó��
				quitButton.setVisible(false);
				leftButton.setVisible(true); //��ư�� ���̰� ó��
				rightButton.setVisible(true);
				easyButton.setVisible(true);
				hardButton.setVisible(true);
				background = new ImageIcon(Main.class.getResource("../image/mainBackground.jpg")).getImage();

			}
		});
		add(startButton);

		quitButton.setBounds(540, 280, 200, 100);
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				quitButton.setIcon(endButtonEnteredImage); // ���콺�� �ö��� �� �̹����� �ٲ��ش�.
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // ���콺 Ŀ����� ����
				Music buttonEnteredMusic = new Music("ButtonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(endButtonBasicdImage); // ���콺�� �������� �� �ٽ� �̹����� �ٲ��ش�.
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

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
		add(quitButton);

		leftButton.setVisible(false);
		leftButton.setBounds(140, 310, 60, 60);
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				leftButton.setIcon(leftButtonEnteredImage); // ���콺�� �ö��� �� �̹����� �ٲ��ش�.
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // ���콺 Ŀ����� ����
				Music buttonEnteredMusic = new Music("ButtonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftButtonBasicImage); // ���콺�� �������� �� �ٽ� �̹����� �ٲ��ش�.
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonPressedMusic = new Music("ButtonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				// ���� ��ư �̺�Ʈ
				selectLeft();
			}
		});
		add(leftButton);

		rightButton.setVisible(false);
		rightButton.setBounds(1040, 310, 60, 60);
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rightButton.setIcon(rightButtonEnteredImage); // ���콺�� �ö��� �� �̹����� �ٲ��ش�.
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // ���콺 Ŀ����� ����
				Music buttonEnteredMusic = new Music("ButtonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightButtonBasicImage); // ���콺�� �������� �� �ٽ� �̹����� �ٲ��ش�.
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonPressedMusic = new Music("ButtonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				// ������ ��ư �̹���
				selectRight();
			}
		});
		add(rightButton);
		
		easyButton.setVisible(false);
		easyButton.setBounds(375, 620, 210, 50);
		easyButton.setBorderPainted(false);
		easyButton.setContentAreaFilled(false);
		easyButton.setFocusPainted(false);
		easyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				easyButton.setIcon(easyButtonEnteredImage); // ���콺�� �ö��� �� �̹����� �ٲ��ش�.
				easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // ���콺 Ŀ����� ����
				Music buttonEnteredMusic = new Music("ButtonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				easyButton.setIcon(easyButtonBasicImage); // ���콺�� �������� �� �ٽ� �̹����� �ٲ��ش�.
				easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonPressedMusic = new Music("ButtonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				gameStart(nowSelected,"easy");


				
			}
		});
		add(easyButton);
		
		hardButton.setVisible(false);
		hardButton.setBounds(655, 620, 210, 50);
		hardButton.setBorderPainted(false);
		hardButton.setContentAreaFilled(false);
		hardButton.setFocusPainted(false);
		hardButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				hardButton.setIcon(hardButtonEnteredImage); // ���콺�� �ö��� �� �̹����� �ٲ��ش�.
				hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // ���콺 Ŀ����� ����
				Music buttonEnteredMusic = new Music("ButtonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				hardButton.setIcon(hardButtonBasicImage); // ���콺�� �������� �� �ٽ� �̹����� �ٲ��ش�.
				hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonPressedMusic = new Music("ButtonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				gameStart(nowSelected,"hard");
				
			}
		});
		add(hardButton);

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

	}

	public void paint(Graphics g) { // paint()�� JFrame�� ��ӹ��� GUI���ӿ��� ���� �� �κп� ȭ���� �׷��ִ� �޼ҵ�ν� ��ӵ� �κ��̴�.
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // 1280*720�� ũ��� ��ũ�� �̹����� �������.
		screenGraphic = screenImage.getGraphics(); // ��ũ�� �̹����� ���� �׷��� ��ü�� ����.
		screenDraw(screenGraphic); // ��ũ�� �׷��ȿ� ��� �׸��� �׷��ش�.
		g.drawImage(screenImage, 0, 0, null); // ��ũ�� �̹����� (0,0)��ġ�� �׷��ش�.
	}

	public void screenDraw(Graphics g) {
		g.drawImage(background, 0, 0, null); // background�� ��ũ�� �̹����� �׷��ش�.
		// �ܼ� �̹��� ���.

		if (isMainScreen) {
			g.drawImage(selectedImage, 430, 200, null);
			g.drawImage(titleImage, 340, 70, null);
		}

		paintComponents(g);
		// add�κ��� �׷�����. // �������� �̹����� ��ư�� �׷��� �� �̿�.
		this.repaint();
	} // ���� ���۸����.

	public void selectTrack(int nowSelected) {
		if (selectedMusic != null) {
			selectedMusic.close();
		}
		titleImage = new ImageIcon(Main.class.getResource("../image/" + trackList.get(nowSelected).getTitleImage()))
				.getImage();// ���� ���� �� ���� Ÿ��Ʋ �̹��� ���� �����´�.
		selectedImage = new ImageIcon(Main.class.getResource("../image/" + trackList.get(nowSelected).getStartImage()))
				.getImage();// ���� ���� �� ���� Ÿ��Ʋ �̹��� ���� �����´�.
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true);
		selectedMusic.start();
	}

	public void selectLeft() {
		if (nowSelected == 0) { // 0��°���϶��� Ʈ������Ʈ ����� 1�� �� �ش�. ��, ���� �����ʿ� �ִ� ���� ���� �Ǿ�� �ϱ� �����̴�.
			nowSelected = trackList.size() - 1;
		} else
			nowSelected--;
		selectTrack(nowSelected);
	}

	public void selectRight() {
		if (nowSelected == trackList.size() - 1) { // 0��°���϶��� Ʈ������Ʈ ����� 1�� �� �ش�. ��, ���� �����ʿ� �ִ� ���� ���� �Ǿ�� �ϱ� �����̴�.
			nowSelected = 0;
		} else
			nowSelected++;
		selectTrack(nowSelected);
	}
	
	public void gameStart(int noswSelected, String difficulty) {
		if(selectedMusic != null) {
			selectedMusic.close(); //���� ������̾��� ������ ����.
			isMainScreen = false; 
			//		if (isMainScreen) {
//			g.drawImage(selectedImage, 430, 200, null);
//			g.drawImage(titleImage, 340, 70, null);
//		}������ �ȵǵ��� �Ѵ�.
			leftButton.setVisible(false);
			rightButton.setVisible(false);
			easyButton.setVisible(false);
			hardButton.setVisible(false);
			background = new ImageIcon(Main.class.getResource("../image/"+trackList.get(noswSelected).getGameImage())).getImage();
		}
	}

}