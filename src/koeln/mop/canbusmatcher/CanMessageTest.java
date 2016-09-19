package koeln.mop.canbusmatcher;

import static org.junit.Assert.*;

import org.junit.Test;

public class CanMessageTest {

	@Test(expected = IllegalArgumentException.class)
	public void testSetData() {
		byte[] data = {1,2,3,4};
		CanMessage message = new CanMessage();
		message.setData(data);
	}
	
	@Test
	public void testGetData() {
		byte[] data = {1,2,3,4,5,6,7,8};
		CanMessage message = new CanMessage();
		message.setData(data);
		assertEquals(4, message.getData(3));
	}
	
	@Test
	public void testSetDataIndex() {
		CanMessage message = new CanMessage();
		message.setData(3, (byte)0xf1);
		assertEquals((byte)0xf1, message.getData(3));
	}
	
	@Test
	public void testGetDataLong1() {
		CanMessage message = new CanMessage();
		message.setData(0, (byte)0xff);
		assertEquals(0xff, message.getDataLong());
	}
	
	@Test
	public void testGetDataMaxUnsignedLong() {
		CanMessage message = new CanMessage();
		message.setData(0, (byte) 0xff);
		message.setData(1, (byte) 0xff);
		message.setData(2, (byte) 0xff);
		message.setData(3, (byte) 0xff);
		message.setData(4, (byte) 0xff);
		message.setData(5, (byte) 0xff);
		message.setData(6, (byte) 0xff);
		message.setData(7, (byte) 0xff);
		// mop: actually reminds me of PHP :D
		assertEquals(0, Long.compareUnsigned(Long.parseUnsignedLong("18446744073709551615"), message.getDataLong()));
	}
	
	@Test
	public void testGetDataInBetween() {
		CanMessage message = new CanMessage();
		message.setData(0, (byte) 0xff);
		message.setData(1, (byte) 0xff);
		message.setData(3, (byte) 0xff);
		assertEquals(0xff00ffffL, message.getDataLong());
	}
	
	@Test
	public void testGetDataLong3() {
		CanMessage message = new CanMessage();
		message.setData(0, (byte) 0xff);
		message.setData(5, (byte) 0xff);
		assertEquals(0xff00000000ffL, message.getDataLong());
	}
}
