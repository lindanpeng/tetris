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
	private JButton jbOK=new JButton("ȷ��");
	private JButton jbCancel=new JButton("ȡ��");
	private JButton jbApply=new JButton("Ӧ��");
		public JFrameConfig(){
		   jbOK.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("haha");
			}});
			//���ò��ֹ�����		
			this.setLayout(new BorderLayout());
			//��������
			this.add(this.createMainPanel(), BorderLayout.CENTER);
			//��Ӱ�ť���
			this.add(this.createButtonPanel(),BorderLayout.SOUTH);
			this.setSize(512,256);
			//TODO ������
			this.setVisible(true);
			this.setDefaultCloseOperation(3);
		}

		//����ѡ����
		private JTabbedPane createMainPanel() {
			// TODO Auto-generated method stub
		JTabbedPane jtp=new JTabbedPane();
		jtp.addTab("��������",new JLabel("����"));
		jtp.add("Ƥ������", new JLabel("Ƥ��"));
			return jtp;
		}
     
		private JPanel createButtonPanel() {
			// ������ʽ����
			JPanel jp=new JPanel(new FlowLayout(FlowLayout.RIGHT));
			jp.add(jbOK);
			jp.add(jbCancel);
			jp.add(jbApply);

			
			return jp;
		}
		
}
