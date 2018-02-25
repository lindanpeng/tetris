package service;
import java.awt.Point;
import java.net.MalformedURLException;
import java.util.Random;

import config.GameConfig;
import dto.GameDto;
import entity.GameAct;
import util.Music;

/*
 * the algorithm of the game
 */
public class GameTetris implements GameService {

	private GameDto dto;
	private Random random=new Random();
	private Music music=null;
	private static final int LEVEL_UP=GameConfig.getSystemConfig().getLevelUp();
	//方块种类个数
	private static final int MAX_TYPE=7;
	public GameTetris (GameDto dto) throws MalformedURLException{
		this.dto=dto;
		music=new Music();
	}
	public boolean KeyUp() {
		// 旋转
		if(this.dto.isPause()||!this.dto.isStart())return true;
		synchronized (this.dto){
		this.dto.getGameAct().round(this.dto.getGameMap());
		this.music.playRoundMusic();
		}
		return true;
	}
	public boolean KeyDown() {
		if(this.dto.isPause()||!this.dto.isStart())return true;
		//可以向下则返回
		synchronized (this.dto){//独占dto
		if(this.dto.getGameAct().move(0,1,this.dto.getGameMap())){
			this.music.playMoveMusic();
			return false;
		}
		//否则设置坐标为true
		boolean[][] map=this.dto.getGameMap();
		Point[] act=this.dto.getGameAct().getActPoints();
	
		for(int i=0;i<act.length;i++)
		{
			map[act[i].x][act[i].y]=true;
		}
		//获取经验值 
	    int exp= this.plusLExp();
        this.plusPoint(exp);
		//算分操作
	
		//判断是否升级
		//升级
		//创建下一个方块
		this.dto.getGameAct().init(this.dto.getNext());
		//随机生成再下一个方块
		this.dto.setNext(random.nextInt(MAX_TYPE));
		//检查游戏是否失败
		if(this.isLose()){
			//游戏失败后的处理
			this.dto.setStart(false);
			  //关闭游戏主线程
			}
		}
		return true;
	
	}
	public boolean KeyLeft() {
		if(this.dto.isPause()||!this.dto.isStart())return true;
		synchronized (this.dto){
		this.dto.getGameAct().move(-1,0,this.dto.getGameMap());
		this.music.playMoveMusic();
		}
		return true;
	}
	public boolean KeyRight() {
		if(this.dto.isPause()||!this.dto.isStart())return true;
		synchronized (this.dto){
		this.dto.getGameAct().move(1,0,this.dto.getGameMap());
		this.music.playMoveMusic();
		}
		return true;
	}
@Override
public boolean KeyFunUp() {
	//作弊建
		plusPoint(4);
		return true;
}
@Override
public boolean KeyFunDown() {
	if(this.dto.isPause())return true;
	//瞬间下落
	while(!this.KeyDown());
	return true;
}
@Override
public boolean KeyFunLeft() {
  // 阴影开关
	this.dto.setShowShadow();
	return true;
}
@Override
public boolean KeyFunRight() {
	// 暂停
	if(this.dto.isStart())
	{
		this.dto.setPause();
		
	}
	return true;
}
	
/**
 * 判断是否结束
 * @return
 */
private boolean isLose() {
	Point[] actPoints=this.dto.getGameAct().getActPoints();
	boolean[][] map=this.dto.getGameMap();
	for(int i=0;i<actPoints.length;i++)
	{
	 if(map[actPoints[i].x][actPoints[i].y]&&(actPoints[i].y==0)){
		 return true;
	 }
	}
	
	return false;
	
}
private void plusPoint(int exp) {
	// TODO Auto-generated method stub
	int lv=this.dto.getLevel();
	int rmline=this.dto.getNowRemoveLine();
	int point=this.dto.getNowPoint();
	if(rmline%LEVEL_UP+exp>=LEVEL_UP){
		this.dto.setLevel(++lv);
	}
	this.dto.setNowRemoveLine(rmline+exp);
	this.dto.setNowPoint(point+exp*10);
}
private int plusLExp() {
	// TODO Auto-generated method stub 
		int exp=0;
	boolean[][] map=this.dto.getGameMap();

	for(int y=0;y<this.dto.GAMEZONE_H;y++)
	{
		//判断是否可消行
		if(isCanRemoveLine(y, map)){
			this.removeline(y,map);
			exp++;
		}
	}
	return exp;
}
private void removeline(int row, boolean[][] map) {
	// TODO Auto-generated method stub
	this.music.playRmlineMusic();
	for(int x=0;x<this.dto.GAMEZONE_W;x++){
		for(int y=row;y>0;y--){
			map[x][y]=map[x][y-1];
		}
		map[x][0]=false;
	}
}
private boolean isCanRemoveLine(int y,boolean[][] map){
	for(int x=0;x<this.dto.GAMEZONE_W;x++)
	{
	  if(!map[x][y]){
		  //如果有一个方块为false则直接跳行
		  return false;
	  }
	}
	return true;
}
@Override
public void startGame() {	
	//默认打开阴影开关
	 this.dto.setShowShadow();
	//随机生成下一个方块
	this.dto.setNext(random.nextInt(MAX_TYPE));
	//随机生成现在方块
	dto.setGameAct(new GameAct(random.nextInt(MAX_TYPE)));
	//把游戏状态设置为开始
	this.dto.setStart(true);
	//游戏数据初始化
	this.dto.dtoInit();
}
public void mainAction(){
	if(this.dto.isPause()||!this.dto.isStart())return;
	//可以向下则返回
	synchronized (this.dto){//独占dto
	if(this.dto.getGameAct().move(0,1,this.dto.getGameMap())){
		return;
	}
	//否则设置坐标为true
	boolean[][] map=this.dto.getGameMap();
	Point[] act=this.dto.getGameAct().getActPoints();

	for(int i=0;i<act.length;i++)
	{
		map[act[i].x][act[i].y]=true;
	}
	//获取经验值 
    int exp= this.plusLExp();
    this.plusPoint(exp);
	//算分操作

	//判断是否升级
	//升级
	//创建下一个方块
	this.dto.getGameAct().init(this.dto.getNext());
	//随机生成再下一个方块
	this.dto.setNext(random.nextInt(MAX_TYPE));
	//检查游戏是否失败
	if(this.isLose()){
		//游戏失败后的处理
		this.dto.setStart(false);
		  //关闭游戏主线程
		}
	}
}
}
