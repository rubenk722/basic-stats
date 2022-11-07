package gui;

import java.util.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import model.BasicStatsModel;
import gui.view.AddNumView;
import gui.view.CountView;
import gui.view.MeanView;
import gui.view.MedianView;
import gui.view.NumbersView;
import gui.view.ResetView;
import gui.view.View;


/**
 * Create a simple GUI that includes:
 * - a text field and a button that allows the user to enter numbers.
 * - a button that allows the user to clear all entered numbers.
 * - a panel with labels and text fields for count, median, and mean.
 * - a text area that shows all numbers.
 *
 * For the MVC architecture pattern, these are the views and controllers.
 */
public class BasicStatsGUI implements View
{
    public static final String APP_TITLE = "Simple stats";
    
    private static BasicStatsModel model = new BasicStatsModel();
	private CountView countView = new CountView();
	private MedianView medianView = new MedianView();
	private MeanView meanView = new MeanView();
	private NumbersView numbersView = new NumbersView();
	private AddNumView addNumView = new AddNumView(this, model);
	private ResetView resetView = new ResetView(this, model);
    private JFrame jfMain = new JFrame(APP_TITLE);

    public BasicStatsGUI() {	
	// Create the main frame of the application, and set size and position
	jfMain.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	jfMain.setSize(600,400);
	jfMain.setLocationRelativeTo(null);
	
	// Panel that shows stats about the numbers
	JPanel jpStats = new JPanel(new FlowLayout(FlowLayout.CENTER));
	jpStats.add(new JLabel("Numbers:"));
	jpStats.add(countView.getTextField());
	jpStats.add(new JLabel("Median:"));
	jpStats.add(medianView.getTextField());
	jpStats.add(new JLabel("Mean:"));
	jpStats.add(meanView.getTextField());
	jfMain.getContentPane().add(jpStats, BorderLayout.CENTER);
	
	// TextArea that shows all the numbers
	jfMain.getContentPane().add(numbersView.getTextArea(), BorderLayout.SOUTH);

	JPanel jpInput = new JPanel(new FlowLayout(FlowLayout.CENTER));
	jpInput.add(addNumView.getTextField());
	jpInput.add(addNumView.getButton());
	jpInput.add(resetView.getButton());
	
	
	jfMain.getContentPane().add(jpInput, BorderLayout.NORTH);
    }

	//updates all views to their corresponding values upon adding numbers
    public void update(BasicStatsModel model) {
		addNumView.update(model);
		numbersView.update(model);
		countView.update(model);
		medianView.update(model);
		meanView.update(model);
    }

	//Resets all views when called
	public void Reset() {
		addNumView.Reset();
		numbersView.Reset();
		countView.Reset();
		medianView.Reset();
		meanView.Reset();

	}

    public void show() {
	// Show the frame
	jfMain.setVisible(true);
    }
    
}
