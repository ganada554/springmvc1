package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller //view 반환
public class ResponseViewController { // 뷰 템플릿을 호출하는 컨트롤러

    /**
     * templates/response/hello.html 반환
     * */
    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1(){
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data","hello!");
        return mav;
    }

    /**
     * templates/response/hello.html 반환
     * */
    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model){
        model.addAttribute("data","hello!!");
        return "response/hello";
    }

    /**
     * !!비추!!
     * void 반환
     * 요청 url을 참고해 논리 뷰를 반환
     * */
    @RequestMapping("/response/hello")
    public void responseViewV3(Model model){
        model.addAttribute("data","hello");
    }

}
