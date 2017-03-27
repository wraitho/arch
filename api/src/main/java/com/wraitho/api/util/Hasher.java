package com.wraitho.api.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher {

	private final String MD5 = "MD5";

	public String md5Hash(String toHash) {
		try {
			MessageDigest md = MessageDigest.getInstance(MD5);
			//return new BigInteger(1, md.digest(toHash.getBytes())).toString(16);

			md.update(toHash.getBytes());
			byte messageDigest[] = md.digest();

			// Create Hex String
			StringBuilder hexString = new StringBuilder();
			for (byte aMessageDigest : messageDigest) {
				String h = Integer.toHexString(0xFF & aMessageDigest);
				while (h.length() < 2)
					h = "0" + h;
				hexString.append(h);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException ignored) {
		}
		return null;
	}
}
