/**
 * 
 */
package com.ssstutorials.AstrologyHome.services;

import com.ssstutorials.AstrologyHome.vo.MarriageFactors;

/**
 * @author suman
 *
 */
	
public interface HomeAstrologyService {
	public int calculatePercentage(MarriageFactors factor);
	public int calculateTaraSudhiScore(int nBoy, int nGirl);
	public int calculateChandraSudhiScore(int rBoy, int rGirl);
	public int calculateMandalaScore(int nBoy, int nGirl);
	public int[] calculateJatiScore(int nBoy, int nGirl);
	public int calculateJoniScore(int nBoy, int nGirl);
}
