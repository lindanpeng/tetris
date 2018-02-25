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
 * 游戏控制器，负责玩家、游戏画面、业务逻辑之间的数据转发
 * @author Administrator
 *
 */
public class GameControl {
	
	//游戏数据保存窗口
	private JFrameSavePoint frameSavePoint;
	//数据访问接口A
	private Data dataA;
	//数据访问接口B
	private Data dataB;
	//游戏界面层
	private JPanelGame panelgame;
	//创建游戏业务逻辑
	private GameTetris gameService;
	//游戏行为控制
	private Map<Integer,Method> actionList;
	//方块下移线程
	private Thread gameThread;
	//游戏数据源
	private GameDto dto=null;
	//键盘监听器
	private Music music=null;
	private PlayerControl playerControl=null;
	public GameControl() throws MalformedURLException{
		//创建音乐控制器
        music=new Music();
		//创建玩家控制器
		playerControl=new PlayerControl(this);
		//创建游戏数据源
		 dto=new GameDto();
		//创建游戏逻辑快
		this.gameService=new GameTetris(dto);
		//创建游戏面板
		this.panelgame=new JPanelGame(dto,this);
		 // 从数据接口A获得数据库记录
		this.dataA=createDataObject(GameConfig.getDataConfig().getDataA());
			//设置数据库记录到游戏
		this.dto.setDbRecode(dataA.loadData());
			//从数据接口B获得本地磁盘记录
		this.dataB=createDataObject(GameConfig.getDataConfig().getDataB());
			//设置本地记录到游戏
		this.dto.setDiskRecode(dataB.loadData());
		//安装事件监听器
		this.panelgame.setPlayerControl(this.playerControl);
		//创建游戏窗口，安装游戏面板
		new JFrameGame(this.panelgame);
        this.frameSavePoint=new JFrameSavePoint(this);
		
		actionList=new HashMap<Integer,Method>();
		try {
			actionList.put(37, this.gameService.getClass().getMethod("KeyLeft"));//左
			actionList.put(38, this.gameService.getClass().getMethod("KeyUp"));//上
			actionList.put(39, this.gameService.getClass().getMethod("KeyRight"));//右
			actionList.put(40, this.gameService.getClass().getMethod("KeyDown"));//下
			actionList.put(32, this.gameService.getClass().getMethod("KeyFunDown"));//快速下落
			actionList.put(16, this.gameService.getClass().getMethod("KeyFunLeft"));//阴影开关
			actionList.put(17, this.gameService.getClass().getMethod("KeyFunUp"));//作弊
			actionList.put(90, this.gameService.getClass().getMethod("KeyFunRight"));//暂停
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
	 * 将键盘事件响应到业务逻辑
	 * @param keyCode 按键类型
	 */
	public void actionByKeyCode(int keyCode) {
		//获得方法名
		//获得方法对象
		try {
		if(this.actionList.containsKey(keyCode))
		{
			this.actionList.get(keyCode).invoke(this.gameService);//调用gameService对象中的方法
		}
		}  catch (Exception e) {
			e.printStackTrace();
		}
		this.panelgame.repaint();
	}
	/**
	 * 游戏开始预处理
	 */
	public void start() {
		
		this.dto.setStart(true);
		music.playBackgroundMusic();
		this.panelgame.requestFocus();
		// m面板按钮设置为不可点击
		this.panelgame.buttonSwitch(false);
		this.frameSavePoint.setVisible(false);
		//游戏数据初始化
		this.gameService.startGame();
	//	this.panelgame.repaint();
		this.gameThread=new MainThread();
		//启动线程
		this.gameThread.start();
		//刷新画面
		this.panelgame.repaint();
	}
	/**
	 * 保存分数
	 * @param name
	 */
	public void savePoint(String name){
		Player p=new Player(name,this.dto.getNowPoint());
		this.dataB.saveData(p);
		this.dto.setDiskRecode(dataB.loadData());
		this.panelgame.repaint();
	}
	/**
	 * 游戏结束后处理
	 */
	private void afterLose(){
		//显示保存得分窗口
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
				//如果暂停。不执行主行为
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
