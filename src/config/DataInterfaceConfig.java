package config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;

public class DataInterfaceConfig {

	public  final String className;
	private final Map<String,String> param;
	public DataInterfaceConfig(Element dataInterfaceConfig){
		className=dataInterfaceConfig.attributeValue("className");
		this.param=new HashMap<String,String>();
		List<Element> params=dataInterfaceConfig.elements();
		for(Element e:params){
			this.param.put(e.attributeValue("key"),e.attributeValue("value"));
		}
	}
	public String getClassName() {
		return className;
	}
	public Map<String, String> getParam() {
		return param;
	}
}
