package GYH_202145024;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class PaymentMethod extends JFrame implements ActionListener {
	private SnackMenu SK;
	private JButton card;
	private JButton cash;
	private String paymentName = "";
	private PayMent PY;

	public PaymentMethod(SnackMenu sk) {

		SK = sk;

		setTitle("결제 방법");
		setSize(300, 220);
		setLocationRelativeTo(sk);
		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

		ImageIcon I_Card = new ImageIcon("images/snack/card.png");
		Image I_Card_img = I_Card.getImage();
		Image New_I_Card_img = I_Card_img.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newCardIcon = new ImageIcon(New_I_Card_img);

		card = new JButton("카드결제", newCardIcon);
		card.setPreferredSize(new Dimension(250, 80));
		card.addActionListener(this);

		ImageIcon I_Cash = new ImageIcon("images/snack/money.png");
		Image I_Cash_img = I_Cash.getImage();
		Image New_I_Cash_img = I_Cash_img.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newCashIcon = new ImageIcon(New_I_Cash_img);

		cash = new JButton("현금결제", newCashIcon);
		cash.setPreferredSize(new Dimension(250, 80));
		cash.addActionListener(this);

		add(card);
		add(cash);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj == card) {
			paymentName = "카드결제";
			SK.getStmodel().setRowCount(0);
			this.dispose();

			if (PY == null) {
				PY = new PayMent(SK, this);
			} else {
				PY.dispose();
				PY = new PayMent(SK, this);
			}
		} else if (obj == cash) {
			paymentName = "현금결제";
			SK.getStmodel().setRowCount(0);
			this.dispose();
			if (PY == null) {
				PY = new PayMent(SK, this);
			} else {
				PY.dispose();
				PY = new PayMent(SK, this);
			}
		}

	}

	public String getPaymentName() {
		return paymentName;
	}

}
