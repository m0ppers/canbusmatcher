package koeln.mop.canbusmatcher;

public class Differ {
	public static long diff(byte[] a, byte[] b) {
		if (a.length > 8) {
			throw new IllegalArgumentException("Size " + a.length + " > 8");
		}
		if (b.length > 8) {
			throw new IllegalArgumentException("Size " + b.length + " > 8");
		}
		
		int max = Math.max(a.length, b.length);
		long result = 0;
		for (int i=0;i<max;i++) {
			int diff;
			if (a.length < i || b.length < i) {
				diff = 0xff;
			} else {
				diff = Byte.toUnsignedInt(a[i]) ^ Byte.toUnsignedInt(b[i]);
			}
			result |= diff << (i*8);
		}
		return result;
	}
}
