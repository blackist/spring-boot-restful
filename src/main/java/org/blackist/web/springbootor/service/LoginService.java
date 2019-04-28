package org.blackist.web.springbootor.service;

import org.blackist.web.springbootor.model.security.TokenDetail;

public interface LoginService {

    /**
     * get user detail from db
     *
     * @param username username
     * @return user detail
     */
    TokenDetail getTokenDetail(String username);

    /**
     * generate token with user detail
     *
     * @return token
     */
    String generateToken(TokenDetail tokenDetail);
}
