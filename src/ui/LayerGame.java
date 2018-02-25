package ui;

import java.awt.Graphics;

import java.awt.Point;

import config.GameConfig;
import entity.GameAct;


public class LayerGame extends Layer {
	
	private static final int SIZE_ROL=GameConfig.getFrameConfig().getSizeRol();
	private static final int LEFT_SIDE=0;
	private static final int RIGHT_SIDE=GameConfig.getSystemConfig().getMaxX();
	private static final int LOSE_IDX=GameConfig.getFrameConfig().getLoseIdx();
	public LayerGame(int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
	}
	@Override
	 public void paint(Graphics g){
		 this.createWindow(g);	
		
		 if(this.dto.isStart()){	 
		 GameAct act=this.dto.getGameAct();
		 Point[] points=act.getActPoints();
			//����Ӱӡ
		 if(this.dto.isShowShadow())
		 this.drawShadow(points, true, g);
		//���ƻ���飻
		 this.drawMainAct(points,g);
          if(this.dto.isPause()){
        	  this.drawImageAtCenter(g, Img.PAUSE);
          }
		 }
		 
		 //������Ϸ��ͼ
		 this.drawMap(g);	
		 
	}
  /**
   * ������Ϸ��ͼ
   * @param g
   */
	private void drawMap(Graphics g) {
		boolean[][] map=this.dto.getGameMap();
		for(int x=0;x<map.length;x++)
		{
			//���㵱ǰ�ѻ���ɫ
			int lv=this.dto.getLevel();
			int ImgIdx=lv==0?0:(lv-1)%7+1;
			//����䣬imgIdx=8
			
			for(int y=0;y<map[x].length;y++)
		
			{
				if(map[x][y])
					{
					drawActByPoint(x,y,this.dto.isStart()?ImgIdx:LOSE_IDX,g);
					}
			}
		}
	}
	/**
	 * ���ݶ������Ʒ���
	 * @param points
	 * @param g
	 */
	private void drawMainAct(Point[] points,Graphics g) {
		// TODO Auto-generated method stub
		
		//��÷������ͱ��
		int typeCode=this.dto.getGameAct().getTypeCode();
		for(int i=0;i<points.length;i++)
			drawActByPoint(points[i].x,points[i].y,typeCode+1,g);
	}
	/**
	 * ���������ο�
	 * @param x
	 * @param y
	 * @param imgIdx
	 * @param g
	 */
  private void drawActByPoint(int x,int y,int imgIdx,Graphics g){
	  g.drawImage(Img.ACT, this.x+BORDER+(x<<SIZE_ROL),
				 this.y+BORDER+(y<<SIZE_ROL),
				 this.x+BORDER+(x+1<<SIZE_ROL),
				 this.y+BORDER+(y+1<<SIZE_ROL)
				 ,(imgIdx)<<SIZE_ROL, 0,(imgIdx+1)<<SIZE_ROL,1<< SIZE_ROL, null);

  }
  public void drawShadow(Point[] points,boolean isShowdow,Graphics g){
 if(!isShowdow){
	 return;
 }
 int leftX=RIGHT_SIDE;
 int rightX=LEFT_SIDE;
   for(Point p:points){
	 leftX=p.x<leftX?p.x:leftX;
	 rightX=p.x>rightX?p.x:rightX;
   }
   g.drawImage(Img.SHADOW,this.x+this.BORDER+(leftX<<SIZE_ROL),this.y+this.BORDER,(rightX-leftX+1)*32,this.h-(this.BORDER<<1),null);
	}
  
}
