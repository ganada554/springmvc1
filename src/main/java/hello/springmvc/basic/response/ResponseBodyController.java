package hello.springmvc.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
//@RestController -> RestAPI를 만들 때 사용하는 컨트롤러 -> 모든 메서드에 @ResponseBody가 붙기 떄문..
public class ResponseBodyController { //정적 리소스나 뷰 템플릿을 거치지 않고, 직접 HTTP 응답 메시지를 전달하는 경우

    /**
     * 서블릿을 직접 다룰 때처럼 HttpServletResponse를 이용해 바디 메시지에 직접 ok응답 전달
     * */
    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("ok");
    }

    /**
     * HttpEntity를 상속 받은 ResponseEntity(Http Status 추가)
     * HttpEntity는 HTTP 메시지의 헤더, 바디 정보를 갖고 있음
     * ResponseEntity는 HTTP 응답 코드를 설정할 수 있음
     * @return
     */
    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2(){
        return new ResponseEntity<>("ok", HttpStatus.OK);//body, status code
    }

    /**
     * @ResponseBody는 반환타입이 String일 때 뷰를 반환하지 않고
     * 반환값을 HTTP 메시지 컨버터를 통해 HTTP 바디 메시지에 입력함
     * ResponseEntity 도 같은 방식으로 동작!
     * */
    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3(){
        return "ok";
    }

    /**
     * ResponseEntity를 반환
     * HTTP 메시지 컨버터를 통해 JSON 형식으로 변환
     * */
    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1(){
        HelloData helloData = new HelloData();
        helloData.setUsername("은정");
        helloData.setAge(26);
        return new ResponseEntity<>(helloData,HttpStatus.OK); //body, status code
    }

    /**
     * ResponseEntity로 HTTP 응답 코드를 동적으로 변경할 수 잇음
     *
     * */
    @ResponseStatus(HttpStatus.OK) //응답코드 설정
    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2(){
        HelloData helloData = new HelloData();
        helloData.setUsername("은정2");
        helloData.setAge(25);
        return helloData;
    }

}
