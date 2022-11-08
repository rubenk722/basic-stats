package gui.view;

import javax.swing.JTextField;

import gui.BasicStats;
import model.BasicStatsModel;

public class MaxView implements View{

    private JTextField jtfMax;

    public MaxView() {
        jtfMax = new JTextField(5);
	    jtfMax.setEditable(false);
    }

    @Override
    public void update(BasicStatsModel model) {
        if (model.getArrayDouble().length == 0) {
            jtfMax.setText("");
        }
        else {          
            // Compute and set the median
            double max = BasicStats.max(model.getArrayDouble());
            jtfMax.setText("" + max);	    
        }
    }

    @Override
    public void Reset() {
        jtfMax.setText("");
    }

    public JTextField getTextField() {
        return jtfMax;
    }
}
