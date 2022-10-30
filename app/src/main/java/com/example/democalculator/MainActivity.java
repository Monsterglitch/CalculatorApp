package com.example.democalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.mozilla.javascript.JavaScriptException;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity {

    TextView data;
    TextView output;
    String workings = "";
    String formula = "";
    String tempFormula = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTextViews();
    }
    private void initTextViews(){
        data = (TextView)findViewById(R.id.data);
        output = (TextView)findViewById(R.id.output);
    }
    private void setData(String givenValue){
        workings = workings + givenValue;
        data.setText(workings);
    }
    public void equalsOnClick(View view) {
        Double result = null;
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        checkForPowerOf();

        try {
            result = (double) engine.eval(formula);
        } catch (JavaScriptException | ScriptException e) {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }
        if (result != null) {
            output.setText(String.valueOf(result.doubleValue()));
        }
    }
    private void checkForPowerOf(){
        ArrayList<Integer>indexOfPowers = new ArrayList<>();
        for(int i = 0;i<workings.length();i++){
            if(workings.charAt(i) == '^')
                indexOfPowers.add(i);
        }
        formula = workings;
        tempFormula = workings;
        for(Integer index: indexOfPowers){
            changeFormula(index);
        }
        formula = tempFormula;
    }
    private void changeFormula(Integer index){
        String numberLeft = "";
        String numberRight = "";
        for(int i = index+1;i<workings.length();i++){
            if(isNumeric(workings.charAt(i)))
                numberRight = numberRight + workings.charAt(i);
            else
                break;
        }
        for(int i = index - 1;i>=0;i--){
            if(isNumeric(workings.charAt(i)))
                numberLeft = numberLeft + workings.charAt(i);
            else
                break;
        }
        String original = numberLeft +"^"+numberRight;
        String changed = "Math.pow("+numberLeft+","+numberRight+")";
        tempFormula = tempFormula.replace(original, changed);
    }
    private boolean isNumeric(char c)
    {
        if((c <= '9' && c >= '0') || c == '.')
            return true;

        return false;
    }


    public void clearOnClick(View view)
    {
        data.setText("");
        workings = "";
        output.setText("");
        leftBracket = true;
    }

    boolean leftBracket = true;

    public void bracketsOnClick(View view)
    {
        if(leftBracket)
        {
            setData("(");
            leftBracket = false;
        }
        else
        {
            setData(")");
            leftBracket = true;
        }
    }

    public void powerOfOnClick(View view)
    {
        setData("^");
    }

    public void divisionOnClick(View view)
    {
        setData("/");
    }

    public void sevenOnClick(View view)
    {
        setData("7");
    }

    public void eightOnClick(View view)
    {
        setData("8");
    }

    public void nineOnClick(View view)
    {
        setData("9");
    }

    public void timesOnClick(View view)
    {
        setData("*");
    }

    public void fourOnClick(View view)
    {
        setData("4");
    }

    public void fiveOnClick(View view)
    {
        setData("5");
    }

    public void sixOnClick(View view)
    {
        setData("6");
    }

    public void minusOnClick(View view)
    {
        setData("-");
    }

    public void oneOnClick(View view)
    {
        setData("1");
    }

    public void twoOnClick(View view)
    {
        setData("2");
    }

    public void threeOnClick(View view)
    {
        setData("3");
    }

    public void plusOnClick(View view)
    {
        setData("+");
    }

    public void decimalOnClick(View view)
    {
        setData(".");
    }

    public void zeroOnClick(View view)
    {
        setData("0");
    }

}

