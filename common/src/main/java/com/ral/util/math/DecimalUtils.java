package com.ral.util.math;

import java.math.BigDecimal;

/**
 * 
 * @author victor
 * @desc 小数计算工具类
 */
public final class DecimalUtils {

	private DecimalUtils(){
	}
	
	private static final int SCALE = 6;
	
    private static final double DOUBLE_ZERO = 0D;
    
    /**
     * 加法, num1 + num2
     */
    public static double add(double num1, double num2) {
        return BigDecimal.valueOf(num1).add(BigDecimal.valueOf(num2)).doubleValue();
    }

    /**
     * 加法, num1 + num2
     */
    public static BigDecimal add(BigDecimal num1, BigDecimal num2) {
        if (num1 == null) {
            num1 = BigDecimal.ZERO;
        }
        if (num2 == null) {
            num2 = BigDecimal.ZERO;
        }
        return num1.add(num2);
    }

    /**
     * 减法, num1 - num2
     */
    public static double subtract(double num1, double num2) {
        return BigDecimal.valueOf(num1).subtract(BigDecimal.valueOf(num2)).doubleValue();
    }

    /**
     * 减法, num1 - num2
     */
    public static BigDecimal subtract(BigDecimal num1, BigDecimal num2) {
        if (num1 == null) {
            num1 = BigDecimal.ZERO;
        }
        if (num2 == null) {
            num2 = BigDecimal.ZERO;
        }
        return num1.subtract(num2);
    }

    /**
     * 乘法, num1 * num2
     */
    public static double multiply(double num1, double num2) {
        return BigDecimal.valueOf(num1).multiply(BigDecimal.valueOf(num2)).doubleValue();
    }

    /**
     * 乘法, num1 * num2
     */
    public static BigDecimal multiply2(double num1, double num2) {
        return BigDecimal.valueOf(num1).multiply(BigDecimal.valueOf(num2));
    }

    /**
     * 乘法, num1 * num2
     */
    public static BigDecimal multiply2(BigDecimal num1, BigDecimal num2) {
        return num1.multiply(num2);
    }

    /**
     * 除法, num1 / num2
     */
    public static double divide(double num1, double num2) {
        if (num2 == DOUBLE_ZERO) {
            return DOUBLE_ZERO;
        }
        return BigDecimal.valueOf(num1).divide(BigDecimal.valueOf(num2), SCALE, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 除法, num1 / num2
     */
    public static BigDecimal divide(BigDecimal num1, BigDecimal num2) {
        if (BigDecimal.ZERO.equals(num2)) {
            return BigDecimal.ZERO;
        }
        return num1.divide(num2, SCALE, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 除法, num1 / num2
     */
    public static BigDecimal divide(BigDecimal num1, BigDecimal num2, int scale) {
        if (BigDecimal.ZERO.equals(num2)) {
            return BigDecimal.ZERO;
        }
        return num1.divide(num2, scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 除法, num1 / num2
     */
    public static BigDecimal divide2(double num1, double num2) {
        if (num2 <= DOUBLE_ZERO) {
            return BigDecimal.ZERO;
        }
        return BigDecimal.valueOf(num1).divide(BigDecimal.valueOf(num2), SCALE, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 转换为字符串
     */
    public static String toString(BigDecimal decimal, int scale) {
        if (decimal == null) {
            decimal = BigDecimal.ZERO;
        }
        return decimal.setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
    }
}
