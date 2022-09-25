package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// RequestMappingHandlerMapping.isHandler() 로 @Controller 또는 @RequestMapping 이 클래스 레벨에 있어야 매핑됨
@Controller
//@Component
//@RequestMapping
public class SpringMemberFormControllerV1 {
    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process() {
        return new ModelAndView("new-form");
    }
}
