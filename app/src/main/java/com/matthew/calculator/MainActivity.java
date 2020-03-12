package com.matthew.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

        // widgets/views that will hold results/display info.
    private EditText result;
    private EditText newNumber;
    private TextView displayOperation;

    // Variables to hold the operands and type of calculations.
    // wrapper class Double has one field for double, and can hold null values.
    // keeping these as fields so that they may used throughout the program in various functions/methods.
    // if they were local variables, I could only use them in the method/function in which they were created.
    private Double operand1 = null;
    private Double operand2 = null;
    private String pendingOperation = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // getting references to the widgets/views by setting the widget/view dataTypes to the specific instance of the widget by ID.
        // we cast the widgets to the specific widget type since findViewById returns a generic view
        result = findViewById(R.id.result);
        newNumber = findViewById(R.id.newNumber);
        displayOperation = findViewById(R.id.operation);

        // references to our button views/widgets
        Button button0 = findViewById(R.id.button0);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        Button buttonDot = findViewById(R.id.buttonDot);

        // storing Buttons in an array for later use (use 1, assigning event listeners to the buttons)
        Button[] buttonArray = {button0, button1, button2, button3, button4, button5, button6, button7, button8, button9, buttonDot};

        Button buttonEquals = findViewById(R.id.buttonEquals);
        Button buttonDivide = findViewById(R.id.buttonDivide);
        Button buttonMultiply = findViewById(R.id.buttonMultiply);
        Button buttonMinus = findViewById(R.id.buttonMinus);
        Button buttonPlus = findViewById(R.id.buttonPlus);

        // storing buttons in an array for later use (use 1, assigning event listeners to the buttons)
        Button[] operationButtonArray = {buttonEquals, buttonMultiply, buttonDivide, buttonPlus, buttonMinus};

        // creating onClickListener
        View.OnClickListener listener = new View.OnClickListener() {
            @Override

            /* this method takes a view as an argument, casts it to a Button object named b,
                appends the text toString of that Button object to the newNumber EditText widget.
                Not all widgets have a text field, so we are casting the view/widget to a widget/view
                that we know has text, in this case a Button, since we know that we are going to attach the event listener to Buttons only.  */
            public void onClick(View view) {
                Button b = (Button) view;
                if(newNumber.getText().toString().equals("Enter Number")  || newNumber.getText().toString().equals("Cannot Divide by Zero")) {
                    newNumber.setText("");
                }
                newNumber.append(b.getText().toString());
            }
        };


        // adding the OnClickListener listener to the 1-9 and Dot Buttons.
//        button0.setOnClickListener(listener);

        // I used a button array and a for loop to add listeners to my buttons.

        addListenerToBtnArray(buttonArray, listener);



        View.OnClickListener opListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button) view;
                String op = b.getText().toString();
                if(!op.equals("=")) {
                    pendingOperation = op;
                }
                // displayOperation is a widget/view
                displayOperation.setText(pendingOperation);
                String value = newNumber.getText().toString();
                try {
                    Double doubleValue = Double.valueOf(value);
                } catch (NumberFormatException e){
                    newNumber.setText("");
                    return;

                }
                if (value.length() != 0 && op.equals("=")) {

                    performCalculation();
                }
                else if (value.length() != 0){
                    setCalculation(value);

                }


            }
        };

        // adding the operations buttons to the opListener View.OnClickListener
        addListenerToBtnArray(operationButtonArray, opListener);



    }

    // methods can be written here, outside of the onCreate method.  Might be better up top, not sure.

            // for loop to add event listeners to an array of buttons
        private void addListenerToBtnArray(Button[] btnArray, View.OnClickListener listen){
            for( Button btn : btnArray){
                btn.setOnClickListener(listen);
            }
        }

        private void performCalculation(){
        // need to get value of operand2
            if(newNumber.getText().toString().equals("Cannot Divide by Zero")){
                return;
            }
            operand2 = Double.valueOf(newNumber.getText().toString());
            String warning = "Cannot Divide by Zero";
            newNumber.setText(null);
            switch(pendingOperation){
                case "/":
                    if(operand2 == 0) {
                        operand2 = null;
                        newNumber.setText(warning);
                    }
                    else {
                        operand1 /= operand2;

                        result.setText(String.format(Locale.US, "Result: %.8f", operand1));
                    }
                    break;
                case "*":
                    operand1 *= operand2;
                    result.setText(String.format(Locale.US, "Result: %.8f", operand1));
                    break;
                case "+":
                    operand1 += operand2;
                    result.setText(String.format(Locale.US, "Result: %.8f", operand1));
                    break;
                case "-":
                    operand1 -= operand2;
                    result.setText(String.format(Locale.US, "Result: %.8f", operand1));
                    break;


            }
        }



        private void setCalculation(String value){
            String enterNumber = "Enter Number";
            if(value != null){
                if (operand1 == null) {
                    operand1 = Double.valueOf(value);
                    result.setText(String.format(Locale.US, "%.8f", operand1));
                    newNumber.setText(enterNumber);
                }
                else {
                        operand2 = Double.valueOf(value);
                }



            }
        }









}
