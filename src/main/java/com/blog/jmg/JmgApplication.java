package com.blog.jmg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class JmgApplication {

	public static void main(String[] args) {
		SpringApplication.run(JmgApplication.class, args);
	}

}
