package GYH_202145024;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Detail extends JFrame implements ActionListener {

	// DB관련 변수
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	private String Driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://118.46.199.58:3306/movie";

	private Container c;
	private JPanel SubMenu;
	private JLabel imglabel1;
	private JButton menuBtn;
	private JButton addBtn;
	private JButton cancelBtn;
	private JButton plus;
	private JButton minus;
	private JLabel text;
	private int count = 1;
	private String MenuName = "";
	private SnackMenu sm;
	private int price;
	private int check = 1;
	private JLabel menuPrice;

	public Detail(SnackMenu snackMenu, String title) {

		this.sm = snackMenu;

		setTitle("Detail Menu" + title);
		setSize(1100, 800);
		setLocationRelativeTo(null);

		c = getContentPane();
		c.setLayout(new BorderLayout());

		MenuList(title);
		BtnBox();
		CountBox();

		setVisible(true);
	}

	private void MenuList(String menu) {
		JPanel Main = new JPanel();
		Main.setLayout(new GridLayout(0, 5));
		Main.setPreferredSize(new Dimension(1100, 500));
		Main.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		Main.setBackground(new Color(60, 42, 120));

		try {
			String sql = null;
			Class.forName(Driver);
			conn = DriverManager.getConnection(url, "javapj", "1234");
			stmt = conn.createStatement();

			sql = "SELECT DISTINCT snName, snPrice  FROM snack  where snType='" + menu + "'";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String name = rs.getString("snName");
				int price = Integer.parseInt(rs.getString("snPrice"));

				SubMenu = new JPanel();
				SubMenu.setPreferredSize(new Dimension(300, 400));
				// SubMenu.setBackground(Color.WHITE);
				SubMenu.setBackground(new Color(60, 42, 120));

				ImageIcon ic1 = new ImageIcon("images/snack/" + name + ".jpeg");
				Image img1 = ic1.getImage();
				Image newimg1 = img1.getScaledInstance(180, 280, java.awt.Image.SCALE_SMOOTH);
				ImageIcon newic1 = new ImageIcon(newimg1);
				imglabel1 = new JLabel(newic1);

				DecimalFormat formatter = new DecimalFormat("###,###");
				Font font = new Font("맑은 고딕", Font.BOLD, 18);
				menuPrice = new JLabel(formatter.format(price) + "원");
				menuPrice.setFont(font);
				menuPrice.setForeground(Color.WHITE);

				menuBtn = new JButton(name);
				menuBtn.setFont(font);
				menuBtn.setPreferredSize(new Dimension(200, 50));
				menuBtn.setBackground(Color.WHITE);
				menuBtn.addActionListener(this);

				SubMenu.add(imglabel1);
				SubMenu.add(menuPrice);
				SubMenu.add(menuBtn);

				Main.add(SubMenu);

				c.add(Main, BorderLayout.CENTER);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		c.add(Main, BorderLayout.NORTH);
	}

	public void BtnBox() {
		JPanel BtnBox = new JPanel();
		BtnBox.setPreferredSize(new Dimension(1100, 100));
		BtnBox.setBackground(new Color(60, 42, 120));
		addBtn = new JButton("담기");
		addBtn.setPreferredSize(new Dimension(300, 50));
		addBtn.addActionListener(this);
		cancelBtn = new JButton("취소");
		cancelBtn.setPreferredSize(new Dimension(300, 50));
		cancelBtn.addActionListener(this);
		BtnBox.add(addBtn);
		BtnBox.add(cancelBtn);

		c.add(BtnBox, BorderLayout.SOUTH);
	}

	public void CountBox() {
		JPanel Maincount = new JPanel();
		Maincount.setBorder(BorderFactory.createEmptyBorder(70, 0, 0, 0));
		Maincount.setBackground(new Color(60, 42, 120));

		Font font = new Font("맑은고딕", Font.BOLD, 20);

		plus = new JButton("+");
		plus.setFont(font);
		plus.setPreferredSize(new Dimension(100, 50));
		plus.addActionListener(this);

		text = new JLabel(String.valueOf(count));
		text.setForeground(Color.WHITE);
		text.setFont(font);
		text.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));

		minus = new JButton("-");
		minus.setFont(font);
		minus.setPreferredSize(new Dimension(100, 50));
		minus.addActionListener(this);

		Maincount.add(minus);
		Maincount.add(text);
		Maincount.add(plus);

		c.add(Maincount, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == plus) {
			count++;
			text.setText(String.valueOf(count));
		} else if (source == minus) {
			count--;
			text.setText(String.valueOf(count));
			if (count < 1) {
				JOptionPane.showMessageDialog(this, "1개 이상이여야 합니다.", "Message", JOptionPane.OK_OPTION);
				count = 1;
				text.setText(String.valueOf(count));
			}
		} else if (source == addBtn) {
			if (MenuName == "") {
				count = 1;
				text.setText(String.valueOf(count));
				JOptionPane.showMessageDialog(this, "메뉴를 지정하지 않았습니다.", "오류!!!", JOptionPane.OK_OPTION);
			} else {
				int open = JOptionPane.showConfirmDialog(this, "메뉴:" + MenuName + "\n" + "개수: " + count + "\n", "메뉴확인",
						JOptionPane.YES_NO_OPTION);

				if (open == 1) {
					MenuName = "";
					count = 1;
					text.setText(String.valueOf(count));
				} else {
					try {
						String sql = null;
						Class.forName(Driver);
						conn = DriverManager.getConnection(url, "javapj", "1234");
						stmt = conn.createStatement();

						sql = "SELECT snPrice  FROM snack  where snName='" + MenuName + "'";
						rs = stmt.executeQuery(sql);

						while (rs.next()) {
							price = Integer.valueOf(rs.getString("snPrice"));
						}

					} catch (Exception e1) {
						e1.printStackTrace();
					} finally {
						try {
							rs.close();
							stmt.close();
							conn.close();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}

					JOptionPane.showMessageDialog(this, "메뉴를 추가하였습니다.", "완료!", JOptionPane.OK_OPTION);

					int row = sm.getStmodel().getRowCount(); // row 값
					int col = sm.getStmodel().getColumnCount(); // col 값

					for (int i = 0; i < row; i++) {
						String vu = sm.getStmodel().getValueAt(i, 0).toString();
						if (vu.equals(MenuName)) {
							String a = sm.getStmodel().getValueAt(i, 1).toString();
							int b = Integer.parseInt(a);
							sm.getStmodel().removeRow(i);
							sm.getStmodel().addRow(new Object[] { MenuName, b + count, price * (b + count) });
							check = 0;
							break;
						}
						check = 1;
					}
					if (check == 1) {
						sm.getStmodel().addRow(new Object[] { MenuName, count, price * count });
					}

					this.dispose();
				}
			}
		} else if (source == cancelBtn) {
			this.dispose();
		}

		switch (e.getActionCommand()) {
		case "일반팝콘":
			MenuName = e.getActionCommand();
			break;
		case "담기":
			break;
		case "+":
			break;
		case "-":
			break;

		default:
			MenuName = e.getActionCommand();
			break;
		}
	}

}
