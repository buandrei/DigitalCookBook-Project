package ro.sci.digitalCookBook.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ro.sci.digitalCookBook.domain.Events;
import ro.sci.digitalCookBook.exception.ValidationException;
import ro.sci.digitalCookBook.service.EventsService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/events")
public class EventsController {

    private static Logger LOGGER = LoggerFactory.getLogger("EventsController");

    @Autowired
    private EventsService eventsService;

    @RequestMapping("")
    public ModelAndView list() {
        ModelAndView result = new ModelAndView("events/list");
        Collection<Events> events = eventsService.listAll();
        result.addObject("events", events);
        return result;
    }

    @RequestMapping("/add")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("events/add");
        modelAndView.addObject("events", new Events());
        return modelAndView;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(Integer id) {
        Events events = eventsService.get(id);
        ModelAndView modelAndView = new ModelAndView("events/add");
        modelAndView.addObject("events", events);
        return modelAndView;
    }

    @RequestMapping("/admin")
    public ModelAndView admin(Integer id) {
        Events events = eventsService.get(id);
        ModelAndView modelAndView = new ModelAndView("events/admin");
        modelAndView.addObject("events", events);
        return modelAndView;
    }

    @RequestMapping("/delete")
    public String delete(Integer id) {
        eventsService.delete(id);
        return "redirect:/events";
    }

    @RequestMapping("/save")
    public ModelAndView save(@Valid Events events,
                             BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView();
        if (!bindingResult.hasErrors()) {
            try {
                eventsService.save(events);
                RedirectView redirectView = new RedirectView("/events");
                modelAndView.setView(redirectView);
            } catch (ValidationException ex) {

                LOGGER.error("validation error", ex);

                List<String> errors = new LinkedList<>();
                errors.add(ex.getMessage());
                modelAndView = new ModelAndView("events/add");
                modelAndView.addObject("errors", errors);
                modelAndView.addObject("events", events);
            }

        } else {
            List<String> errors = new LinkedList<>();

            for (FieldError error :
                    bindingResult.getFieldErrors()) {
                errors.add(error.getField() + ":" + error.getDefaultMessage());
            }

            modelAndView = new ModelAndView("events/add");
            modelAndView.addObject("errors", errors);
            modelAndView.addObject("events", events);
        }
        return modelAndView;
    }
}