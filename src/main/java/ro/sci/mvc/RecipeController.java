package ro.sci.mvc;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ro.sci.digitalCookBook.domain.Recipe;
import ro.sci.digitalCookBook.service.RecipeService;

import java.util.Collection;

@Controller
@RequestMapping("/retete")
public class RecipeController {

    private static Logger LOGGER = LoggerFactory.getLogger("Recipe Controller");

    @Autowired
    private RecipeService recipeService;

    @RequestMapping("")
    public ModelAndView list() {
        ModelAndView result = new ModelAndView("retete/list_all");


        Collection<Recipe> recipes = recipeService.listAll();
        result.addObject("recipes", recipes);

        return result;
    }

//    @RequestMapping("/add_recipe")
//    public ModelAndView add() {
//        ModelAndView modelAndView = new ModelAndView("employee/add");
//        modelAndView.addObject("employee", new Employee());
//        return modelAndView;
//    }
//
//    @RequestMapping("/view")
//    public ModelAndView view() {
//        ModelAndView modelAndView = new ModelAndView("employee/view");
//        modelAndView.addObject("employee", new Employee());
//        return modelAndView;
//    }
//
//    @RequestMapping("/edit")
//    public ModelAndView edit(long id) {
//        Employee employee = employeeService.get(id);
//        ModelAndView modelAndView = new ModelAndView("employee/add");
//        modelAndView.addObject("employee", employee);
//        return modelAndView;
//    }
//
//    @RequestMapping("/delete")
//    public String delete(long id) {
//        employeeService.delete(id);
//        return "redirect:/employee";
//    }
//
//    @RequestMapping("/save")
//    public ModelAndView save(@Valid Employee employee,
//                             BindingResult bindingResult) {
//
//        ModelAndView modelAndView = new ModelAndView();
//        if (!bindingResult.hasErrors()) {
//            try {
//                employeeService.save(employee);
//                RedirectView redirectView = new RedirectView("/employee");
//                modelAndView.setView(redirectView);
//            } catch (ValidationException ex) {
//
//                LOGGER.error("validation error", ex);
//
//                List<String> errors = new LinkedList<>();
//                errors.add(ex.getMessage());
//                modelAndView = new ModelAndView("employee/add");
//                modelAndView.addObject("errors", errors);
//                modelAndView.addObject("employee", employee);
//            }
//
//        } else {
//            List<String> errors = new LinkedList<>();
//
//            for (FieldError error :
//                    bindingResult.getFieldErrors()) {
//                errors.add(error.getField() + ":" + error.getDefaultMessage());
//            }
//
//            modelAndView = new ModelAndView("employee/add");
//            modelAndView.addObject("errors", errors);
//            modelAndView.addObject("employee", employee);
//        }
//
//        return modelAndView;
//    }

}
