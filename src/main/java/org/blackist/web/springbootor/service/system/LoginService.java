package org.blackist.web.springbootor.service.system;

import org.blackist.web.springbootor.model.security.TokenDetail;

public interface LoginService {

    /**
     * get system detail from db
     *
     * @param username username
     * @return system detail
     */
    TokenDetail getTokenDetail(String username);

    /**
     * generate token with system detail
     *
     * @return token
     */
    String generateToken(TokenDetail tokenDetail);
}
