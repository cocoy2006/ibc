package molab.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

	protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	protected static MessageDigest messagedigest = null;
	static {
		try {
			messagedigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException nsaex) {
			System.err.println(MD5Util.class.getName() + "初始化失败，不支持MD5Util。");
		}
	}
	
	public static String getStringMD5(String str) {
		messagedigest.update(str.getBytes());
		return bufferToHex(messagedigest.digest());
	}
	
	public static String getBytesMD5(byte[] bytes) {
		messagedigest.update(bytes);
		return bufferToHex(messagedigest.digest());
	}
	
	public static String getInputStreamMD5(InputStream is) throws IOException {
		StringBuffer md5 = new StringBuffer();
		byte[] dataBytes = new byte[1024];  
        
        int nread = 0;   
        while ((nread = is.read(dataBytes)) != -1) {  
        	messagedigest.update(dataBytes, 0, nread);
        };  
        byte[] mdbytes = messagedigest.digest();  
          
        // convert the byte to hex format  
        for (int i = 0; i < mdbytes.length; i++) {  
            md5.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));  
        }  
        return md5.toString();
	}

	public static String getFileMD5(File file) throws FileNotFoundException {
		FileInputStream fis = new FileInputStream(file);
		return getFileMD5(fis);
	}
	
	public static String getFileMD5(FileInputStream fis) {
		try {
			FileChannel ch = fis.getChannel();
			MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, fis.available());
			messagedigest.update(byteBuffer);
			return bufferToHex(messagedigest.digest());
		} catch(IOException ioe) {
			return "";
		}
	}

	private static String bufferToHex(byte bytes[]) {
		return bufferToHex(bytes, 0, bytes.length);
	}

	private static String bufferToHex(byte bytes[], int m, int n) {
		StringBuffer stringbuffer = new StringBuffer(2 * n);
		int k = m + n;
		for (int l = m; l < k; l++) {
			appendHexPair(bytes[l], stringbuffer);
		}
		return stringbuffer.toString();
	}

	private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
		char c0 = hexDigits[(bt & 0xf0) >> 4];
		char c1 = hexDigits[bt & 0xf];
		stringbuffer.append(c0);
		stringbuffer.append(c1);
	}
	
}
