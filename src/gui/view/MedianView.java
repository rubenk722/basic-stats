package gui.view;

import javax.swing.JTextField;

import gui.BasicStats;
import model.BasicStatsModel;

public class MedianView implements View{

    private JTextField jtfMedian;

    public MedianView() {
        jtfMedian = new JTextField(5);
	    jtfMedian.setEditable(false);
    }

    @Override
    public void update(BasicStatsModel model) {
        if (model.getArrayDouble().length == 0) {
            jtfMedian.setText("");
        }
        else {          
            // Compute and set the median
            double median = BasicStats.median(model.getArrayDouble());
            jtfMedian.setText("" + median);	    
        }
    }

    @Override
    public void Reset() {
        jtfMedian.setText("");
    }

    public JTextField getTextField() {
        return jtfMedian;
    }
}
