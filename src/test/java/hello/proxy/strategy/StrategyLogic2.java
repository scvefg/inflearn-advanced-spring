package hello.proxy.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StrategyLogic2 implements Strategy{
	@Override
	public void call() {
		log.info("Logic 2 call!");
	}
}
