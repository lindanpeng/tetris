package control;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
/**
 * ��ҿ���������Ӧ�����¼�
 * @author Administrator
 *
 */
public class PlayerControl extends KeyAdapter{
	private GameControl gameControl;
	
	public PlayerControl(GameControl gameControl){
		this.gameControl=gameControl;
		}
	@Override
	public void keyPressed(KeyEvent e) {	
		this.gameControl.actionByKeyCode(e.getKeyCode());
	}

}
