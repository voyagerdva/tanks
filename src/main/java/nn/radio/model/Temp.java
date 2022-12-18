package nn.radio.model;

import jdk.swing.interop.SwingInterOpUtils;

public class Temp {

    public static void main(String args[]) {
        double a = 90.0;
        double b = 45.0;
        double c = 0.017453;

        System.out.format("%.1f радиан = %.3f градусов %n", 90.0, Math.toDegrees(90.0));
        System.out.format("%.1f радиан = %.3f градусов %n", 45.0, Math.toDegrees(45.0));
        System.out.format("%.6f радиан = %.5f градусу %n", c, Math.toDegrees(c));
        System.out.println();

        System.out.format("%.1f градусов = %.3f радиан %n", 90.0, Math.toRadians(90.0));
        System.out.format("%.1f градусов = %.3f радиан %n", 45.0, Math.toRadians(45.0));
        System.out.format("%.6f градусов = %.5f радиан %n", c, Math.toRadians(c));
        System.out.println();

        System.out.format("Math.PI     = %.1f радиан = %.3f градусов %n", Math.PI, Math.toDegrees(Math.PI));
        System.out.format("Math.PI / 2 = %.1f радиан = %.3f градусов %n", Math.PI / 2, Math.toDegrees(Math.PI / 2));
        System.out.format("Math.PI / 4 = %.1f радиан = %.3f градусов %n", Math.PI / 4, Math.toDegrees(Math.PI / 4));
        System.out.println();

        System.out.format("sin(180) градусов (PI)     = %.3f\n", Math.sin(Math.PI));
        System.out.format("cos(180) градусов (PI)     = %.3f\n", Math.cos(Math.PI));
        System.out.println();
        System.out.format("sin(90)  градусов (PI / 2) = %.3f\n", Math.sin(Math.PI / 2));
        System.out.format("cos(90)  градусов (PI / 2) = %.3f\n", Math.cos(Math.PI / 2));
        System.out.println();
        System.out.format("sin(45)  градусов (PI / 4) = %.3f\n", Math.sin(Math.PI / 4));
        System.out.format("cos(45)  градусов (PI / 4) = %.3f\n", Math.cos(Math.PI / 4));
        System.out.println();
        System.out.format("sin(60)  градусов (PI / 3) = %.3f\n", Math.sin(Math.PI / 3));
        System.out.format("cos(60)  градусов (PI / 3) = %.3f\n", Math.cos(Math.PI / 3));
        System.out.println();
        System.out.format("sin(30)  градусов (PI / 6) = %.3f\n", Math.sin(Math.PI / 6));
        System.out.format("cos(30)  градусов (PI / 6) = %.3f\n", Math.cos(Math.PI / 6));

//        System.out.format("sin(90)  %.1f градусов = %.3f градусов \n", Math.PI / 2, Math.toDegrees(Math.PI / 2));

        System.out.println();



    }
}