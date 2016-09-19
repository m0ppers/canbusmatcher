package koeln.mop.canbusmatcher;

public class CanMessage {
	private long address;
	private byte[] data;
	
	public CanMessage() {
		address = 0;
		data = new byte[] {0,0,0,0,0,0,0,0};
	}
	
	public long getAddress() {
		return address;
	}
	public void setAddress(long address) {
		this.address = address;
	}
	
	public byte getData(int index) {
		if (index < 0 || index > 7) {
			throw new IllegalArgumentException("Index must be >=0 <=7");
		}
		return this.data[index];
	}
	
	public byte[] getData() {
		return this.data;
	}
	
	public long getDataLong() {
		long result = 0;
		for (int i=0;i<data.length;i++) {
			// mop: stupid fucking java with its signed byte stuff
			long b = data[i] & 0xff;
			result |= (b << (i*8));
		}
		return result;
	}
	
	public void setData(int index, byte data) {
		if (index < 0 || index > 7) {
			throw new IllegalArgumentException("Index must be >=0 <=7");
		}
		this.data[index] = data;
	}
	
	public void setData(byte[] data) {
		if (data.length != 8) {
			throw new IllegalArgumentException("data length must be exactly 8");
		}
		this.data = data;
	}
}