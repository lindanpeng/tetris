package config;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

public class SystemConfig {
	private final int maxX;
	private final int minX;
	private final int maxY;
	private final int minY;
	private final int levelUp;
	private final  List<Point[]> typeConfig;
	private final List<Boolean> typeRound;
	public SystemConfig(Element system ){
		minX=Integer.parseInt(system.attributeValue("minX"));
	    maxX=Integer.parseInt(system.attributeValue("maxX"));
	    minY=Integer.parseInt(system.attributeValue("minY"));
	    maxY=Integer.parseInt(system.attributeValue("maxY"));
	    levelUp=Integer.parseInt(system.attributeValue("levelUp"));
	    List<Element> rects=system.elements("rect");
	    typeConfig=new ArrayList<Point[]>(rects.size());
	    typeRound=new ArrayList<Boolean>(rects.size());
	    for(Element rect:rects){
	    	this.typeRound.add(Boolean.parseBoolean(rect.attributeValue("round")));
	    	List<Element> pointConfig=rect.elements("Point");
	    	Point[] points=new Point[pointConfig.size()];
	    	for(int i=0;i<points.length;i++)
	    	{
	    		int x=Integer.parseInt(pointConfig.get(i).attributeValue("x"));
	    		int y=Integer.parseInt(pointConfig.get(i).attributeValue("y"));
	    		points[i]=new Point(x,y);
	    		
	    	}
	    	typeConfig.add(points);
	    	
	    }
	}
	public List<Boolean> getTypeRound() {
		return typeRound;
	}
	public int getMaxX() {
		return maxX;
	}
	public int getMinX() {
		return minX;
	}
	public int getMaxY() {
		return maxY;
	}
	public int getMinY() {
		return minY;
	}
	public List<Point[]> getTypeConfig() {
		return typeConfig;
	}
	public int getLevelUp(){
		return this.levelUp;
	}
}
