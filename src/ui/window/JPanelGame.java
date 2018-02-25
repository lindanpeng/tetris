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
	//��Ӽ��̴�����
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
		   //��������
			Class<?> cls=Class.forName(layerCfg.getClassName());
			//��ù��캯��
			Constructor<?> ctr=cls.getConstructor(int.class,int.class,int.class,int.class);
		    //���ù��캯����������
			Layer l=(Layer)ctr.newInstance(layerCfg.getX(),layerCfg.getY(),
					layerCfg.getW(),layerCfg.getH());
			//���뼯��
			lays.add(l);
			
			
	   } 
	   }catch (Exception e) {
			e.printStackTrace();
		}
	  
	   for(int i=0;i<lays.size();i++)
		   lays.get(i).setDto(dto);
   }
   //�˷�����ʱ����??
   /*
    * :׼ȷ��˵ paint �������� repaint ���������� paint �¼�����ָ�ɸ� UI �̣߳�EDT��ִ�еġ��� paintComponent ���� paint ���õġ��� Swing �ڲ��Ľ���ˢ�´��룬һ�����ն��ǵ��� repaint ����ɵġ�
    * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
    */
   @Override
	public void paintComponent(Graphics g){
	    super.paintComponent(g);//swing����
	for(int i=0;i<lays.size();i++)
		{
		   lays.get(i).paint(g);
		}
	 
   }

   /*
    * ���ư�ť�Ƿ�ɵ��
    */
   public void buttonSwitch(boolean onOff){
	   this.play.setEnabled(onOff);
   }
}