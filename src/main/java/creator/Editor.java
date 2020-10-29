package creator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Editor extends JPanel {

	/**
	 * 
	 */

	private static final long serialVersionUID = 7191236744628114238L;
	private static final String[] listModel = {
			Workbench.getLang().getHorizontal(),
			Workbench.getLang().getVertical()
	};
	private static SpinnerListModel modelForSpinner = new SpinnerListModel(listModel);
			
	private static JCheckBox automaticNumberingBox = new JCheckBox();
	private static JLabel numberLabel = new JLabel();
	private static JTextField numberField = new JTextField(5);
	private static JLabel directionLabel = new JLabel();
	private static JSpinner directionSpinner = new JSpinner(modelForSpinner);
	private static JLabel solutionLabel = new JLabel();
	private static JTextField solutionField = new JTextField();
	private static JLabel definitionLabel = new JLabel();
	private static JTextArea definitionArea = new JTextArea(3, 30);
	private static JScrollPane scrollableTextArea = new JScrollPane(definitionArea);
	private static Dimension dim;
	private int i = 0;
	private boolean pressed = false;
	private char keyChar = 0;
	private static String text = "";

	Editor() {
		updateUI();
	}

	public void updateUI() {
		listModel[0] = Workbench.getLang().getHorizontal();
		listModel[1] = Workbench.getLang().getVertical();
		modelForSpinner = new SpinnerListModel(listModel);
		directionSpinner.setModel(modelForSpinner);
		
		automaticNumberingBox.setText(Workbench.getLang().getAutomaticNumberingText());

		if (automaticNumberingBox.isEnabled())
			automaticNumberingBox.setEnabled(false);
		else
			automaticNumberingBox.setEnabled(true);

		numberLabel.setText(Workbench.getLang().getNumberText());
		directionLabel.setText(Workbench.getLang().getDirectionText());
		solutionLabel.setText(Workbench.getLang().getSolutionLabelText());
		definitionLabel.setText(Workbench.getLang().getDefinitionLabelText());

		scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		definitionArea.setLineWrap(true);

		solutionField.setColumns(30);

		solutionField.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent ke) {
				keyChar = ke.getKeyChar();

				if (!pressed) {
//					if (keyChar == 8) {
					
//						if (Workbench.getCharIndex() > 0) {
//							Workbench.setCharIndex(Workbench.getCharIndex() - 1);
//							for (int i = solutionField.getCaretPosition(); i < Workbench.getSelectedSquaresNumber() - 1; i++) {
//								Workbench.getSquares()[Workbench.getSelection()[i - 1]]
//										.setLetter(Workbench.getSquares()[Workbench.getSelection()[i]].getLetter());
//								System.out.println("caretPos: " + i);
//							}
//							Workbench.getSquares()[Workbench.getSelection()[text.length() - 1]].setLetter((char) 0);
//							text = text.substring(0, text.length() > 0 ? text.length() - 1 : 0);
//						}

					if (keyChar == 10 || keyChar == 9){
						if (solutionField.getText().strip() != "" )
							definitionArea.grabFocus();
					} 
					else if (Character.isAlphabetic(keyChar)) {
//
//						System.out.println(text + " length: " + solutionField.getText().length() + " charIndex: "
//								+ Workbench.getCharIndex());
//						if (Workbench.getCharIndex() <= Workbench.getSelectedSquaresNumber()
//								&& text.length() <= Workbench.getSelectedSquaresNumber()) {
//							text += String.valueOf(keyChar);
//							Workbench.getSquares()[!Workbench.isMultiple() ? Workbench.getSelection()[0]
//									: Workbench.getSelection()[Workbench.getCharIndex()]].setLetter(keyChar);
//							Workbench.setCharIndex(Workbench.getCharIndex() + 1);
//						}
//						text = "";
//						solutionField.setText("");
//						solutionField.setText(text);
//					}
					
					
				}
					Workbench.getDrawArea().repaint();
					pressed = !pressed;
			}
			}

			public void keyReleased(KeyEvent ke) {
				if (pressed)
					pressed = !pressed;
//				solutionField.setText(text);
			}
		});
		
		definitionArea.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				keyChar = ke.getKeyChar();
				if (keyChar == 10 || keyChar == 9) {
					if (definitionArea.getText().strip() != "" && solutionField.getText().strip() != "") {
						Workbench.getSaveButton().setEnabled(true);
						Workbench.getSaveButton().grabFocus();
					}
				}
			}
		});
		
//		add(automaticNumberingBox);	 this will be used later
		add(numberLabel);
		add(numberField);
		add(directionLabel);
		add(directionSpinner);
		add(solutionLabel);
		add(solutionField);
		add(definitionLabel);
		add(scrollableTextArea);

		setLayout(null);
		int indent;

		dim = numberLabel.getPreferredSize();
		numberLabel.setBounds(10, 10, dim.width, dim.height);
		indent = 10 + dim.width + 10;

		dim = definitionLabel.getPreferredSize();
		solutionLabel.setBounds(10, numberLabel.getY() + numberField.getPreferredSize().height + 10,
				solutionLabel.getPreferredSize().width, solutionLabel.getPreferredSize().height);
		if (indent < 10 + dim.width + 10)
			indent = 10 + dim.width + 10;
		
		dim = solutionLabel.getPreferredSize();
		definitionLabel.setBounds(10, solutionLabel.getY() + solutionLabel.getPreferredSize().height + 10,
				definitionLabel.getPreferredSize().width, definitionLabel.getPreferredSize().height);
		if (indent < 10 + dim.width + 10)
			indent = 10 + dim.width + 10;

		numberField.setBounds(indent, numberLabel.getY(), numberField.getPreferredSize().width,
				numberField.getPreferredSize().height);
		
		directionLabel.setBounds(numberField.getX() + numberField.getWidth() + 10, numberField.getY(), 
								directionLabel.getPreferredSize().width, directionLabel.getPreferredSize().height);
		
		directionSpinner.setBounds(directionLabel.getX() + directionLabel.getWidth() + 10,
									directionLabel.getY(), directionSpinner.getPreferredSize().width,
									directionSpinner.getPreferredSize().height);

		solutionField.setBounds(indent, solutionLabel.getY(), solutionField.getPreferredSize().width,
				solutionField.getPreferredSize().height);
		scrollableTextArea.setBounds(indent, definitionLabel.getY(), scrollableTextArea.getPreferredSize().width,
				scrollableTextArea.getPreferredSize().height);

		dim = automaticNumberingBox.getPreferredSize();
		automaticNumberingBox.setBounds(numberField.getX() + numberField.getWidth() + 10, numberLabel.getY(), dim.width,
				dim.height);

		automaticNumberingBox.setEnabled(true);
		automaticNumberingBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (automaticNumberingBox.isSelected())
					numberField.setEnabled(false);
				else
					numberField.setEnabled(true);
			}
		});
		
		dim = scrollableTextArea.getPreferredSize();
		
		setPreferredSize(new Dimension(scrollableTextArea.getX() + scrollableTextArea.getWidth() + 10,
				definitionLabel.getY() + scrollableTextArea.getPreferredSize().height + 10));
		
	}

	public static JLabel getSolutionLabel() {
		return solutionLabel;
	}

	public static void setSolutionLabel(JLabel solutionLabel) {
		Editor.solutionLabel = solutionLabel;
	}

	public static JTextField getSolutionField() {
		return solutionField;
	}

	public static JLabel getDefinitionLabel() {
		return definitionLabel;
	}

	public static void setDefinitionLabel(JLabel definitionLabel) {
		Editor.definitionLabel = definitionLabel;
	}

	public static JTextArea getDefinitionArea() {
		return definitionArea;
	}

	public static void setDefinitionArea(JTextArea definitionArea) {
		Editor.definitionArea = definitionArea;
	}

	public static JLabel getNumberLabel() {
		return numberLabel;
	}

	public static void setNumberLabel(JLabel numberLabel) {
		Editor.numberLabel = numberLabel;
	}

	public static void setText(String text) {
		Editor.text = text;
	}

	public static JTextField getNumberField() {
		return numberField;
	}

	public static JSpinner getDirectionSpinner() {
		return directionSpinner;
	}
	
}
