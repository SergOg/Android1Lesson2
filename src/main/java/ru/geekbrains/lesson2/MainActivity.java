package ru.geekbrains.lesson2;
//1. С этого урока будем писать приложение «Калькулятор». Выберите макет для работы с
//калькулятором. Обоснуйте, почему будете использовать именно этот тип макета:
//Выбрал GridLayout по причине того, что он наиболее подходит ко всем задачам с рабочим полем в виде
//сетки тем более, что он наследуется от простого LinearLayout. TableLayout древний и обладает наименьшим
//функционалом по сравнению с GridLayout. Для фоновой картинки пришлось задействовать FrameLayout.
//Constraintlayout избыточен для данной задачи и не оптимален, хотя перспективный с большими возможностями.

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textView;  //Поле результата
    private Numbers numbers;    //Числа для операций
    private int num = 0;        //Признак первого числа

    private final static String KEY_NUMBERS = "Numbers";

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numbers = new Numbers();
        initViews();

        sharedPreferences = getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE);

        checkNightModeActivated();
    }

    public void checkNightModeActivated() {
        if (sharedPreferences.getBoolean("keyNightMode", false)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    // Сохранение данных
    @Override
    public void onSaveInstanceState(@NonNull Bundle instanceState) {
        super.onSaveInstanceState(instanceState);
        instanceState.putParcelable(KEY_NUMBERS, numbers);
    }

    // Восстановление данных
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle instanceState) {
        super.onRestoreInstanceState(instanceState);
        numbers = (Numbers) instanceState.getParcelable(KEY_NUMBERS);
        setTextButtons();
    }

    private void setTextButtons() {
        setTextButton(textView, String.valueOf(numbers.getNumber1()));
    }

    // Установить текст на TextView
    private void setTextButton(TextView textView, String buttonText) {
        textView.setText(String.format(Locale.getDefault(), "%s", buttonText));
    }

    private void addNumToString(String strIn) {
        String s = numbers.getNumber();
        s = s + strIn;
        setTextButton(textView, s);
        numbers.setNumber(s);
        num = 1;
    }

    private void makeOperation(String strIn, int i) {
        double a = 0;
        if (num == 1) {
            String s = numbers.getNumber();
            try {
                a = Double.parseDouble(s);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Неверный формат строки!", Toast.LENGTH_SHORT).show();
            }
        } else if (!numbers.getNumber().equals("")) {
            String s = numbers.getNumber();
            a = Double.parseDouble(s);
            num = 1;
        }
        if ((numbers.getNumber1() == 0) && (num == 1)) {
            numbers.setNumber1(a);
            if (i != 0) {
                setTextButton(textView, strIn);
            }
        } else if ((numbers.getNumber2() == 0) && (num == 1)) {
            numbers.setNumber2(a);
            numbers.operations(numbers.getOperation());
            setTextButton(textView, String.valueOf(numbers.getResult()));
            numbers.setNumber2(0);
            if (i == 0) {
                numbers.setNumber1(0);
            } else {
                numbers.setNumber1(numbers.getResult());
            }
        } else Toast.makeText(this, "Введите число", Toast.LENGTH_SHORT).show();
        numbers.setNumber("");
        num = 0;
        numbers.setOperation(i);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        setTextButton(textView, "");
        switch (v.getId()) {
            case R.id.button_00:
                addNumToString("00");
                break;
            case R.id.button_div:
                makeOperation("/", 1);
                break;
            case R.id.button_multi:
                makeOperation("*", 2);
                break;
            case R.id.button_minus:
                makeOperation("-", 3);
                break;
            case R.id.button_plus:
                makeOperation("+", 4);
                break;
            case R.id.button_equal:
                makeOperation("=", 0);
                break;
            case R.id.button_point:
                addNumToString(".");
                break;
            default:
                setTextButton(textView, "?");
                break;
        }
    }

    private void initViews() {
        textView = findViewById(R.id.button_result);
        buttonsClickListener();
    }

    private final int[] numberButtonIds = new int[]{R.id.button_0, R.id.button_1, R.id.button_2, R.id.button_3,
            R.id.button_4, R.id.button_5, R.id.button_6, R.id.button_7, R.id.button_8, R.id.button_9};

    @SuppressLint("SetTextI18n")
    private void setNumberButtonListeners() {
        for (int i = 0; i < numberButtonIds.length; i++) {
            findViewById(numberButtonIds[i]).setOnClickListener(v -> {
                Button btn = (Button) v;
                addNumToString(btn.getText().toString());
            });
        }
    }

    private void buttonsClickListener() {
        setNumberButtonListeners();
        Button button00 = findViewById(R.id.button_00);
        button00.setOnClickListener(this);
        Button buttonEqual = findViewById(R.id.button_equal);
        buttonEqual.setOnClickListener(this);
        Button buttonDel = findViewById(R.id.button_div);
        buttonDel.setOnClickListener(this);
        Button buttonMulti = findViewById(R.id.button_multi);
        buttonMulti.setOnClickListener(this);
        Button buttonMinus = findViewById(R.id.button_minus);
        buttonMinus.setOnClickListener(this);
        Button buttonPlus = findViewById(R.id.button_plus);
        buttonPlus.setOnClickListener(this);
        Button buttonPoint = findViewById(R.id.button_point);
        buttonPoint.setOnClickListener(this);
    }

    public void press(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}