package gui.view;

import javax.swing.JButton;
import javax.swing.plaf.basic.BasicButtonUI;

import gui.BasicStatsGUI;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import model.BasicStatsModel;

public class ResetView implements View{

    private JButton jbReset;

    public ResetView(BasicStatsGUI gui, BasicStatsModel model) {
        // Panel with a text field/button to enter numbers and a button to reset the application
        jbReset = new JButton("Reset");
        jbReset.addActionListener(new ActionListener() {
            // The interface ActionListener defines a call-back method actionPerformed,
            // which is invoked if the user interacts with the GUI component -- in this
            // case, if the user clicks the button.
            public void actionPerformed(ActionEvent e) {
                // Clear the ArrayList and all text fields
                model.reset();
    
                gui.Reset();
            }
        });
    }

    @Override
    public void update(BasicStatsModel model) {
        
    }

    @Override
    public void Reset() {

    }

    public JButton getButton() {
        return jbReset;
    }
}
