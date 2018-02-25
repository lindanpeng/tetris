package ui;

import java.awt.Graphics;


public class LayerLevel extends Layer {
	
	private static final int LV_IMG_W=Img.LV_IMG.getWidth(null);
	//private static final int LV_IMG_H=Img.LV_IMG.getHeight(null);
	
	public LayerLevel(int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
	}
	@Override
	 public void paint(Graphics g){
		 this.createWindow(g);
		 int centerX=this.x+(this.w-LV_IMG_W)/2;
		 int centerY=this.y+PADDING;
		 g.drawImage(Img.LV_IMG, centerX,centerY,null);
		 this.drawNumber(20, 60, this.dto.getLevel(), g);
	 }



}
