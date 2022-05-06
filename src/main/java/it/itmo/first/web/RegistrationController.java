package it.itmo.first.web;


import it.itmo.first.services.CarService;
import it.itmo.first.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
public class RegistrationController {
    private final UserService userService;
    private final CarService carService;

    public RegistrationController(UserService userService, CarService carService) {
        this.userService = userService;
        this.carService = carService;
    }

    @GetMapping( "/main")
    public String mainPage() {
        return "mainPage";
    }

    @GetMapping({"/", "/registration"})
    public String hello(Model model, String name) {
        model.addAttribute("name", name);
        return "userRegistration";
    }

    @RequestMapping(value = "/addCar", method = RequestMethod.POST)
    public String translate(@RequestParam String name, Model model) {

        model.addAttribute("name", name);
        userService.addUserName(name.trim());
        return "addCar";
    }
    @GetMapping({"/", "/addCar"})
    public String addCar(Model model, String brandName, String brandModelName, String color) {

           model.addAttribute("brandName", brandName)
                .addAttribute("brandModelName", brandModelName)
                .addAttribute("color", color);

        return "addCar";
    }

    @RequestMapping(value = "/carSuccess", method = RequestMethod.POST)
    public String carSuccess(@RequestParam String brandName,@RequestParam String brandModelName,
                            @RequestParam String color, Model model) {
           model.addAttribute("brandName", brandName)
                .addAttribute("brandModelName", brandModelName)
                .addAttribute("color", color);

        carService.addCar(brandName.trim(), brandModelName.trim(), color.trim());

        return "carSuccess";
        }

    @GetMapping("/inData")
    public String carList(Model model, String name){
        model.addAttribute("name", name);
        return "inData";
    }
    @RequestMapping(value = "/showData", method = RequestMethod.POST)
    public String getData(@RequestParam String name, Model model) {
        String data = carService.showCars(name.trim());
        model.addAttribute("name", name).addAttribute("data", data);

        return "showData";
    }
}
