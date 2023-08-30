package com.in28minutes.learnspringframework;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//JDK16 record 게터세터 생성자를 자동으로 만들어준다
record Person (String name, int age, Address address) {};

record Address(String address, String city) {};

//스프링 설정 클래스
@Configuration
public class HelloWorldConfiguration {
	
	@Bean
	public String name() {
		return "Ranga";
	}
	
	@Bean
	public int age() {
		return 15;
	}
	
	@Bean
	public Person person() {
		return new Person("Ravi", 20, new Address("Hard", "Coding"));
	}
	
	@Bean
	public Person person2MethodCall(String name, int age, Address address2) { //name, age, address2 Bean 주입
		return new Person(name, age, address2);
	}
	
	@Bean
	public Person person3Parameters() {
		return new Person(name(), age(), address());
	}
	
	@Bean(name = "address2")
	public Address address() {
		return new Address("GwangJu", "GwangJu");
	}
	
	@Bean(name = "address3")
	public Address address3() {
		return new Address("GwangJu", "GwangJu");
	}
}
