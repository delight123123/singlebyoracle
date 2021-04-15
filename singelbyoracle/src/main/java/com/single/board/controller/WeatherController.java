package com.single.board.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.single.board.common.GeoUtil;

@Controller
public class WeatherController {

	private final static Logger logger=LoggerFactory.getLogger(WeatherController.class);
	
	@Autowired
	private GeoUtil geoUtil;
	
	@RequestMapping("/weather")
	public Object weather(@RequestParam(defaultValue = "37.5544878391868") double xx
			,@RequestParam (defaultValue = "126.9706545246099")double yy,Model model) {
		logger.info("일기예보 화면 보이기");
		
		
		model.addAttribute("xx", xx);
		model.addAttribute("yy", yy);
		
		return "weather/weatherForecast";
	}
	
	@ResponseBody
	@RequestMapping("/weatherAjax")
	public Object weatherAjax(@RequestParam String code,@RequestParam String mapLat,@RequestParam String mapLng) throws IOException {
		logger.info("일기예보 받기 파라미터 code={},mapLat={},mapLng={}",code,mapLat,mapLng);
		String key="%2Fjed2bo0NiEBKIFXlO5%2F2ErSJgaWM4XKGKGIO5bZOo6UEUYU06Nv%2B3sjbAoxZCaev3uHF%2BeIsJAClSGqNCkMAg%3D%3D";
		Map<String, String> map=geoUtil.dfs_xy_conv(code, mapLat, mapLng);
		
		logger.info("격자 변환 x={},y={}",map.get("x"),map.get("y"));
		
		Date date=new Date();
		Map<String, String> cutDayHour=geoUtil.getStandardHour(date);
		String currentDay=cutDayHour.get("currentDay");
		String currentHour=cutDayHour.get("currentHour");
		
		String apiurl="http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst?"
				+ "serviceKey="+key
				+ "&pageNo=1&numOfRows=10"
				+ "&dataType=json"
				+ "&base_date="+currentDay
				+ "&base_time="+currentHour
				+ "&nx="+map.get("x").toString()
				+ "&ny="+map.get("y").toString();
		
		URL url = new URL(apiurl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
		logger.info("검색결과 sb={}",sb);
		
		return sb;
	}
}
