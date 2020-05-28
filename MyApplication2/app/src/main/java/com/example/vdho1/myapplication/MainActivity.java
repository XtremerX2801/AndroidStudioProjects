package com.example.vdho1.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.vdho1.myapplication.MainActivity.STATE.FIRST_OP;
import static com.example.vdho1.myapplication.MainActivity.STATE.RESET_SECOND_OP;
import static com.example.vdho1.myapplication.MainActivity.STATE.SECOND_OP;

public class MainActivity extends Activity implements View.OnClickListener{

    TextView txtResult;
    Button btnZero, btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine,
             btnAdd, btnSub, btnMul, btnDiv, btnMod, btnDot, btnAc, btnDel, btnEqual;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtResult = findViewById(R.id.txtResult);

        btnZero = findViewById(R.id.btnZero);
        btnZero.setOnClickListener(this);
        btnOne = findViewById(R.id.btnOne);
        btnOne.setOnClickListener(this);
        btnTwo = findViewById(R.id.btnTwo);
        btnTwo.setOnClickListener(this);
        btnThree = findViewById(R.id.btnThree);
        btnThree.setOnClickListener(this);
        btnFour = findViewById(R.id.btnFour);
        btnFour.setOnClickListener(this);
        btnFive = findViewById(R.id.btnFive);
        btnFive.setOnClickListener(this);
        btnSix = findViewById(R.id.btnSix);
        btnSix.setOnClickListener(this);
        btnSeven = findViewById(R.id.btnSeven);
        btnSeven.setOnClickListener(this);
        btnEight = findViewById(R.id.btnEight);
        btnEight.setOnClickListener(this);
        btnNine = findViewById(R.id.btnNine);
        btnNine.setOnClickListener(this);
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        btnSub = findViewById(R.id.btnSub);
        btnSub.setOnClickListener(this);
        btnMul = findViewById(R.id.btnMul);
        btnMul.setOnClickListener(this);
        btnDiv = findViewById(R.id.btnDiv);
        btnDiv.setOnClickListener(this);
        btnMod = findViewById(R.id.btnMod);
        btnMod.setOnClickListener(this);
        btnEqual = findViewById(R.id.btnEqual);
        btnEqual.setOnClickListener(this);
        btnAc = findViewById(R.id.btnAc);
        btnAc.setOnClickListener(this);
        btnDel = findViewById(R.id.btnDel);
        btnDel.setOnClickListener(this);
        btnDot = findViewById(R.id.btnDot);
        btnDot.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (state){
            case FIRST_OP:
                if (v.getId() == R.id.btnZero || v.getId() == R.id.btnOne
                        || v.getId() == R.id.btnTwo || v.getId() == R.id.btnThree
                        || v.getId() == R.id.btnFour || v.getId() == R.id.btnFive
                        || v.getId() == R.id.btnSix || v.getId() == R.id.btnSeven
                        || v.getId() == R.id.btnEight || v.getId() == R.id.btnNine){
                    isNum = 1;
                    String displayNumber = ((Button)v).getText().toString();
                    if (operator.equals("Equal") && afterEqual == 0){
                        txtResult.setText(displayNumber);
                        afterEqual += 1;
                    }
                    else if(txtResult.getText().toString().equals("0")){
                        txtResult.setText(displayNumber);
                    }
                    else {
                        txtResult.setText(txtResult.getText() + displayNumber);
                    }
                }
                if (v.getId() == R.id.btnDot && hasDot == 0 && isNum == 1){
                    hasDot += 1;
                    txtResult.setText(txtResult.getText() + ".");
                    isNum = 0;
                }
                if ((v.getId() == R.id.btnAdd || v.getId() == R.id.btnSub
                        || v.getId() == R.id.btnMul || v.getId() == R.id.btnDiv
                        || v.getId() == R.id.btnMod) && isNum == 1){
                    first_op = Double.parseDouble(txtResult.getText().toString());
                    if(v.getId() == R.id.btnAdd){
                        operator = "Add";
                    }
                    if(v.getId() == R.id.btnSub){
                        operator = "Sub";
                    }
                    if(v.getId() == R.id.btnMul){
                        operator = "Mul";
                    }
                    if(v.getId() == R.id.btnDiv){
                        operator = "Div";
                    }
                    if(v.getId() == R.id.btnMod){
                        operator = "Mod";
                    }
                    state = RESET_SECOND_OP;
                }
                if(v.getId() == R.id.btnAc){
                    txtResult.setText("0");
                    hasDot = 0;
                    state = FIRST_OP;
                }
                if(v.getId() == R.id.btnDel){
                    sub = txtResult.getText().toString();
                    sub = sub.substring(0, sub.length() - 1);
                    txtResult.setText(sub);
                }
                if(v.getId() == R.id.btnEqual){
                    if (hasDot == 1){
                        txtResult.setText(txtResult.getText() + "0");
                    }
                    else {
                        txtResult.setText(txtResult.getText());
                    }
                    operator = "Equal";
                    afterEqual = 0;
                    hasDot = 0;
                    isNum = 0;
                    state = FIRST_OP;
                }
                break;
            case RESET_SECOND_OP:
                if (v.getId() == R.id.btnZero || v.getId() == R.id.btnOne
                        || v.getId() == R.id.btnTwo || v.getId() == R.id.btnThree
                        || v.getId() == R.id.btnFour || v.getId() == R.id.btnFive
                        || v.getId() == R.id.btnSix || v.getId() == R.id.btnSeven
                        || v.getId() == R.id.btnEight || v.getId() == R.id.btnNine){
                    hasDot = 0;
                    String displayNumber = ((Button)v).getText().toString();
                    txtResult.setText(displayNumber);
                    state = SECOND_OP;
                }
                if(v.getId() == R.id.btnAc){
                    txtResult.setText("0");
                    hasDot = 0;
                    state = FIRST_OP;
                }
                if(v.getId() == R.id.btnEqual){
                    txtResult.setText(txtResult.getText());
                    operator = "Equal";
                    afterEqual = 0;
                    hasDot = 0;
                    isNum = 0;
                    state = FIRST_OP;
                }
                break;
            case SECOND_OP:
                if (v.getId() == R.id.btnZero || v.getId() == R.id.btnOne
                        || v.getId() == R.id.btnTwo || v.getId() == R.id.btnThree
                        || v.getId() == R.id.btnFour || v.getId() == R.id.btnFive
                        || v.getId() == R.id.btnSix || v.getId() == R.id.btnSeven
                        || v.getId() == R.id.btnEight || v.getId() == R.id.btnNine){
                    isNum = 1;
                    String displayNumber = ((Button)v).getText().toString();
                    txtResult.setText(txtResult.getText() + displayNumber);
                }
                if (v.getId() == R.id.btnDot && hasDot == 0){
                    hasDot += 1;
                    txtResult.setText(txtResult.getText() + ".");
                    isNum = 0;
                }
                if ((v.getId() == R.id.btnAdd || v.getId() == R.id.btnSub
                        || v.getId() == R.id.btnMul || v.getId() == R.id.btnDiv
                        || v.getId() == R.id.btnMod) && isNum == 1) {
                    second_op = Double.parseDouble(txtResult.getText().toString());
                    if (operator.equals("Add")) {
                        first_op = first_op + second_op;
                    }
                    if (operator.equals("Sub")) {
                        first_op = first_op - second_op;
                    }
                    if (operator.equals("Mul")) {
                        first_op = first_op * second_op;
                    }
                    if (operator.equals("Div")) {
                        first_op = first_op / second_op;
                    }
                    if (operator.equals("Mod")) {
                        first_op = first_op % second_op;
                    }
                    txtResult.setText(first_op + "");

                    if(v.getId() == R.id.btnAdd){
                        operator = "Add";
                    }
                    if(v.getId() == R.id.btnSub){
                        operator = "Sub";
                    }
                    if(v.getId() == R.id.btnMul){
                        operator = "Mul";
                    }
                    if(v.getId() == R.id.btnDiv){
                        operator = "Div";
                    }
                    if(v.getId() == R.id.btnMod){
                        operator = "Mod";
                    }
                    state = RESET_SECOND_OP;
                }
                if(v.getId() == R.id.btnAc){
                    txtResult.setText("0");
                    hasDot = 0;
                    state = FIRST_OP;
                }
                if(v.getId() == R.id.btnDel){
                    sub = txtResult.getText().toString();
                    sub = sub.substring(0, sub.length() - 1);
                    txtResult.setText(sub);
                }
                if(v.getId() == R.id.btnEqual){
                    second_op = Double.parseDouble(txtResult.getText().toString());
                    if (operator.equals("Add")) {
                        first_op = first_op + second_op;
                    }
                    if (operator.equals("Sub")) {
                        first_op = first_op - second_op;
                    }
                    if (operator.equals("Mul")) {
                        first_op = first_op * second_op;
                    }
                    if (operator.equals("Div")) {
                        first_op = first_op / second_op;
                    }
                    if (operator.equals("Mod")) {
                        first_op = first_op % second_op;
                    }
                    txtResult.setText(first_op + "");
                    operator = "Equal";
                    afterEqual = 0;
                    hasDot = 0;
                    isNum = 0;
                    state = FIRST_OP;
                }
                break;
            default:
                break;
        }
    }

    public enum STATE {
        FIRST_OP, RESET_SECOND_OP, SECOND_OP
    }

    STATE state = FIRST_OP;
    double first_op = 0, second_op = 0;
    String operator = "", sub = "";
    int hasDot = 0, isNum = 0, afterEqual = 0;
}