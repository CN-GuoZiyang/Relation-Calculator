import java.awt.Desktop;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.Hashtable;
import java.awt.event.ActionEvent;

public class Calculator extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField record;
	private JTextField result;
	private int times = 0;
	private Hashtable<String, String> relationTable = new Hashtable<String, String>();
	private JButton husband;
	private JButton wife;
	private JButton father;
	private JButton mother;
	private JButton old_brother;
	private JButton young_brother;
	private JButton old_sister;
	private JButton young_sister;
	private JButton son;
	private JButton daughter;
	private JButton clear;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calculator frame = new Calculator();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Calculator() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 353, 424);
		setTitle("亲属关系计算器 by Guo Ziyang");
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 359, 22);
		contentPane.add(menuBar);
		
		JMenu helpMenu = new JMenu("帮助");
		menuBar.add(helpMenu);
		JMenuItem aboutItem = new JMenuItem("关于");
		aboutItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new About();
			}
		});
		helpMenu.add(aboutItem);
		
		BtnListener btnListener = new BtnListener();
		
		record = new JTextField();
		record.setHorizontalAlignment(SwingConstants.RIGHT);
		record.setText("我");
		record.setEditable(false);
		record.setBounds(6, 35, 338, 52);
		contentPane.add(record);
		record.setColumns(10);
		
		result = new JTextField();
		result.setFont(new Font("Lucida Grande", Font.BOLD, 30));
		result.setHorizontalAlignment(SwingConstants.RIGHT);
		result.setText("自己");
		result.setEditable(false);
		result.setColumns(10);
		result.setBounds(6, 83, 338, 52);
		contentPane.add(result);
		
		husband = new JButton("夫");
		husband.setFont(new Font("Lucida Grande", Font.BOLD, 40));
		husband.setBounds(8, 147, 75, 75);
		contentPane.add(husband);
		
		wife = new JButton("妻");
		wife.setFont(new Font("Lucida Grande", Font.BOLD, 40));
		wife.setBounds(95, 147, 75, 75);
		contentPane.add(wife);
		
		father = new JButton("父");
		father.setFont(new Font("Lucida Grande", Font.BOLD, 40));
		father.setBounds(8, 234, 75, 75);
		contentPane.add(father);
		
		mother = new JButton("母");
		mother.setFont(new Font("Lucida Grande", Font.BOLD, 40));
		mother.setBounds(95, 234, 75, 75);
		contentPane.add(mother);
		
		old_brother = new JButton("兄");
		old_brother.setFont(new Font("Lucida Grande", Font.BOLD, 40));
		old_brother.setBounds(182, 234, 75, 75);
		contentPane.add(old_brother);
		
		young_brother = new JButton("弟");
		young_brother.setFont(new Font("Lucida Grande", Font.BOLD, 40));
		young_brother.setBounds(269, 234, 75, 75);
		contentPane.add(young_brother);
		
		old_sister = new JButton("姐");
		old_sister.setFont(new Font("Lucida Grande", Font.BOLD, 40));
		old_sister.setBounds(8, 321, 75, 75);
		contentPane.add(old_sister);
		
		young_sister = new JButton("妹");
		young_sister.setFont(new Font("Lucida Grande", Font.BOLD, 40));
		young_sister.setBounds(95, 321, 75, 75);
		contentPane.add(young_sister);
		
		son = new JButton("子");
		son.setFont(new Font("Lucida Grande", Font.BOLD, 40));
		son.setBounds(182, 321, 75, 75);
		contentPane.add(son);
		
		daughter = new JButton("女");
		daughter.setFont(new Font("Lucida Grande", Font.BOLD, 40));
		daughter.setBounds(269, 321, 75, 75);
		contentPane.add(daughter);
		
		clear = new JButton("AC");
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				record.setText("我");
				result.setText("自己");
				times = 0;
				husband.setEnabled(true);
				wife.setEnabled(true);
				father.setEnabled(true);
				mother.setEnabled(true);
				old_brother.setEnabled(true);
				young_brother.setEnabled(true);
				old_sister.setEnabled(true);
				young_sister.setEnabled(true);
				son.setEnabled(true);
				daughter.setEnabled(true);
			}
		});
		clear.setFont(new Font("Lucida Grande", Font.BOLD, 40));
		clear.setBounds(269, 147, 75, 75);
		contentPane.add(clear);
		
		husband.addActionListener(btnListener);
		wife.addActionListener(btnListener);
		father.addActionListener(btnListener);
		mother.addActionListener(btnListener);
		old_brother.addActionListener(btnListener);
		young_brother.addActionListener(btnListener);
		old_sister.addActionListener(btnListener);
		young_sister.addActionListener(btnListener);
		son.addActionListener(btnListener);
		daughter.addActionListener(btnListener);
		
		initData();
	}
	
	public void initData() {
		BufferedReader bfReader = null;
		try {
			bfReader = new BufferedReader(new InputStreamReader(new FileInputStream("relations.txt"), "UTF-8"));
		}catch (FileNotFoundException e) {  
            JOptionPane.showMessageDialog(null, "数据文件不存在！");  
        } catch (UnsupportedEncodingException e) {  
              
        }
		
		String tempString;
		try {
			tempString = bfReader.readLine();
			while(tempString!=null) {
				relationTable.put(SplitString.split(tempString, "[", "]"), SplitString.split(tempString, "{", "}"));
				//System.out.print(relationTable.size());
				tempString = bfReader.readLine();
			}
			//System.out.println("数据载入完成，共载入" + relationTable.size() + "条数据。");
		}catch (IOException e) {
			e.printStackTrace();
		}  
	}
	
	class About extends JFrame{
		private static final long serialVersionUID = 1L;
		public About() {
			setTitle("关于");
			setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			setSize(300, 230);
			setLocationRelativeTo(null);
			
			JPanel aboutPane = new JPanel();
			aboutPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(aboutPane);
			aboutPane.setLayout(null);
			
			JLabel aboutlabel = new JLabel();
			String aboutstr = "<html><body><center>一个简单的亲属关系计算器。<br>可以计算三代以内的亲属称呼<br>欢迎贡献你的代码帮助我提升<br>如果你觉得我做的好<br>请打开我的GitHub给我一个star<br><br>My Gmail：guoziyang0033@gmail.com<br>My Github：<br>https://github.com/CN-GuoZiyang</center></body></html>";
			aboutlabel.setText(aboutstr);
			aboutlabel.setBounds(30, 8, 310, 160);
			aboutPane.add(aboutlabel);
			
			JButton opengithub = new JButton("打开github");
			opengithub.setBounds(10, 170, 110, 29);
			opengithub.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Desktop desktop = Desktop.getDesktop();  
					try {
						desktop.browse(new URI("https://github.com/CN-GuoZiyang"));
					}catch(Exception e1) {
						System.out.print(e1);
					}
				}
			});
			aboutPane.add(opengithub);
			
			JButton yes = new JButton("确定");
			yes.setBounds(175, 170, 110, 29);
			yes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
			});
			aboutPane.add(yes);
			
			setVisible(true);
		}
	}
	
	class BtnListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			times++;
			if(times > 3) {
				result.setText("关系太远啦");
				husband.setEnabled(false);
				wife.setEnabled(false);
				father.setEnabled(false);
				mother.setEnabled(false);
				old_brother.setEnabled(false);
				young_brother.setEnabled(false);
				old_sister.setEnabled(false);
				young_sister.setEnabled(false);
				son.setEnabled(false);
				daughter.setEnabled(false);
			}else {
				String objName = ((JButton)e.getSource()).getText();
				String tempRes = "";
				switch(objName) {
				case "夫":
					record.setText(record.getText() + "的丈夫");
					tempRes = searchData(result.getText(), 0);
					if(tempRes.equals("同性")){
						result.setText("暂不支持同性婚姻");
						husband.setEnabled(false);
					wife.setEnabled(false);
					father.setEnabled(false);
					mother.setEnabled(false);
					old_brother.setEnabled(false);
					young_brother.setEnabled(false);
					old_sister.setEnabled(false);
					young_sister.setEnabled(false);
					son.setEnabled(false);
					daughter.setEnabled(false);
					}else{
						result.setText(tempRes);
					}
					break;
				case "妻":record.setText(record.getText() + "的妻子");
					tempRes = searchData(result.getText(), 1);
					if(tempRes.equals("同性")){
						result.setText("暂不支持同性婚姻");
						husband.setEnabled(false);
					wife.setEnabled(false);
					father.setEnabled(false);
					mother.setEnabled(false);
					old_brother.setEnabled(false);
					young_brother.setEnabled(false);
					old_sister.setEnabled(false);
					young_sister.setEnabled(false);
					son.setEnabled(false);
					daughter.setEnabled(false);
					}else{
						result.setText(tempRes);
					}
					break;
				case "父":record.setText(record.getText() + "的爸爸");
					tempRes = searchData(result.getText(), 2);
					if(tempRes.equals("同性")){
						result.setText("暂不支持同性婚姻");
					husband.setEnabled(false);
					wife.setEnabled(false);
					father.setEnabled(false);
					mother.setEnabled(false);
					old_brother.setEnabled(false);
					young_brother.setEnabled(false);
					old_sister.setEnabled(false);
					young_sister.setEnabled(false);
					son.setEnabled(false);
					daughter.setEnabled(false);
					}else{
						result.setText(tempRes);
					}
					break;
				case "母":record.setText(record.getText() + "的妈妈");
					tempRes = searchData(result.getText(), 3);
					if(tempRes.equals("同性")){
						result.setText("暂不支持同性婚姻");
						husband.setEnabled(false);
					wife.setEnabled(false);
					father.setEnabled(false);
					mother.setEnabled(false);
					old_brother.setEnabled(false);
					young_brother.setEnabled(false);
					old_sister.setEnabled(false);
					young_sister.setEnabled(false);
					son.setEnabled(false);
					daughter.setEnabled(false);
					}else{
						result.setText(tempRes);
					}
					break;
				case "兄":record.setText(record.getText() + "的哥哥");
					tempRes = searchData(result.getText(), 4);
					if(tempRes.equals("同性")){
						result.setText("暂不支持同性婚姻");
						husband.setEnabled(false);
					wife.setEnabled(false);
					father.setEnabled(false);
					mother.setEnabled(false);
					old_brother.setEnabled(false);
					young_brother.setEnabled(false);
					old_sister.setEnabled(false);
					young_sister.setEnabled(false);
					son.setEnabled(false);
					daughter.setEnabled(false);
					}else{
						result.setText(tempRes);
					}
					break;
				case "弟":record.setText(record.getText() + "的弟弟");
					tempRes = searchData(result.getText(), 5);
					if(tempRes.equals("同性")){
						result.setText("暂不支持同性婚姻");
						husband.setEnabled(false);
					wife.setEnabled(false);
					father.setEnabled(false);
					mother.setEnabled(false);
					old_brother.setEnabled(false);
					young_brother.setEnabled(false);
					old_sister.setEnabled(false);
					young_sister.setEnabled(false);
					son.setEnabled(false);
					daughter.setEnabled(false);
					}else{
						result.setText(tempRes);
					}
					break;
				case "姐":record.setText(record.getText() + "的姐姐");
					tempRes = searchData(result.getText(), 6);
					if(tempRes.equals("同性")){
						result.setText("暂不支持同性婚姻");
						husband.setEnabled(false);
					wife.setEnabled(false);
					father.setEnabled(false);
					mother.setEnabled(false);
					old_brother.setEnabled(false);
					young_brother.setEnabled(false);
					old_sister.setEnabled(false);
					young_sister.setEnabled(false);
					son.setEnabled(false);
					daughter.setEnabled(false);
					}else{
						result.setText(tempRes);
					}
					break;
				case "妹":record.setText(record.getText() + "的妹妹");
					tempRes = searchData(result.getText(), 7);
					if(tempRes.equals("同性")){
						result.setText("暂不支持同性婚姻");
						husband.setEnabled(false);
					wife.setEnabled(false);
					father.setEnabled(false);
					mother.setEnabled(false);
					old_brother.setEnabled(false);
					young_brother.setEnabled(false);
					old_sister.setEnabled(false);
					young_sister.setEnabled(false);
					son.setEnabled(false);
					daughter.setEnabled(false);
					}else{
						result.setText(tempRes);
					}
					break;
				case "子":record.setText(record.getText() + "的儿子");
					tempRes = searchData(result.getText(), 8);
					if(tempRes.equals("同性")){
						result.setText("暂不支持同性婚姻");
						husband.setEnabled(false);
					wife.setEnabled(false);
					father.setEnabled(false);
					mother.setEnabled(false);
					old_brother.setEnabled(false);
					young_brother.setEnabled(false);
					old_sister.setEnabled(false);
					young_sister.setEnabled(false);
					son.setEnabled(false);
					daughter.setEnabled(false);
					}else{
						result.setText(tempRes);
					}
					break;
				case "女":record.setText(record.getText() + "的女儿");
					tempRes = searchData(result.getText(), 9);
					if(tempRes.equals("同性")){
						result.setText("暂不支持同性婚姻");
						husband.setEnabled(false);
					wife.setEnabled(false);
					father.setEnabled(false);
					mother.setEnabled(false);
					old_brother.setEnabled(false);
					young_brother.setEnabled(false);
					old_sister.setEnabled(false);
					young_sister.setEnabled(false);
					son.setEnabled(false);
					daughter.setEnabled(false);
					}else{
						result.setText(tempRes);
					}
					break;
				}
			}
		}
	}
	
	public String searchData(String results, int index) {
		String[] keys = results.split("/");
		String res = "";
		for(String eachKey:keys) {
			String tempSubString = relationTable.get(eachKey);
			String subString = tempSubString.substring(1, tempSubString.length() - 1);
			//System.out.println(subString);
			String[] subTable = subString.split("[)(]");
			//System.out.println(subTable[index]);
			res += subTable[index * 2].split("-")[1] + "/";
			}
		//System.out.println(res.substring(0, res.length()-1));
		return res.substring(0, res.length()-1);
	}
}

class SplitString{
	public static String split(String raw, String str1, String str2) {
		int x = raw.indexOf(str1);
		int y = raw.indexOf(str2);
		return raw.substring(x+1, y);
	}
}
