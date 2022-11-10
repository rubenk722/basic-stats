package gui.view;

import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;

import model.BasicStatsModel;

public class NumbersView implements View{
    
    private JTextArea jtaNumbers;

    public NumbersView() {
        jtaNumbers = new JTextArea(10,50);
	    jtaNumbers.setEditable(false);
    }

    @Override
    public void update(BasicStatsModel model) {
        if (model.getArrayDouble().length == 0) {
            jtaNumbers.setText("");
        }
        else if (model.getArrayDouble().length == 1) {
            double num = model.getArrayDouble()[model.getArrayDouble().length - 1];
            jtaNumbers.append(num + "");
        } 
        else {
            // Update the displayed list of numbers
            double num = model.getArrayDouble()[model.getArrayDouble().length - 1];
            jtaNumbers.append(", " + num); 
        }
    }

    @Override
    public void Reset() {
        jtaNumbers.setText("");
    }

    public JTextArea getTextArea() {
        return jtaNumbers;
    }
}
