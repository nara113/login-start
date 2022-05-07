package hello.login.web.session;

import hello.login.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.Arrays;

class SessionManagerTest {
    SessionManager sessionManager = new SessionManager();

    @Test
    void test() {
        // 세션 생성
        MockHttpServletResponse response = new MockHttpServletResponse();
        Member member = new Member();
        sessionManager.createSession(member, response);

        // 세션 조회
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies());

        Object session1 = sessionManager.getSession(request);
        Assertions.assertThat(session1).isEqualTo(member);

        // 세션 만료
        sessionManager.expire(request);
        Object session = sessionManager.getSession(request);
        Assertions.assertThat(session).isNull();
    }
}