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
        // ----------�������Ե�����----------
//    	FlowLayout layout = new FlowLayout();
        setLayout(null);
        setTitle("��ӭ��½");// ���ڱ���
        setSize(400, 230);// ���ڴ�С
        setBackground(Color.gray);//���ñ�����ɫ
        setLocationRelativeTo(null);// ���ھ�����Ļ����
        setDefaultCloseOperation(EXIT_ON_CLOSE);// ����رմ��ں��˳�jvm�����
        getContentPane().setLayout(new BorderLayout(10, 10));// �߽粼��,ˮƽ���10,��ֱ���10
        setResizable(false);
        
        // ��Ҫ���ĳ�ʼ��
        initPanel();
    }
	
	/**
	 * ��ʼ������
	 */
	
	private void initPanel() {
		// TODO Auto-generated method stub
		
		JPanel jp = new JPanel();
        jp.setLayout(null);
		
		jl_name = new JLabel("�ʺ� �� ");
        jl_name.setBounds(50, 30, 50, 25);
        
        jtf_name = new JTextField(30);
        jtf_name.setBounds(110, 30, 170, 25);
        jtf_name.addFocusListener(this);// ��ӽ�����Ӧ
        
        jl_password=new JLabel("���� �� ");
        jl_password.setBounds(50, 80, 50, 25);
        
        jtf_passord = new JPasswordField(30);
        jtf_passord.setBounds(110,80, 170, 25);
        jtf_passord.addFocusListener(this);
        
        jbu_forget = new JButton("�������룿");
        jbu_forget.setBounds(280, 80, 100, 25);
        jbu_forget.setBorderPainted(false);//���ñ߿�͸��
        jbu_forget.setContentAreaFilled(false);//���ñ���͸��
        jbu_forget.addActionListener(this);//��Ӷ�����Ӧ 	�������밴ť
        jbu_forget.addFocusListener(this);//��ӽ�����Ӧ		�������밴ť
        
        jbu_login = new JButton("��¼");
        jbu_login.setBounds(80, 140, 80, 25);
        jbu_login.addActionListener(this);// ��Ӷ�����Ӧ     ��½��ť
        jbu_login.addFocusListener(this);// ��ӽ�����Ӧ        ��½��ť
        
        jbu_new = new JButton("ע��");
        jbu_new.setBounds(220, 140, 80, 25);
        jbu_new.addActionListener(this);// ��Ӷ�����Ӧ	ע�ᰴť
        jbu_new.addFocusListener(this);// ��ӽ�����Ӧ	ע�ᰴť

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
	 * ��ý���
	 */
	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * ʧ������
	 */
	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * ������Ӧ����
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

        String cmd = e.getActionCommand();//different commands 

        /*
         * when the command is users pressing the login button
         */
        if (cmd.equals("��¼")) {
        	
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
	    						JOptionPane.showMessageDialog(this, "����������������룡");
	    					}
	    				}else{
	    					JOptionPane.showMessageDialog(this, "���û������ڣ��Ƿ�Ҫע�᣿");
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
	    						JOptionPane.showMessageDialog(this, "����������������룡");
	    						break;
	    					}
	    				}
    				}	
    			}
            }catch(SQLException e1){
    			e1.printStackTrace();
//    			System.out.println("MySQL��������!");
    		}catch(Exception e2){
    			e2.printStackTrace();
//    			System.out.println("��������");
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
        if(cmd.equals("ע��")){
        	new Register().setVisible(true);
        }
        
        /*
         * when the command is user pressing the forgetPassword button
         */
        if(cmd.equals("�������룿")){
        	JOptionPane.showMessageDialog(this, "Are you forget your password ?");
        }
	}

}
