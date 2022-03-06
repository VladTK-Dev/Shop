package pl.edu.wszib.sport.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.sport.store.model.Manufacturer;
import pl.edu.wszib.sport.store.service.impl.ManufacturerService;
import pl.edu.wszib.sport.store.session.SessionObject;

import javax.annotation.Resource;
import java.util.Optional;

@Controller
@RequestMapping(value = "/ski_article")
public class SkisController {

    @Autowired
    ManufacturerService manufacturerService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/{ski_article_type_id}", method = RequestMethod.GET)
    public String getOfficeArticleByTypeId(Model model, @PathVariable Integer ski_article_type_id) {

        if (!sessionObject.isLogged())
            return "redirect:/login";

        Optional<Manufacturer> manufacturerBox = this.manufacturerService.getManufacturerById(ski_article_type_id);

        if (manufacturerBox.isEmpty())
            return "redirect:/main";

        model.addAttribute("manufacturer", manufacturerBox.get());

        return "office_article";
    }
}
