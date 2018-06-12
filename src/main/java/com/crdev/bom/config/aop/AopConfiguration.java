package com.crdev.bom.config.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.crdev.bom.config.aop")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AopConfiguration {}
