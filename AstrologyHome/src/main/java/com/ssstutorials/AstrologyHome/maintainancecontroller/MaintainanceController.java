/**
 * 
 */
package com.ssstutorials.AstrologyHome.maintainancecontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author suman
 *
 *Controller to expose maintainance apis
 */
@RestController
public class MaintainanceController {
	@GetMapping("/isAlive")
	public String isAlive() {
		return "Server is up";
	}
}
