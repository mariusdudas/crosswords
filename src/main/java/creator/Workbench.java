/**
 * 
 */
package creator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.persistence.NoResultException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.MaskFormatter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import languages.English;
import languages.ILanguages;
import languages.Romanian;

/**
 * @author ioan-marius
 *
 */
public class Workbench extends JPanel {

	/**
	 * 
	 */

	private static final long serialVersionUID = -3048081682144129479L;
	private static final int side = 30;
	private static final String prefsFile = "preferences.set";

	private static ILanguages lang = new Romanian();

	public static ILanguages getLang() {
		return lang;
	}

	private static final SpinnerNumberModel modelWidth = new SpinnerNumberModel(1, 1, 15, 1);
	private static final SpinnerNumberModel modelHeight = new SpinnerNumberModel(1, 1, 15, 1);

	// File menu section
	
	private static JMenuBar menuBar = new JMenuBar();
	private static JMenu fileMenu = new JMenu();
	private static JMenuItem newItem = new JMenuItem();
	private static JMenuItem openItem = new JMenuItem();
	private static JMenuItem continueItem = new JMenuItem();
	private static JMenuItem saveItem = new JMenuItem();
	private static JMenuItem deleteItem = new JMenuItem();
	private static JMenuItem exitItem = new JMenuItem();

	// Settings menu section
	
	private static JMenu settingsMenu = new JMenu();
	private static JMenu colorsMenu = new JMenu();

	private static JMenuItem squareBckColorMenu = new JMenuItem();
	private static JMenuItem squareOutColorMenu = new JMenuItem();
	private static JMenuItem squareSelColorMenu = new JMenuItem();
	private static JMenuItem squareTextColorMenu = new JMenuItem();
	private static JMenuItem numberTextColorMenu = new JMenuItem();
	private static JMenuItem squareBlancColorMenu = new JMenuItem();
	private static JMenuItem drawAreaColorMenu = new JMenuItem();

	private static JMenu sizeMenu = new JMenu();
	private static JMenu widthMenu = new JMenu();
	private static JMenuItem widthItem = new JMenuItem("         ");
	private static JSpinner widthSpinner = new JSpinner(modelWidth);
	private static JMenu heightMenu = new JMenu();
	private static JMenuItem heightItem = new JMenuItem("         ");
	private static JSpinner heightSpinner = new JSpinner(modelHeight);

	private static JMenuItem loadImageItem = new JMenuItem();
	private static JMenuItem saveSettingsItem = new JMenuItem();
	private static JMenuItem loadSettingsItem = new JMenuItem();

	// Help menu section
	
	private static JMenu helpMenu = new JMenu();

	// Language menu section
	
	private static JMenu langMenu = new JMenu();
	private static JMenuItem langItem = new JMenuItem();

	private static Square[] squares;
	private static int selectedSquare = -1;
	private static int lastHoveredSquare = -1, hoveredSquare = -1;
	private static int charIndex = 0;
	private static Preferences actualPreference = new Preferences(), tempPreference = new Preferences();

	private static ArrayList<Preferences> preferencesList;
	private static Workbench drawArea;
	private static Editor editArea;
	private static List<JLabel> valuesLabels = new ArrayList<JLabel>();
	private static JPanel labelsContainer = new JPanel();
	private static JScrollPane scrollingValues;
	private static JButton saveButton;

	private static Game currentGame = new Game();
	private static String squareInfo = "";

	private static JPopupMenu popup = new JPopupMenu();
	private static JMenuItem blancNonBlancItem = new JMenuItem();
	private static JMenuItem multipleSelectionItem = new JMenuItem();
	private static int lastSelectedSquare = -1;
	private static int[] selection;
	private static boolean multiple = false, tracking = false;
	private static int deltaX = 0, deltaY = 0;
	private static MaskFormatter maskFormatter;
	private static StringBuilder sb = new StringBuilder();
	private static int selectedSquaresNumber = 0;
	private static long loaded = -1;
	
	// Hibernate stuff
	
	private static SessionFactory factory;
	private static Transaction transaction;
//	private static Connection connection;
//	private static 
	
	public Workbench() {
		setPreferredSize(new Dimension(626, 626));
		setFocusable(true);
	}

	public static void main(String[] args) {

		// First create factory
		factory = new Configuration().configure()
				.addAnnotatedClass(Game.class)
				.addAnnotatedClass(GameCoreData.class)
				.buildSessionFactory();

		// Second we load the text for menus
		loadMenusText();

		// Then we draw the working bench
		drawArea = new Workbench();

		drawArea.addMouseMotionListener(new MouseAdapter() {

			public void mouseMoved(MouseEvent me) {

				if (me.getX() > squares[0].getX() && me.getX() < squares[squares.length - 1].getX() + side
						&& me.getY() > squares[0].getX() && me.getY() < squares[squares.length - 1].getY() + side) {

					hoveredSquare = ((me.getY() - squares[0].getY()) / side) * actualPreference.getWidth()
							+ (me.getX() - squares[0].getX()) / side;

					if (lastHoveredSquare != hoveredSquare) {
						lastHoveredSquare = hoveredSquare;
						if (tracking)
							squaresSelection(me.getX(), me.getY());
						drawArea.repaint();
					}
				}
			}
		});

		drawArea.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent me) {

				if (me.getX() > squares[0].getX() && me.getX() < squares[squares.length - 1].getX() + side
						&& me.getY() > squares[0].getY() && me.getY() < squares[squares.length - 1].getY() + side) {

					selectedSquare = ((me.getY() - squares[0].getY()) / side) * actualPreference.getWidth()
							+ (me.getX() - squares[0].getX()) / side;

					// If we have not set the multiple flag
					if (!multiple) {
					// Setting the left clicking behaviour
						if (me.getButton() == MouseEvent.BUTTON1) {
							if (lastSelectedSquare != -1 && lastSelectedSquare != hoveredSquare) {
								squares[lastSelectedSquare].setSelected(!squares[lastSelectedSquare].isSelected());
							}
							squares[hoveredSquare].setSelected(!squares[hoveredSquare].isSelected());
							if (squares[hoveredSquare].isSelected())
								squaresSelection(me.getX(), me.getY());
							else {
								selection[0] = -1;
								drawArea.repaint();
							}
							editSquare(hoveredSquare);
					// Setting the right clicking behaviour
						} else if (me.getButton() == MouseEvent.BUTTON3) {
							loadPopupMenusText(squares[selectedSquare].isBlanc());
							popup.show(drawArea, me.getX(), me.getY());
						}
					// I want no mouse wheel behaviour

					} else {
					// I we have set the multiple flag
						if (tracking) {
							tracking = false;
							editSelection();
						} else
							multiple = false;
					}
				}
				drawArea.repaint();
				lastSelectedSquare = hoveredSquare;
			}
		});

		createSquares();

		editArea = new Editor();

		menuBar.add(fileMenu);

		fileMenu.add(newItem);
		newItem.addMouseListener(new MouseAdapter() {

			// Invokes the method to create a new crosswords game

			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				newGame();
			}
		});

		fileMenu.add(openItem);
		openItem.addMouseListener(new MouseAdapter() {

			// Invokes the method to open an existing game

			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				gamesListerDialog(lang.getOpenGameText(), lang.getOpenGameText(), 
						lang.getOpenMnemonic(), ActOnGame.LOAD);;
			}
		});

		fileMenu.addSeparator();

		fileMenu.add(continueItem);
		continueItem.addActionListener(new ActionListener() {

			// Invokes the method that opens the last game created

			@Override
			public void actionPerformed(ActionEvent arg0) {
				continueGame();
			}
		});

		fileMenu.add(saveItem);
		saveItem.addMouseListener(new MouseAdapter() {
			// Invokes the method to save the working progress on current game creation

			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				saveGameDialog();
			}
		});

		fileMenu.add(deleteItem);
		deleteItem.addMouseListener(new MouseAdapter() {

			// Invokes the method to delete the selected game

			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				gamesListerDialog(lang.getDeleteGameText(), lang.getDeleteText(), 
						lang.getDeleteMnemonic(), ActOnGame.DELETE);
			}
		});

		fileMenu.addSeparator();

		fileMenu.add(exitItem);
		exitItem.addActionListener(new ActionListener() {

			// Invokes the method for exiting the program

			@Override
			public void actionPerformed(ActionEvent arg0) {
				exitProgram();
			}
		});

		menuBar.add(settingsMenu);
		settingsMenu.add(colorsMenu);
		colorsMenu.add(squareBckColorMenu);
		squareBckColorMenu.addActionListener(new ActionListener() {

			// Invokes the method for choosing color for the square background

			@Override
			public void actionPerformed(ActionEvent arg0) {
				chooseBckSquareColor();
			}
		});

		colorsMenu.add(squareOutColorMenu);
		squareOutColorMenu.addActionListener(new ActionListener() {

			// Invokes the method for choosing color for the square outline

			@Override
			public void actionPerformed(ActionEvent arg0) {
				chooseOutSquareColor();
			}
		});

		colorsMenu.add(squareSelColorMenu);
		squareSelColorMenu.addActionListener(new ActionListener() {

			// Invokes the method for choosing the background color for the chosen square

			@Override
			public void actionPerformed(ActionEvent arg0) {
				chooseSelColor();
			}
		});

		colorsMenu.add(squareTextColorMenu);
		squareTextColorMenu.addActionListener(new ActionListener() {

			// Invokes the method for choosing the text color for the edited square

			@Override
			public void actionPerformed(ActionEvent arg0) {
				chooseTextColor();
			}
		});

		colorsMenu.add(numberTextColorMenu);
		numberTextColorMenu.addActionListener(new ActionListener() {

			// Invokes the method for choosing the text color for the edited square

			@Override
			public void actionPerformed(ActionEvent arg0) {
				chooseNumberTextColor();
			}
		});

		colorsMenu.add(squareBlancColorMenu);
		squareBlancColorMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Invokes the method for choosing the text color for the blanc square
				chooseBlancColor();
			}
		});

		colorsMenu.add(drawAreaColorMenu);
		drawAreaColorMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Invokes the method for choosing the color for the drawing area
				chooseDrawAreaColor();
			}
		});

		settingsMenu.add(sizeMenu);
		sizeMenu.add(widthMenu);
		widthMenu.add(widthItem);
		widthItem.add(widthSpinner);
		widthSpinner.addChangeListener(new ChangeListener() {

			// Invokes the method for choosing the width of the drawing area in number of
			// squares

			@Override
			public void stateChanged(ChangeEvent arg0) {
				chooseWidth((int) widthSpinner.getValue());
			}
		});

		sizeMenu.add(heightMenu);
		heightMenu.add(heightItem);
		heightItem.add(heightSpinner);
		heightSpinner.addChangeListener(new ChangeListener() {

			// Invokes the method for choosing the width of the drawing area in number of
			// squares

			@Override
			public void stateChanged(ChangeEvent arg0) {
				chooseHeight((int) heightSpinner.getValue());
			}
		});

		settingsMenu.add(loadImageItem);
		loadImageItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				chooseImage();
			}

			
		});
		
		settingsMenu.add(saveSettingsItem);
		saveSettingsItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				// Invokes the method for saving the actual preference

				try {
					savePreference();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
				}
			}
		});

		settingsMenu.add(loadSettingsItem);
		loadSettingsItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				// Invokes the method for loading preference

				try {
					loadpreference();
				} catch (ClassNotFoundException | IOException e) {

					// Displays the error message

					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});

		menuBar.add(helpMenu);
		helpMenu.addMouseListener(new MouseAdapter() {

			// Invokes the method that shows a message with the program version

			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				showVersion();
			}
		});

		menuBar.add(langMenu);
		langMenu.add(langItem);
		langItem.addMouseListener(new MouseAdapter() {

			// Invokes the method to change the language

			@Override
			public void mousePressed(MouseEvent e) {
				super.mouseClicked(e);
				try {
					chooseLanguage();
				} catch (ClassNotFoundException cnfe) {
					JOptionPane.showMessageDialog(null, cnfe.getMessage());
				}
			}
		});

		popup.add(blancNonBlancItem);
		blancNonBlancItem.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent me) {
				squares[selectedSquare].setBlanc(!squares[selectedSquare].isBlanc());
				if (!squares[selectedSquare].isBlanc()) {
					deleteXGameCoreData(selectedSquare);
				}
				drawArea.repaint();
			}
		});

		popup.add(multipleSelectionItem);
		multipleSelectionItem.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				multiple = !multiple;
				tracking = !tracking;
			}
		});
		
		scrollingValues = new JScrollPane(labelsContainer);
		BoxLayout boxLayout = new BoxLayout(labelsContainer, BoxLayout.Y_AXIS);
		labelsContainer.setLayout(boxLayout);
		saveButton = new JButton();
		saveButton.setText(Workbench.getLang().getSaveButtonText());
		saveButton.setMnemonic(Workbench.getLang().getSaveButtonMnemonic());
		saveButton.setEnabled(false);
		saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				saveGameCoreData();
			}
		});

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				JFrame window = new JFrame("Crosswords creator");

				Dimension size;
				Insets insets = window.getInsets();

				window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				window.setLayout(null);

				window.add(menuBar);

				window.add(drawArea);
				drawArea.setBackground(actualPreference.getDrawAreaColor());
				window.add(editArea);
				window.add(scrollingValues);
				editArea.setBackground(Color.yellow);
				window.add(saveButton);
				

				size = menuBar.getPreferredSize();

				menuBar.setBounds(insets.left, insets.top, size.width, size.height);

				size = drawArea.getPreferredSize();

				drawArea.setBounds(20 + insets.left, 20 + insets.top + menuBar.getHeight(), size.width, size.height);

				size = editArea.getPreferredSize();
				
				editArea.setBounds(20 + insets.left + drawArea.getWidth() + 20, drawArea.getBounds().y, size.width,
						size.height);
				
				setButtonSize();
				
				scrollingValues.setBounds(saveButton.getX(), saveButton.getY() + saveButton.getHeight() + 10, 
						editArea.getPreferredSize().width, 
						drawArea.getX() + drawArea.getHeight() - (saveButton.getY() + saveButton.getHeight() + 10) + 20);
				
				setWindowSize();
				
				window.setVisible(true);
			}
		});
	}
	
	public static void chooseImage() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(new FileFilter() {

			@Override
			public String getDescription() {
				return "*.tiff, *.gif, *.jpeg, *.jpg";
			}

			@Override
			public boolean accept(File file) {
				if (file.isDirectory())
					return true;
				String extension = getExtension(file);
				if (extension != null) {
					if (extension.equals("tiff")
							|| extension.equals("gif")
							|| extension.equals("jpeg")
							|| extension.equals("jpg"))
						return true;
					else
						return false;
				}
				return false;
			}

			private String getExtension(File file) {
				return file.getName().substring(file.getName().lastIndexOf('.') + 1).toLowerCase();
			}
		});
		fileChooser.setAcceptAllFileFilterUsed(false);

		int option = fileChooser.showOpenDialog(drawArea.getParent());
		if (option == JFileChooser.APPROVE_OPTION) {
			currentGame.setImage(fileChooser.getSelectedFile());
		}
	}

	protected static void deleteXGameCoreData(int fromHere) {
		// This method is invoked when a blank square is deleted, meaning that the word delimiter is deleted
		// so the words delimited by it have to be deleted also, along with the core data saved with them
		// For this we search all direction and delete all data found
		// Searching left
		deleteXLeft(fromHere);
		// Searching right
		deleteXRight(fromHere);
		// Searching up
		deleteXUp(fromHere);
		// Searching down
		deleteXDown(fromHere);
		Editor.getNumberField().setText("");
		Editor.getDirectionSpinner().setValue(lang.getHorizontal());
		Editor.getSolutionField().setText("");
		Editor.getDefinitionArea().setText("");
	}

	private static void deleteXDown(int fromHere) {
		for (int i = fromHere; i < squares.length; i += actualPreference.getWidth()) {
			if (!squares[i].isBlanc()) 
				squares[i].setLetter((char) 0);
			else {
				for (int j = 0; j < currentGame.getSolutionsDefinitions().size(); j++) {
					if ((currentGame.getSolutionsDefinitions().get(j)).getNumber() == i - 1
						&& (currentGame.getSolutionsDefinitions().get(j)).getDirection().equals(lang.getVertical()))
						currentGame.getSolutionsDefinitions().remove(j);
				}
				break;
			}
			for (int j = 0; j < currentGame.getSolutionsDefinitions().size(); j++) {
				if ((currentGame.getSolutionsDefinitions().get(j)).getNumber() == fromHere + 1
					&& (currentGame.getSolutionsDefinitions().get(j)).getDirection().equals(lang.getVertical()))
					currentGame.getSolutionsDefinitions().remove(j);
			}
		}
	}

	private static void deleteXUp(int fromHere) {
		for (int i = fromHere; i >= actualPreference.getWidth(); i -= actualPreference.getWidth()) {
			if (squares[i].getLetter() == (char)0)
				return;
			if (!squares[i].isBlanc()) 
				squares[i].setLetter((char)0);
			else {
				for (int j = 0; j < currentGame.getSolutionsDefinitions().size(); j++) {
					if ((currentGame.getSolutionsDefinitions().get(j)).getNumber() == i + actualPreference.getWidth()
						&& (currentGame.getSolutionsDefinitions().get(j)).getDirection().equals(lang.getVertical()))
						currentGame.getSolutionsDefinitions().remove(j);
				}
				break;
			}
			for (int j = 0; j < currentGame.getSolutionsDefinitions().size(); j++) {
				if ((currentGame.getSolutionsDefinitions().get(j)).getNumber() == i
					&& (currentGame.getSolutionsDefinitions().get(j)).getDirection().equals(lang.getVertical()))
					currentGame.getSolutionsDefinitions().remove(j);
			}
		}
	}

	private static void deleteXRight(int fromHere) {
		for (int i = fromHere; i < ((fromHere / actualPreference.getWidth() + 1) * actualPreference.getWidth()); i++) {
			if (!squares[i].isBlanc()) 
				squares[i].setLetter((char)0);
			else {
				for (int j = 0; j < currentGame.getSolutionsDefinitions().size(); j++) {
					if ((currentGame.getSolutionsDefinitions().get(j)).getNumber() == i - 1
						&& (currentGame.getSolutionsDefinitions().get(j)).getDirection().equals(lang.getHorizontal()))
						currentGame.getSolutionsDefinitions().remove(j);
				}
				break;
			}
			for (int j = 0; j < currentGame.getSolutionsDefinitions().size(); j++) {
				if ((currentGame.getSolutionsDefinitions().get(j)).getNumber() == fromHere + 1
					&& (currentGame.getSolutionsDefinitions().get(j)).getDirection().equals(lang.getHorizontal()))
					currentGame.getSolutionsDefinitions().remove(j);
			}
		}
	}

	private static void deleteXLeft(int fromHere) {
		for (int i = fromHere; i >= fromHere - fromHere % actualPreference.getWidth(); i--) {
			if (squares[i].getLetter() == (char)0)
				return;
			if (!squares[i].isBlanc()) 
				squares[i].setLetter((char)0);
			else {
				for (int j = 0; j < currentGame.getSolutionsDefinitions().size(); j++) {
					if ((currentGame.getSolutionsDefinitions().get(j)).getNumber() == i + 1
						&& (currentGame.getSolutionsDefinitions().get(j)).getDirection().equals(lang.getHorizontal()))
						currentGame.getSolutionsDefinitions().remove(j);
				}
				break;
			}
			for (int j = 0; j < currentGame.getSolutionsDefinitions().size(); j++) {
				if ((currentGame.getSolutionsDefinitions().get(j)).getNumber() == i
					&& (currentGame.getSolutionsDefinitions().get(j)).getDirection().equals(lang.getHorizontal()))
					currentGame.getSolutionsDefinitions().remove(j);
			}
		}
	}

	protected static void saveGameCoreData() {
		GameCoreData newStage = new GameCoreData();
		JLabel valueSetLabel = new JLabel();
		labelsContainer.add(valueSetLabel);
		
		
		Editor.getSolutionField().setText(
				Editor.getSolutionField().getText().substring(0, selectedSquaresNumber + 1));
		newStage.setNumber(selection[0]);
		newStage.setDirection(Editor.getDirectionSpinner().getValue().toString());
		newStage.setSolutionLength(Editor.getSolutionField().getText().length());
		newStage.setDefinition(Editor.getDefinitionArea().getText());
		saveButton.setEnabled(false);
		for (int i = 0; i < currentGame.getSolutionsDefinitions().size(); i++) {
			if ((currentGame.getSolutionsDefinitions().get(i)).getNumber() == newStage.getNumber()
				&& (currentGame.getSolutionsDefinitions().get(i)).getDirection().equals(newStage.getDirection()))
				currentGame.getSolutionsDefinitions().remove(i);
		}
		currentGame.getSolutionsDefinitions().add(newStage);
//		JSONArray JSONvar  = new JSONArray();
//		JSONvar.add(JSONvar.toJSONString(currentGame.getSolutionsDefinitions()).toString());
//		System.out.println(JSONvar);
		
		Collections.sort(currentGame.getSolutionsDefinitions(), Comparator.comparing(GameCoreData::getDirection)
						.thenComparing(GameCoreData::getNumber));
		for (int j = 0; j < selectedSquaresNumber + 1; j++) {
			squares[selection[j]].setLetter(Editor.getSolutionField().getText().charAt(j));
		}
		determineMargines();
		populateScrollPane();
	}

	protected static void determineMargines() {
		// Setting blanc squares before and after the words 
		if (Editor.getDirectionSpinner().getValue().equals(lang.getHorizontal())) {
			if (selection[0] % actualPreference.getWidth() >= 1 && !squares[selection[0] - 1].isBlanc())
				squares[selection[0] - 1].setBlanc(true);
			if (selection[selectedSquaresNumber] % actualPreference.getWidth() < 
					actualPreference.getWidth() - 1 && !squares[selection[selectedSquaresNumber] + 1].isBlanc())
				squares[selection[selectedSquaresNumber] + 1].setBlanc(true);
		} else {
			if (selection[0] / actualPreference.getWidth() >= 1 && !squares[selection[0] - actualPreference.getWidth()].isBlanc())
				squares[selection[0] - actualPreference.getWidth()].setBlanc(true);
			if (selection[selectedSquaresNumber] / actualPreference.getWidth() < 
					actualPreference.getWidth() - 1 
					&& !squares[selection[selectedSquaresNumber] + actualPreference.getWidth()].isBlanc())
				squares[selection[selectedSquaresNumber] + actualPreference.getWidth()].setBlanc(true);
		}
		drawArea.repaint();
	}

	protected static void populateScrollPane() {
		// Fills the labelsContainer with labels with saved values
		// Each value set is printed on a separate label
		labelsContainer.removeAll();
		labelsContainer.revalidate();
		labelsContainer.repaint();
		for (int i = 0; i < currentGame.getSolutionsDefinitions().size(); i++) {
		// tested until here
			addLabel((currentGame.getSolutionsDefinitions().get(i)));
		}
		saveButton.setEnabled(true);
	}

	private static void addLabel(GameCoreData e) {
		// We are adding the labels with game core info to the panel
		JLabel values = new JLabel();
		Border border = BorderFactory.createBevelBorder(BevelBorder.RAISED);
		values.setBorder(border);
		values.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent me) {
				values.setBackground(Color.DARK_GRAY);
			}
			
			public void mouseExited(MouseEvent me) {
				values.setBackground(Color.LIGHT_GRAY);
			}
			
			public void mouseClicked(MouseEvent me) {
				
				for (int i = 0; i < labelsContainer.getComponents().length; i++) {
					if (labelsContainer.getComponent(i).equals(me.getComponent())) {
						redirectToWorkbench(i);
						break;
					}
				}
			}
		});
		
		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		sb.append(e.getDirection().equals(lang.getHorizontal()) 
				? String.valueOf(e.getNumber() / actualPreference.getWidth() + 1) + " "
				: String.valueOf(e.getNumber() % actualPreference.getWidth() + 1) + " ");
		sb.append(e.getDirection().toString() + "<br>");
		sb.append(e.getSolution() + ": " + e.getDefinition() + "</html>");
		values.setHorizontalTextPosition((int) CENTER_ALIGNMENT);
		values.setText(sb.toString());
		labelsContainer.add(values);
	}

	protected static void redirectToWorkbench(int i) {
		// Now we fill the editor with data from list of gameCoreData
		// and we select the word in the workbench
		int j = 0;
		char[] chars = new char[(currentGame.getSolutionsDefinitions().get(i)).getSolutionLength()];
		Editor.getNumberField().setText(String.valueOf(
				(currentGame.getSolutionsDefinitions().get(i)).getDirection().equals(lang.getHorizontal()) 
				? String.valueOf((currentGame.getSolutionsDefinitions().get(i)).getNumber() / actualPreference.getWidth() + 1) + " "
				: String.valueOf((currentGame.getSolutionsDefinitions().get(i)).getNumber() % actualPreference.getWidth() + 1) + " "));
		Editor.getDirectionSpinner().setValue((currentGame.getSolutionsDefinitions().get(i)).getDirection());
		for (int k = 0; k < chars.length; k++) {
			chars[k] = (currentGame.getSolutionsDefinitions().get(i)).getDirection().equals(lang.getHorizontal())
					? currentGame.getLetter((currentGame.getSolutionsDefinitions().get(i)).getNumber() + k)
					: currentGame.getLetter((currentGame.getSolutionsDefinitions().get(i)).getNumber() + 
					                              k * actualPreference.getWidth());
		}
		Editor.getSolutionField().setText(String.valueOf(chars));
		Editor.getDefinitionArea().setText((currentGame.getSolutionsDefinitions().get(i)).getDefinition());
		selection[0] = (currentGame.getSolutionsDefinitions().get(i)).getNumber();
		if ((currentGame.getSolutionsDefinitions().get(i)).getSolutionLength() > 1)
		for (j = 0; j < (currentGame.getSolutionsDefinitions().get(i)).getSolutionLength(); j++) {
			selection[j + 1] = (currentGame.getSolutionsDefinitions().get(i)).getDirection().equals(lang.getHorizontal())
					? selection[j] + 1 
					: selection[j] + actualPreference.getWidth();
		}
		if (j < actualPreference.getWidth() - 1)
			selection[j] = -1;
		Editor.getNumberField().grabFocus();
		drawArea.repaint();
	}

	protected static void editSelection() {
//		multiple = false;
		charIndex = 0;
		sb = new StringBuilder();
//		StringBuilder text = new StringBuilder();
//		Editor.getSolutionField().setText("");
		detectDirection();
		detectNumber();
		checkOverwrite();
		Editor.getSolutionField().grabFocus();
		
		for (int i = 0; i < selection.length; i++) {
			if (selection[i] == -1)
				break;
			sb.append("U");
//			text.append(squares[selection[i]].getLetter());
		}
		try {
			maskFormatter = new MaskFormatter(sb.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
//		Editor.getSolutionField().setFormatterFactory(new DefaultFormatterFactory(maskFormatter));
//		Editor.getSolutionField().setText(text.toString());
	}
	
	private static void checkOverwrite() {
		// This method checks if we selected squares over already selected and saved squares
		// and deletes the overwritten word from records
		// First get direction to check, then act accordingly
		if (Editor.getDirectionSpinner().getValue().equals(lang.getHorizontal()))
			deleteXLeft(selection[0]); 
		else 
			deleteXUp(selection[0]);
	}

	protected static void editSquare(int squareIndex) {
		Editor.getDirectionSpinner().setValue(lang.getHorizontal());
		detectNumber();
		Editor.getSolutionField().grabFocus();
		try {
			maskFormatter = new MaskFormatter("?");
		} catch (ParseException e) {
			e.printStackTrace();
		}
//		Editor.getSolutionField().setFormatterFactory(new DefaultFormatterFactory(maskFormatter));
	}

	private static void detectNumber() {
		
		Editor.getNumberField().setText(
				Editor.getDirectionSpinner().getValue().equals(lang.getHorizontal()) ?
				String.valueOf(selection[0] / actualPreference.getWidth() + 1) :
				String.valueOf(selection[0] % actualPreference.getWidth() + 1)
				);
	}

	private static void detectDirection() {
		if (selection.length > 1)
		if (Math.abs(selection[0] - selection[1]) == 1)
			Editor.getDirectionSpinner().setValue(lang.getHorizontal());
		else
			Editor.getDirectionSpinner().setValue(lang.getVertical());
	}

	protected static void squaresSelection(int x, int y) {
		int i = 0;
		if (selectedSquare == -1)
			return;
//		selection[i++] = squares[selectedSquare].isBlanc() ? -1 : selectedSquare;
		selection[i++] = currentGame.getLetter(selectedSquare) == '1' ? -1 : selectedSquare;
		if (x < squares[selectedSquare].getX())						// Going left
			deltaX = x - squares[selectedSquare].getX() - side;
		else if (x >= squares[selectedSquare].getX())				// Going right
			deltaX = x - squares[selectedSquare].getX();
		if (y < squares[selectedSquare].getY())						// Going down
			deltaY = y - squares[selectedSquare].getY() - side;
		else if (y >= squares[selectedSquare].getY())				// Going up
			deltaY = y - squares[selectedSquare].getY();
		
		if (Math.abs(deltaX) >= Math.abs(deltaY))
			while (i <= Math.abs(deltaX / side) && i < selection.length 
					&& currentGame.getLetter(selection[i - 1] + (int)Math.signum(deltaX)) != '1')
//					&& !squares[selection[i - 1] + (int) Math.signum(deltaX)].isBlanc())
				selection[i] = selection[i++ - 1] + (int) Math.signum(deltaX);
		else
			while (i <= Math.abs(deltaY / side) && i < selection.length 
//					&& !squares[selection[i - 1] + (int) Math.signum(deltaY) * actualPreference.getWidth()].isBlanc())
					&& currentGame.getLetter(selection[i - 1] + (int) Math.signum(deltaY) * actualPreference.getWidth()) != '1')
				selection[i] = selection[i++ - 1] + (int) Math.signum(deltaY) * actualPreference.getWidth();

		if (i < selection.length)
			selection[i] = -1;
		
		selectedSquaresNumber = i - 1;
	}

	private static void setWindowSize() {
		Insets insets = (Insets) drawArea.getParent().getInsets();
		Dimension size = editArea.getPreferredSize();
		Frame[] frames = JFrame.getFrames();
		frames[0].setSize(scrollingValues.getX() + size.width + insets.right + 20,
				80 + insets.top + menuBar.getHeight() + drawArea.getHeight() + insets.bottom);
	}

	private static void createSquares() {

		// The method creates squares using square properties

		squares = new Square[actualPreference.getWidth() * actualPreference.getHeight()];
		widthSpinner.setValue(actualPreference.getWidth());
		heightSpinner.setValue(actualPreference.getHeight());

		// Instantiating the selection array with its largest possible number of
		// elements
		selection = new int[actualPreference.getWidth() >= actualPreference.getHeight() ? actualPreference.getWidth()
				: actualPreference.getHeight()];

		int x, y;

		if (drawArea.getHeight() == 0) { 	// Being like this, it means that we only have one single square
			x = (626 - side) / 2; 			// It's simpler to let computer do the math
			y = (626 - side) / 2; 			// See the above line
		} else {
			x = (626 - actualPreference.getWidth() * side) / 2;
			y = (626 - actualPreference.getHeight() * side) / 2;
		}

		for (int i = 0; i < actualPreference.getWidth(); i++) {
			for (int j = 0; j < actualPreference.getHeight(); j++) {
				squares[j * actualPreference.getWidth() + i] = new Square(x + i * side, y + j * side, false, (char) 0);
			}
		}
		// TODO initGame imi distruge practic obiectul currentGame incarcat din baza de date
		if (loaded == -1)
			currentGame.initGame(squares.length);
		drawArea.repaint();
	}

	protected static void chooseLanguage() throws ClassNotFoundException {

		// This method switches between the languages

		if (langItem.getText().equals("Engleza")) {
			lang = new English();
		} else {
			lang = new Romanian();
		}
		loadMenusText();
		editArea.updateUI();
		setButtonSize();
		setWindowSize();
	}

	private static void setButtonSize() {
		saveButton.setBounds(editArea.getX(), editArea.getY() + editArea.getHeight() + 10,
				(int)editArea.getPreferredSize().getWidth(), saveButton.getPreferredSize().height);
	}

	protected static void showVersion() {
		// Showing the version of the program
		JOptionPane.showMessageDialog(null, lang.getVersionContent(), lang.getInformationContent(),
				JOptionPane.INFORMATION_MESSAGE);
	}

	protected static void loadpreference() throws ClassNotFoundException, IOException {

		// Loads the preference

		JPanel selectionPanel, infos = new JPanel(), buttons, rename, renameButtons, empty, loader;
		JComboBox<String> preferencesBox = new JComboBox<String>();
		JButton okBtn, cancelBtn, deleteBtn, renameBtn;
		JButton yesRenameBtn, noRenameBtn;
		JDialog dialog = new JDialog();
		JLabel renameLabel = new JLabel(lang.getLoadSettingsRenameLabelText());
		JTextField renameText = new JTextField(20);

		getPreferences();
		populatePreferencesBox(preferencesBox);

		selectionPanel = new JPanel();
		BoxLayout boxLayout = new BoxLayout(selectionPanel, BoxLayout.Y_AXIS);
		selectionPanel.setLayout(boxLayout);
		selectionPanel.add(preferencesBox);

		okBtn = new JButton(lang.getOKButtonText());
		okBtn.setMnemonic(lang.getOKButtonMnemonic());
		if (preferencesList.isEmpty())
			okBtn.setEnabled(false);
		okBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (preferencesBox.getSelectedItem() != null)
					actualPreference = preferencesList.get(preferencesBox.getSelectedIndex());
				createSquares();
				dialog.dispose();
			}

		});

		cancelBtn = new JButton(lang.getLoadSettingsCancelButtonText());
		cancelBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dialog.dispose();
			}
		});

		deleteBtn = new JButton(lang.getDeleteText());
		renameBtn = new JButton(lang.getLoadSettingsRenameButtonText());
		if (preferencesBox.getSelectedItem() == null) {
			deleteBtn.setEnabled(false);
			renameBtn.setEnabled(false);
		}

		deleteBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (JOptionPane.showConfirmDialog(null, lang.getDeletePreferenceNameText(),
						lang.getDeleteText(), JOptionPane.YES_NO_OPTION) == 0) {
					if (preferencesBox.getItemCount() > 0)
						try {
							deleteSettings(preferencesBox.getSelectedIndex());
							preferencesBox.removeAllItems();
							populatePreferencesBox(preferencesBox);
							if (preferencesBox.getItemCount() == 0) {
								deleteBtn.setEnabled(false);
								okBtn.setEnabled(false);
								renameBtn.setEnabled(false);
							}
						} catch (IOException | ClassNotFoundException e) {
							JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
						} finally {
						}
				}
			}
		});

		rename = new JPanel();
		rename.setPreferredSize(new Dimension(200, 70));
		rename.setLayout(new BorderLayout());

		renameButtons = new JPanel();

		yesRenameBtn = new JButton(lang.getYesRenameBtnText());
		yesRenameBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				preferencesList.get(preferencesBox.getSelectedIndex()).setName(renameText.getText());
				try {
					savePreferencesList();
					populatePreferencesBox(preferencesBox);
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				} finally {
					renameText.setText("");
					renameLabel.setVisible(false);
					renameText.setVisible(false);
					renameButtons.setVisible(false);
				}
			}
		});

		noRenameBtn = new JButton(lang.getNoRenameBtnText());
		noRenameBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				renameText.setText("");
				renameLabel.setVisible(false);
				renameText.setVisible(false);
				renameButtons.setVisible(false);
			}
		});

		renameButtons.add(yesRenameBtn);
		renameButtons.add(noRenameBtn);
		renameButtons.setVisible(false);
		renameLabel.setVisible(false);
		renameText.setVisible(false);

		rename.add(BorderLayout.NORTH, renameLabel);
		rename.add(BorderLayout.CENTER, renameText);
		rename.add(BorderLayout.SOUTH, renameButtons);

		empty = new JPanel();
		empty.setPreferredSize(new Dimension(100, 60));

		selectionPanel.add(rename);
		selectionPanel.add(empty);

		renameBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				renameLabel.setVisible(true);
				renameText.setVisible(true);
				renameButtons.setVisible(true);
				renameText.requestFocus();
			}
		});

		buttons = new JPanel();
		buttons.add(okBtn);
		buttons.add(cancelBtn);
		buttons.add(deleteBtn);
		buttons.add(renameBtn);

		preferencesBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (preferencesBox.getItemCount() > 0) {
					deleteBtn.setEnabled(true);
					tempPreference = preferencesList.get(preferencesBox.getSelectedIndex());
					paintInfoPanel(infos, preferencesBox);
				}
			}
		});

		preferencesBox.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				// Nothing specific to do
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				if (preferencesBox.getItemCount() > 0) {
					deleteBtn.setEnabled(true);
					tempPreference = preferencesList.get(preferencesBox.getSelectedIndex());
					paintInfoPanel(infos, preferencesBox);
				}
			}
		});

		infos.setPreferredSize(new Dimension(200, 150));

		loader = new JPanel();
		loader.setLayout(new BorderLayout());

		loader.add(selectionPanel, BorderLayout.WEST);
		loader.add(infos, BorderLayout.EAST);
		loader.add(buttons, BorderLayout.SOUTH);

		JOptionPane optionPane = new JOptionPane(null, JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION,
				null, new Object[] {}, null);

		optionPane.add(loader);
		dialog.setModal(true);
		dialog.setTitle(lang.getLoadSettingsText());
		dialog.setContentPane(optionPane);

		dialog.pack();

		dialog.setVisible(true);
	}

	protected static void deleteSettings(int position) throws IOException, ClassNotFoundException {
		preferencesList.remove(position);
		savePreferencesList();
	}

	private static void getPreferences() {
		// Reads the file and populates the preferences list with preference saved here
		preferencesList = new ArrayList<Preferences>();

		File file = new File(prefsFile);
		try {
			if (file.exists()) {
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
				preferencesList = (ArrayList<Preferences>) ois.readObject();
				ois.close();
				fis.close();
			}
		} catch (IOException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "getPreferences exception: " + e.getMessage());
		}

		if (preferencesList.size() > 0)
			tempPreference = preferencesList.get(0);
	}

	private static void populatePreferencesBox(JComboBox<String> box) throws ClassNotFoundException {
		// Reads the preferencesList and populates the preferencesBox with preferences
		// saved here
		if (box.getItemCount() > 0)
			box.removeAllItems();
		for (Preferences pref : preferencesList)
			box.addItem(pref.getName());
	}

	protected static void savePreference() throws IOException {
		/*
		 * This method loads all the preferences from the prefsFile in the
		 * preferencesList then it adds the actualPreferences to the preferencesList and
		 * then it saves the preferencesList to file
		 */

		JPanel editPanel, buttons, saver;
		JButton yesBtn, noBtn;
		JDialog dialog = new JDialog();
		JTextField saveNameText = new JTextField(20);

		JLabel saveNameLabel = new JLabel(lang.getSaveSettingsNameLabelText());
		ArrayList<String> alphabeticalPrefs = new ArrayList<String>();

		getPreferences();

		editPanel = new JPanel();
		editPanel.add(saveNameLabel);
		editPanel.add(saveNameText);

		yesBtn = new JButton(lang.getSaveSettingsYesButtonText());
		yesBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				for (Preferences pref : preferencesList)
					if (saveNameText.getText().equals(pref.getName())) {
						JOptionPane.showMessageDialog(null, lang.getSaveSettingsIdenticalNameText());
						return;
					}

				getPreferences();
				actualPreference.setName(saveNameText.getText());
				preferencesList.add(actualPreference);
				try {
					savePreferencesList();
				} catch (IOException e) {
					e.printStackTrace();
				}
				dialog.dispose();
			}
		});

		noBtn = new JButton(lang.getSaveSettingsNoButtonText());
		noBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dialog.dispose();
			}
		});

		buttons = new JPanel();
		buttons.add(yesBtn);
		buttons.add(noBtn);

		saver = new JPanel();

		BoxLayout boxLayout = new BoxLayout(saver, BoxLayout.Y_AXIS);
		saver.setLayout(boxLayout);
		saver.add(editPanel);
		saver.add(buttons);

		saveNameText.addKeyListener(new KeyAdapter() {
			int caretPos = 0;

			public void keyReleased(KeyEvent ke) {
				int key = ke.getKeyCode();
				if (!Character.isLetterOrDigit(key) && key != KeyEvent.VK_ENTER)
					return;
				if (key == KeyEvent.VK_ENTER)
					yesBtn.doClick();
				caretPos = saveNameText.getCaretPosition();
				String text = "";
				try {
					text = saveNameText.getText(0, caretPos);
				} catch (BadLocationException e) {
					e.printStackTrace();
				}

				ArrayList<String> alphabeticalList = new ArrayList<String>();

				for (Preferences pref : preferencesList)
					alphabeticalList.add(pref.getName());

				for (String name : alphabeticalList) {
					if (name.startsWith(text)) {
						saveNameText.setText(name);
						saveNameText.setSelectionEnd(caretPos + saveNameText.getText().length());
						saveNameText.moveCaretPosition(caretPos);
						return;
					}
				}
			}
		});

		JOptionPane optionPane = new JOptionPane(null, JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION,
				null, new Object[] {}, null);

		optionPane.add(saver);
		dialog.setModal(true);
		dialog.setTitle(lang.getSaveSettingsText());
		dialog.setContentPane(optionPane);

		dialog.pack();

		dialog.setVisible(true);
	}

	protected static void savePreferencesList() throws IOException {
		File file = new File(prefsFile);
		file.delete();
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(preferencesList);
//		actualPreference.writeExternal(oos);
		oos.close();
		fos.close();
	}

	protected static void chooseHeight(int spinnerHeight) {
		// Choosing the number of square in height of the drawing area
		actualPreference.setHeight(spinnerHeight);
		createSquares();
	}

	protected static void chooseWidth(int spinnerWidth) {
		// Choosing the number of square in width of the drawing area
		actualPreference.setWidth(spinnerWidth);
		createSquares();
	}

	protected static void chooseDrawAreaColor() {
		// Chooses the color for the drawing area (background)
		actualPreference.setDrawAreaColor(JColorChooser.showDialog(null, "Draw area color", Color.magenta));
		drawArea.setBackground(actualPreference.getDrawAreaColor());
	}

	protected static void chooseBlancColor() {
		// Chooses the color for the blanc square
		actualPreference.setBlancSquareColor(JColorChooser.showDialog(null, "Blanc square color", Color.black));
		createSquares();
	}

	protected static void chooseTextColor() {
		// Choosing the color for the text written in the squares
		actualPreference.setTextSquareColor(JColorChooser.showDialog(null, "Text color", null));
		createSquares();
	}

	protected static void chooseNumberTextColor() {
		// Choosing the color for the numbering of the squares
		actualPreference.setNumberingColor(JColorChooser.showDialog(null, "Text color", null));
		createSquares();
	}

	protected static void chooseSelColor() {
		// Choosing the color for the background of the selected squares
		actualPreference.setSelColor(JColorChooser.showDialog(null, "Selected square color", null));
		createSquares();
	}

	protected static void chooseOutSquareColor() {
		// Choosing the color for the outline of the squares
		actualPreference.setOutSquareColor(JColorChooser.showDialog(null, "Lines color", null));
		createSquares();
	}

	protected static void chooseBckSquareColor() {
		// Choosing the color for the background of the not-selected squares
		actualPreference.setBckSquareColor(JColorChooser.showDialog(null, "Square fill color", null));
		createSquares();
	}

	protected static void exitProgram() {

		// Exits the program

		System.exit(0);

	}

	private static void gamesListerDialog(String windowTitle, String actionText, char actionMnemonic, ActOnGame command) {
		JPanel lister, buttons;
		JButton action, cancel;
		JDialog dialog = new JDialog();
		Dimension dim;
		Session session = factory.openSession();
		List<Game> games = session.createQuery("from games").getResultList();
		session.close();
		
		String[] columns = {lang.getGameId(), lang.getGameNameText(), lang.getGameThemeText(), lang.getGameSize(),
				lang.getGameDescriptionText(), lang.getGamePreferences(), lang.getGameImage(),
				lang.getGameRating(), lang.getCompleteLabelText()};
		Object[][] data = new Object[games.size()][columns.length];
		for (int i = 0; i < data.length; i++) {
//			data[i][0] = games.get(i).getGameId();
			data[i][1] = games.get(i).getName();
			data[i][2] = games.get(i).getTheme();
			data[i][3] = games.get(i).getSize();
			data[i][4] = games.get(i).getDescription();
			data[i][5] = games.get(i).getPreference().getName();
			data[i][6] = games.get(i).getImage() == null ? " " : games.get(i).getImage().getName();
			data[i][7] = games.get(i).getRating();
			data[i][8] = games.get(i).isComplete();
		}
		
		JTable table = new JTable(data, columns);
		DefaultTableModel tableModel = new DefaultTableModel(data, columns) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(tableModel);
		JScrollPane tableContainer = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		dim = table.getPreferredSize();
		table.setSize(dim.width, dim.height);
		
		action = new JButton(actionText);
		action.setMnemonic(actionMnemonic);
		action.setEnabled(false);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (table.rowAtPoint(me.getPoint()) == -1) {
					table.clearSelection();
					action.setEnabled(false);
				}
				else
					action.setEnabled(true);
			}
		});
		action.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				actOnTheGame(games.get(table.getSelectedRow()), command);
				dialog.dispose();
			}
		});
		
		cancel = new JButton(lang.getCancelButtonText());
		cancel.setMnemonic(lang.getCancelButtonMnemonic());
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dialog.dispose();
			}
		});
		
		buttons = new JPanel();
		buttons.add(action);
		buttons.add(cancel);
		
		lister = new JPanel();
		lister.add(tableContainer);
		lister.add(buttons);
		BoxLayout boxLayout = new BoxLayout(lister, BoxLayout.Y_AXIS);
		lister.setLayout(boxLayout);
		JOptionPane optionPane = new JOptionPane(null, JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION,
				null, new Object[] {}, null);

		optionPane.add(lister);
		dialog.setModal(true);
		dialog.setTitle(windowTitle);
		dialog.setContentPane(optionPane);

		dialog.pack();

		dialog.setVisible(true);
	}
	
	protected static void actOnTheGame(Game game, ActOnGame action) {
		switch (action) {
			case DELETE: deleteTheGame(game); break;
			case LOAD: loadTheGame(game); break;
			default: ;
		}
	}
	
	protected static void deleteTheGame(Game game) {
		Session session = factory.openSession();
		session.beginTransaction();
		session.delete(game);
		session.getTransaction().commit();
		session.close();
	}

	private static void saveGameDialog() {
		JPanel gameSaver, saveContainer, namePanel, themePanel, sizePanel, descriptionPanel, preferencePanel,
				imagePanel, completePanel, buttonsPanel;
		JLabel nameLabel, themeLabel, sizeLabel, descriptionLabel, preferenceLabel, imageLabel,
				completeLabel;
		JTextField nameField, themeField, sizeField, preferenceField, imageField;
		JTextArea descriptionArea;
		JCheckBox completeCheckBox;
		JScrollPane saveScroller, descriptionScroller;
		JButton save, cancel;
		JDialog dialog = new JDialog();

		nameLabel = new JLabel(lang.getNameLabelText());
		nameField = new JTextField(15);
		nameField.setText(currentGame.getName());
		namePanel = new JPanel();
		namePanel.add(nameLabel);
		namePanel.add(nameField);
		
		themeLabel = new JLabel(lang.getGameThemeText());
		themeField = new JTextField(15);
		themeField.setText(currentGame.getTheme());
		themePanel = new JPanel();
		themePanel.add(themeLabel);
		themePanel.add(themeField);
		
		sizeLabel = new JLabel(lang.getSizeLabelText());
		sizeField = new JTextField(10);
		sizeField.setText(currentGame.getSize());
		sizeField.setEditable(false);
		sizePanel = new JPanel();
		sizePanel.add(sizeLabel);
		sizePanel.add(sizeField);
		
		descriptionLabel = new JLabel(lang.getDescriptionLabelText());
		descriptionArea = new JTextArea(3, 30);
		descriptionArea.setLineWrap(true);
		descriptionArea.setText(currentGame.getDescription());
		descriptionScroller = new JScrollPane(descriptionArea);
		descriptionScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		descriptionPanel = new JPanel();
		descriptionPanel.add(descriptionLabel);
		descriptionPanel.add(descriptionScroller);
		
		preferenceLabel = new JLabel(lang.getPreferenceLabelText());
		preferenceField = new JTextField(15);
		preferenceField.setText(currentGame.getPreference().getName());
		preferencePanel = new JPanel();
		preferencePanel.add(preferenceLabel);
		preferencePanel.add(preferenceField);
		
		imageLabel = new JLabel(lang.getImageLabelText());
		imageField = new JTextField(25);
		imageField.setText(currentGame.getImage() == null ? null : currentGame.getImage().getName());
		imageField.setEditable(false);
		imagePanel = new JPanel();
		imagePanel.add(imageLabel);
		imagePanel.add(imageField);
		
		completeLabel = new JLabel(lang.getCompleteLabelText());
		completeCheckBox = new JCheckBox();
		checkCompletion();
		completeCheckBox.setSelected(currentGame.isComplete());
		completeCheckBox.setEnabled(false);
		completePanel = new JPanel();
		completePanel.add(completeLabel);
		completePanel.add(completeCheckBox);
		
		save = new JButton(lang.getSaveButtonText());
		save.setMnemonic(lang.getSaveButtonMnemonic());
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				saveCurrentGame(nameField.getText(), themeField.getText(), descriptionArea.getText(),
					preferenceField.getText().trim().isEmpty() 
					? actualPreference.getName()
					: preferenceField.getText());
				dialog.dispose();
			}
		});
		
		cancel = new JButton(lang.getCancelButtonText());
		cancel.setMnemonic(lang.getCancelButtonMnemonic());
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dialog.dispose();
			}
		});
		
		buttonsPanel = new JPanel();
		buttonsPanel.add(save);
		buttonsPanel.add(cancel);
		
		gameSaver = new JPanel();
		BoxLayout boxSaver = new BoxLayout(gameSaver, BoxLayout.Y_AXIS);
		gameSaver.setLayout(boxSaver);
		
		gameSaver.add(namePanel);
		gameSaver.add(themePanel);
		gameSaver.add(sizePanel);
		gameSaver.add(descriptionPanel);
		gameSaver.add(preferencePanel);
		gameSaver.add(imagePanel);
		gameSaver.add(completePanel);
		
		saveScroller = new JScrollPane(gameSaver);
		saveScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		saveContainer = new JPanel();
		BoxLayout boxContainer = new BoxLayout(saveContainer, BoxLayout.Y_AXIS);
		saveContainer.setLayout(boxContainer);
		saveContainer.add(gameSaver);
		saveContainer.add(buttonsPanel);
		
		JOptionPane optionPane = new JOptionPane(null, JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION,
				null, new Object[] {}, null);

		optionPane.add(saveContainer);
		dialog.setModal(true);
		dialog.setTitle(lang.getSaveGameText());
		dialog.setContentPane(optionPane);

		dialog.pack();

		dialog.setVisible(true);
	}

	protected static void saveCurrentGame(String name, String theme, String description, String preference) {
		currentGame.setName(name);
		currentGame.setTheme(theme);
		currentGame.setDescription(description);
		currentGame.setPreferenceName(preference);
		for (int i = 0; i < squares.length; i++) {
			currentGame.setLetter(i, squares[i].isBlanc() ? ' ' : squares[i].getLetter());
		}
		currentGame.setAccessTime(new Timestamp(System.currentTimeMillis()));
		Session session = factory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(currentGame);
		List<Game> games = session.createQuery("from games where accessTime = (select max(accessTime) from games)").getResultList();
		session.getTransaction().commit();
		session.close();
//		loaded = games.get(0).getGameId();
	}

	private static void checkCompletion() {
		for (int i = 0; i < squares.length; i++)
			if (squares[i].getLetter() == 0) {
				currentGame.setComplete(false);
				break;
			}
	}

	private static void loadImage(JPanel panel) {
		Graphics g = panel.getGraphics();
		if (currentGame.getImage() != null)
		try {
			g.drawImage(ImageIO.read(currentGame.getImage()), 0, 0, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected static void continueGame() {
		// TODO Continuing the last unfinished game
		Session session = factory.openSession();
		List<Game> games = (List<Game>) session.createQuery("from games where accessTime = (select max(accessTime) from games) and complete = false").getResultList();
		if (games.size() == 0)
			noIncompleteGameMessage();
		else 
			loadTheGame(games.get(0));
		session.close();
	}

	private static void noIncompleteGameMessage() {
		// TODO Auto-generated method stub
		JPanel messageHolder, buttons;
		JLabel message = new JLabel(lang.getNoIncompleteGameText());
		JButton ok = new JButton(lang.getOKButtonText());
		ok.setMnemonic(lang.getOKButtonMnemonic());
		JDialog dialog = new JDialog();
		
		ok.setMnemonic(lang.getOKButtonMnemonic());
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dialog.dispose();
			}
		});
		
		buttons = new JPanel();
		buttons.add(ok);
		
		messageHolder = new JPanel();
		messageHolder.add(message);
		messageHolder.add(buttons);
		BoxLayout box = new BoxLayout(messageHolder, BoxLayout.Y_AXIS);
		messageHolder.setLayout(box);
		
		JOptionPane optionPane = new JOptionPane(null, JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION,
				null, new Object[] {}, null);

		optionPane.add(messageHolder);
		dialog.setModal(true);
		dialog.setTitle(lang.getContinueGameText());
		dialog.setContentPane(optionPane);

		dialog.pack();

		dialog.setVisible(true);
	}

	public static void newGame() {
		currentGame = new Game();
		actualPreference = new Preferences();
		selectedSquare = -1;
		createSquares();
		populateScrollPane();
		Editor.getNumberField().setText("");
		Editor.getDirectionSpinner().setValue(lang.getHorizontal());
		Editor.getSolutionField().setText("");
		Editor.getDefinitionArea().setText("");
		drawArea.repaint();
		loaded = -1;
	}

	private static void loadMenusText() {
		fileMenu.setText(lang.getFileText());
		fileMenu.setMnemonic(lang.getFileMnemonic());
		newItem.setText(lang.getNewText());
		newItem.setMnemonic(lang.getNewMnemonic());
		openItem.setText(lang.getOpenText());
		openItem.setMnemonic(lang.getOpenMnemonic());
		continueItem.setText(lang.getContinueText());
		continueItem.setMnemonic(lang.getContinueMnemonic());
		saveItem.setText(lang.getSaveText());
		saveItem.setMnemonic(lang.getSaveMnemonic());
		deleteItem.setText(lang.getDeleteText());
		deleteItem.setMnemonic(lang.getDeleteMnemonic());
		exitItem.setText(lang.getExitText());
		exitItem.setMnemonic(lang.getExitMnemonic());
		settingsMenu.setText(lang.getSettingsText());
		settingsMenu.setMnemonic(lang.getSettingsMnemonic());
		colorsMenu.setText(lang.getColorsText());
		colorsMenu.setMnemonic(lang.getColorsMnemonic());
		squareBckColorMenu.setText(lang.getSquareBackgroundText());
		squareBckColorMenu.setMnemonic(lang.getSquareBackgroundMnemonic());
		squareOutColorMenu.setText(lang.getSquareOutlineText());
		squareOutColorMenu.setMnemonic(lang.getSquareOutlineMnemonic());
		squareSelColorMenu.setText(lang.getSelectedSquareColorText());
		squareSelColorMenu.setMnemonic(lang.getSelectedSquareColorMnemonic());
		squareTextColorMenu.setText(lang.getSquareTextColorText());
		squareTextColorMenu.setMnemonic(lang.getSquareTextColorMnemonic());
		numberTextColorMenu.setText(lang.getSquareNumberColor());
		numberTextColorMenu.setMnemonic(lang.getSquareNumberColorMnemonic());
		squareBlancColorMenu.setText(lang.getBlancSquareColorText());
		squareBlancColorMenu.setMnemonic(lang.getBlancSquareColorMnemonic());
		drawAreaColorMenu.setText(lang.getDrawAreaColorText());
		drawAreaColorMenu.setMnemonic(lang.getDrawAreaColorMnemonic());
		sizeMenu.setText(lang.getSizeText());
		sizeMenu.setMnemonic(lang.getSizeMnemonic());
		widthMenu.setText(lang.getWidthInSquaresText());
		widthMenu.setMnemonic(lang.getWidthInSquaresMnemonic());
		heightMenu.setText(lang.getHeightInSquaresText());
		heightMenu.setMnemonic(lang.getHeightInSquaresMnemonic());
		loadImageItem.setText(lang.getLoadImageText());
		loadImageItem.setMnemonic(lang.getLoadImageMnemonic());
		saveSettingsItem.setText(lang.getSaveSettingsText());
		saveSettingsItem.setMnemonic(lang.getSaveSettingsMnemonic());
		loadSettingsItem.setText(lang.getLoadSettingsText());
		loadSettingsItem.setMnemonic(lang.getLoadSettingsMnemonic());

		helpMenu.setText(lang.getHelpText());
		helpMenu.setMnemonic(lang.getHelpMnemonic());
		langMenu.setText(lang.getLanguageText());
		langMenu.setMnemonic(lang.getLanguageMnemonic());
		langItem.setText(lang.getLanguageItemText());
		langItem.setMnemonic(lang.getLanguageItemMnemonic());

		menuBar.setSize(menuBar.getPreferredSize());
	}

	public static void loadPopupMenusText(boolean blanc) {
		blancNonBlancItem.setText(blanc ? lang.getSetNonBlancText() : lang.getSetBlancText());
		blancNonBlancItem.setMnemonic(blanc ? lang.getSetNonBlancMnemonic() : lang.getSetBlancMnemonic());
		multipleSelectionItem
				.setText(!tracking ? lang.getMultipleSelectionText() : lang.getStopMultipleSelectionText());
		multipleSelectionItem
				.setMnemonic(!tracking ? lang.getMultipleSelectionMnemonic() : lang.getStopMultipleSelectionMnemonic());
	}

	public void paint(Graphics g) {
		// We need to determine the width and height of the text representing the
		// numbering
		// so we can accurately draw them on screen
		FontMetrics fm = getFontMetrics(g.getFont());
		FontRenderContext context = fm.getFontRenderContext();
		String rowNumber, columnNumber, text;

		super.paint(g);

		// First we load the image
		if (currentGame.getImage() != null)
		try {
			g.drawImage(ImageIO.read(currentGame.getImage()), 0, 0, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		else 
			super.setBackground(actualPreference.getDrawAreaColor());

		Font font = new Font(getFont().getName(), getFont().getStyle(), 15);

		g.setFont(font);

		// Then setting the background color for the work area
		
		g.setColor(actualPreference.getBckSquareColor());
		g.fillRect(squares[0].getX(), squares[0].getY(), actualPreference.getWidth() * side,
				actualPreference.getHeight() * side);

		// Now draw the side numbers
		
		for (int i = 0; i < squares.length; i++) {
			columnNumber = "";
			rowNumber = "";
			// fonts are scaled in such a way that it is impossible for me to know
			// how they will actually look on screen so I added numbers after many
			// tries
			if (i < actualPreference.getWidth()) {
				columnNumber += (i + 1) / 10 == 0 ? "" : (i + 1) / 10;
				columnNumber += (i + 1) % 10;
			}

			g.setColor(actualPreference.getNumberingColor());
			g.drawString("" + columnNumber.toString(),
					squares[i].getX()
							+ (side - (g.getFont().getStringBounds(columnNumber, context).getBounds().width)) / 2,
					squares[0].getY() - 1);

			// dividing i with actualPreference.getWidth() can show us that we advance a row
			if (i % actualPreference.getWidth() == 0) {
				rowNumber += (i / actualPreference.getWidth() + 1) / 10 == 0 ? ""
						: (i / actualPreference.getWidth() + 1) / 10;
				rowNumber += (i / actualPreference.getWidth() + 1) % 10;
			}
			g.setColor(actualPreference.getNumberingColor());
			g.drawString("" + rowNumber.toString(),
					squares[0].getX() - g.getFont().getStringBounds(rowNumber, context).getBounds().width - 1,
					+squares[i].getY() + 27);

			// and we do the square drawing
			g.setColor(actualPreference.getOutSquareColor());
			g.drawRect(squares[i].getX(), squares[i].getY(), side, side);
		}

		// And now doing the filling of the squares
		
		g.setColor(actualPreference.getBckSquareColor());
		if (multiple) {
			for (int i = 0; i < selection.length; i++) {
				if (selection[i] == -1)
					break;
				g.setColor(actualPreference.getSelColor());
				g.fillRect(squares[selection[i]].getX() + 1, squares[selection[i]].getY() + 1, side - 1, side - 1);
			}
		} else {
			if (selectedSquare != -1 && squares[selectedSquare].isSelected()) {
				g.setColor(actualPreference.getSelColor());
				g.fillRect(squares[selectedSquare].getX() + 1, squares[selectedSquare].getY() + 1, side - 1, side - 1);
			}
		}
		for (int i = 0; i < squares.length; i++) {
			if (squares[i].isBlanc()) {
				g.setColor(actualPreference.getBlancSquareColor());
				g.fillRect(squares[i].getX() + 1, squares[i].getY() + 1, side - 1, side - 1);
			}
			if (squares[i].getLetter() != 0) {
				g.setColor(actualPreference.getTextSquareColor());
				g.drawString(String.valueOf(squares[i].getLetter()).toUpperCase(),
						squares[i].getX()
								+ (side - (g.getFont().getStringBounds(String.valueOf(squares[i].getLetter()), context)
										.getBounds().width)) / 2,
						squares[i].getY() + 22);
			}
		}
//		 g has already set the color to the text inside the square color
		g.setColor(actualPreference.getTextSquareColor());
		if ((text = Editor.getSolutionField().getText()).strip() != null && text.length() > 0) {
			for (int i = 0; i < (selection.length < text.length() ? selection.length : text.length()); i++) {
				if (selection[i] == -1)
					break;
				if (!Character.isAlphabetic(text.charAt(i)))
					break;
				g.drawString(String.valueOf(text.charAt(i)).toUpperCase(),
						squares[selection[i]].getX()
								+ (side - (g.getFont().getStringBounds(String.valueOf(text.charAt(i)), context)
										.getBounds().width)) / 2,
						squares[selection[i]].getY() + 22);
			}
		}
		g.setColor(actualPreference.getTextSquareColor());
//		g.drawString(squareInfo, 10, 10);
	}

	private static void paintInfoPanel(JPanel panel, JComboBox<String> preferencesBox) {
		// Draws one rectangle with bckSquareColor, one with selColor and one with
		// blancColor,
		// surrounded by outSquareColor, containing one letter T each, and writes the
		// number of
		// width squares and height squares

		Graphics g = panel.getGraphics();
		g.setColor(tempPreference.getDrawAreaColor());

		g.fillRect(0, 0, panel.getWidth(), panel.getHeight());
		g.setColor(Color.black);
		g.drawString(lang.getNormalSquareText(), 10, 20);
		g.setColor(tempPreference.getOutSquareColor());
		g.drawRect(20, 30, 25, 25);
		g.setColor(tempPreference.getBckSquareColor());
		g.fillRect(21, 31, 24, 24);
		g.setColor(tempPreference.getTextSquareColor());
		g.drawString("T", 30, 47);

		g.setColor(Color.black);
		g.drawString(lang.getSelectedSquareText(), 65, 20);
		g.setColor(tempPreference.getOutSquareColor());
		g.drawRect(80, 30, 25, 25);
		g.setColor(tempPreference.getSelColor());
		g.fillRect(81, 31, 24, 24);
		g.setColor(tempPreference.getTextSquareColor());
		g.drawString("T", 90, 47);

		g.setColor(Color.black);
		g.drawString(lang.getBlancSquareText(), 130, 20);
		g.setColor(tempPreference.getOutSquareColor());
		g.drawRect(132, 30, 25, 25);
		g.setColor(tempPreference.getBlancSquareColor());
		g.fillRect(133, 31, 24, 24);

		g.setColor(Color.black);
		g.drawString(lang.getWidthText() + tempPreference.getWidth(), 10, 75);
		g.drawString(lang.getHeightText() + tempPreference.getHeight(), 10, 95);
	}

	public static int getCharIndex() {
		return charIndex;
	}

	public static void setCharIndex(int charIndex) {
		Workbench.charIndex = charIndex;
	}

	public static Square[] getSquares() {
		return squares;
	}

	public static boolean isMultiple() {
		return multiple;
	}

	public static int[] getSelection() {
		return selection;
	}

	public static Workbench getDrawArea() {
		return drawArea;
	}

	public static int getSelectedSquaresNumber() {
		return selectedSquaresNumber;
	}

	public static JButton getSaveButton() {
		return saveButton;
	}

	public static Preferences getActualPreference() {
		return actualPreference;
	}

	public static void setActualPreference(Preferences actualPreference) {
		Workbench.actualPreference = actualPreference;
	}

	public static void loadTheGame(Game game) {
		currentGame = game;
		loaded = currentGame.getGameId();
		actualPreference = currentGame.getPreference();
		createSquares();
		
		for (int i = 0; i < currentGame.getLetters().length; i++) {
			if (currentGame.getLetters()[i] == ' ' ) {
				squares[i].setLetter((char) 0);
				squares[i].setBlanc(true);
			} else
			squares[i].setLetter(currentGame.getLetters()[i]);
		}
		copyLetters();
		drawArea.repaint();
		loaded = game.getGameId();
		populateScrollPane();
		if (currentGame.getSolutionsDefinitions().size() == 0)
			return;
		GameCoreData data = currentGame.getSolutionsDefinitions().get(0);
		Editor.getNumberField().setText(String.valueOf(
				data.getDirection().equals(lang.getHorizontal())
				? data.getNumber() % actualPreference.getWidth() + 1
				: data.getNumber() / actualPreference.getWidth() + 1));
		Editor.getDefinitionArea().setText(data.getDefinition());
		Editor.getDirectionSpinner().setValue(lang.getHorizontal());
		Editor.getSolutionField().setText(currentGame.getSolution(0));
	}

	private static void copyLetters() {
		for (int i = 0; i < currentGame.getLetters().length; i++) {
			if (currentGame.getLetters()[i] == ' ') {
				squares[i].setLetter((char)0);
				squares[i].isBlanc();
			} else
				squares[i].setLetter(currentGame.getLetters()[i]);
		}
	}
}