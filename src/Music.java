  
package dynamic_beat_6;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Music extends Thread { // 스레드는 하나의 작은 프로그램이다.(곡을 재생 시켜줌)

	private Player player; // 라이브러리
	private boolean isLoop; // 곡이 무한반복인지 한번재생인지에 대한 설정.
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;

	public Music(String name, boolean isLoop) {
		try {
			this.isLoop = isLoop;
			file = new File(Main.class.getResource("../music/" + name).toURI());// music안에 있는 name 파일을 실행시킴.toURI로 위치를
																				// 가져옴
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis); // 해당 파일을 버퍼에 담아 읽어올 수 있도록 한다.
			player = new Player(bis); // 해당 파일을 받는다.
		} catch (Exception e) { // try구문에서 예외처리 발생 시 catch문으로 넘어감
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public int getTime() { // 현재 음악이 어느 부분에 흐르고 있는 지 체크해주는 함수. 음악에 맞춰 노트를 떨어트릴 때, 그 시간을 분석해준다.
		if (player == null)
			return 0;
		return player.getPosition();

	}

	public void close() { // 음악이 언제 실행되고 있던 간에 항상 종료할 수 있게 실행해주는 함수. 해당 곡을 안정적으로 종료시켜준다.
		isLoop = false;
		player.close();
		this.interrupt(); // 곡을 실행하는 프로그램을 종료시켜준다. 스레드를 사용할 때 무조건 사용해줘야 하는 함수!
	}

	@Override
	public void run() { // 스레드를 상속받으면 무조건 사용해야 하는 함수.
		try {
			do {
				player.play();
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis); // 해당 파일을 버퍼에 담아 읽어올 수 있도록 한다.
				player = new Player(bis);
			} while (isLoop); // isLoop가 true 값이라면 해당 곡이 무한반복 실행된다.
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}