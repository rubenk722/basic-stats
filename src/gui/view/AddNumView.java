package gui.view;

import javax.swing.JButton;
import javax.swing.JTextField;

import gui.BasicStats;
import gui.BasicStatsGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.BasicStatsModel;

public class AddNumView implements View{

    private JTextField jtfNumber;
	private JButton jbAdd;

    public AddNumView(BasicStatsGUI gui, BasicStatsModel model) {
        jtfNumber = new JTextField(5);
        jbAdd = new JButton("Add Number");

        jbAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Parse input and add number to the ArrayList
                
                Double num = Double.parseDouble(jtfNumber.getText());
                model.addNumber(num);
    
                gui.update(model);
                Reset();
            }
        });
    }

    @Override
    public void update(BasicStatsModel model) {
        double arr[] = model.getArrayDouble();
        jtfNumber.setText("" + arr[arr.length - 1]);
    }

    @Override
    public void Reset() {
        jtfNumber.setText("");
    }

    public JTextField getTextField() {
        return jtfNumber;
    }

    public JButton getButton() {
        return jbAdd;
    }
}