package com.example.demo;


import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

//JPA할때 쓴거 2개
@EntityScan(basePackages = {"com.example.entity"}) //entity위치
@EnableJpaRepositories(basePackages = {"com.example.repository"})//repository위치

@ComponentScan({
	"com.example.interceptor",
	"com.example.controller",
	"com.example.rest_controller",	
	"com.example.dao"})  //controller를 찾을 수 있도록 패키지명 등록

@MapperScan({"com.example.mapper"}) //추가
public class DemoApplication extends SpringBootServletInitializer{

	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		System.out.println("server start");
	}
	
	@Bean
	//@Bean은 톰캣(서버)이 돌기전에 먼저 돌아서 환경설정 해줌
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean 
			= new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		Resource[] arrResource 
			= new PathMatchingResourcePatternResolver().getResources("classpath:mappers/*.xml");
		sqlSessionFactoryBean.setMapperLocations(arrResource);
		return sqlSessionFactoryBean.getObject();
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(DemoApplication.class);
	}

}
