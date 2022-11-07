package gui.view;

import javax.swing.JTextField;

import gui.BasicStats;
import model.BasicStatsModel;

public class MeanView implements View {
    
    private JTextField jtfMean;

    public MeanView() {
        jtfMean = new JTextField(5);
	    jtfMean.setEditable(false);
    }

    @Override
    public void update(BasicStatsModel model) {
        if (model.getArrayDouble().length == 0) {
	        jtfMean.setText("");
        }  else {
            // Compute and set the mean
            double mean = BasicStats.mean(model.getArrayDouble());
            jtfMean.setText("" + mean);
        }
    }

    @Override
    public void Reset() {
        jtfMean.setText("");
    }

    public JTextField getTextField() {
        return jtfMean;
    }
}
