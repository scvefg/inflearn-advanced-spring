package hello.proxy.v2;

import hello.proxy.app.util.TraceStatus;
import org.junit.jupiter.api.Test;

class TraceV2Test {


	@Test
	void testV2(){
		TraceV2 traceV2 = new TraceV2();
		TraceStatus begin = traceV2.begin("Hello 1");
		TraceStatus beginSync = traceV2.beginSync(begin.getTraceId(), "Hello 2");
		traceV2.end(beginSync);
		traceV2.end(begin);


	}

}