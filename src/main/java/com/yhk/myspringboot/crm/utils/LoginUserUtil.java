package com.yhk.myspringboot.crm.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * 在Cookie中获取用户id工具类
 */
public class LoginUserUtil {

    /**
     * 从cookie中获取userId
     *
     * @param request
     * @return
     */
    public static int releaseUserIdFromCookie(HttpServletRequest request) {
        String userIdString = CookieUtil.getCookieValue(request, "userID");
        Optional<String> op = Optional.ofNullable(userIdString);
        if (!op.isPresent()) {
            return 0;
        }
        Optional<Integer> userID = Optional.ofNullable(UserIDBase64.decoderUserID(op.get()));
        return userID.orElse(0);
    }
}
