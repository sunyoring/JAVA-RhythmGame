package dynamic_beat_1;

import javax.swing.JFrame;

public class DynamicBeat extends JFrame {
	public DynamicBeat() {
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
		setResizable(false); //�� �� ������� �������� ũ�⸦ ����ڰ� ���Ƿ� �������� ���ϰ� ��.
		setLocationRelativeTo(null); //����â�� ��ǻ���� ���߾ӿ� �߰� ��.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //����â�� �������� �� ���α׷��� ���� ����ǰ� ��. (�ʼ�)
		setVisible(true); // ����â�� ���� ���̰� ����.
	}

}
