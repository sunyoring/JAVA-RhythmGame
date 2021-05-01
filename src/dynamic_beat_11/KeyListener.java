package dynamic_beat_11;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter {

	@Override
	public void keyPressed (KeyEvent e) { // 키를 누르는 경우
		if(DynamicBeat.game == null) { //게임이 실행중이지 않다면 키보드가 눌려도 무시한다.
			return;
		}
		if(e.getKeyCode() == e.VK_S) {
			DynamicBeat.game.pressedS();
		}else if(e.getKeyCode() == e.VK_D) {
			DynamicBeat.game.pressedD();

		}else if(e.getKeyCode() == e.VK_F) {
			DynamicBeat.game.pressedF();

		}else if(e.getKeyCode() == e.VK_SPACE) {
			DynamicBeat.game.pressedSpace();

		}else if(e.getKeyCode() == e.VK_J) {
			DynamicBeat.game.pressedJ();

		}else if(e.getKeyCode() == e.VK_K) {
			DynamicBeat.game.pressedK();

		}else if(e.getKeyCode() == e.VK_L) {
			DynamicBeat.game.pressedL();

		}
	}
	public void keyReleased(KeyEvent e) {//키를 눌렸다 떼지는 경우
		if(DynamicBeat.game == null) { //게임이 실행중이지 않다면 키보드가 눌렸다 떼져도 무시한다.
			return;
		}
		if(e.getKeyCode() == e.VK_S) {
			DynamicBeat.game.releasedS();
		}else if(e.getKeyCode() == e.VK_D) {
			DynamicBeat.game.releasedD();
		}else if(e.getKeyCode() == e.VK_F) {
			DynamicBeat.game.releasedF();
		}else if(e.getKeyCode() == e.VK_SPACE) {
			DynamicBeat.game.releasedSpace();

		}else if(e.getKeyCode() == e.VK_J) {
			DynamicBeat.game.releasedJ();

		}else if(e.getKeyCode() == e.VK_K) {
			DynamicBeat.game.releasedK();

		}else if(e.getKeyCode() == e.VK_L) {
		DynamicBeat.game.releasedL();

	}
	}
}
