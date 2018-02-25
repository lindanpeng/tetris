package ui;

import java.awt.Graphics;

public class LayerBackground extends Layer {
	
	public LayerBackground(int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
	   int index=this.dto.getLevel()%Img.BG_LIST.size();
      g.drawImage(Img.BG_LIST.get(index), 0, 0,1166,688,0,128,1720,1000,null);
	}

}
