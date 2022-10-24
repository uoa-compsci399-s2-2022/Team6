

package com.project.modules.security.service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * verification code
 *
 *
 */
public interface CaptchaService {

    /**
     * Image Verification
     */
    void create(HttpServletResponse response, String uuid) throws IOException;

    /**
     * Check verification code
     * @param uuid  uuid
     * @param code  Verification code
     * @return  true：success  false：failure
     */
    boolean validate(String uuid, String code);
}
