package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    @Override // 해당 서블릿이 호출되면 service 메서드가 호출된다.
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //웹 브라우저에서 http 요청 메시지를 만들어서 서버에 요청한다.
        //서버는 http 요청이 오면 was 가 request, response 객체를 만들어서 servlet 에 던져준다.
        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);//org.apache.catalina.connector.RequestFacade@195703bf
        System.out.println("response = " + response);//org.apache.catalina.connector.ResponseFacade@73a74f11
        //톰켓, 제티, 언더토우

        String username = request.getParameter("username");
        System.out.println("username = " + username);
        //http 스펙에 있는 메시지를 직접 다 파싱해서 읽는것을 서블릿이 해줌! // 개발자는 핵심 로직에만 집중!

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("hello " + username);
    }
}
