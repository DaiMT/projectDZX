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
        setTitle("注册");// 窗口标题
        setSize(320, 350);// 窗口大小
        setBackground(Color.gray);//设置背景颜色
        setLocationRelativeTo(null);// 窗口居于屏幕中央
        setDefaultCloseOperation(EXIT_ON_CLOSE);// 点击关闭窗口后退出jvm虚拟机
        getContentPane().setLayout(new BorderLayout(10, 10));// 边界布局,水平间距10,垂直间距10
        setResizable(false);
        
        // 主要面板的初始化
        initPanel();
	}

	
	private void initPanel() {
		// TODO Auto-generated method stub
		JPanel jp = new JPanel();
        jp.setLayout(null);
        
        jl_name = new JLabel("姓     名 ： ");
        jl_name.setBounds(30, 40, 100, 25);
        
        jtf_name = new JTextField(30);
        jtf_name.setBounds(110, 40, 170, 25);
        jtf_name.addFocusListener(this);// 添加焦点响应
        
        jl_num = new JLabel("工/学号 ： ");
        jl_num.setBounds(30, 75, 100, 25);
        
        jtf_num = new JTextField(30);
        jtf_num.setBounds(110, 75, 170, 25);
        jtf_num.addFocusListener(this);// 添加焦点响应
        
        jl_role = new JLabel("类     型 ： ");
        jl_role.setBounds(30, 110, 100, 25);
        
        jcb_role = new JComboBox<String>();
        jcb_role.addItem("  学    生");
        jcb_role.addItem("  教    师");
        jcb_role.setBounds(110, 110, 170, 25);
		//设置下拉框可以手动输入
        jcb_role.setEditable(true);
        jcb_role.addFocusListener(this);// 添加焦点响应
        
        jl_password = new JLabel("密     码 ： ");
        jl_password.setBounds(30, 145, 100, 25);
        
        jtf_password = new JPasswordField(30);
        jtf_password.setBounds(110, 145, 170, 25);
        jtf_password.addFocusListener(this);// 添加焦点响应
        
        jbu_yes = new JButton("确认");
        jbu_yes.setBounds(50,250,80,25);
        jbu_yes.addActionListener(this);// 添加动作响应     登陆按钮
        jbu_yes.addFocusListener(this);// 添加焦点响应        登陆按钮
        
        jbu_no = new JButton("取消");
        jbu_no.setBounds(190,250,80,25);
        jbu_no.addActionListener(this);// 添加动作响应	注册按钮
        jbu_no.addFocusListener(this);// 添加焦点响应	注册按钮
        
        
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
		
		if(cmd.equals("确认")){
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
            	if(role.equals("学生")){
            		String sql = "select * from Student";
        			ResultSet rs = stmt.executeQuery(sql);
        			while(rs.next()){
        				int stuNum = rs.getInt(2);
        				if(rs.isLast()){
        					if(stuNum==Integer.parseInt(num)){
            					String stuName = rs.getString(1);
            					if(stuName.equals(name)){
            						//insert record to User
            						JOptionPane.showMessageDialog(this, "注册成功！");
            						dispose();
            						break;
            					}else{
            						JOptionPane.showMessageDialog(this, "姓名与学号不符，请核实后重新注册！");
            					}
            				}else{
            					JOptionPane.showMessageDialog(this, "该学生不存在！请核实后重新注册！");
            				}
        				}else{
        					if(stuNum==Integer.parseInt(num)){
            					String stuName = rs.getString(1);
            					if(stuName.equals(name)){
            						//insert record to User
            					}else{
            						JOptionPane.showMessageDialog(this, "姓名与学号不符，请核实后重新注册！");
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
            						JOptionPane.showMessageDialog(this, "注册成功！");
            					}else{
            						JOptionPane.showMessageDialog(this, "姓名与工号不符，请核实后重新注册！");
            					}
            				}else{
            					JOptionPane.showMessageDialog(this, "该教师不存在！请核实后重新注册！");
            				}
        				}else{
        					if(stuNum==Integer.parseInt(num)){
            					String stuName = rs.getString(1);
            					if(stuName.equals(name)){
            						//insert record to User
            						JOptionPane.showMessageDialog(this, "注册成功！");
            						dispose();
            						break;
            					}else{
            						JOptionPane.showMessageDialog(this, "姓名与工号不符，请核实后重新注册！");
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
		
		if(cmd.equals("取消")){
			dispose();
		}
	}

	
}
