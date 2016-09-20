package koeln.mop.canbusmatcher.example;

import koeln.mop.canbusmatcher.CanMessage;
import koeln.mop.canbusmatcher.Logger;

public class ExampleLogger implements Logger {

	@Override
	public void log(CanMessage message, long unhandled, CanMessage previous) {
		System.out.print("Unhandled change address " + Integer.toHexString(message.getAddress()) + ": 0x" + Long.toHexString(message.getDataLong()));
		if (previous != null) {
			System.out.print(" => 0x" + Long.toHexString(previous.getDataLong()));
		}
		System.out.println(" = 0x" + Long.toHexString(unhandled));
	}
}
