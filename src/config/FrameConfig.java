package config;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

public class FrameConfig {
	private final String title="¶íÂÞË¹·½¿é";
	private final int windowUp;
	private final int width;
	private final int height;
	private final int border;
	private final int padding;
	private final int sizeRol;
	private final int loseIdx;
	private List<LayerConfig> layersConfig;

public FrameConfig(Element frame){
	this.width=Integer.parseInt(frame.attributeValue("width"));
	this.height=Integer.parseInt(frame.attributeValue("height"));
	this.padding=Integer.parseInt(frame.attributeValue("padding"));
	this.border=Integer.parseInt(frame.attributeValue("border"));
	this.windowUp=Integer.parseInt(frame.attributeValue("windowUp"));
	this.sizeRol=Integer.parseInt(frame.attributeValue("sizeRol"));
	this.loseIdx=Integer.parseInt(frame.attributeValue("loseIdx"));
	List<Element> layers=frame.elements("layer");
	layersConfig=new ArrayList<LayerConfig>();
	for(Element layer:layers){
		LayerConfig lc=new LayerConfig(
				layer.attributeValue("className"),
				Integer.parseInt(layer.attributeValue("x")),
				Integer.parseInt(layer.attributeValue("y")),
				Integer.parseInt(layer.attributeValue("w")),
				Integer.parseInt(layer.attributeValue("h"))
				);
		layersConfig.add(lc);
	}
}
public int getLoseIdx() {
	return loseIdx;
}
public int getSizeRol() {
	return sizeRol;
}
public String getTitle() {
	return title;
}
public int getWindowUp() {
	return windowUp;
}
public int getWidth() {
	return width;
}
public int getHeight() {
	return height;
}
public int getBorder() {
	return border;
}
public int getPadding() {
	return padding;
}
public List<LayerConfig> getLayersConfig() {
	return layersConfig;
}
}
