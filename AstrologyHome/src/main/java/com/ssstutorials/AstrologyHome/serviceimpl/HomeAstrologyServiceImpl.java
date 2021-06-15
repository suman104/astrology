/**
 * 
 */
package com.ssstutorials.AstrologyHome.serviceimpl;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssstutorials.AstrologyHome.Enum.GanaType;
import com.ssstutorials.AstrologyHome.Enum.GanaType.ganatype;
import com.ssstutorials.AstrologyHome.services.HomeAstrologyService;
import com.ssstutorials.AstrologyHome.vo.MarriageFactors;

/**
 * @author suman
 *
 */
@Service
public class HomeAstrologyServiceImpl implements HomeAstrologyService {

	@Autowired
	DBServicesImpl dbServices;
	

	@Override
	public int calculatePercentage(MarriageFactors factor) {
		ObjectMapper m = new ObjectMapper();
		Map<String, Integer> factors = m.convertValue(factor, Map.class);
		List<Integer> factorList = factors.values().stream().collect(Collectors.toList());
		IntSummaryStatistics statistics = factorList.stream().mapToInt((x) -> x).summaryStatistics();
		/**
		 * System.out.println("Sum is "+ statistics.getSum());
		 * System.out.println("Avarage is "+statistics.getAverage());
		 * System.out.println("Maximum is "+statistics.getMax());
		 * System.out.println("Minimum is "+statistics.getMin());
		 * System.out.println("Count is "+statistics.getCount());
		 */

		return (int) statistics.getSum();
	}

	@Override
	public int calculateTaraSudhiScore(int nBoy, int nGirl) {
		if(nBoy==0 || nGirl==0) {
			return 0;
		}
		int diff=Math.abs(nBoy-nGirl);
		diff+=1;
		if(diff==2 || diff==4 || diff==6 || diff==8 || diff==9) {
			return 8;
		}else {
		return 0;
		}
		/**if(nBoy < nGirl) {
			diff =diff+ 1;
		}else if(nBoy > nGirl) {
			diff= 28-diff;
		}else {
			return 0;
		}
		if(diff==2 || diff==4 || diff==6 || diff==8 || diff==9 || diff==27 || diff==25 || diff==23 || diff==21 || diff==20 ) {
			return 8;
		}else {
		return 0;
		}*/
	}
	
	@Override
	public int calculateChandraSudhiScore(int rBoy, int rGirl) {
		if(rBoy==0 || rGirl==0) {
			return 0;
		}
		List<Integer> list = Arrays.asList(rBoy,rGirl);
		List<Integer> sortedList = list.stream().sorted().collect(Collectors.toList());
		List<Integer> cList1 = Arrays.asList(1,7);
		List<Integer> cList2 = Arrays.asList(3,9);
		List<Integer> cList3 = Arrays.asList(5,11);
		if(sortedList.equals(cList1) || sortedList.equals(cList2) || sortedList.equals(cList3)) {
			return 0;
		}
		int diff=Math.abs(rBoy-rGirl);
		if(rBoy==rGirl) {
			return 18;
		}else if(rBoy < rGirl) {
			diff =13-diff;
		}else if(rBoy > rGirl) {
			diff= diff+1;
		}
		if(diff==3 || diff==4 || diff==7 || diff==10 || diff==11) {
			return 18;
		}else {
		return 0;
		}
	}

	@Override
	public int calculateMandalaScore(int nBoy, int nGirl) {
		if(nBoy==0 || nGirl==0) {
			return 0;
		}
		int result= ((nGirl+nBoy)*5)%27;
		if(result == 0 || result == 1) {
			return 0;
		}else if(result == 2) {
			return 3;
		}else if(result==3) {
			return 0;
		}else if(result==4) {
			return 3;
		}  else {
			return 3;
		}
		
	}

	
	@Override
	public int[] calculateJatiScore(int nBoy, int nGirl) {
		List<Integer> debaGanaList= Arrays.asList(1,5,7,8,13,15,17,23,27);
		List<Integer> naraGanaList= Arrays.asList(2,4,6,11,12,25,20,21,26);
		List<Integer> rakshyasaGanaList= Arrays.asList(3,9,10,14,16,18,19,22,24);
		ganatype boyGanaType=null;  
		ganatype girlGanaType=null;  
		int score=0;
		int flag=0;
		int[] ganaData=new int[2];

		if (debaGanaList.contains(nBoy)) {
			boyGanaType = GanaType.setGanaType(1);
		} else if (naraGanaList.contains(nBoy)) {
			boyGanaType = GanaType.setGanaType(2);
		} else if (rakshyasaGanaList.contains(nBoy)) {
			boyGanaType = GanaType.setGanaType(3);
		} else {
			boyGanaType = null;
		}
		
		if (debaGanaList.contains(nGirl)) {
			girlGanaType = GanaType.setGanaType(1);
		} else if (naraGanaList.contains(nGirl)) {
			girlGanaType = GanaType.setGanaType(2);
		} else if (rakshyasaGanaList.contains(nGirl)) {
			girlGanaType = GanaType.setGanaType(3);
		} else {
			girlGanaType = null;
		}
		if(boyGanaType.equals(girlGanaType)) {
			score=16;
		}else if(boyGanaType==ganatype.DEBAGANA && girlGanaType==ganatype.NARAGANA) {
			score=16;
		}else if(boyGanaType==ganatype.NARAGANA && girlGanaType==ganatype.RAKSHYASAGANA) {
			score=0;
			flag=1;
			
		}else if(boyGanaType==ganatype.RAKSHYASAGANA && girlGanaType==ganatype.NARAGANA) {
			score=0;
		}else if(boyGanaType==ganatype.DEBAGANA && girlGanaType==ganatype.RAKSHYASAGANA) {
			score=0;
		}else {
			score=16;
		}
		ganaData[0]=flag;
		ganaData[1]=score;
		return ganaData;
	}
	@Override
	public int calculateJoniScore(int nBoy, int nGirl) {
		
		
		 
		
		/*
		 * List<Integer> goru = Arrays.asList(12,26); List<Integer> bagha =
		 * Arrays.asList(14,16); List<Integer> hati = Arrays.asList(2,27); List<Integer>
		 * singha = Arrays.asList(23,25); List<Integer> musa = Arrays.asList(9,11);
		 * List<Integer> biradi = Arrays.asList(6,8); List<Integer> ghoda =
		 * Arrays.asList(1,24); List<Integer> mahisha = Arrays.asList(13,15);
		 * List<Integer> kukura = Arrays.asList(10,19); List<Integer> mruga =
		 * Arrays.asList(17,18); List<Integer> sarpa = Arrays.asList(4,5); List<Integer>
		 * nakula = Arrays.asList(21,22);//aa List<Integer> banara =
		 * Arrays.asList(20,23);
		 */
		int bPasuId=dbServices.getPasuId(nBoy);
		int gPasuId=dbServices.getPasuId(nGirl);

		System.out.println("PasuId of Boy:"+bPasuId);
		System.out.println("PasuId of Girl:"+gPasuId);
		
		List<Integer> list = Arrays.asList(bPasuId, gPasuId);
		List<Integer> sortedList = list.stream().sorted().collect(Collectors.toList());
		
		List<Integer> pList1 = Arrays.asList(8,10);
		List<Integer> pList2 = Arrays.asList(2,14);
		List<Integer> pList3 = Arrays.asList(1,9);  
		List<Integer> pList4 = Arrays.asList(7,11);
		List<Integer> pList5 = Arrays.asList(4,13);  
		List<Integer> pList6 = Arrays.asList(11,12);  
		List<Integer> pList7 = Arrays.asList(5,6);  

		if(sortedList.equals(pList1) || sortedList.equals(pList2) || sortedList.equals(pList3) || sortedList.equals(pList4) || sortedList.equals(pList5) || sortedList.equals(pList6) || sortedList.equals(pList7)) {
			return 0;
		}else {
			return 10;
		}

	}

}
