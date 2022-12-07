package gui.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

import gui.BasicStatsGUI;
import model.BasicStatsModel;

public class UndoView implements View {

    private BasicStatsGUI gui;
    private JButton jbUndo;

    public UndoView(BasicStatsGUI gui, JPanel jpInput) {
        jbUndo = new JButton("Undo");
        jbUndo.setEnabled(false);
        this.gui = gui;

        jbUndo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // execute gui undo method
                gui.undo();
            }
        });
        jpInput.add(jbUndo);
    }

    @Override
    public void update(BasicStatsModel model) {
        if (model != null && model.getArrayDouble().length != 0) {
            jbUndo.setEnabled(true);
        } else {
            jbUndo.setEnabled(false);
        }
    }

    @Override
    public String getStringValue() {
        return "";
    }
    
}
