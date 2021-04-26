package dynamic_beat_10;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
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

	private Image screenImage; // ������۸��� ���� �ν��Ͻ�
	private Graphics screenGraphic;

	private Image notRouteLineImage = new ImageIcon(Main.class.getResource("../image/notRouteLine.png")).getImage();
	private Image notRouteImage = new ImageIcon(Main.class.getResource("../image/notRoute.png")).getImage();
	private Image notBasicImage = new ImageIcon(Main.class.getResource("../image/notBasic.png")).getImage();
	private Image judgementLineImage = new ImageIcon(Main.class.getResource("../image/judgementLine.png")).getImage();
	private Image gameInfoImage = new ImageIcon(Main.class.getResource("../image/gameInfo.png")).getImage();
	private Image background = new ImageIcon(Main.class.getResource("../image/intro_background(Title).jpg")).getImage();
	// �̹��� ���� background�� getResource("../images/background.jpg")���� ������
	// �̹����� �ʱ�ȭ.
	private boolean isMainScreen = false;
	private boolean isGameScreen = false; // ���� ȭ������ �Ѿ�Դ����� ���� ���� ��
	
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../image/menuBar.png")));
	// menuBar��� ��ü�ȿ� �ش� �̹����� ����.
	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../image/exitButton_1.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../image/exitButton_2.png"));
	private ImageIcon startButtonEnteredImage = new ImageIcon(Main.class.getResource("../image/StartButton00.png"));
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../image/StartButton0.png"));
	private ImageIcon endButtonEnteredImage = new ImageIcon(Main.class.getResource("../image/ExitButton00.png"));
	private ImageIcon endButtonBasicdImage = new ImageIcon(Main.class.getResource("../image/ExitButton0.png"));
	private ImageIcon leftButtonEnteredImage = new ImageIcon(Main.class.getResource("../image/leftButtonEntered.png"));
	private ImageIcon leftButtonBasicImage = new ImageIcon(Main.class.getResource("../image/leftButtonBasic.png"));
	private ImageIcon rightButtonEnteredImage = new ImageIcon(Main.class.getResource("../image/rightButtonEntered.png"));
	private ImageIcon rightButtonBasicImage = new ImageIcon(Main.class.getResource("../image/rightButtonBasic.png"));	
	private ImageIcon easyButtonEnteredImage = new ImageIcon(Main.class.getResource("../image/easyButtonEntered.png"));
	private ImageIcon easyButtonBasicImage = new ImageIcon(Main.class.getResource("../image/easyButtonBasic.png"));
	private ImageIcon hardButtonEnteredImage = new ImageIcon(Main.class.getResource("../image/hardButtonEntered.png"));
	private ImageIcon hardButtonBasicImage = new ImageIcon(Main.class.getResource("../image/hardButtonBasic.png"));
	private ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("../image/backButtonEntered.png"));
	private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../image/backButtonBasic.png"));

	private JButton exitButton = new JButton(exitButtonBasicImage);
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton quitButton = new JButton(endButtonBasicdImage);
	private JButton leftButton = new JButton(leftButtonBasicImage);
	private JButton rightButton = new JButton(rightButtonBasicImage);
	private JButton easyButton = new JButton(easyButtonBasicImage);
	private JButton hardButton = new JButton(hardButtonBasicImage);
	private JButton backButton = new JButton(backButtonBasicImage);

	private Music introMusic = new Music("intro_music.mp3", true);

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
				
				enterMain();

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
		
		backButton.setVisible(false);
		backButton.setBounds(20, 50, 60, 60);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backButton.setIcon(backButtonEnteredImage); // ���콺�� �ö��� �� �̹����� �ٲ��ش�.
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // ���콺 Ŀ����� ����
				Music buttonEnteredMusic = new Music("ButtonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(backButtonBasicImage); // ���콺�� �������� �� �ٽ� �̹����� �ٲ��ش�.
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonPressedMusic = new Music("ButtonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				backMain();
			}
		});
		add(backButton);

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
		screenDraw((Graphics2D)screenGraphic); // ��ũ�� �׷��ȿ� ��� �׸��� �׷��ش�.
		g.drawImage(screenImage, 0, 0, null); // ��ũ�� �̹����� (0,0)��ġ�� �׷��ش�.
	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(background, 0, 0, null); // background�� ��ũ�� �̹����� �׷��ش�.
		// �ܼ� �̹��� ���.

		if (isMainScreen) {
			g.drawImage(selectedImage, 430, 200, null);
			g.drawImage(titleImage, 340, 70, null);
		}
		if (isGameScreen) {
			
			g.drawImage(gameInfoImage, 0, 660, null);
			
			g.drawImage(notRouteImage,228,30,null);
			g.drawImage(notRouteImage,332,30,null);
			g.drawImage(notRouteImage,436,30,null);
			g.drawImage(notRouteImage,540,30,null);
			g.drawImage(notRouteImage,640,30,null);
			g.drawImage(notRouteImage,744,30,null);
			g.drawImage(notRouteImage,848,30,null);
			g.drawImage(notRouteImage,952,30,null);

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
			//���� ���� ���� �ذ� ��� : Graphics2D ����ȯ
			//g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g.drawString("Easy",1190, 702);
			g.setFont(new Font("Arial",Font.BOLD,26));
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
			isGameScreen = true;
			//		if (isMainScreen) {
//			g.drawImage(selectedImage, 430, 200, null);
//			g.drawImage(titleImage, 340, 70, null);
//		}������ �ȵǵ��� �Ѵ�.
			leftButton.setVisible(false);
			rightButton.setVisible(false);
			easyButton.setVisible(false);
			hardButton.setVisible(false);
			backButton.setVisible(true);
			background = new ImageIcon(Main.class.getResource("../image/"+trackList.get(noswSelected).getGameImage())).getImage();
		}
	}
	public void backMain() {
		isMainScreen = true;
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		background = new ImageIcon(Main.class.getResource("../image/mainBackground.jpg")).getImage();
		backButton.setVisible(false);
		selectTrack(nowSelected); //���� ���õ� Ʈ���� �ٽ� ���.
		isGameScreen = false;

	}
	public void enterMain() {

		
		startButton.setVisible(false); // ��ư�� �������ʰ� ó��
		quitButton.setVisible(false);

		background = new ImageIcon(Main.class.getResource("../image/mainBackground.jpg")).getImage();

		isMainScreen = true;

		leftButton.setVisible(true); //��ư�� ���̰� ó��
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		
		selectTrack(0);
		introMusic.close();
	}

}