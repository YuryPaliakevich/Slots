package by.slots.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import by.slots.domain.math.probabilities.SingleCombinationProbabilitiesPerReelResult;
import by.slots.service.math.probabilities.SlotsProbabilitiesService;

@RestController("/math")
public class ProbabilitiesRestController {

    @Autowired
    private SlotsProbabilitiesService service;

    @RequestMapping(value = "/reel-probabilities", method = RequestMethod.GET)
    public ResponseEntity<List<SingleCombinationProbabilitiesPerReelResult>> singleProbabilitiesPerReel() {
        return ResponseEntity.ok(service.getResults());
    }


}
