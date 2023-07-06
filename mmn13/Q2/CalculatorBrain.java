public class CalculatorBrain {
    private String state, memory;
    private CalculatorAction lastAction;
    private boolean isInitialState, hasDecimalPoint, hasPendingCalculation;

    public CalculatorBrain() {
        this.memory = "";
        this.state = "";
        this.isInitialState = true;
        this.hasDecimalPoint = false;
        this.hasPendingCalculation = false;
        this.lastAction = CalculatorAction.NoAction;
    }

    public String getState() {
        return state;
    }

    public void addDigit(int digit) {
        if (isInitialState) {
            isInitialState = false;
            state = String.valueOf(digit);
        } else {
            if (state.charAt(0) == '0' && digit == 0) return;
            state += String.valueOf(digit);
        }
        if (lastAction != CalculatorAction.NoAction && !hasPendingCalculation) {
            hasPendingCalculation = true;
        }
    }

    public void addDecimalPoint() {
        if (!hasDecimalPoint) {
            hasDecimalPoint = true;
            if (isInitialState) {
                isInitialState = false;
                state = "0.";
            } else {
                state += ".";
            }
        }
    }

    public void performPlusMinus() {
        if (isInitialState) {
            return;
        }

        if (state.startsWith("-")) {
            state = state.substring(1);
        } else {
            state = "-" + state;
        }
    }

    public void performAction(CalculatorAction action) {
        if (hasPendingCalculation) {
            calculate();
            hasPendingCalculation = false;
        }
        lastAction = action;
        memory = state;
        isInitialState = true;
        hasDecimalPoint = false;
    }

    public void performEquals() {
        if (hasPendingCalculation) {
            calculate();
            hasPendingCalculation = false;
        }
        lastAction = CalculatorAction.NoAction;
        isInitialState = true;
        hasDecimalPoint = false;
    }

    private double parseToDouble(String s) {
        try {
            return Double.parseDouble(s);
        } catch(NumberFormatException exception) {
            return 0;
        }
    }

    public void calculate() {
        double memoryFloat = parseToDouble(memory);
        double stateFloat = parseToDouble(state);
        double result;
        switch (lastAction) {
            case Add:
                result = memoryFloat + stateFloat;
                break;
            case Subtract:
                result = memoryFloat - stateFloat;
                break;
            case Multiply:
                result = memoryFloat * stateFloat;
                break;
            case Divide:
                if (stateFloat == 0)  {
                    // "NaN" is also a valid double value, which lets us
                    // carry on the calculation without any more modifications
                    state = "NaN";
                    return;
                }
                result = memoryFloat / stateFloat;
                break;
            default:
                throw new IllegalArgumentException(String.format("Action %s is unsupported!", lastAction));
        }

        if (Math.ceil(result) == result) {
            state = String.valueOf((int) result);
        } else {
            state = String.valueOf(result);
        }
    }
}