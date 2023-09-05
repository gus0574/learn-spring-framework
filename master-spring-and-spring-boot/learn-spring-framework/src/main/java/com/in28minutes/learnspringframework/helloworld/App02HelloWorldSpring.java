package com.in28minutes.learnspringframework.helloworld;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App02HelloWorldSpring {

	public static void main(String[] args) {
		//1. Launch a Spring Context -
		//프라이빗 리소스
		try(var context = new AnnotationConfigApplicationContext(HelloWorldConfiguration.class)){
			
			//2. Configure the things that we want Spring to manage 
			//HelloWorldConfiguration 을 만듦을 통해 관리하고자 하는 항목을 설정했다. - @Configuration
			//name - @Bean
			
			//3. Retrieving Beans managed by Spring
			
			System.out.println(context.getBean("name"));
			System.out.println();
			
			System.out.println(context.getBean("age"));
			System.out.println();
			
			System.out.println(context.getBean("person"));
			System.out.println();
			
			System.out.println(context.getBean("person2MethodCall"));
			System.out.println();
			
			System.out.println(context.getBean("person3Parameters"));
			System.out.println();
			
			System.out.println(context.getBean("address2"));
			System.out.println();
			
			System.out.println(context.getBean(Person.class));
			System.out.println();
			
			System.out.println(context.getBean(Address.class));
			System.out.println();
			
			System.out.println(context.getBean("person5Qualifier"));
			
//			Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
		}

		
	}

}
