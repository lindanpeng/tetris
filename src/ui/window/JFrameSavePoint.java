package ui.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import control.GameControl;
import util.FrameUtil;

public class JFrameSavePoint extends JFrame {
	private JButton btnSave=null;
	private JButton  btnCancel=null;
    private JLabel lbPoint=null;
    private JTextField txName=null;
    private JLabel errMsg=null;
    private GameControl gameControl=null;
		public JFrameSavePoint(GameControl gameControl){
			this.gameControl=gameControl;
			this.setTitle("保存记录");
			this.setSize(256, 128);
			FrameUtil.setFrameCenter(this);
			this.setResizable(false);
			this.setLayout(new BorderLayout());
			this.createCom();
			this.createAction();
			//TODO 测试用
			this.setDefaultCloseOperation(2);
			//this.setVisible(true);
			
			
		}
		//显示窗口
		public void show(int point){
			this.lbPoint.setText("您的得分："+point);
			this.setVisible(true);
		
		}
		//创建事件监听器
		private void createAction() {
			this.btnSave.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO 添加文字验证功能（正则表达式）
					String name=txName.getText();
					
					if(name.length()>12|!name.matches("[^\\s]{1,}")){
						errMsg.setText("名字输入错误！");
					}
					else{
						setVisible(false);
						   gameControl.savePoint(name);
					}
		
				}
				
			});
			this.btnCancel.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
			      setVisible(false);	
				}
				
			});
			
		}
		//初始化控件
		private void createCom(){
			JPanel north=new JPanel(new FlowLayout(FlowLayout.LEFT));
			this.lbPoint=new JLabel();
			//创建错误信息控件
			this.errMsg=new JLabel();
			this.errMsg.setForeground(Color.RED);
			north.add(lbPoint);
			north.add(errMsg);
			JPanel center=new JPanel(new FlowLayout(FlowLayout.LEFT));
			this.txName=new JTextField(14);
			center.add(new JLabel("您的名字："));
			center.add(this.txName);
			this.add(center,BorderLayout.CENTER);
			this.btnSave=new JButton("保存");
			this.btnCancel=new JButton("取消");
			 this.add(north,BorderLayout.NORTH);
			JPanel south=new JPanel(new FlowLayout(FlowLayout.LEFT,30,0));
			
	        south.add(btnSave);
	        south.add(btnCancel);
	        this.add(south,BorderLayout.SOUTH);
	       
		}

}
