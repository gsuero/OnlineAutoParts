package com.autoservice.commons;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.sun.enterprise.util.Utility;

import static java.lang.Math.round;
import static java.lang.Math.random;
import static java.lang.Math.pow;
import static java.lang.Math.abs;
import static java.lang.Math.min;

public class Pruebas {

	/**
	 * @param args
	 * @throws NoSuchAlgorithmException
	 */
	public static void main(String[] args) throws NoSuchAlgorithmException {
		
		System.out.println(randomstring());
	}

	public static String randomstring(int lo, int hi) {
		int n = rand(lo, hi);
		byte b[] = new byte[n];
		for (int i = 0; i < n; i++)
			b[i] = (byte) rand('A', 'z');
		return new String(b, 0);
	}

	private static int rand(int lo, int hi) {
		java.util.Random rn = new java.util.Random();
		int n = hi - lo + 1;
		int i = rn.nextInt(n);
		if (i < 0)
			i = -i;
		return lo + i;
	}

	public static String randomstring() {
		return randomstring(5, 25);
	}


}
