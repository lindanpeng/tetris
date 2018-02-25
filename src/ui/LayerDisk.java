package ui;

import java.awt.Graphics;


public class LayerDisk extends LayerData {
	
	public LayerDisk(int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
	}
@Override
 public void paint(Graphics g){
	 this.createWindow(g);
	// g.drawImage(Img.RE_IMG, this.x+PADDING, this.y+PADDING, null);
	 this.showData(Img.RE_IMG,this.dto.getDiskRecode(), g);
 }


}
