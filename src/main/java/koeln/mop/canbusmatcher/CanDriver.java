package koeln.mop.canbusmatcher;

public interface CanDriver extends CanMessageRecipient {
	public long[] getAddresses();
}