  
package dynamic_beat_6;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Music extends Thread { // ������� �ϳ��� ���� ���α׷��̴�.(���� ��� ������)

	private Player player; // ���̺귯��
	private boolean isLoop; // ���� ���ѹݺ����� �ѹ���������� ���� ����.
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;

	public Music(String name, boolean isLoop) {
		try {
			this.isLoop = isLoop;
			file = new File(Main.class.getResource("../music/" + name).toURI());// music�ȿ� �ִ� name ������ �����Ŵ.toURI�� ��ġ��
																				// ������
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis); // �ش� ������ ���ۿ� ��� �о�� �� �ֵ��� �Ѵ�.
			player = new Player(bis); // �ش� ������ �޴´�.
		} catch (Exception e) { // try�������� ����ó�� �߻� �� catch������ �Ѿ
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public int getTime() { // ���� ������ ��� �κп� �帣�� �ִ� �� üũ���ִ� �Լ�. ���ǿ� ���� ��Ʈ�� ����Ʈ�� ��, �� �ð��� �м����ش�.
		if (player == null)
			return 0;
		return player.getPosition();

	}

	public void close() { // ������ ���� ����ǰ� �ִ� ���� �׻� ������ �� �ְ� �������ִ� �Լ�. �ش� ���� ���������� ��������ش�.
		isLoop = false;
		player.close();
		this.interrupt(); // ���� �����ϴ� ���α׷��� ��������ش�. �����带 ����� �� ������ �������� �ϴ� �Լ�!
	}

	@Override
	public void run() { // �����带 ��ӹ����� ������ ����ؾ� �ϴ� �Լ�.
		try {
			do {
				player.play();
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis); // �ش� ������ ���ۿ� ��� �о�� �� �ֵ��� �Ѵ�.
				player = new Player(bis);
			} while (isLoop); // isLoop�� true ���̶�� �ش� ���� ���ѹݺ� ����ȴ�.
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}