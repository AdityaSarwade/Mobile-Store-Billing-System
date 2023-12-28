import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.border.CompoundBorder;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

import javax.swing.border.BevelBorder;

@SuppressWarnings("unused")
public class MainMenuGUI implements ActionListener {
	
	
    JFrame frame;
    
    
    private JTextField textName;
    private JTextField textEmail;
    private JTextField textPhoneno;
    private JComboBox<?> comboBoxMobile;
    
    
    private JLabel statusConnection;
    private JButton btnstartConnection;
    private JButton btncloseConnection;
    
    
    
    private JButton btnSubmit;
    private JButton btnReset;
    
    
    mongoConnection mong = new mongoConnection();

    MainMenuGUI()
    {
    	
        createWindow();
        actionEvent();
        
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public void createWindow()
    {
        frame=new JFrame();
        frame.setResizable(false);
        frame.setTitle("MOBILE STORE BILLING SYSTEM");
        frame.setBounds(50,50,1300,700);
        frame.setSize(1300, 700);
        frame.getContentPane().setBackground(new Color(255, 204, 255));
        frame.getContentPane().setLayout(null);
        
        //connection status label
        statusConnection = new JLabel("Disconnected");
        statusConnection.setHorizontalTextPosition(SwingConstants.CENTER);
        statusConnection.setFont(new Font("Century Gothic", Font.BOLD, 14));
        statusConnection.setHorizontalAlignment(SwingConstants.CENTER);
        statusConnection.setOpaque(true);
        statusConnection.setBackground(Color.red);
        statusConnection.setForeground(Color.black);
        statusConnection.setBounds(1110, 45, 110, 23);
        frame.getContentPane().add(statusConnection);
        
        //connect button
        btnstartConnection = new JButton("Connect");
        btnstartConnection.setBounds(1175, 10, 105, 25);
        frame.getContentPane().add(btnstartConnection);
        
        //disconnect button
        btncloseConnection = new JButton("Disconnect");
        btncloseConnection.setBounds(1060, 10, 105, 25);
        frame.getContentPane().add(btncloseConnection);
        
        //Heading
        JLabel lblHeading = new JLabel("BILLING SYSTEM");
        lblHeading.setFont(new Font("Bahnschrift", Font.PLAIN, 40));
        lblHeading.setHorizontalAlignment(SwingConstants.CENTER);
        lblHeading.setBounds(492, 10, 329, 80);
        frame.getContentPane().add(lblHeading);
        
        //Billing Form labels
        JLabel lblName = new JLabel("Name");
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblName.setHorizontalAlignment(SwingConstants.CENTER);
        lblName.setBounds(45, 126, 117, 33);
        frame.getContentPane().add(lblName);
        
        JLabel lblEmail = new JLabel("Email");
        lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblEmail.setBounds(45, 169, 117, 33);
        frame.getContentPane().add(lblEmail);
        
        JLabel lblPhoneno = new JLabel("Phone no");
        lblPhoneno.setHorizontalAlignment(SwingConstants.CENTER);
        lblPhoneno.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblPhoneno.setBounds(45, 212, 117, 33);
        frame.getContentPane().add(lblPhoneno);
        
        JLabel lblMobile = new JLabel("Mobile");
        lblMobile.setHorizontalAlignment(SwingConstants.CENTER);
        lblMobile.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblMobile.setBounds(45, 253, 117, 33);
        frame.getContentPane().add(lblMobile);
        
        
        //Billing Form Inputs
        textName = new JTextField();
        textName.setBounds(196, 130, 180, 25);
        frame.getContentPane().add(textName);
        textName.setColumns(10);
        
        textEmail = new JTextField();
        textEmail.setColumns(10);
        textEmail.setBounds(196, 173, 180, 25);
        frame.getContentPane().add(textEmail);
        
        textPhoneno = new JTextField();
        textPhoneno.setColumns(10);
        textPhoneno.setBounds(196, 216, 180, 25);
        frame.getContentPane().add(textPhoneno);
        
        comboBoxMobile = new JComboBox();
        comboBoxMobile.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), null, null, null));
        comboBoxMobile.setToolTipText("");
        comboBoxMobile.setFont(new Font("Tahoma", Font.PLAIN, 14));
        comboBoxMobile.setModel(new DefaultComboBoxModel(new String[] {"Realme", "Xiaomi", "Apple"}));
        comboBoxMobile.setSelectedIndex(0);
        comboBoxMobile.setBounds(196, 257, 180, 25);
        frame.getContentPane().add(comboBoxMobile);
        
        
        
        //Submit and Clear Buttons
        btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(133, 356, 85, 21);
        frame.getContentPane().add(btnSubmit);
        
        btnReset = new JButton("Reset");
        btnReset.setBounds(267, 356, 85, 21);
        frame.getContentPane().add(btnReset);
        
        
        //Setting Close Behavior
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    
    
    public void actionEvent()
    {
    	btnstartConnection.addActionListener(this);
    	btncloseConnection.addActionListener(this);
    	btnReset.addActionListener(this);
    	btnSubmit.addActionListener(this);
    }
    
    


    @Override
    public void actionPerformed(ActionEvent e) {
    	
    	
    	
        if(e.getSource()==btnstartConnection)
        {
        	mong.connect();
        	statusConnection.setText("Connected");
        	statusConnection.setBackground(Color.green);
        }
        
        if(e.getSource()==btncloseConnection)
        {
        	mong.close();
        	statusConnection.setText("Disconnected");
        	statusConnection.setBackground(Color.red);
        }
        
        
        if(e.getSource()==btnSubmit)
        {
        	if(mong.isConnected())
        	{
        		Document document = new Document("Name", textName.getText())
                        .append("Email", textEmail.getText())
                        .append("Phone no:", textPhoneno.getText())
                        .append("Mobile", comboBoxMobile.getSelectedItem().toString());
            	
                mong.insert(document);
                
                JOptionPane.showMessageDialog(null, "Entry Successful");
        	}
        	
        	else
        	{
        		JOptionPane.showMessageDialog(null, "Database not connected");
        	}
        }
        
        if(e.getSource()==btnReset)
        {
        	textEmail.setText("");
        	textName.setText("");
        	textPhoneno.setText("");
        	comboBoxMobile.setSelectedIndex(0);
        }
        

    }
	
	
}