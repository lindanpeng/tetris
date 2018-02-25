package ui.window;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class JFrameConfig extends JFrame {
	private JButton jbOK=new JButton("确定");
	private JButton jbCancel=new JButton("取消");
	private JButton jbApply=new JButton("应用");
		public JFrameConfig(){
		   jbOK.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("haha");
			}});
			//设置布局管理器		
			this.setLayout(new BorderLayout());
			//添加主面板
			this.add(this.createMainPanel(), BorderLayout.CENTER);
			//添加按钮面板
			this.add(this.createButtonPanel(),BorderLayout.SOUTH);
			this.setSize(512,256);
			//TODO 测试用
			this.setVisible(true);
			this.setDefaultCloseOperation(3);
		}

		//创建选项卡面板
		private JTabbedPane createMainPanel() {
			// TODO Auto-generated method stub
		JTabbedPane jtp=new JTabbedPane();
		jtp.addTab("控制设置",new JLabel("控制"));
		jtp.add("皮肤设置", new JLabel("皮肤"));
			return jtp;
		}
     
		private JPanel createButtonPanel() {
			// 创建流式布局
			JPanel jp=new JPanel(new FlowLayout(FlowLayout.RIGHT));
			jp.add(jbOK);
			jp.add(jbCancel);
			jp.add(jbApply);

			
			return jp;
		}
		
}
