package org.brienze.biscoint.adapter;

public interface Sha384EncoderAdapter {

	String encode(String signBase64, String apiSecret);

}
