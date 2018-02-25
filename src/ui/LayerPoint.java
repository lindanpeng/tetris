package ui;
import java.awt.Graphics;


public class LayerPoint extends Layer {

	private static final int ELIMINATE_IMG_H=Img.ELIMINATE_IMG.getHeight(null);
	private static final int LEVEL_UP=20;
	//�������λ��
	private static final int POINT_BIT=5;
	//����x����
	private final  int comX;
	//����y����
	private  final int pointY;
	//����y����
	private  final int rmlineY;
	//����ֵy����
	private final int expY;

	public LayerPoint(int x, int y, int w, int h) {
		super(x, y, w, h);
		//��ʼ����ͨ����ʾ��x����
		comX=this.w-IMG_NUMBER_W*POINT_BIT-PADDING;
		pointY=PADDING;
		rmlineY=Img.ELIMINATE_IMG.getHeight(null)+(PADDING<<1);
		expY=this.rmlineY+ELIMINATE_IMG_H+PADDING;
		// TODO Auto-generated constructor stub
	}
	@Override
	 public void paint(Graphics g){
		 this.createWindow(g);
		 g.drawImage(Img.POINT_IMG, this.x+PADDING, this.y+pointY, null);
		 this.drawNumber(comX, pointY, this.dto.getNowPoint(), g);
		 g.drawImage(Img.ELIMINATE_IMG,this.x+PADDING, this.y+rmlineY,null);
		 this.drawNumber(comX, rmlineY, this.dto.getNowRemoveLine(), g);
		 int rmline=this.dto.getNowRemoveLine();
		 this.drawRect(expY,"��һ��",null,(double)(rmline%LEVEL_UP)/(double)LEVEL_UP, g);
	}
	

}
