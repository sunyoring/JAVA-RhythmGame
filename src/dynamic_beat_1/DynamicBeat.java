package dynamic_beat_1;

import javax.swing.JFrame;

public class DynamicBeat extends JFrame {
	public DynamicBeat() {
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
		setResizable(false); //한 번 만들어진 게임장의 크기를 사용자가 임의로 수정하지 못하게 함.
		setLocationRelativeTo(null); //게임창이 컴퓨터의 정중앙에 뜨게 함.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //게임창을 종료했을 때 프로그램이 같이 종료되게 함. (필수)
		setVisible(true); // 게임창이 눈에 보이게 해줌.
	}

}
