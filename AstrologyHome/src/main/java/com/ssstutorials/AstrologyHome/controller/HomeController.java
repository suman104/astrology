/**
 * 
 */
package com.ssstutorials.AstrologyHome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ssstutorials.AstrologyHome.services.HomeAstrologyService;
import com.ssstutorials.AstrologyHome.vo.HomeRequestInput;
import com.ssstutorials.AstrologyHome.vo.MarriageFactors;

/**
 * @author suman
 *
 */
@Controller
public class HomeController {
	@Autowired
	HomeAstrologyService service;

	@GetMapping("/")
	public String welcome(Model model) {
		return "index";
	}
	@GetMapping("/calculate")
    public String calculate(Model model) {
		model.addAttribute("homeRequestInput",new HomeRequestInput());    
        return "find-match";
    }

	@PostMapping("/getMatchPercentage")
	public String getMatchPercentage(@ModelAttribute HomeRequestInput homeRequestInput,Model model) {
		StringBuilder sb = new StringBuilder();
		int percentValue = 0;
		// get Boy & Girl's Nakhaytra code
		// int[] nCode= IntStream.of(1,6).toArray();
		int nBoy = homeRequestInput.getNboy();
		int nGirl = homeRequestInput.getNgirl();

		// get Boy & girl's Rasi Code
		// int[] rCode= IntStream.of(1,3).toArray();
		int rBoy = homeRequestInput.getRboy();
		int rGirl = homeRequestInput.getRgirl();

		MarriageFactors factor = new MarriageFactors();
		int[] jatiData=service.calculateJatiScore(nBoy, nGirl);
		
		int rejectFlag=jatiData[0];
		int jatiScore=jatiData[1];
		if(rejectFlag==1) {
			sb.append("Sorry!!Marriage Can not be possible due to Jati Gana Dasa");
			System.out.println("Marriage Can not be possible due to Jati Gana Dasa");
		}else {
			int chandraSudhiScore = service.calculateChandraSudhiScore(rBoy, rGirl);
			factor.setChandraSudhiScore(chandraSudhiScore);
			int taraSudhiScore=0;
			int barnaScore =0;
			int nadiScore = 0;
			int balaBicharaScore = 5;
			int bhabanaDheepScore = 13;
			int mandalaScore=0;
			
			if(chandraSudhiScore==18) {
				taraSudhiScore=8;
				barnaScore = 3;
				nadiScore = 21;
				balaBicharaScore = 5;
				bhabanaDheepScore = 13;

			}else {
				// get Tara Sudhi Bichara
				taraSudhiScore = service.calculateTaraSudhiScore(nBoy, nGirl);
				barnaScore = 0;
				nadiScore = 0;
				balaBicharaScore = 0;
				bhabanaDheepScore = 0;
			}
			if(jatiScore==0 || nadiScore==0) {
				mandalaScore = 3;
			}else {
				mandalaScore = service.calculateMandalaScore(nBoy, nGirl);
			}
			factor.setTaraSudhiScore(taraSudhiScore);
			factor.setBarnaScore(barnaScore);
			factor.setNadiScore(nadiScore);
			factor.setBalaBicharaScore(balaBicharaScore);
			factor.setMandalaScore(mandalaScore);
			factor.setBhabanaDheepScore(bhabanaDheepScore);
			
			factor.setJatiScore(jatiScore);
			int joniScore = service.calculateJoniScore(nBoy, nGirl);
			factor.setJoniScore(joniScore);
			int bargaScore = 3;
			factor.setBargaScore(bargaScore);

			// Calculate Percentage
			percentValue = service.calculatePercentage(factor);

			sb.append("Congratulations!!! Your matched percentage is ").append(percentValue).append("%").append("\n")
					.append("Summary \n").append("===================")
					.append("\n").append("Tara ").append(taraSudhiScore)
					.append("\n").append("Chandra ").append(chandraSudhiScore)
					.append("\n").append("Bala ").append(balaBicharaScore)
					.append("\n").append("Barna ").append(barnaScore)
					.append("\n").append("Mandala ").append(mandalaScore)
					.append("\n").append("BhabanaDeepa ").append(bhabanaDheepScore)
					.append("\n").append("Nadi ").append(nadiScore)
					.append("\n").append("Gana(Jati) ").append(jatiScore)
					.append("\n").append("Jonikuta(Swara) ").append(joniScore)
					.append("\n").append("Barga ").append(bargaScore);
		}
		
	    model.addAttribute("totalPercent", percentValue);
	    model.addAttribute("scores", factor);

	    return "result";

		//return sb.toString();
	}
}
