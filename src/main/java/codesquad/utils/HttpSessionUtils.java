package codesquad.utils;

import codesquad.domain.User;
import codesquad.exception.UserNotLoggedInException;

import javax.servlet.http.HttpSession;

public class HttpSessionUtils {
    public static final String USER_SESSION_KEY = "sessionedUser";

    public static boolean isLogin(HttpSession httpSession) {
        Object sessionedUser = httpSession.getAttribute(USER_SESSION_KEY);
        if (sessionedUser == null) {
            return false;
        }
        return true;
    }

    public static User getSessionedUser(HttpSession httpSession) {
        if (!isLogin(httpSession)) {
            throw new UserNotLoggedInException();
        }
        return (User) httpSession.getAttribute(USER_SESSION_KEY);
    }
}