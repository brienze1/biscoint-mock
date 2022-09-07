package org.brienze.biscoint.utils;

import java.util.Base64;

import org.brienze.biscoint.adapter.Base64EncoderAdapter;
import org.springframework.stereotype.Component;

@Component
public class Base64Encoder implements Base64EncoderAdapter {

	public String encode(String signString) {
		return  Base64.getEncoder().encodeToString(signString.getBytes());
	}

}
