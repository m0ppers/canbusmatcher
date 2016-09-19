package koeln.mop.canbusmatcher.example;

import koeln.mop.canbusmatcher.CanMessage;
import koeln.mop.canbusmatcher.Logger;

public class ExampleLogger implements Logger {

	@Override
	public void log(CanMessage message, long unhandled, CanMessage previous) {
		System.out.println("Unhandled change: 0x" + Long.toHexString(unhandled));
		
	}
	
}
