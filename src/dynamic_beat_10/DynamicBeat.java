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

	private Image screenImage; // 더블버퍼링을 위한 인스턴스
	private Graphics screenGraphic;

	private Image notRouteLineImage = new ImageIcon(Main.class.getResource("../image/notRouteLine.png")).getImage();
	private Image notRouteImage = new ImageIcon(Main.class.getResource("../image/notRoute.png")).getImage();
	private Image notBasicImage = new ImageIcon(Main.class.getResource("../image/notBasic.png")).getImage();
	private Image judgementLineImage = new ImageIcon(Main.class.getResource("../image/judgementLine.png")).getImage();
	private Image gameInfoImage = new ImageIcon(Main.class.getResource("../image/gameInfo.png")).getImage();
	private Image background = new ImageIcon(Main.class.getResource("../image/intro_background(Title).jpg")).getImage();
	// 이미지 변수 background를 getResource("../images/background.jpg")에서 가져온
	// 이미지로 초기화.
	private boolean isMainScreen = false;
	private boolean isGameScreen = false; // 게임 화면으로 넘어왔는지에 대한 변수 값
	
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../image/menuBar.png")));
	// menuBar라는 객체안에 해당 이미지를 담음.
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

	private int mouseX, mouseY;// 현재 프로그램안에서 마우스의 좌표

	// 곡이 선택되는 부분
	ArrayList<Track> trackList = new ArrayList<Track>();
	private Image selectedImage;
	private Image titleImage;
	private Music selectedMusic;

	private int nowSelected = 0;

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
				startButton.setIcon(startButtonEnteredImage); // 마우스가 올라갔을 때 이미지를 바꿔준다.
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 커서모양 변경
				Music buttonEnteredMusic = new Music("ButtonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage); // 마우스가 내려왔을 때 다시 이미지를 바꿔준다.
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
				quitButton.setIcon(endButtonEnteredImage); // 마우스가 올라갔을 때 이미지를 바꿔준다.
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 커서모양 변경
				Music buttonEnteredMusic = new Music("ButtonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(endButtonBasicdImage); // 마우스가 내려왔을 때 다시 이미지를 바꿔준다.
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

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
		add(quitButton);

		leftButton.setVisible(false);
		leftButton.setBounds(140, 310, 60, 60);
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				leftButton.setIcon(leftButtonEnteredImage); // 마우스가 올라갔을 때 이미지를 바꿔준다.
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 커서모양 변경
				Music buttonEnteredMusic = new Music("ButtonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftButtonBasicImage); // 마우스가 내려왔을 때 다시 이미지를 바꿔준다.
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonPressedMusic = new Music("ButtonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				// 왼쪽 버튼 이벤트
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
				rightButton.setIcon(rightButtonEnteredImage); // 마우스가 올라갔을 때 이미지를 바꿔준다.
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 커서모양 변경
				Music buttonEnteredMusic = new Music("ButtonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightButtonBasicImage); // 마우스가 내려왔을 때 다시 이미지를 바꿔준다.
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonPressedMusic = new Music("ButtonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				// 오른쪽 버튼 이미지
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
				easyButton.setIcon(easyButtonEnteredImage); // 마우스가 올라갔을 때 이미지를 바꿔준다.
				easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 커서모양 변경
				Music buttonEnteredMusic = new Music("ButtonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				easyButton.setIcon(easyButtonBasicImage); // 마우스가 내려왔을 때 다시 이미지를 바꿔준다.
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
				hardButton.setIcon(hardButtonEnteredImage); // 마우스가 올라갔을 때 이미지를 바꿔준다.
				hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 커서모양 변경
				Music buttonEnteredMusic = new Music("ButtonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				hardButton.setIcon(hardButtonBasicImage); // 마우스가 내려왔을 때 다시 이미지를 바꿔준다.
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
				backButton.setIcon(backButtonEnteredImage); // 마우스가 올라갔을 때 이미지를 바꿔준다.
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 커서모양 변경
				Music buttonEnteredMusic = new Music("ButtonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(backButtonBasicImage); // 마우스가 내려왔을 때 다시 이미지를 바꿔준다.
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

	}

	public void paint(Graphics g) { // paint()는 JFrame을 상속받은 GUI게임에서 가장 앞 부분에 화면을 그려주는 메소드로써 약속된 부분이다.
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // 1280*720의 크기로 스크린 이미지를 만들어줌.
		screenGraphic = screenImage.getGraphics(); // 스크린 이미지를 통해 그래픽 객체를 얻어옴.
		screenDraw((Graphics2D)screenGraphic); // 스크린 그래픽에 어떠한 그림을 그려준다.
		g.drawImage(screenImage, 0, 0, null); // 스크린 이미지를 (0,0)위치에 그려준다.
	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(background, 0, 0, null); // background를 스크린 이미지에 그려준다.
		// 단순 이미지 출력.

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
			//글자 깨짐 현상 해결 방법 : Graphics2D 형변환
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
		// add부분이 그려진다. // 고정적인 이미지나 버튼을 그려줄 때 이용.
		this.repaint();
	} // 더블 버퍼링기법.

	public void selectTrack(int nowSelected) {
		if (selectedMusic != null) {
			selectedMusic.close();
		}
		titleImage = new ImageIcon(Main.class.getResource("../image/" + trackList.get(nowSelected).getTitleImage()))
				.getImage();// 현재 선택 된 곡의 타이틀 이미지 값을 가져온다.
		selectedImage = new ImageIcon(Main.class.getResource("../image/" + trackList.get(nowSelected).getStartImage()))
				.getImage();// 현재 선택 된 곡의 타이틀 이미지 값을 가져온다.
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true);
		selectedMusic.start();
	}

	public void selectLeft() {
		if (nowSelected == 0) { // 0번째곡일때는 트랙리스트 사이즈에 1을 빼 준다. 즉, 가장 오른쪽에 있는 곡이 선택 되어야 하기 때문이다.
			nowSelected = trackList.size() - 1;
		} else
			nowSelected--;
		selectTrack(nowSelected);
	}

	public void selectRight() {
		if (nowSelected == trackList.size() - 1) { // 0번째곡일때는 트랙리스트 사이즈에 1을 빼 준다. 즉, 가장 오른쪽에 있는 곡이 선택 되어야 하기 때문이다.
			nowSelected = 0;
		} else
			nowSelected++;
		selectTrack(nowSelected);
	}
	
	public void gameStart(int noswSelected, String difficulty) {
		if(selectedMusic != null) {
			selectedMusic.close(); //현재 재생중이었던 음악은 종료.
			isMainScreen = false; 
			isGameScreen = true;
			//		if (isMainScreen) {
//			g.drawImage(selectedImage, 430, 200, null);
//			g.drawImage(titleImage, 340, 70, null);
//		}실행이 안되도록 한다.
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
		selectTrack(nowSelected); //현재 선택된 트랙을 다시 재생.
		isGameScreen = false;

	}
	public void enterMain() {

		
		startButton.setVisible(false); // 버튼이 보이지않게 처리
		quitButton.setVisible(false);

		background = new ImageIcon(Main.class.getResource("../image/mainBackground.jpg")).getImage();

		isMainScreen = true;

		leftButton.setVisible(true); //버튼이 보이게 처리
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		
		selectTrack(0);
		introMusic.close();
	}

}