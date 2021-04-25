package com.single.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.single.board.common.FileuploadProperties;

@EnableConfigurationProperties({FileuploadProperties.class})
@SpringBootApplication
public class SingelApplication extends SpringBootServletInitializer{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SingelApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SingelApplication.class, args);
	}

}
