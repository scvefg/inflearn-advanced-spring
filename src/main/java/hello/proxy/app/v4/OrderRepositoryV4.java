package hello.proxy.app.v4;


import hello.proxy.trace.template.AbstractTemplate;
import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {
	private final LogTrace trace;

	public void save(String itemId) {
		TraceStatus status = null;
		AbstractTemplate<String> template = new AbstractTemplate<String>(trace) {
			@Override
			public String call() {
				if (itemId.equals("ex")) {
					throw new IllegalStateException("예외 발생");
				}
				sleep(1000);
				return null;
			}
		};
		template.execute("OrderRepository V4");
	}

	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
