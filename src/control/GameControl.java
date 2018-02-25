package control;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import config.DataInterfaceConfig;
import config.GameConfig;
import dao.Data;
import dao.DataBase;
import dto.GameDto;
import dto.Player;
import service.GameTetris;
import ui.window.JFrameGame;
import ui.window.JFrameSavePoint;
import ui.window.JPanelGame;
import util.Music;
/**
 * ��Ϸ��������������ҡ���Ϸ���桢ҵ���߼�֮�������ת��
 * @author Administrator
 *
 */
public class GameControl {
	
	//��Ϸ���ݱ��洰��
	private JFrameSavePoint frameSavePoint;
	//���ݷ��ʽӿ�A
	private Data dataA;
	//���ݷ��ʽӿ�B
	private Data dataB;
	//��Ϸ�����
	private JPanelGame panelgame;
	//������Ϸҵ���߼�
	private GameTetris gameService;
	//��Ϸ��Ϊ����
	private Map<Integer,Method> actionList;
	//���������߳�
	private Thread gameThread;
	//��Ϸ����Դ
	private GameDto dto=null;
	//���̼�����
	private Music music=null;
	private PlayerControl playerControl=null;
	public GameControl() throws MalformedURLException{
		//�������ֿ�����
        music=new Music();
		//������ҿ�����
		playerControl=new PlayerControl(this);
		//������Ϸ����Դ
		 dto=new GameDto();
		//������Ϸ�߼���
		this.gameService=new GameTetris(dto);
		//������Ϸ���
		this.panelgame=new JPanelGame(dto,this);
		 // �����ݽӿ�A������ݿ��¼
		this.dataA=createDataObject(GameConfig.getDataConfig().getDataA());
			//�������ݿ��¼����Ϸ
		this.dto.setDbRecode(dataA.loadData());
			//�����ݽӿ�B��ñ��ش��̼�¼
		this.dataB=createDataObject(GameConfig.getDataConfig().getDataB());
			//���ñ��ؼ�¼����Ϸ
		this.dto.setDiskRecode(dataB.loadData());
		//��װ�¼�������
		this.panelgame.setPlayerControl(this.playerControl);
		//������Ϸ���ڣ���װ��Ϸ���
		new JFrameGame(this.panelgame);
        this.frameSavePoint=new JFrameSavePoint(this);
		
		actionList=new HashMap<Integer,Method>();
		try {
			actionList.put(37, this.gameService.getClass().getMethod("KeyLeft"));//��
			actionList.put(38, this.gameService.getClass().getMethod("KeyUp"));//��
			actionList.put(39, this.gameService.getClass().getMethod("KeyRight"));//��
			actionList.put(40, this.gameService.getClass().getMethod("KeyDown"));//��
			actionList.put(32, this.gameService.getClass().getMethod("KeyFunDown"));//��������
			actionList.put(16, this.gameService.getClass().getMethod("KeyFunLeft"));//��Ӱ����
			actionList.put(17, this.gameService.getClass().getMethod("KeyFunUp"));//����
			actionList.put(90, this.gameService.getClass().getMethod("KeyFunRight"));//��ͣ
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}

		
	}
	private Data createDataObject(DataInterfaceConfig cfg){

		try {
				Class<?> cls;cls = Class.forName(cfg.getClassName());
				Constructor<?> ctr= cls.getConstructor(HashMap.class);
				return (Data)ctr.newInstance(cfg.getParam());
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			
	
	}
	/**
	 * �������¼���Ӧ��ҵ���߼�
	 * @param keyCode ��������
	 */
	public void actionByKeyCode(int keyCode) {
		//��÷�����
		//��÷�������
		try {
		if(this.actionList.containsKey(keyCode))
		{
			this.actionList.get(keyCode).invoke(this.gameService);//����gameService�����еķ���
		}
		}  catch (Exception e) {
			e.printStackTrace();
		}
		this.panelgame.repaint();
	}
	/**
	 * ��Ϸ��ʼԤ����
	 */
	public void start() {
		
		this.dto.setStart(true);
		music.playBackgroundMusic();
		this.panelgame.requestFocus();
		// m��尴ť����Ϊ���ɵ��
		this.panelgame.buttonSwitch(false);
		this.frameSavePoint.setVisible(false);
		//��Ϸ���ݳ�ʼ��
		this.gameService.startGame();
	//	this.panelgame.repaint();
		this.gameThread=new MainThread();
		//�����߳�
		this.gameThread.start();
		//ˢ�»���
		this.panelgame.repaint();
	}
	/**
	 * �������
	 * @param name
	 */
	public void savePoint(String name){
		Player p=new Player(name,this.dto.getNowPoint());
		this.dataB.saveData(p);
		this.dto.setDiskRecode(dataB.loadData());
		this.panelgame.repaint();
	}
	/**
	 * ��Ϸ��������
	 */
	private void afterLose(){
		//��ʾ����÷ִ���
		this.frameSavePoint.show(this.dto.getNowPoint());
		this.panelgame.buttonSwitch(true);
		this.dto.setStart(false);
		this.music.stopMusic();
		this.music.playOverMusic();
	
	}
	private class MainThread extends Thread{
		public void run(){
			panelgame.repaint();
			while(dto.isStart()){
		   
		    try {
				Thread.sleep(dto.getSleepTime());
				//�����ͣ����ִ������Ϊ
				if(!dto.isPause()){
				 gameService.mainAction();
				panelgame.repaint();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			afterLose();
		}
	}
}
