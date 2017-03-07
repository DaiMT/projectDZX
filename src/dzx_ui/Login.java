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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener, FocusListener {

	private JLabel jl_name;
	private JLabel jl_password;
 	private JTextField jtf_name;
    private JPasswordField jtf_passord;
    private JButton jbu_forget;
    private JButton jbu_login;
    private JButton jbu_new;
    
    public Login(){
        // ----------窗口属性的设置----------
//    	FlowLayout layout = new FlowLayout();
        setLayout(null);
        setTitle("欢迎登陆");// 窗口标题
        setSize(400, 230);// 窗口大小
        setBackground(Color.gray);//设置背景颜色
        setLocationRelativeTo(null);// 窗口居于屏幕中央
        setDefaultCloseOperation(EXIT_ON_CLOSE);// 点击关闭窗口后退出jvm虚拟机
        getContentPane().setLayout(new BorderLayout(10, 10));// 边界布局,水平间距10,垂直间距10
        setResizable(false);
        
        // 主要面板的初始化
        initPanel();
    }
	
	/**
	 * 初始化方法
	 */
	
	private void initPanel() {
		// TODO Auto-generated method stub
		
		JPanel jp = new JPanel();
        jp.setLayout(null);
		
		jl_name = new JLabel("帐号 ： ");
        jl_name.setBounds(50, 30, 50, 25);
        
        jtf_name = new JTextField(30);
        jtf_name.setBounds(110, 30, 170, 25);
        jtf_name.addFocusListener(this);// 添加焦点响应
        
        jl_password=new JLabel("密码 ： ");
        jl_password.setBounds(50, 80, 50, 25);
        
        jtf_passord = new JPasswordField(30);
        jtf_passord.setBounds(110,80, 170, 25);
        jtf_passord.addFocusListener(this);
        
        jbu_forget = new JButton("忘记密码？");
        jbu_forget.setBounds(280, 80, 100, 25);
        jbu_forget.setBorderPainted(false);//设置边框透明
        jbu_forget.setContentAreaFilled(false);//设置背景透明
        jbu_forget.addActionListener(this);//添加动作响应 	忘记密码按钮
        jbu_forget.addFocusListener(this);//添加焦点响应		忘记密码按钮
        
        jbu_login = new JButton("登录");
        jbu_login.setBounds(80, 140, 80, 25);
        jbu_login.addActionListener(this);// 添加动作响应     登陆按钮
        jbu_login.addFocusListener(this);// 添加焦点响应        登陆按钮
        
        jbu_new = new JButton("注册");
        jbu_new.setBounds(220, 140, 80, 25);
        jbu_new.addActionListener(this);// 添加动作响应	注册按钮
        jbu_new.addFocusListener(this);// 添加焦点响应	注册按钮

        jp.add(jl_name);
        jp.add(jtf_name);
        jp.add(jl_password);
        jp.add(jtf_passord);
        jp.add(jbu_forget);
        jp.add(jbu_login);
        jp.add(jbu_new);
        
        getContentPane().add(jp, BorderLayout.CENTER);
	}



	/**
	 * 获得焦点
	 */
	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 失焦处理
	 */
	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 动作响应处理
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

        String cmd = e.getActionCommand();//different commands 

        /*
         * when the command is users pressing the login button
         */
        if (cmd.equals("登录")) {
        	
        	String name = jtf_name.getText().trim();
            String password = jtf_passord.getText().trim();
            Connection con = null;
            try{
            	Class.forName("com.mysql.jdbc.Driver");
            	String url = "jdbc:mysql://localhost:3306/mybase?"
        				+"user=root&password=root&useUnicode=true&characterEncoding=UTF8";
            	con = DriverManager.getConnection(url);
            	Statement stmt = con.createStatement();
    			String sql = "select * from User";
    			ResultSet rs = stmt.executeQuery(sql);
    			String userName;
    			String userPassword;
    			int role;
    			while(rs.next()){
    				userName = rs.getString(2);
    				userPassword = rs.getString(3);
					role = rs.getInt(4);
    				if(rs.isLast()){
    					if(name.equals(userName)){
	    					if(password.equals(userPassword)){
	    						if(role==1){//Student
//	    							new HomePage_administrator().setVisible(true);
	    							dispose();
	    							break;
	    						}else{//Teacher
//	    							new HomePage_user().setVisible(true);
	    							dispose();
	    							break;
	    						}
	    						
	    					}else{
	    						JOptionPane.showMessageDialog(this, "密码错误！请重新输入！");
	    					}
	    				}else{
	    					JOptionPane.showMessageDialog(this, "该用户不存在！是否要注册？");
	    				}
    				}else{
    					if(name.equals(userName)){
	    					if(password.equals(userPassword)){
	    						if(role==1){
//	    							new HomePage_administrator().setVisible(true);
	    							dispose();
	    							break;
	    						}else{
//	    							new HomePage_user().setVisible(true);
	    							dispose();
	    							break;
	    						}
	    					}else{
	    						JOptionPane.showMessageDialog(this, "密码错误！请重新输入！");
	    						break;
	    					}
	    				}
    				}	
    			}
            }catch(SQLException e1){
    			e1.printStackTrace();
//    			System.out.println("MySQL操作错误!");
    		}catch(Exception e2){
    			e2.printStackTrace();
//    			System.out.println("发生错误！");
    		}finally {
    			try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    		}
            
        }
        
        /*
         * when the commmand is user pressing the register button
         */
        if(cmd.equals("注册")){
        	new Register().setVisible(true);
        }
        
        /*
         * when the command is user pressing the forgetPassword button
         */
        if(cmd.equals("忘记密码？")){
        	JOptionPane.showMessageDialog(this, "Are you forget your password ?");
        }
	}

}
