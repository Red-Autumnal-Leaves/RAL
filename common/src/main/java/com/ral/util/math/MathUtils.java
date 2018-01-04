package com.ral.util.math;

import java.math.BigDecimal;

/**
 * 
 * @author victor
 * @desc 数字辅助类
 */
public class MathUtils {

	/***
	 * integer相加
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static Integer add(Integer a, Integer b) {
		Integer sum = 0;
		if (a != null)
			sum = sum + a;
		if (b != null)
			sum = sum + b;
		return sum;
	}

	/***
	 * 动态参数相加
	 * 
	 * @param args
	 * @return
	 */
	public static Integer add(Integer... args) {
		Integer sum = 0;
		for (Integer o : args) {
			if (o != null) {
				sum = sum + o;
			}
		}
		return sum;
	}

	/***
	 * 相加
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static BigDecimal add(BigDecimal a, BigDecimal b) {
		BigDecimal sum = BigDecimal.ZERO;
		if (a != null) {
			sum = sum.add(a);
		}
		if (b != null)
			return sum.add(b);
		return sum;
	}

	/***
	 * 相减
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static Integer subtract(Integer a, Integer b) {
		int intA = 0, intB = 0;
		if (a != null)
			intA = a.intValue();
		if (b != null)
			intB = b.intValue();
		return intA - intB;
	}

	public static BigDecimal subtract(BigDecimal a, BigDecimal b) {
		BigDecimal tmp = BigDecimal.ZERO;
		if (a != null) {
			tmp = tmp.add(a);
		}
		if (b != null) {
			return tmp.subtract(b);
		}
		return tmp;
	}
}
