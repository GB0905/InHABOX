package GYH_202145024;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PayMent extends JFrame implements ActionListener {

	// DB관련 변수
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	private String Driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://118.46.199.58:3306/movie";

	private Container c;
	private JLabel text;
	private long start = System.currentTimeMillis();
	private int time = 5000;
	private SnackMenu SK;
	private int count = 0;
	private JPanel Receipt;
	private JButton checkBtn;
	private JPanel header;
	private JPanel body;
	private JPanel footer;
	private JPanel Rlist;
	private JButton closeBtn;
	private JPanel listBox;
	ArrayList<Integer> AllPrice = new ArrayList<Integer>();
	ArrayList<String> AllName = new ArrayList<String>();
	private int total = 0;
	private JLabel Rtitle;
	private JLabel RCount;
	private JLabel RPrice;
	private JLabel RTotal;
	private int ImageCount = 0;
	private String R_name;
	private int R_price;
	private int R_count;
	private int top = 50;
	private JLabel CartIcon;
	private PaymentMethod PM;

	public PayMent(SnackMenu sk, PaymentMethod pm) {

		this.SK = sk;
		this.PM = pm;

		setTitle("결제창");
		setSize(800, 400);

		setLocationRelativeTo(sk);

		c = getContentPane();
		c.setLayout(new BorderLayout());

		ShowFrame();

		setVisible(true);
	}

	public void ShowFrame() {
		text = new JLabel();
		Font font1 = new Font("맑은고딕", Font.ITALIC, 20);
		text.setFont(font1);
		text.setHorizontalAlignment(JLabel.CENTER);
		c.add(text);

		Timer timer = new Timer();
		TimerTask task = new TimerTask() {

			private JButton closeBtn;

			@Override
			public void run() {
				count++;
				if (count <= 2) {
					text.setText("결제중...");
				} else if (count >= 2 && count <= 4) {
					text.setText("결제 완료되었습니다.");
				} else if (count == 5) {
					text.setText("");
					changShow();
				} else {
					timer.cancel(); // 타이머 종료
				}

			}
		};

		timer.schedule(task, 1000, 1000);
	}

	public void changShow() {

		setSize(400, 600);
		setLocationRelativeTo(SK);
		setLayout(new BorderLayout());

		header = new JPanel();
		header.setLayout(new BorderLayout());
		header.setPreferredSize(new Dimension(400, 90));
		header.setBackground(new Color(75, 52, 149));

		ImageIcon I_receipt = new ImageIcon("images/snack/receipt.png");
		Image I_receipt_img = I_receipt.getImage();
		Image New_I_receipt_img = I_receipt_img.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newreceiptIcon = new ImageIcon(New_I_receipt_img);

		Font font1 = new Font("맑은고딕", Font.BOLD, 23);
		JLabel title = new JLabel("Cart", newreceiptIcon, SwingConstants.CENTER);
		title.setFont(font1);
		title.setForeground(Color.WHITE);
		header.add(title);

		body = new JPanel();
		body.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

		try {
			String sql = null;
			Class.forName(Driver);
			conn = DriverManager.getConnection(url, "javapj", "1234");
			stmt = conn.createStatement();
			sql = "SELECT * FROM snorder WHERE smcheck=" + SK.getOrderId() + "";
			rs = stmt.executeQuery(sql);
			System.out.println(SK.getOrderId());
			while (rs.next()) {
				DecimalFormat formatter = new DecimalFormat("###,###");
				String name = rs.getString("snName");
				int price = Integer.parseInt(rs.getString("snPrice"));
				int Count = Integer.parseInt(rs.getString("snCount"));

				AllName.add("상품명: " + name + " 개수: " + Count + " 가격: " + formatter.format(price));

				total += price;

				listBox = new JPanel();
				listBox.setLayout(new FlowLayout());
				listBox.setPreferredSize(new Dimension(380, 50));
				listBox.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
				listBox.setBackground(new Color(214, 214, 214));

				Rtitle = new JLabel("상품명: " + name + ",", SwingConstants.CENTER);
				RCount = new JLabel(" 개수: " + Count + ",", SwingConstants.CENTER);
				RPrice = new JLabel("가격: " + formatter.format(price) + "원", SwingConstants.CENTER);

				listBox.add(Rtitle, BorderLayout.WEST);
				listBox.add(RCount, BorderLayout.CENTER);
				listBox.add(RPrice, BorderLayout.EAST);

				body.add(listBox);
			}
			DecimalFormat formatter = new DecimalFormat("###,###");
			RTotal = new JLabel("합계: " + formatter.format(total), SwingConstants.CENTER);
			body.add(RTotal);

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

		footer = new JPanel();
		footer.setPreferredSize(new Dimension(400, 70));

		checkBtn = new JButton("영수증 출력");
		checkBtn.addActionListener(this);
		closeBtn = new JButton("닫기");
		closeBtn.addActionListener(this);

		footer.add(checkBtn);
		footer.add(closeBtn);

		add(header, BorderLayout.NORTH);
		add(body);
		add(footer, BorderLayout.SOUTH);
		revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj == checkBtn) {
			ImageCount++;
			DecimalFormat formatter = new DecimalFormat("###,###");
			if (ImageCount == 1) {

				BufferedImage img = new BufferedImage(400, 300, BufferedImage.TYPE_INT_RGB);
				Graphics2D graphics2d = img.createGraphics(); // Graphics2D를 얻어와 그림을 그린다.

				graphics2d.setColor(Color.WHITE); // 색상지정
				graphics2d.setBackground(Color.WHITE);
				graphics2d.setFont(new Font("맑은 고딕", Font.BOLD, 20));
				for (int i = 0; i < AllName.size(); i++) {
					graphics2d.drawString(AllName.get(i), 30, top);
					top += 20;
				}

				graphics2d.drawString("합계: " + formatter.format(total) + "원", 50, 250);
				graphics2d.drawString("결제방법: " + PM.getPaymentName(), 50, 290);

				try {
					Random random = new Random();
					int num = random.nextInt(100);
					File file = new File(".Receipt/imgtest" + num + ".jpg"); // 파일의 이름을 설정한다.
					ImageIO.write(img, "jpg", file);// write메소드를 이용해 파일을 만든다
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, "영수증 출력이 이미 되었습니다.");
				return;
			}

		} else if (obj == closeBtn) {
			total = 0;
			this.dispose();
		}

	}
}
