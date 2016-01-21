package br.com.leitor.utils;

public class Types {

    public static boolean isInteger(Object o) {
        try {
            Integer.parseInt(o.toString());
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean isFloat(Object o) {
        try {
            Float.parseFloat(o.toString());
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean isDouble(Object o) {
        try {
            Double.parseDouble(o.toString());
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean isLong(Object o) {
        try {
            Long.parseLong(o.toString());
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean isBoolean(Object o) {
        try {
            Boolean.parseBoolean(o.toString());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean isObject(Object p) {
        try {
            Object o = p;
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
