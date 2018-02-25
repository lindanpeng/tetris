package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import config.GameConfig;
import dto.Player;

public abstract class LayerData extends Layer{
    /*
     *最大数据行
     */
	private static final int MAX_ROW=GameConfig.getDataConfig().getMaxRow();
	//起始y坐标
	private static  int START_Y=0;
	//间距
	private static  int  SPA;
	public LayerData(int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
		SPA=(this.h-(RECT_H+4)*5-(PADDING<<1)-Img.DB_IMG.getHeight(null))/MAX_ROW;
        START_Y=PADDING+Img.DB_IMG.getHeight(null)+SPA;	
	}

	/*
	 *绘制该窗口所有值槽
	 */
   public void showData(Image imgTitle,List<Player> players,Graphics g){
	   g.drawImage(imgTitle, this.x+PADDING, this.y+PADDING,null);
	   int nowPoint=this.dto.getNowPoint();
		// players=this.dto.getDiskRecode();
		 for(int i=0;i<MAX_ROW;i++)
	       {
			 Player pla=players.get(i);
			 int recodePoint=pla.getPoint();
            double percent =(double)nowPoint/recodePoint;
            //如果已破记录。比值设为100%
            percent=percent>1?1.0:percent;
            String strPoint=recodePoint==0?null:Integer.toString(recodePoint);
            //绘制单条记录 
			 this.drawRect(START_Y+i*(SPA+RECT_H+4), players.get(i).getName(),
					strPoint,percent, g);
			 
	       }
   }

	@Override
	abstract public void paint(Graphics g);
}
