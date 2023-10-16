package by.slots.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("/slots")
public class SlotsController {

    @RequestMapping(value = "/spin", method = RequestMethod.POST)
    public void spin() {

    }


}
