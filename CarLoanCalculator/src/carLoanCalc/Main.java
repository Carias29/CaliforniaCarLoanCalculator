package carLoanCalc;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main {
	public static void main(String[] args) {
		frame();
	}
	
	public static void frame() {
		JFrame frame = new JFrame("California Car Loan Calculator");
		frame.add(mainPanel());
		frame.setSize(500,1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
	public static JPanel mainPanel() {
		JPanel main = new JPanel(new BorderLayout());
		main.add(centerPanel(), BorderLayout.CENTER);
		return main;
	}
	public static JPanel centerPanel() {
		JPanel panel = new JPanel(new GridLayout(11,1,5,5));
		JPanel carPrice =  LabelAndTextHelper("Car Price $", 10);
		JPanel loanTerm =  LabelAndTextHelper("Loan Term (months)", 10);
		JPanel interest =  LabelAndTextHelper("Annual Interest %", 10);
		JPanel downPayment =  LabelAndTextHelper("Down Payment $", 10);
		JPanel salesTax =  LabelAndTextHelper("Sales Tax %", 10);
		List<String> list = new ArrayList<>();
		list.add("Weekly");
		list.add("Bi-Weekly");
		list.add("Monthly");
		list.add("Quarterly");
		list.add("Yearly");
		
		JPanel frequency = LabelAndCBHelper("Frequency", list);
		JPanel tradeIn = LabelAndTextHelper("Trade-in Value $",10);
		JPanel amountOwed = LabelAndTextHelper("Amount Owed on Trade-in $",10);
		JPanel titleAndRegistration = LabelAndTextHelper("Title/Registration fees $",10);
		JPanel otherFees = LabelAndTextHelper("Other fees $",10);
		
		panel.add(carPrice);
		panel.add(loanTerm);
		panel.add(interest);
		panel.add(downPayment);
		panel.add(salesTax);
		panel.add(frequency);
		panel.add(tradeIn);
		panel.add(amountOwed);
		panel.add(titleAndRegistration);
		panel.add(otherFees);
		
	
		
		List<JPanel> requiredPanelsList = new ArrayList<>();
		requiredPanelsList.add(carPrice);
		requiredPanelsList.add(interest);
		requiredPanelsList.add(downPayment);
		requiredPanelsList.add(salesTax);
		requiredPanelsList.add(titleAndRegistration);
		
		
		
		
		JButton submit = new JButton("Submit");
		JPanel submissionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,5));
		submissionPanel.add(submit);
		
		panel.add(submissionPanel);
		
		JLabel error = new JLabel("");
		error.setForeground(java.awt.Color.RED);
		submit.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				int n = requiredPanelsList.size();
				String s = "";
				for(int i = 0; i < n; i++) {
					s = retrieveText(requiredPanelsList.get(i),1);
					if(s.isEmpty()) {
						error.setText("Please fill in the required fields");
					}
					else {
						error.setText("");
					}
				}
				if (error.getText().equals("")) {
					String cPrice = retrieveText(carPrice,1);
					String lTerm = retrieveText(loanTerm, 1);
					String annualInt = retrieveText(interest,1);
					String dp = retrieveText(downPayment, 1);
					String salesTx = retrieveText(salesTax, 1);
					String freq = retrieveCBText(frequency,1);
					String tradeVal = retrieveText(tradeIn, 1);
					String amOwed = retrieveText(amountOwed,1);
					String titleReg = retrieveText(titleAndRegistration,1);
					String oFees = retrieveText(otherFees,1);
					
					Loan loan = new Loan(cPrice, lTerm, annualInt, dp, salesTx, freq, tradeVal, amOwed, titleReg, oFees);
					
					JFrame submitFrame = resultFrame(loan);
					submitFrame.setVisible(true);
				}
				
			}
			
		});
		panel.add(error);
		return panel;
	}
	
	public static JFrame resultFrame(Loan loan) {
		JLabel payment = new JLabel();
		String freq = loan.getFrequency();
		DecimalFormat df = new DecimalFormat("#.00");
		if(freq.equals("Weekly")) {
			payment.setText("Weekly Payment: $" + df.format(loan.getWeeklyPayment()));
		}
		else if(freq.equals("Bi-Weekly")) {
			payment.setText("Bi-Weekly Payment: $" + df.format(loan.getBiWeeklyPayment()));
		}
		else if(freq.equals("Monthly")) {
			payment.setText("Monthly Payment: $" + df.format(loan.getMonthlyPayment()));
		}
		else if(freq.equals("Quarterly")) {
			payment.setText("Quarterly Payment: $" + df.format(loan.getQuarterlyPayment()));
		}
		else {
			payment.setText("Yearly: $" + df.format(loan.getYearlyPayment()));
		}
		
		JLabel totTax = new JLabel("Total Tax Cost: $" + df.format(loan.totalTaxCost()));
		JLabel loanAmount = new JLabel("Amount Loaned: $" + df.format(loan.LoanAmount()));
		
		JPanel subPanel = new JPanel(new GridLayout(3,1));
		subPanel.add(payment);
		subPanel.add(totTax);
		subPanel.add(loanAmount);
		
		JFrame subFrame = new JFrame();
		subFrame.add(subPanel);
		subFrame.setSize(300,300);
		subFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//subFrame.setVisible(true);
		
		return subFrame;
	}
	
	public static JPanel LabelAndTextHelper(String text, int width) {
		JPanel row = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		JLabel itemLabel = new JLabel(text);
		JTextField itemText = new JTextField(width);
		row.add(itemLabel);
		row.add(itemText);
		return row;
	}
	public static <T> JPanel LabelAndCBHelper(String text, List<T> options) {
		JPanel row = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		JLabel itemLabel = new JLabel(text);
		JComboBox<T> cb = new JComboBox<>(new Vector<>(options));
		row.add(itemLabel);
		row.add(cb);
		return row;
		
	}
	
	public static String retrieveText(JPanel panel, int index) {
		JTextField textField = (JTextField) panel.getComponent(index);
		String text = textField.getText();
		return text;
	}
	
	@SuppressWarnings("rawtypes")
	public static String retrieveCBText(JPanel panel, int index) {
		JComboBox cb = (JComboBox) panel.getComponent(index);
		String text = (String) cb.getSelectedItem();
		return text;
	}
	
	
}
