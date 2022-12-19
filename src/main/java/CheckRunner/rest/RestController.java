package CheckRunner.rest;

import CheckRunner.CheckRunner;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RestController {

    @RequestMapping("/")
    public String showFirstView() {
        return "first-view";
    }

    @RequestMapping("/check")
    public String showCheckReceipt(HttpServletRequest request, Model model) {
        String argsString = request.getParameter("itemId");
        String[] args = argsString.toLowerCase().trim().split(" ");

        model.addAttribute("nameAttribute", argsString);

        CheckRunner.main(args);

        return "show-check-view";
    }

}
