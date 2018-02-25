package ui.window;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import config.FrameConfig;
import config.GameConfig;
import config.LayerConfig;
import control.GameControl;
import control.PlayerControl;
import dto.GameDto;
import ui.Img;
import ui.Layer;
public class JPanelGame extends JPanel{
	private static  JButton play=null;
	private static  JButton quit=null;
	private static  JButton reopen=null;
	private static  GameControl gameControl;
private List<Layer> lays=null;
	public JPanelGame(GameDto dto,GameControl gameControl){
	    this.setLayout(null);
	    this.gameControl=gameControl;
		this.initLayer(dto);
		this.initComponent();
		
	}
	//添加键盘处理器
   public void  setPlayerControl(PlayerControl control){
	   this.addKeyListener(control);
   }
   private void initComponent(){
	   play=new JButton(Img.PLAY_BUTTON);
	   quit=new JButton(Img.QUIT_BUTTON);
	   play.setBounds(795+40,64, 105, 40);
	   quit.setBounds(play.getX()+140, play.getY(), 105, 40);
	   this.play.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			gameControl.start();
		}
		   
	   });
	   this.quit.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
		   
	   });
	   this.add(play);
	   this.add(quit);
   }

   private void  initLayer(GameDto dto){
	   try { 
		   FrameConfig fCfg=GameConfig.getFrameConfig();
	    List<LayerConfig> layersCfg=fCfg.getLayersConfig();
	   lays=new ArrayList<Layer>(layersCfg.size());
	   for(LayerConfig layerCfg:layersCfg){
		   //获得类对象
			Class<?> cls=Class.forName(layerCfg.getClassName());
			//获得构造函数
			Constructor<?> ctr=cls.getConstructor(int.class,int.class,int.class,int.class);
		    //调用构造函数创建对象
			Layer l=(Layer)ctr.newInstance(layerCfg.getX(),layerCfg.getY(),
					layerCfg.getW(),layerCfg.getH());
			//放入集合
			lays.add(l);
			
			
	   } 
	   }catch (Exception e) {
			e.printStackTrace();
		}
	  
	   for(int i=0;i<lays.size();i++)
		   lays.get(i).setDto(dto);
   }
   //此方法何时调用??
   /*
    * :准确的说 paint 方法是由 repaint 方法产生了 paint 事件，并指派给 UI 线程（EDT）执行的。而 paintComponent 是由 paint 调用的。而 Swing 内部的界面刷新代码，一般最终都是调用 repaint 来完成的。
    * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
    */
   @Override
	public void paintComponent(Graphics g){
	    super.paintComponent(g);//swing机制
	for(int i=0;i<lays.size();i++)
		{
		   lays.get(i).paint(g);
		}
	 
   }

   /*
    * 控制按钮是否可点击
    */
   public void buttonSwitch(boolean onOff){
	   this.play.setEnabled(onOff);
   }
}