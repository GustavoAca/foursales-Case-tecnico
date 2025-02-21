package com.foursales.foursale_desafio;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.NestedTestConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.TimeZone;

@SpringBootTest(classes = FoursaleDesafioApplication.class)
@NestedTestConfiguration(NestedTestConfiguration.EnclosingConfiguration.OVERRIDE)
@ExtendWith(SpringExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@WebAppConfiguration
public abstract class FoursaleDesafioApplicationTests {

	static {
		System.setProperty("spring.profiles.active", "test");
		TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
		System.setProperty("spring.application.name", "foursales");
	}
}
