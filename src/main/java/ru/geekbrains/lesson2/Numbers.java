package ru.geekbrains.lesson2;

import android.os.Parcel;
import android.os.Parcelable;

public class Numbers implements Parcelable {

    private String number;
    private double number1;
    private double number2;
    private double result;
    private int operation;

    public Numbers() {
        number = "";
        number1 = 0;
        number2 = 0;
        result = 0;
        operation = 0;
    }

    protected Numbers(Parcel in) {
        number = in.readString();
        number1 = in.readDouble();
        number2 = in.readDouble();
        result = in.readDouble();
        operation = in.readInt();
    }

    public static final Creator<Numbers> CREATOR = new Creator<Numbers>() {
        @Override
        public Numbers createFromParcel(Parcel in) {
            return new Numbers(in);
        }

        @Override
        public Numbers[] newArray(int size) {
            return new Numbers[size];
        }
    };

    public void setResult(int result) {
        this.result = result;
    }

    public int getOperation() {
        return operation;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }

    public double getResult() {
        return result;
    }

    public String getNumber() {
        return number;
    }

    public double getNumber1() {
        return number1;
    }

    public double getNumber2() {
        return number2;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setNumber1(double number1) {
        this.number1 = number1;
    }

    public void setNumber2(double number2) {
        this.number2 = number2;
    }

    public void operations(int operationNumber) {
        switch (operationNumber) {
            case 0:
                result = 0;
                break;
            case 1:
                result = number1 / number2;
                break;
            case 2:
                result = number1 * number2;
                break;
            case 3:
                result = number1 - number2;
                break;
            case 4:
                result = number1 + number2;
                break;
            default:
                break;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(number);
        dest.writeDouble(number1);
        dest.writeDouble(number2);
        dest.writeDouble(result);
        dest.writeInt(operation);
    }
}