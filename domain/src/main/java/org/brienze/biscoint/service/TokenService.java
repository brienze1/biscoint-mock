package org.brienze.biscoint.service;

import org.brienze.biscoint.adapter.Base64EncoderAdapter;
import org.brienze.biscoint.adapter.Sha384EncoderAdapter;
import org.brienze.biscoint.adapter.StringUtilsAdapter;
import org.brienze.biscoint.exception.AuthenticationException;
import org.brienze.biscoint.validator.Validators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TokenService {

    @Autowired
    private Base64EncoderAdapter base64Encoder;

    @Autowired
    private Sha384EncoderAdapter sha384Encoder;

    @Autowired
    private StringUtilsAdapter stringUtils;

    public String signToken(Object body, String path, String nonce, String secret) {
        Validators.validateNotNull(body, "Body cannot be null");
        Validators.validateNotEmpty(path, "path cannot be empty");
        Validators.validateNotEmpty(nonce, "Nonce cannot be empty");
        Validators.validateNotEmpty(secret, "Secret cannot be empty");

        String bodyString;
        try {
            bodyString = stringUtils.parseToString(body);
        } catch (Exception e) {
            throw new AuthenticationException(e.getMessage());
        }

        String signString = path + nonce + bodyString
                .replace("\\\"", "\"")
                .replace("\"{", "{")
                .replace("}\"", "}")
                .replace("\\n", "")
                .replace("\\r", "")
                .replace(" ", "");

        System.out.println(signString);

        String signBase64 = base64Encoder.encode(signString);

        return sha384Encoder.encode(signBase64, secret);
    }

}
