package by.slots.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import by.slots.domain.dto.SpinRequest;
import jakarta.validation.Valid;

@RestController("/slots")
public class SlotsController {

    @RequestMapping(value = "/spin", method = RequestMethod.POST)
    public void spin(@Valid SpinRequest spinRequest) {

    }


}
