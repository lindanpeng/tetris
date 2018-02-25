package entity;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import config.GameConfig;

public class GameAct {
 private Point[] actPoints=null;
 /*
  * 方块编号
  */
 private int typeCode;
 private static int MIN_X=GameConfig.getSystemConfig().getMinX();
 private static int MAX_X=GameConfig.getSystemConfig().getMaxX();
 private static int MIN_Y=GameConfig.getSystemConfig().getMinY();
 private static int MAX_Y=GameConfig.getSystemConfig().getMaxY();
 private static List<Boolean> TYPE_ROUND=GameConfig.getSystemConfig().getTypeRound();
 private static List<Point[]> TYPE_CONFIG=GameConfig.getSystemConfig().getTypeConfig();
 public GameAct(int typeCode){
	 this.init(typeCode);
 }
 public void init(int actCode){
	 this.typeCode=actCode;
	Point[] points=TYPE_CONFIG.get(actCode);
	//获得编号方块的数组
	actPoints=new Point[points.length];
	for(int i=0;i<points.length;i++)
	{
		actPoints[i]=new Point(points[i].x,points[i].y);
	}
	 
 }
public int getTypeCode() {
	return typeCode;
}
public Point[] getActPoints() {
	return actPoints;
}
/*
 * moveX x轴偏移量
 * moveY y轴偏移量
 */
public boolean move(int moveX,int moveY,boolean[][] gameMap){
	//边界判定
	//移动处理
	for(int i=0;i<actPoints.length;i++)
	{	int newX=actPoints[i].x+moveX;
	    int newY=actPoints[i].y+moveY;
	    if(isOverZone(newX,newY,gameMap))
	    	return false;
	}
	for(int i=0;i<actPoints.length;i++)
	{	actPoints[i].x+=moveX;
	    actPoints[i].y+=moveY;
	}
	return true;
}
public void round(boolean[][] gameMap){
	if(!this.TYPE_ROUND.get(typeCode)){
		return;
	}
	for(int i=0;i<actPoints.length;i++)
	{
		int newX=actPoints[0].y+actPoints[0].x-actPoints[i].y;
		int newY=actPoints[0].y-actPoints[0].x+actPoints[i].x;
		//判断可否旋转
		if(this.isOverZone(newX,newY,gameMap))return;		
	}
	for(int i=0;i<actPoints.length;i++)
	{
		int newX=actPoints[0].y+actPoints[0].x-actPoints[i].y;
		int newY=actPoints[0].y-actPoints[0].x+actPoints[i].x;
		actPoints[i].x=newX;
		actPoints[i].y=newY;
	}
	
}
private boolean isOverZone(int newX,int newY,boolean[][] gameMap){
	return newX<MIN_X||newX>MAX_X||newY<MIN_Y||newY>MAX_Y||gameMap[newX][newY];
	
}
}
