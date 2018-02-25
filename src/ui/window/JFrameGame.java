package ui.window;
import javax.swing.JFrame;

import config.FrameConfig;
import config.GameConfig;
import util.FrameUtil;
public class JFrameGame extends JFrame{
 public  JFrameGame(JPanelGame panelGame){
	 FrameConfig fCfg=GameConfig.getFrameConfig();
	 this.setTitle(fCfg.getTitle());
	 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 this.setSize(fCfg.getWidth(),fCfg.getHeight());
	 this.setResizable(false);
     FrameUtil.setFrameCenter(this);
	 this.setContentPane(panelGame);
	 this.setVisible(true);
 }

}
