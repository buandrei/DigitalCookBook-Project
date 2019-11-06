package ro.sci.digitalCookBook.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ro.sci.digitalCookBook.domain.PromovareAleasa;
import ro.sci.digitalCookBook.service.PromovareAleasaService;
import ro.sci.digitalCookBook.service.PromovariService;

@Controller
@RequestMapping("/promovari")
public class PromovariController {

    private static Logger LOGGER = LoggerFactory.getLogger("Promovari Controller");

    @Autowired
    private PromovariService promovariService;

    @Autowired
    private PromovareAleasaService promovareAleasaService;


    @RequestMapping("/add")
    public ModelAndView add() {
        ModelAndView result = new ModelAndView("promovari/add_promovare");
        result.addObject("promovareAleasa", new PromovareAleasa());
        return result;
    }

}
