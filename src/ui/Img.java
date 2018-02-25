package ui;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import config.GameConfig;

public class Img {
	public static final Image LV_IMG=new ImageIcon("Graphics/string/level.png").getImage();
	public static final Image BG_IMG=new ImageIcon("Graphics/background/bg03.jpg").getImage();
	public static final Image RECT_IMG=new ImageIcon("Graphics/windows/rect.png").getImage();
    public static final Image IMG_NUMBER=new ImageIcon("Graphics/string/number.png").getImage();
	public static final Image ABOUT_IMG=new ImageIcon("Graphics/string/about.png").getImage();
	public static final Image ACT=new ImageIcon("Graphics/game/rect.png").getImage();
	public static final Image WINDOW_IMG=new ImageIcon("Graphics/windows/window.png").getImage();
	public static final Image POINT_IMG=new ImageIcon("Graphics/string/point.png").getImage();
	public static final Image ELIMINATE_IMG=new ImageIcon("Graphics/string/eliminate.png").getImage();
	public static final Image DB_IMG=new ImageIcon("Graphics/string/db.png").getImage();
	public static final Image RE_IMG=new ImageIcon("Graphics/string/record.png").getImage();
	public static final Image SHADOW=new ImageIcon("Graphics/game/shadow.png").getImage();
	public static final ImageIcon PLAY_BUTTON=new ImageIcon("Graphics/string/play.png");
	public static final ImageIcon QUIT_BUTTON=new ImageIcon("Graphics/string/quit.png");
	public static final Image PAUSE=new ImageIcon("Graphics/string/pause.png").getImage();
	public static List<Image> BG_LIST;
	
	private Img(){}
	public  static final Image[] NEXT_ACT;
	static{
		NEXT_ACT=new Image[GameConfig.getSystemConfig().getTypeConfig().size()];
		 for(int i=0;i<NEXT_ACT.length;i++)
		 {
		 	NEXT_ACT[i]=new ImageIcon("graphics/game/"+i+".png").getImage();
		 }
		    File dir=new File("graphics/background");
			File[] files=dir.listFiles();
		    BG_LIST=new ArrayList<Image>();
			for(File file:files){
				if(!file.isDirectory())
				{
				  String path=file.getPath();
				  BG_LIST.add(new ImageIcon(path).getImage());
				//System.out.println(path);
				}
		}
	   }


	
}
