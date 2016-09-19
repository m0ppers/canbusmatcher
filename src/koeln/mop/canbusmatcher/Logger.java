package koeln.mop.canbusmatcher;

public interface Logger {
	public void log(CanMessage message, long unhandled, CanMessage previous);
}
