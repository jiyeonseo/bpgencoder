package bpgencoder;

import com.sun.javafx.sg.prism.NGShape;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;

@Controller
public class EncoderController {

    @RequestMapping(value="/encoding", method = RequestMethod.GET)
    public String test(){
        System.out.println("????????????????????>>>>>>");
        return "index";
    }

    @RequestMapping(value = "/encoding", method = RequestMethod.POST)
    public Model encode(Model model,
                        @RequestParam MultipartFile file){


        model.addAttribute("encodedFile");
        return model;
    }
}
