package Calculator;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Label;

import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.SystemColor;

public class Calculator extends JFrame
{
	double num1, num2, memory;
	String symbol = "";
	String textLb = "";
	String text = "";
	private JPanel contentPane;
	private JTextField output;
	private JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					Calculator frame = new Calculator();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Calculator()
	{
		ActionListener numBtnClick = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (text.equals("0") || text.equals("Infinity"))
					text = "";

				text += ((JButton) e.getSource()).getText();
				output.setText(text);
			}
		};

		ActionListener symbolBtnClick = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (symbol.equals(""))
				{
					if (text.equals("") || text.equals("-")) // case start symbol
						return;
					textLb = text + ((JButton) e.getSource()).getText();
					label.setText(textLb);

					symbol = ((JButton) e.getSource()).getText();

					if (text.equals(".")) // case .
					{
						num1 = 0;
					} else if (text.indexOf('%') != -1) // case %
					{
						num1 = Double.parseDouble(text.substring(0, text.length() - 1));
						num1 *= 0.01;
					} else
					{
						num1 = Double.parseDouble(text);
					}
					text = "";
					output.setText(text);
				} else
				{
					//
					if (text.equals(".")) // case .
					{
						num2 = 0;
					} else if (text.indexOf('%') != -1) // case %
					{
						if (symbol.equals("+") || symbol.equals("-"))
						{
							num2 = Double.parseDouble(text.substring(0, text.length() - 1));
							num2 = (num2 / 100) * num1;
						} else
						{
							num2 = Double.parseDouble(text.substring(0, text.length() - 1));
							num2 /= 100;
						}
					} else
					{
						num2 = Double.parseDouble(text);
					}

					double result = 0;
					if (symbol.equals("+"))
					{
						result = num1 + num2;
					}
					if (symbol.equals("-"))
					{
						result = num1 - num2;
					}
					if (symbol.equals("*"))
					{
						result = num1 * num2;
					}
					if (symbol.equals("/"))
					{
						result = num1 / num2;
					}
					text = "" + result;
					if (result % 1 == 0)
					{
						text = text.substring(0, text.length() - 2);
					}
					
					textLb = text + ((JButton) e.getSource()).getText();
					label.setText(textLb);
					
					text = "";
					output.setText(text);				

					symbol = ((JButton) e.getSource()).getText();
					num1 = result;
					//
				}

			}
		};

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 570);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(198, 10, 178, 13);
		contentPane.add(label);

		output = new JTextField();
		output.setBackground(SystemColor.controlHighlight);
		output.setEditable(false);
		output.setHorizontalAlignment(SwingConstants.RIGHT);
		output.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		output.setBounds(10, 33, 366, 80);
		contentPane.add(output);
		output.setColumns(10);

		// Memory start

		JButton McBtn = new JButton("MC");
		McBtn.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		McBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				memory = 0;
			}
		});
		McBtn.setBounds(10, 134, 84, 55);
		contentPane.add(McBtn);

		JButton MpBtn = new JButton("M+");
		MpBtn.setFont(new Font("Tahoma", Font.PLAIN, 25));
		MpBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (text.equals(".")) // case .
				{
					memory = 0;
				} else if (text.indexOf('%') != -1) // case %
				{
					memory = Double.parseDouble(text.substring(0, text.length() - 1));
					memory *= 0.01;
				} else
				{
					memory += Double.parseDouble(text);
				}

			}
		});
		MpBtn.setBounds(104, 134, 84, 55);
		contentPane.add(MpBtn);

		JButton MrBtn = new JButton("MR");
		MrBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (memory == 0)
					return;

				text = "" + memory;
				if (memory % 1 == 0)
				{
					text = text.substring(0, text.length() - 2);
				}

				output.setText(text);
			}
		});
		MrBtn.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		MrBtn.setBounds(198, 134, 84, 55);
		contentPane.add(MrBtn);

		// Memory end
		// Delete start

		JButton DeleteBtn = new JButton("X");
		DeleteBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (text.equals(""))
					return;
				text = text.substring(0, text.length() - 1);
				output.setText(text);
			}
		});
		DeleteBtn.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		DeleteBtn.setBounds(292, 134, 84, 55);
		contentPane.add(DeleteBtn);

		JButton CBtn = new JButton("C");
		CBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				text = "";
				textLb = "";
				label.setText(textLb);
				output.setText(text);
			}
		});
		CBtn.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		CBtn.setBounds(10, 199, 84, 55);
		contentPane.add(CBtn);

		JButton CeBtn = new JButton("CE");
		CeBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				text = "";
				output.setText(text);
			}
		});
		CeBtn.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		CeBtn.setBounds(104, 199, 84, 55);
		contentPane.add(CeBtn);

		// Delete end
		// +-*/ Start

		JButton PmBtn = new JButton("+/-");
		PmBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (text.equals(""))
					return;

				if (text.charAt(0) == '-')
				{
					text = text.substring(1, text.length());
				} else
				{
					text = "-" + text;
				}
				output.setText(text);
			}
		});
		PmBtn.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		PmBtn.setBounds(198, 199, 84, 55);
		contentPane.add(PmBtn);

		JButton DivBtn = new JButton("/");
		DivBtn.addActionListener(symbolBtnClick);
		DivBtn.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		DivBtn.setBounds(292, 199, 84, 55);
		contentPane.add(DivBtn);

		JButton MulBtn = new JButton("*");
		MulBtn.addActionListener(symbolBtnClick);
		MulBtn.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		MulBtn.setBounds(292, 264, 84, 55);
		contentPane.add(MulBtn);

		JButton MinusBtn = new JButton("-");
		MinusBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (text.equals(""))
				{
					text += ((JButton) e.getSource()).getText();
					output.setText(text);
				} else if (text.equals("-"))
				{
					return;
				} else
				{
					textLb = text + ((JButton) e.getSource()).getText();
					label.setText(textLb);

					symbol = ((JButton) e.getSource()).getText();

					num1 = Double.parseDouble(text);
					text = "";
					output.setText(text);
				}
			}
		});
		MinusBtn.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		MinusBtn.setBounds(292, 329, 84, 55);
		contentPane.add(MinusBtn);

		JButton PlusBtn = new JButton("+");
		PlusBtn.addActionListener(symbolBtnClick);
		PlusBtn.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		PlusBtn.setBounds(292, 394, 84, 55);
		contentPane.add(PlusBtn);

		// +-*/ end
		// 0-9 start

		JButton NineBtn = new JButton("9");
		NineBtn.addActionListener(numBtnClick);
		NineBtn.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		NineBtn.setBounds(198, 264, 84, 55);
		contentPane.add(NineBtn);

		JButton EightBtn = new JButton("8");
		EightBtn.addActionListener(numBtnClick);
		EightBtn.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		EightBtn.setBounds(104, 264, 84, 55);
		contentPane.add(EightBtn);

		JButton SevenBtn = new JButton("7");
		SevenBtn.addActionListener(numBtnClick);
		SevenBtn.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		SevenBtn.setBounds(10, 264, 84, 55);
		contentPane.add(SevenBtn);

		JButton SixBtn = new JButton("6");
		SixBtn.addActionListener(numBtnClick);
		SixBtn.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		SixBtn.setBounds(198, 329, 84, 55);
		contentPane.add(SixBtn);

		JButton FiveBtn = new JButton("5");
		FiveBtn.addActionListener(numBtnClick);
		FiveBtn.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		FiveBtn.setBounds(104, 329, 84, 55);
		contentPane.add(FiveBtn);

		JButton FourBtn = new JButton("4");
		FourBtn.addActionListener(numBtnClick);
		FourBtn.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		FourBtn.setBounds(10, 329, 84, 55);
		contentPane.add(FourBtn);

		JButton ThreeBtn = new JButton("3");
		ThreeBtn.addActionListener(numBtnClick);
		ThreeBtn.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		ThreeBtn.setBounds(198, 394, 84, 55);
		contentPane.add(ThreeBtn);

		JButton TwoBtn = new JButton("2");
		TwoBtn.addActionListener(numBtnClick);
		TwoBtn.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		TwoBtn.setBounds(104, 394, 84, 55);
		contentPane.add(TwoBtn);

		JButton OneBtn = new JButton("1");
		OneBtn.addActionListener(numBtnClick);
		OneBtn.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		OneBtn.setBounds(10, 394, 84, 55);
		contentPane.add(OneBtn);

		JButton ZeroBtn = new JButton("0");
		ZeroBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (text.equals("0"))
				{
					return;
				}
				text += ((JButton) e.getSource()).getText();
				output.setText(text);
			}
		});
		ZeroBtn.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		ZeroBtn.setBounds(104, 459, 84, 55);
		contentPane.add(ZeroBtn);

		// 0-9 end
		// % . = start

		JButton PercentBtn = new JButton("%");
		PercentBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (text.indexOf("%") != -1)
					return;

				text += ((JButton) e.getSource()).getText();
				output.setText(text);
			}
		});
		PercentBtn.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		PercentBtn.setBounds(10, 459, 84, 55);
		contentPane.add(PercentBtn);

		JButton DotBtn = new JButton(".");
		DotBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (text.indexOf(".") != -1)
					return;
				text += ((JButton) e.getSource()).getText();
				output.setText(text);
			}
		});
		DotBtn.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		DotBtn.setBounds(198, 459, 84, 55);
		contentPane.add(DotBtn);

		JButton EqualBtn = new JButton("=");
		EqualBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (text.indexOf('%') != -1)
				{
					double r = Double.parseDouble(text.substring(0, text.length() - 1));
					r *= 0.01;
					text = "" + r;
					output.setText(text);
				}

				if (symbol.equals(""))
					return;

				if (text.equals(".")) // case .
				{
					num2 = 0;
				} else if (text.indexOf('%') != -1) // case %
				{
					if (symbol.equals("+") || symbol.equals("-"))
					{
						num2 = Double.parseDouble(text.substring(0, text.length() - 1));
						num2 = (num2 / 100) * num1;
					} else
					{
						num2 = Double.parseDouble(text.substring(0, text.length() - 1));
						num2 /= 100;
					}
				} else
				{
					num2 = Double.parseDouble(text);
				}

				double result = 0;
				if (symbol.equals("+"))
				{
					result = num1 + num2;
				}
				if (symbol.equals("-"))
				{
					result = num1 - num2;
				}
				if (symbol.equals("*"))
				{
					result = num1 * num2;
				}
				if (symbol.equals("/"))
				{
					result = num1 / num2;
				}
				text = "" + result;
				if (result % 1 == 0)
				{
					text = text.substring(0, text.length() - 2);
				}
				output.setText(text);

				textLb = "";
				label.setText(textLb);

				symbol = "";
			}
		});
		EqualBtn.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		EqualBtn.setBounds(292, 459, 84, 55);
		contentPane.add(EqualBtn);

	}
}
