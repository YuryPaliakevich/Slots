package by.slots.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import by.slots.domain.dto.SpinRequest;
import by.slots.domain.dto.SpinResponse;
import by.slots.domain.spin.SpinResult;
import by.slots.service.slots.SpinMapperService;
import by.slots.service.slots.SpinService;
import jakarta.validation.Valid;

@RestController("/slots")
public class SlotsController {

    @Autowired
    private SpinService spinService;

    @Autowired
    private SpinMapperService spinMapperService;

    @RequestMapping(value = "/spin", method = RequestMethod.POST)
    public ResponseEntity<SpinResponse> spin(@Valid SpinRequest spinRequest) {
        final int stake = spinRequest.getStake();

        final SpinResult result = spinService.spin(stake);

        return ResponseEntity.ok(spinMapperService.map(result));
    }


}
