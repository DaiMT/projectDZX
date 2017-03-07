package dzx_ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Register extends JFrame implements ActionListener, FocusListener{

	private JLabel jl_name;
	private JLabel jl_password;
	private JLabel jl_role;
	private JLabel jl_num;
	private JTextField jtf_name;
	private JPasswordField jtf_password;
	private JTextField jtf_num;
	private JComboBox<String> jcb_role;
	private JButton jbu_yes;
	private JButton jbu_no;
	
	public Register() {
		// TODO Auto-generated constructor stub
		setLayout(null);
        setTitle("ע��");// ���ڱ���
        setSize(320, 350);// ���ڴ�С
        setBackground(Color.gray);//���ñ�����ɫ
        setLocationRelativeTo(null);// ���ھ�����Ļ����
        setDefaultCloseOperation(EXIT_ON_CLOSE);// ����رմ��ں��˳�jvm�����
        getContentPane().setLayout(new BorderLayout(10, 10));// �߽粼��,ˮƽ���10,��ֱ���10
        setResizable(false);
        
        // ��Ҫ���ĳ�ʼ��
        initPanel();
	}

	
	private void initPanel() {
		// TODO Auto-generated method stub
		JPanel jp = new JPanel();
        jp.setLayout(null);
        
        jl_name = new JLabel("��     �� �� ");
        jl_name.setBounds(30, 40, 100, 25);
        
        jtf_name = new JTextField(30);
        jtf_name.setBounds(110, 40, 170, 25);
        jtf_name.addFocusListener(this);// ��ӽ�����Ӧ
        
        jl_num = new JLabel("��/ѧ�� �� ");
        jl_num.setBounds(30, 75, 100, 25);
        
        jtf_num = new JTextField(30);
        jtf_num.setBounds(110, 75, 170, 25);
        jtf_num.addFocusListener(this);// ��ӽ�����Ӧ
        
        jl_role = new JLabel("��     �� �� ");
        jl_role.setBounds(30, 110, 100, 25);
        
        jcb_role = new JComboBox<String>();
        jcb_role.addItem("  ѧ    ��");
        jcb_role.addItem("  ��    ʦ");
        jcb_role.setBounds(110, 110, 170, 25);
		//��������������ֶ�����
        jcb_role.setEditable(true);
        jcb_role.addFocusListener(this);// ��ӽ�����Ӧ
        
        jl_password = new JLabel("��     �� �� ");
        jl_password.setBounds(30, 145, 100, 25);
        
        jtf_password = new JPasswordField(30);
        jtf_password.setBounds(110, 145, 170, 25);
        jtf_password.addFocusListener(this);// ��ӽ�����Ӧ
        
        jbu_yes = new JButton("ȷ��");
        jbu_yes.setBounds(50,250,80,25);
        jbu_yes.addActionListener(this);// ��Ӷ�����Ӧ     ��½��ť
        jbu_yes.addFocusListener(this);// ��ӽ�����Ӧ        ��½��ť
        
        jbu_no = new JButton("ȡ��");
        jbu_no.setBounds(190,250,80,25);
        jbu_no.addActionListener(this);// ��Ӷ�����Ӧ	ע�ᰴť
        jbu_no.addFocusListener(this);// ��ӽ�����Ӧ	ע�ᰴť
        
        
        jp.add(jl_name);
        jp.add(jtf_name);
        jp.add(jtf_num);
        jp.add(jl_num);
        jp.add(jl_role);
        jp.add(jcb_role);
        jp.add(jl_password);
        jp.add(jtf_password);
        jp.add(jbu_yes);
        jp.add(jbu_no);
        
		jp.setVisible(true);
		getContentPane().add(jp, BorderLayout.CENTER);
	}



	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();//different commands 
		
		if(cmd.equals("ȷ��")){
			String name = jtf_name.getText().trim();
			String role = (String) jcb_role.getSelectedItem();
            String num = jtf_num.getText().trim();
            String password = jtf_password.getText().trim();
            Connection con = null;
            try{
            	Class.forName("com.mysql.jdbc.Driver");
            	String url = "jdbc:mysql://localhost:3306/mybase?"
        				+"user=root&password=root&useUnicode=true&characterEncoding=UTF8";
            	con = DriverManager.getConnection(url);
            	Statement stmt = con.createStatement();
            	if(role.equals("ѧ��")){
            		String sql = "select * from Student";
        			ResultSet rs = stmt.executeQuery(sql);
        			while(rs.next()){
        				int stuNum = rs.getInt(2);
        				if(rs.isLast()){
        					if(stuNum==Integer.parseInt(num)){
            					String stuName = rs.getString(1);
            					if(stuName.equals(name)){
            						//insert record to User
            						JOptionPane.showMessageDialog(this, "ע��ɹ���");
            						dispose();
            						break;
            					}else{
            						JOptionPane.showMessageDialog(this, "������ѧ�Ų��������ʵ������ע�ᣡ");
            					}
            				}else{
            					JOptionPane.showMessageDialog(this, "��ѧ�������ڣ����ʵ������ע�ᣡ");
            				}
        				}else{
        					if(stuNum==Integer.parseInt(num)){
            					String stuName = rs.getString(1);
            					if(stuName.equals(name)){
            						//insert record to User
            					}else{
            						JOptionPane.showMessageDialog(this, "������ѧ�Ų��������ʵ������ע�ᣡ");
            					}
            				}
        				}
        				
        			}
            	}else{
            		String sql = "select * from Teacher";
        			ResultSet rs = stmt.executeQuery(sql);
        			while(rs.next()){
        				int stuNum = rs.getInt(2);
        				if(rs.isLast()){
        					if(stuNum==Integer.parseInt(num)){
            					String stuName = rs.getString(1);
            					if(stuName.equals(name)){
            						//insert record to User
            						JOptionPane.showMessageDialog(this, "ע��ɹ���");
            					}else{
            						JOptionPane.showMessageDialog(this, "�����빤�Ų��������ʵ������ע�ᣡ");
            					}
            				}else{
            					JOptionPane.showMessageDialog(this, "�ý�ʦ�����ڣ����ʵ������ע�ᣡ");
            				}
        				}else{
        					if(stuNum==Integer.parseInt(num)){
            					String stuName = rs.getString(1);
            					if(stuName.equals(name)){
            						//insert record to User
            						JOptionPane.showMessageDialog(this, "ע��ɹ���");
            						dispose();
            						break;
            					}else{
            						JOptionPane.showMessageDialog(this, "�����빤�Ų��������ʵ������ע�ᣡ");
            					}
            				}
        				}
        				
        			}
            		
            	}
    			
            	
            }catch(SQLException e1){
            	System.out.println("----------SQL!");
            }catch(Exception e2){
            	System.out.println("----------other wrongs!");
            }finally{
            	try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
            	
		}
		
		if(cmd.equals("ȡ��")){
			dispose();
		}
	}

	
}
