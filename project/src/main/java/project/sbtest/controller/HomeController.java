package project.sbtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.sbtest.models.User;
import project.sbtest.service.UserService;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String login(Model model, @RequestParam(name = "error", required = false) String error) {
        model.addAttribute("error", error);
        model.addAttribute("user", new User());
        return "login-page";
    }

    @PostMapping("/")
    public String processLogin(User user, RedirectAttributes redirectAttributes) {
        // Log the user details for debugging
        System.out.println("User email: " + user.getEmail());
        System.out.println("User password: " + user.getPassword());

        User authenticatedUser = userService.authenticateUser(user.getEmail(), user.getPassword());

        // Log the result of authentication
        System.out.println("Authenticated User: " + authenticatedUser);

        if (authenticatedUser != null) {
            return "redirect:/dashboard-page"; // Redirect to dashboard page
        } else {
            redirectAttributes.addAttribute("error", "Invalid credentials. Please try again.");
            return "redirect:/"; // Redirect back to login page with error message
        }
    }



    @GetMapping("/dashboard-page")
    public String dashboardPage() {
        return "dashboard-page";
    }
}
