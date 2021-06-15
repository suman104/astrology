/**
 * 
 */
package com.ssstutorials.AstrologyHome.Enum;

/**
 * @author suman
 *
 */
public class GanaType {
	public enum ganatype{
     DEBAGANA, NARAGANA, RAKSHYASAGANA
	}
	
	public static GanaType.ganatype setGanaType(int ganaNumber) {
		if(ganaNumber==1) {
			return ganatype.DEBAGANA;
		}else if(ganaNumber==2) {
			return ganatype.NARAGANA;
		}else if(ganaNumber==3) {
			return ganatype.RAKSHYASAGANA;
		}else {
		return null;
		}
	}
}
