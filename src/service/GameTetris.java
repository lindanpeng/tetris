package service;
import java.awt.Point;
import java.net.MalformedURLException;
import java.util.Random;

import config.GameConfig;
import dto.GameDto;
import entity.GameAct;
import util.Music;

/*
 * the algorithm of the game
 */
public class GameTetris implements GameService {

	private GameDto dto;
	private Random random=new Random();
	private Music music=null;
	private static final int LEVEL_UP=GameConfig.getSystemConfig().getLevelUp();
	//�����������
	private static final int MAX_TYPE=7;
	public GameTetris (GameDto dto) throws MalformedURLException{
		this.dto=dto;
		music=new Music();
	}
	public boolean KeyUp() {
		// ��ת
		if(this.dto.isPause()||!this.dto.isStart())return true;
		synchronized (this.dto){
		this.dto.getGameAct().round(this.dto.getGameMap());
		this.music.playRoundMusic();
		}
		return true;
	}
	public boolean KeyDown() {
		if(this.dto.isPause()||!this.dto.isStart())return true;
		//���������򷵻�
		synchronized (this.dto){//��ռdto
		if(this.dto.getGameAct().move(0,1,this.dto.getGameMap())){
			this.music.playMoveMusic();
			return false;
		}
		//������������Ϊtrue
		boolean[][] map=this.dto.getGameMap();
		Point[] act=this.dto.getGameAct().getActPoints();
	
		for(int i=0;i<act.length;i++)
		{
			map[act[i].x][act[i].y]=true;
		}
		//��ȡ����ֵ 
	    int exp= this.plusLExp();
        this.plusPoint(exp);
		//��ֲ���
	
		//�ж��Ƿ�����
		//����
		//������һ������
		this.dto.getGameAct().init(this.dto.getNext());
		//�����������һ������
		this.dto.setNext(random.nextInt(MAX_TYPE));
		//�����Ϸ�Ƿ�ʧ��
		if(this.isLose()){
			//��Ϸʧ�ܺ�Ĵ���
			this.dto.setStart(false);
			  //�ر���Ϸ���߳�
			}
		}
		return true;
	
	}
	public boolean KeyLeft() {
		if(this.dto.isPause()||!this.dto.isStart())return true;
		synchronized (this.dto){
		this.dto.getGameAct().move(-1,0,this.dto.getGameMap());
		this.music.playMoveMusic();
		}
		return true;
	}
	public boolean KeyRight() {
		if(this.dto.isPause()||!this.dto.isStart())return true;
		synchronized (this.dto){
		this.dto.getGameAct().move(1,0,this.dto.getGameMap());
		this.music.playMoveMusic();
		}
		return true;
	}
@Override
public boolean KeyFunUp() {
	//���׽�
		plusPoint(4);
		return true;
}
@Override
public boolean KeyFunDown() {
	if(this.dto.isPause())return true;
	//˲������
	while(!this.KeyDown());
	return true;
}
@Override
public boolean KeyFunLeft() {
  // ��Ӱ����
	this.dto.setShowShadow();
	return true;
}
@Override
public boolean KeyFunRight() {
	// ��ͣ
	if(this.dto.isStart())
	{
		this.dto.setPause();
		
	}
	return true;
}
	
/**
 * �ж��Ƿ����
 * @return
 */
private boolean isLose() {
	Point[] actPoints=this.dto.getGameAct().getActPoints();
	boolean[][] map=this.dto.getGameMap();
	for(int i=0;i<actPoints.length;i++)
	{
	 if(map[actPoints[i].x][actPoints[i].y]&&(actPoints[i].y==0)){
		 return true;
	 }
	}
	
	return false;
	
}
private void plusPoint(int exp) {
	// TODO Auto-generated method stub
	int lv=this.dto.getLevel();
	int rmline=this.dto.getNowRemoveLine();
	int point=this.dto.getNowPoint();
	if(rmline%LEVEL_UP+exp>=LEVEL_UP){
		this.dto.setLevel(++lv);
	}
	this.dto.setNowRemoveLine(rmline+exp);
	this.dto.setNowPoint(point+exp*10);
}
private int plusLExp() {
	// TODO Auto-generated method stub 
		int exp=0;
	boolean[][] map=this.dto.getGameMap();

	for(int y=0;y<this.dto.GAMEZONE_H;y++)
	{
		//�ж��Ƿ������
		if(isCanRemoveLine(y, map)){
			this.removeline(y,map);
			exp++;
		}
	}
	return exp;
}
private void removeline(int row, boolean[][] map) {
	// TODO Auto-generated method stub
	this.music.playRmlineMusic();
	for(int x=0;x<this.dto.GAMEZONE_W;x++){
		for(int y=row;y>0;y--){
			map[x][y]=map[x][y-1];
		}
		map[x][0]=false;
	}
}
private boolean isCanRemoveLine(int y,boolean[][] map){
	for(int x=0;x<this.dto.GAMEZONE_W;x++)
	{
	  if(!map[x][y]){
		  //�����һ������Ϊfalse��ֱ������
		  return false;
	  }
	}
	return true;
}
@Override
public void startGame() {	
	//Ĭ�ϴ���Ӱ����
	 this.dto.setShowShadow();
	//���������һ������
	this.dto.setNext(random.nextInt(MAX_TYPE));
	//����������ڷ���
	dto.setGameAct(new GameAct(random.nextInt(MAX_TYPE)));
	//����Ϸ״̬����Ϊ��ʼ
	this.dto.setStart(true);
	//��Ϸ���ݳ�ʼ��
	this.dto.dtoInit();
}
public void mainAction(){
	if(this.dto.isPause()||!this.dto.isStart())return;
	//���������򷵻�
	synchronized (this.dto){//��ռdto
	if(this.dto.getGameAct().move(0,1,this.dto.getGameMap())){
		return;
	}
	//������������Ϊtrue
	boolean[][] map=this.dto.getGameMap();
	Point[] act=this.dto.getGameAct().getActPoints();

	for(int i=0;i<act.length;i++)
	{
		map[act[i].x][act[i].y]=true;
	}
	//��ȡ����ֵ 
    int exp= this.plusLExp();
    this.plusPoint(exp);
	//��ֲ���

	//�ж��Ƿ�����
	//����
	//������һ������
	this.dto.getGameAct().init(this.dto.getNext());
	//�����������һ������
	this.dto.setNext(random.nextInt(MAX_TYPE));
	//�����Ϸ�Ƿ�ʧ��
	if(this.isLose()){
		//��Ϸʧ�ܺ�Ĵ���
		this.dto.setStart(false);
		  //�ر���Ϸ���߳�
		}
	}
}
}
