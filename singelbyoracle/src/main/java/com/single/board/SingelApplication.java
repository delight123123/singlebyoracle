package com.single.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.single.board.common.FileuploadProperties;

@EnableConfigurationProperties({FileuploadProperties.class})
@SpringBootApplication
public class SingelApplication {

	public static void main(String[] args) {
		SpringApplication.run(SingelApplication.class, args);
	}

}
