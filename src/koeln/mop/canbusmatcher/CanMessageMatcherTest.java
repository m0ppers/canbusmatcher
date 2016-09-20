package koeln.mop.canbusmatcher;

import static org.junit.Assert.*;

import org.junit.Test;

public class CanMessageMatcherTest {
	@Test
	public void testMatchNone() {
		CanMessage message = new CanMessage();
		message.setData(0, (byte) 0xee);
		
		CanMessageMatcher matcher = new CanMessageMatcher();
		matcher.setLogger(new Logger() {

			@Override
			public void log(CanMessage message, long unhandled, CanMessage previous) {
				assertEquals(0xee, unhandled);
			}
		});
		matcher.publishMessage(message);
	}
	
	@Test
	public void testMatchAll() {
		CanMessage message = new CanMessage();
		message.setData(0, (byte) 0xee);
		
		CanMessageMatcher matcher = new CanMessageMatcher();
		matcher.setLogger(new Logger() {

			@Override
			public void log(CanMessage message, long unhandled, CanMessage previous) {
				fail("should not be called as there is nothing unhandled");
			}
		});
		matcher.subscribeRecipient(0, new CanMessageRecipient() {
			@Override
			public ConsumeResult onCanMessage(CanMessage message) {
				// TODO Auto-generated method stub
				ConsumeResult result = new ConsumeResult();
				result.handled = 0xff;
				return result;
			}
			
		});
		matcher.publishMessage(message);
	}
	
	@Test
	public void testMatchMore() {
		CanMessage message = new CanMessage();
		message.setData(0, (byte) 0xe);
		
		CanMessageMatcher matcher = new CanMessageMatcher();
		matcher.setLogger(new Logger() {

			@Override
			public void log(CanMessage message, long unhandled, CanMessage previous) {
				fail("should not be called as there is nothing unhandled");
			}
		});
		matcher.subscribeRecipient(0, new CanMessageRecipient() {
			@Override
			public ConsumeResult onCanMessage(CanMessage message) {
				// TODO Auto-generated method stub
				ConsumeResult result = new ConsumeResult();
				result.handled = 0xff;
				return result;
			}
			
		});
		matcher.publishMessage(message);
	}
	
	@Test
	public void testMatchLess() {
		CanMessage message = new CanMessage();
		message.setData(0, (byte) 0xff);
		
		CanMessageMatcher matcher = new CanMessageMatcher();
		matcher.setLogger(new Logger() {

			@Override
			public void log(CanMessage message, long unhandled, CanMessage previous) {
				assertEquals(1, unhandled);
			}
		});
		matcher.subscribeRecipient(0, new CanMessageRecipient() {
			@Override
			public ConsumeResult onCanMessage(CanMessage message) {
				// TODO Auto-generated method stub
				ConsumeResult result = new ConsumeResult();
				result.handled = 0xfe;
				return result;
			}
			
		});
		matcher.publishMessage(message);
	}
}