package koeln.mop.canbusmatcher;

public interface CanMessageRecipient {
	public ConsumeResult onCanMessage(CanMessage message);
}
