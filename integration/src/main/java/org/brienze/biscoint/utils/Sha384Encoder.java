package org.brienze.biscoint.utils;

import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.HmacUtils;
import org.brienze.biscoint.adapter.Sha384EncoderAdapter;
import org.springframework.stereotype.Component;

@Component
public class Sha384Encoder implements Sha384EncoderAdapter {

	private static final String HMAC_SHA_384 = "HmacSHA384";

	public String encode(String message, String secret) {
		byte[] key = secret.getBytes(StandardCharsets.UTF_8);

		byte[] hmac = new HmacUtils(HMAC_SHA_384, key).hmac(message);

		return Hex.encodeHexString(hmac);
	}
}
