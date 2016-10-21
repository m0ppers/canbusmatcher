package koeln.mop.canbusmatcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CanMessageMatcher {
	private Map<Long, CanMessage> lastMessages;
	private Map<Long, List<CanMessageRecipient>> recipients;
	private Logger logger;
	
	public CanMessageMatcher() {
		lastMessages = new HashMap<Long, CanMessage>();
		recipients = new HashMap<Long, List<CanMessageRecipient>>();
	}
	
	public void setDriver(CanDriver driver) {
		System.out.println("JAJA 2");
		for (long address: driver.getAddresses()) {
			System.out.println("JAJA 22");
			subscribeRecipient(address, driver);
		}
	}
	
	public void subscribeRecipient(long address, CanMessageRecipient recipient) {
		List<CanMessageRecipient> recs = recipients.get(Long.valueOf(address));
		if (recs == null) {
			recs = new ArrayList<CanMessageRecipient>();
			recipients.put(Long.valueOf(address),  recs);
		}
		recs.add(recipient);
	}
	
	public void publishMessage(CanMessage message) {
		Long address = Long.valueOf(message.getAddress());
		List<CanMessageRecipient> recipients = this.recipients.get(address);		
		CanMessage previous = lastMessages.get(address);
		long diff;
		if (previous == null) {
			diff = message.getDataLong();
		} else {
			diff = Differ.diff(previous.getData(), message.getData());
		}
		if (recipients != null) {
			for(CanMessageRecipient recipient: recipients) {
				ConsumeResult result = recipient.onCanMessage(message);
				diff &= ~result.handled;
			}
		}
		if (diff != 0) {
			this.log(message, diff, previous);
		}
		lastMessages.put(address, message);
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	
	
	public void log(CanMessage message, long unhandled, CanMessage previous) {
		if (logger != null) {
			logger.log(message, unhandled, previous);
		}
	}
}