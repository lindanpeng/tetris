package dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import config.GameConfig;
import entity.GameAct;
import util.GameFunction;

public class GameDto {
 private List<Player> dbRecode;
 private List<Player> diskRecode;
 public static final int GAMEZONE_W=GameConfig.getSystemConfig().getMaxX()+1;
 public static final int GAMEZONE_H=GameConfig.getSystemConfig().getMaxY()+1;
 private static  long sleepTime;
 private boolean[][] gameMap;
 private boolean start;
 private boolean pause=false;
 private boolean lose;
 private GameAct gameAct;
 private int next;
 private int nowlevel;
 private int nowPoint;
 private int nowRemoveLine;
 public boolean isLose() {
	return lose;
}

public boolean isPause() {
	return pause;
}
public void setPause() {
	this.pause = !pause;
}
//阴影开关
 private boolean showShadow;
 public boolean isShowShadow() {
	return showShadow;
}
public void setShowShadow() {
	this.showShadow = !this.showShadow;
}
public boolean isStart() {
	return start;
}
public void setStart(boolean start) {
	this.start = start;
}

 public GameDto(){
	 dtoInit();
 }
 public void dtoInit(){
	 //TODO 
	 this.gameMap=new boolean[this.GAMEZONE_W][this.GAMEZONE_H];
	 //初始化所有游戏对象
	 this.nowlevel=1;
	 this.nowPoint=0;
	 this.nowRemoveLine=0;
	 this.pause=false;
	 this.sleepTime=GameFunction.getSleepTimeByLevel(this.nowlevel);
 }
 public List<Player> getDbRecode() {
	return dbRecode;
}
public void setDbRecode(List<Player> dbRecode) {
this.dbRecode=this.setFillRecode(dbRecode);
}
public List<Player> getDiskRecode() {
	return diskRecode;
}
public void setDiskRecode(List<Player> diskRecode) {
	this.diskRecode =this.setFillRecode(diskRecode);
	
}
private List<Player> setFillRecode(List<Player> players){
	
	if(players==null)
	{
		players=new ArrayList<Player>();
	}
	while(players.size()<5){
		players.add(new Player("No Data",0));
	}
	Collections.sort(players);
	return players;
	}
public boolean[][] getGameMap() {
	return gameMap;
}
public void setGameMap(boolean[][] gameMap) {
	this.gameMap = gameMap;
}
public GameAct getGameAct() {
	return gameAct;
}
public void setGameAct(GameAct gameAct) {
	this.gameAct = gameAct;
}
public int getNext() {
	return next;
}
public void setNext(int next) {
	this.next = next;
}
public int getLevel() {
	return nowlevel;
}
public void setLevel(int level) {
	this.nowlevel = level;
	
	this.sleepTime=GameFunction.getSleepTimeByLevel(level);
}
public int getNowlevel() {
	return nowlevel;
}
public int getNowPoint() {
	return nowPoint;
}
public static long getSleepTime() {
	return sleepTime;
}
public void setNowPoint(int nowPoint) {
	this.nowPoint = nowPoint;
}
public int getNowRemoveLine() {
	return nowRemoveLine;
}
public void setNowRemoveLine(int nowRemoveLine) {
	this.nowRemoveLine = nowRemoveLine;
}
}
