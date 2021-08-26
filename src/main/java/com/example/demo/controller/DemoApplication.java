package com.example.demo.controller;

import com.example.demo.config.RlsDBConfig;
import com.example.demo.entities.rls.RlsData;
import com.example.demo.entities.wfi.WfiData;
import com.example.demo.repository.rls.RlsDataRepository;
import com.example.demo.repository.wfi.WfiDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@SpringBootApplication(scanBasePackageClasses = {RlsDBConfig.class})
public class DemoApplication implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(DemoApplication.class);
	@Autowired
	private RlsDataRepository rlsDataRepository;

	@Autowired
	private WfiDataRepository wfiDataRepository;

//	@PostConstruct
//	public void addData2DB() {
//		rlsDataRepository.saveAll(Stream.of(new RlsData(null, "sanjay"), new RlsData(null, "Pitter")).collect(Collectors.toList()));
//		wfiDataRepository.saveAll(
//				Stream.of(new WfiData(111, "Core Java"), new WfiData(222, "Spring Boot")).collect(Collectors.toList()));
//	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("@@ inserting data");
		rlsDataRepository.save(new RlsData(null,"Sanjay"));
		rlsDataRepository.findAll().forEach(System.out::println);
		wfiDataRepository.save(new WfiData(null,"Sanjay"));
		List<WfiData> efiData = StreamSupport
				.stream(wfiDataRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());
		System.out.println(efiData);
	}
}
