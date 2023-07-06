import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CalculatorController {
    @FXML private TextField display;
    private final CalculatorBrain brain;

    public CalculatorController() {
        this.brain = new CalculatorBrain();
    }

    private void refreshDisplay() {
        display.setText(brain.getState());
    }

    public void onPress9() {onPressNumber(9);}
    public void onPress8() {onPressNumber(8);}
    public void onPress7() {onPressNumber(7);}
    public void onPress6() {onPressNumber(6);}
    public void onPress5() {onPressNumber(5);}
    public void onPress4() {onPressNumber(4);}
    public void onPress3() {onPressNumber(3);}
    public void onPress2() {onPressNumber(2);}
    public void onPress1() {onPressNumber(1);}
    public void onPress0() {onPressNumber(0);}
    private void onPressNumber(int number) {
        brain.addDigit(number);
        refreshDisplay();
    }

    public void onPressPlusMinus() {
       brain.performPlusMinus();
       refreshDisplay();
    }

    public void onPressDot() {
        brain.addDecimalPoint();
        refreshDisplay();
    }

    public void onPressPlus() {onPressAction(CalculatorAction.Add);}
    public void onPressMinus() {onPressAction(CalculatorAction.Subtract);}
    public void onPressMultiply() {onPressAction(CalculatorAction.Multiply);}
    public void onPressDivide() {onPressAction(CalculatorAction.Divide);}
    private void onPressAction(CalculatorAction action) {
        brain.performAction(action);
        refreshDisplay();
    }

    public void onPressEquals(){
        brain.performEquals();
        refreshDisplay();
    }
}
