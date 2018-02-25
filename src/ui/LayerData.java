package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import config.GameConfig;
import dto.Player;

public abstract class LayerData extends Layer{
    /*
     *���������
     */
	private static final int MAX_ROW=GameConfig.getDataConfig().getMaxRow();
	//��ʼy����
	private static  int START_Y=0;
	//���
	private static  int  SPA;
	public LayerData(int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
		SPA=(this.h-(RECT_H+4)*5-(PADDING<<1)-Img.DB_IMG.getHeight(null))/MAX_ROW;
        START_Y=PADDING+Img.DB_IMG.getHeight(null)+SPA;	
	}

	/*
	 *���Ƹô�������ֵ��
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
            //������Ƽ�¼����ֵ��Ϊ100%
            percent=percent>1?1.0:percent;
            String strPoint=recodePoint==0?null:Integer.toString(recodePoint);
            //���Ƶ�����¼ 
			 this.drawRect(START_Y+i*(SPA+RECT_H+4), players.get(i).getName(),
					strPoint,percent, g);
			 
	       }
   }

	@Override
	abstract public void paint(Graphics g);
}
