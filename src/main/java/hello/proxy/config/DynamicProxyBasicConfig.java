package hello.proxy.config;

import hello.proxy.config.LogTraceBasicHandler;
import hello.proxy.v1.*;
import hello.proxy.v3.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

@Configuration
public class DynamicProxyBasicConfig {

	@Bean
	public OrderControllerV1 orderControllerV1(LogTrace logTrace){
		OrderControllerV1 orderControllerV1 = new OrderControllerV1Impl(orderServiceV1(logTrace));
		OrderControllerV1 proxy = (OrderControllerV1) Proxy.newProxyInstance(OrderControllerV1.class.getClassLoader(), new Class[]{OrderControllerV1.class}, new LogTraceBasicHandler(orderControllerV1, logTrace));
		return proxy;
	}

	@Bean
	public OrderServiceV1 orderServiceV1(LogTrace logTrace){
		OrderServiceV1 orderServiceV1 = new OrderServiceV1Impl(orderRepository(logTrace));
		OrderServiceV1 proxy = (OrderServiceV1) Proxy.newProxyInstance(OrderServiceV1.class.getClassLoader(), new Class[]{OrderServiceV1.class}, new LogTraceBasicHandler(orderServiceV1, logTrace));
		return proxy;
	}

	@Bean
	public OrderRepositoryV1 orderRepository(LogTrace logTrace){
		OrderRepositoryV1 orderRepositoryV1 = new OrderRepositoryV1Impl();
		OrderRepositoryV1 proxy = (OrderRepositoryV1) Proxy.newProxyInstance(OrderRepositoryV1.class.getClassLoader(), new Class[]{OrderRepositoryV1.class}, new LogTraceBasicHandler(orderRepositoryV1, logTrace));
		return proxy;
	}

}
