package com.kunitskaya.base.utils;

import java.util.concurrent.ThreadLocalRandom;

public class NumbersUtil
{

	/**
	 * Gets random int value in range from min to max inclusive
	 *
	 * @param min - from number
	 * @param max - to number
	 * @return number between min & max
	 */
	public static int getRandomInt(int min, int max)
	{
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}
}
