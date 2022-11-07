package gui.view;

import javax.swing.JTextField;

import model.BasicStatsModel;


public class CountView implements View {

    private int count;
    private JTextField jtfCount;

    public CountView() {
        count = 0;
        jtfCount = new JTextField(5);
	    jtfCount.setEditable(false);
    }

    @Override
    public void update(BasicStatsModel model) {
        if (model.getArrayDouble().length == 0) {
            jtfCount.setText("");
        } else {
            count = model.getArrayDouble().length;
            jtfCount.setText("" + count);
        }
    }

    @Override
    public void Reset() {
        jtfCount.setText("");
    }

    public JTextField getTextField() {
        return jtfCount;
    }
}
