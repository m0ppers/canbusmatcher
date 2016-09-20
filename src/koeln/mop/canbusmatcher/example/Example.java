package koeln.mop.canbusmatcher.example;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import koeln.mop.canbusmatcher.CanMessage;
import koeln.mop.canbusmatcher.CanMessageMatcher;

public class Example {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		
		CanMessageMatcher matcher = new CanMessageMatcher();
		matcher.setLogger(new ExampleLogger());
		String line;
		
		try (
		    InputStream fis = new FileInputStream("tacho.raw");
		    InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
		    BufferedReader br = new BufferedReader(isr);
		) {
			line = br.readLine();
		    while ((line = br.readLine()) != null) {
		    	//System.out.println(line);
		    	String[] data = line.split(",");
		    	if (data.length != 10) {
		    		System.out.println("Couldn't parse line: " + line);
		    		continue;
		    	}
		    	
		    	CanMessage message = new CanMessage();
		    	message.setAddress(fromString(data[1]));
		    	byte[] byteData = new byte[8];
		    	int index = 0;
		    	for (int i=9;i>=2;i--) {
		    		byteData[index++] = fromString(data[i]);
		    	}
		    	message.setData(byteData);
	    		System.out.println("Line is: " + line + " => " + Long.toHexString(message.getDataLong()));

		    	matcher.publishMessage(message);
		    }
		}
	}
	
	public static byte fromString(String s) {
	    int len = Math.min(s.length(), 2);
	    byte data = 0;
	    int offset = 0;
	    for (int i = len - 1; i >= 0;i--) {
	    	data += (Character.digit(s.charAt(i), 16) << offset);
	    	offset += 4;
	    }
	    return data;
	}
}
