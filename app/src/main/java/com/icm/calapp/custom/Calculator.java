package com.icm.calapp.custom;

import static com.icm.calapp.activity.SelectGenderActivity.GENDER_MEN;
import static com.icm.calapp.activity.SelectGenderActivity.GENDER_WOMEN;

public class Calculator {

    public static double calculateBMR(int gender, int weight, int height, int age) {
        double bmr = 0;
        if (gender == GENDER_MEN) {
            bmr = 66 + (13.7 * weight) + (5 * height) - (6.8 * age);
        } else if (gender == GENDER_WOMEN) {
            bmr = 665 + (9.6 * weight) + (1.8 * height) - (4.7 * age);
        }
        return bmr;
    }

    public static double calculateTDEE(double bmr, int exercise) {
        double exerciseDob = 0.0;
        switch (exercise) {
            case 1:
                exerciseDob = 1.2;
                break;
            case 2:
                exerciseDob = 1.375;
                break;
            case 3:
                exerciseDob = 1.55;
                break;
            case 4:
                exerciseDob = 1.725;
                break;
            case 5:
                exerciseDob = 1.9;
                break;
            default:
                break;
        }
        return bmr * exerciseDob;
    }

    public static double calculateBMI(int weight, int height) {
        double heightDouble = height / 100.0;
        return (weight / (Math.pow(heightDouble, 2)));
    }
}
