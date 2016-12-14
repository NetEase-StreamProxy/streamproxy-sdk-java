package com.netease.cloud.auth;

import com.netease.cloud.exception.ClientException;
import com.netease.cloud.http.Request;

public interface Signer {
    public void sign(Request request, Credentials credentials) throws ClientException;
}
