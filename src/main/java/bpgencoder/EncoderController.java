package bpgencoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/endcoding")
public class EncoderController {

    @RequestMapping(method = RequestMethod.GET)
    public String test(){
        System.out.println("들어옴?");
        return "index";
    }
}
