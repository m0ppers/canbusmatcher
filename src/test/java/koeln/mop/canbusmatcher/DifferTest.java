package koeln.mop.canbusmatcher;

import static org.junit.Assert.*;

import org.junit.Test;

public class DifferTest {

	@Test
	public void test1() {
		byte[] a = {0};
		byte[] b = {1};
		assertEquals(1, Differ.diff(a,b ));
	}

	@Test
	public void test2() {
		byte[] a = {0};
		byte[] b = {0};
		assertEquals(0, Differ.diff(a,b ));
	}
	
	@Test
	public void test3() {
		byte[] a = {1};
		byte[] b = {-127};
		assertEquals(128, Differ.diff(a,b ));
	}
	
	@Test
	public void test4() {
		byte[] a = {127, 0};
		byte[] b = {0, 127};
		assertEquals(0x7f7f, Differ.diff(a,b ));
	}
	
	@Test
	public void test5() {
		byte[] a = {-128, -128};
		byte[] b = {-128, -128};
		assertEquals(0x0000, Differ.diff(a,b));
	}
	
	@Test
	public void testFull() {
		byte[] a = {(byte) 0xff, (byte)0x9f, 0x0f, 0x00, (byte)0x85, 0x06, (byte)0xff, (byte)0xff};
		byte[] b = {(byte) 0xff, (byte)0x9f, 0x10, 0x00, (byte)0x85, 0x06, (byte)0xff, (byte)0xff};
		assertEquals(0x1f0000, Differ.diff(a,b));
	}
}
