package config;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class GameConfig {
	
	public static FrameConfig FRAME_CONFIG=null;
	public static DataConfig DATA_CONFIG=null;
	public static SystemConfig SYSTEM_CONFIG=null;
	static{
		SAXReader reader=new SAXReader();
		Document doc = null;
		try {
			doc = reader.read("data/cfg.xml");
		
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element game=doc.getRootElement();
		FRAME_CONFIG=new FrameConfig(game.element("frame"));
		 DATA_CONFIG=new DataConfig(game.element("data"));
		SYSTEM_CONFIG=new SystemConfig(game.element("system"));
	}
	private GameConfig(){
		
	}
	public static DataConfig getDataConfig(){
		return DATA_CONFIG;
		
	}
	public static SystemConfig getSystemConfig(){
		return SYSTEM_CONFIG;
	} 
	public static FrameConfig getFrameConfig(){
		return  FRAME_CONFIG;
		
	}
}