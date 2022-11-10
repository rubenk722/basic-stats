import org.junit.Test;

import gui.BasicStats;
import gui.BasicStatsGUI;
import gui.view.AddNumView;
import model.BasicStatsModel;

import static org.junit.Assert.*;

import gui.BasicStats;

public class BasicStatsTest {
    private static double EPS = 1e-9;

    @Test
    public void testCentralTendency() {
        double[] numbers = {2, 2, 3, 3, 3, 4, 4};
        double mean   = BasicStats.mean(numbers);
        assertEquals (3, mean, EPS);
        double median = BasicStats.median(numbers);
        assertEquals (3, median, EPS);
        double mode   = BasicStats.mode(numbers);
        assertEquals (3, mode, EPS);
    }

    @Test
    public void testMedian() {
      //Median should be 8.0 since size is even
      /* double[] numbers = {1, 4, 7, 9, 11, 21}; */
      double[] numbers = {9, 11, 1, 4, 7, 21};

      double median = BasicStats.median(numbers);
      assertEquals(8.0, median, EPS);

      //Median should be 7 since size is odd
      double[] numbers2 = {9, 1, 4, 7, 21};
      median = BasicStats.median(numbers2);
      assertEquals(7, median, EPS);

      //Median should be 3 since size is 1
      double[] numbers3 = {3};
      median = BasicStats.median(numbers3);
      assertEquals(3, median, EPS);

      //Median should be 0 since size is 0
      double[] numbers4 = {};
      median = BasicStats.median(numbers4);
      assertEquals(0, median, EPS);
    }

    @Test
    public void testMode() {
      //Mode should be 1 since we are taking the first mode
      double[] numbers = {1, 4, 7, 9, 11, 21};
      double mode   = BasicStats.mode(numbers);
      assertEquals (1, mode, EPS);

      //Mode should be 4
      double[] numbers2 = {1, 4, 4, 7, 9, 11, 21};
      mode   = BasicStats.mode(numbers2);
      assertEquals (4, mode, EPS);

      //Mode should be 7
      double[] numbers3 = {4, 4, 7, 7, 7, 11, 11};
      mode   = BasicStats.mode(numbers3);
      assertEquals (7, mode, EPS);

      //Mode should be 7
      double[] numbers4 = {7};
      mode   = BasicStats.mode(numbers4);
      assertEquals (7, mode, EPS);

      //Mode should be 0
      double[] numbers5 = {};
      mode   = BasicStats.mode(numbers5);
      assertEquals (0, mode, EPS);
    }

    @Test
    public void testInitialConfiguration() {
      BasicStatsGUI gui = new BasicStatsGUI();
      BasicStatsModel model = gui.getModel();

      //initial configuration requires all text fields and the model to be empty.
      assertEquals(gui.getAddNumView().getText(), "");
      assertEquals(gui.getCountView().getText(), "");
      assertEquals(gui.getMedianView().getText(), "");
      assertEquals(gui.getMeanView().getText(), "");
      assertEquals(gui.getMaxView().getText(), "");
      assertEquals(gui.getNumbersView().getText(), "");
      assertEquals(model.getArrayDouble().length, 0);
    }

    @Test
    public void testAddNumSuccessful() {
      BasicStatsGUI gui = new BasicStatsGUI();
      BasicStatsModel model = gui.getModel();
      gui.getAddNumView().setText("5");
      gui.getAddNumButton().doClick();
      double[] modelArr = model.getArrayDouble();

      //The model should have one element in it
      assertEquals(modelArr.length, 1);

      //the addNumber text box should erase its text after the button is pressed
      assertEquals(gui.getAddNumView().getText(), "");
      assertEquals(gui.getCountView().getText(), "1");
      assertEquals(gui.getMedianView().getText(), "5.0");
      assertEquals(gui.getMeanView().getText(), "5.0");
      assertEquals(gui.getMaxView().getText(), "5.0");
      assertEquals(gui.getNumbersView().getText(), "5.0");
    }

    @Test
    public void testAddNumFails() {
      BasicStatsGUI gui = new BasicStatsGUI();
      BasicStatsModel model = gui.getModel();
      boolean exceptionThrown = false;
      
      //tests error handling for a non-number vlue
      try{
        gui.getAddNumView().setText("a");
        gui.getAddNumButton().doClick();
      } catch (NumberFormatException e){
        exceptionThrown = true;
      }

      assertTrue(exceptionThrown);

      double[] modelArr = model.getArrayDouble();
      //model should not be updated
      assertEquals(modelArr.length, 0);
      //add number text field should contain the value after the exception is thrown as nothing is updated
      assertEquals(gui.getAddNumView().getText(), "a");
      //all other text fields remain unchanged
      assertEquals(gui.getCountView().getText(), "");
      assertEquals(gui.getMedianView().getText(), "");
      assertEquals(gui.getMeanView().getText(), "");
      assertEquals(gui.getMaxView().getText(), "");
      assertEquals(gui.getNumbersView().getText(), "");
    }

    @Test
    public void testComputeMaxFailsInputValidtion() {
      BasicStatsModel model = new BasicStatsModel();
      double[] modelArr = model.getArrayDouble();

      boolean exceptionThrown = false;

      //if an empty model array is passed to max, then an exception should be thrown
      try {
        BasicStats.max(modelArr);
      } catch (ArrayIndexOutOfBoundsException e) {
        exceptionThrown = true;
      }

      assertTrue(exceptionThrown);
    }

    @Test
    public void testComputeMaxSuccessful() {
      BasicStatsModel model = new BasicStatsModel();
      model.addNumber(1.0);
      model.addNumber(5.0);
      model.addNumber(3.0);
      model.addNumber(9.0);
      model.addNumber(1.0);
      double[] modelArr = model.getArrayDouble();

      boolean exceptionThrown = false;

      //if an empty model array is passed to max, then an exception should be thrown
      try {
        BasicStats.max(modelArr);
      } catch (ArrayIndexOutOfBoundsException e) {
        exceptionThrown = true;
      }

      //exception should not be thrown
      assertFalse(exceptionThrown);
      //max should be 9.0
      assertEquals(BasicStats.max(modelArr), 9.0, 0);
    }

    @Test
    public void testResetSuccessful() {
      BasicStatsGUI gui = new BasicStatsGUI();

      //simulating adding numbers
      BasicStatsModel model = gui.getModel();
      gui.getAddNumView().setText("1");
      gui.getAddNumButton().doClick();
      gui.getAddNumView().setText("2");
      gui.getAddNumButton().doClick();
      gui.getAddNumView().setText("3");
      gui.getAddNumButton().doClick();
      
      //simulating a click of the reset button
      gui.getResetButton().doClick();

      //model and views should all be empty
      double[] modelArr = model.getArrayDouble();

      assertEquals(modelArr.length, 0);

      assertEquals(gui.getAddNumView().getText(), "");
      assertEquals(gui.getCountView().getText(), "");
      assertEquals(gui.getMedianView().getText(), "");
      assertEquals(gui.getMeanView().getText(), "");
      assertEquals(gui.getMaxView().getText(), "");
      assertEquals(gui.getNumbersView().getText(), "");
    }

    @Test
    public void testMaxSuccessfulForArrayOfSameNumbers() {
      BasicStatsModel model = new BasicStatsModel();
      model.addNumber(5.0);
      model.addNumber(5.0);
      model.addNumber(5.0);
      model.addNumber(5.0);
      model.addNumber(5.0);
      double[] modelArr = model.getArrayDouble();

      assertEquals(BasicStats.max(modelArr), 5.0, 0);
    }

    @Test
    public void testResetSuccessfulAfterInvalidInput() {
      BasicStatsGUI gui = new BasicStatsGUI();

      //simulating adding numbers
      BasicStatsModel model = gui.getModel();
      boolean exceptionThrown = false;
      gui.getAddNumView().setText("1");
      gui.getAddNumButton().doClick();
      gui.getAddNumView().setText("2");
      gui.getAddNumButton().doClick();

      //simulating exception throw at invalid input
      try {
        gui.getAddNumView().setText("a");
        gui.getAddNumButton().doClick();
      } catch (NumberFormatException e) {
        exceptionThrown = true;
      }
      
      assertTrue(exceptionThrown);

      //simulating a click of the reset button
      gui.getResetButton().doClick();

      //model and views should all be empty
      double[] modelArr = model.getArrayDouble();

      assertEquals(modelArr.length, 0);

      assertEquals(gui.getAddNumView().getText(), "");
      assertEquals(gui.getCountView().getText(), "");
      assertEquals(gui.getMedianView().getText(), "");
      assertEquals(gui.getMeanView().getText(), "");
      assertEquals(gui.getMaxView().getText(), "");
      assertEquals(gui.getNumbersView().getText(), "");
    }

    @Test
    public void testNumbersTextAreaProperFormat() {
      BasicStatsGUI gui = new BasicStatsGUI();
      
      //add one number
      gui.getAddNumView().setText("5");
      gui.getAddNumButton().doClick();

      //When there is only one number added to the model, the numbers text area should display a number without a comma
      assertEquals(gui.getNumbersView().getText(), "5.0");

      //add a second number
      gui.getAddNumView().setText("5");
      gui.getAddNumButton().doClick();

      //When a second number is added to the model, the numbers text area should display a comma separated list of numbers
      assertEquals(gui.getNumbersView().getText(), "5.0, 5.0");
    }
}
