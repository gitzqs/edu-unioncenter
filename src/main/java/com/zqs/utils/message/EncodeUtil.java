package com.zqs.utils.message;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class EncodeUtil {
	
	private static Logger logger = LoggerFactory.getLogger(EncodeUtil.class);
	
	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
		'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	
	public static String encode(String plaintext, String method) {
		if (StringUtils.isBlank(plaintext)) {
			return "";
		}
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(method);
			messageDigest.update(plaintext.getBytes());
			return getFormattedText(messageDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			logger.error("Encode error {} msg {}", plaintext, e.getMessage());
		}
		
		return "";
	}
	
	/**
	 * Takes the raw bytes from the digest and formats them correct.
	 * 
	 * @param bytes
	 *            the raw bytes from the digest.
	 * @return the formatted bytes.
	 */
	protected static String getFormattedText(byte[] bytes) {
		int len = bytes.length;
		StringBuilder buf = new StringBuilder(len * 2);
        for (byte aByte : bytes) {
            buf.append(HEX_DIGITS[(aByte >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[aByte & 0x0f]);
        }
		return buf.toString();
	}
}
