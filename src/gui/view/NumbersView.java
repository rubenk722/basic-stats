package gui.view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.BasicStatsModel;


public class NumbersView implements View
{
    /** TextArea that shows all the numbers */
    private JTextArea jtaNumbers;

    
    public NumbersView(JFrame jfMain) {
	super();

	jtaNumbers = new JTextArea(10, 5);
	jtaNumbers.setEditable(false);

	jfMain.getContentPane().add(jtaNumbers, BorderLayout.SOUTH);
    }

    public void update(BasicStatsModel model) {
	// Reset
	if (model.getArrayDouble().length == 0) {
	    jtaNumbers.setText("");
	}
	// Add number
	else {
	    // Update the displayed list of numbers
		jtaNumbers.setText("");
	    for (double num : model.getArrayDouble()) {
	    	jtaNumbers.append(num + ",");
		}
	}
    }

    public String getStringValue() {
	// This is a snapshot of the current value to support
	// testability and debuggability without breaking the encapsulation.
	return jtaNumbers.getText();
    }
}
