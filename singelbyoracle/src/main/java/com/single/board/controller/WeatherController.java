package com.single.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WeatherController {

	private final static Logger logger=LoggerFactory.getLogger(WeatherController.class);
	
	@RequestMapping("/weather")
	public Object weather(@RequestParam double xx,@RequestParam double yy,Model model) {
		logger.info("일기예보 화면 보이기");
		
		
		model.addAttribute("xx", xx);
		model.addAttribute("yy", yy);
		
		return "weather/weatherForecast";
	}
	
}
