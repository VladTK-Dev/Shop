package pl.edu.wszib.sport.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.sport.store.model.Item;
import pl.edu.wszib.sport.store.model.Manufacturer;
import pl.edu.wszib.sport.store.service.IManufacturerService;
import pl.edu.wszib.sport.store.service.impl.ItemService;
import pl.edu.wszib.sport.store.session.SessionObject;

import javax.annotation.Resource;
import java.util.Optional;

@Controller
@RequestMapping(value = "/item")
public class ItemController {

    @Autowired
    ItemService itemService;

    @Autowired
    IManufacturerService iManufacturerService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createForm(Model model) {

        if (!sessionObject.isLogged())
            return "redirect:/login";

        model.addAttribute("authors", this.iManufacturerService.getManufacturerByType("Snowboard"));

        return "item_create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@RequestParam String name, @RequestParam Integer snowboard,
                         @RequestParam Integer quantity,
                         @RequestParam Double price,
                         @RequestParam String description,
                         @RequestParam String type) {

        Optional<Manufacturer> manufacturerBox  = iManufacturerService.getManufacturerById(snowboard);

        if (manufacturerBox.isEmpty())
            return "redirect:/create";


        itemService.addItem(new Item(name, manufacturerBox.get(), price, description, quantity, Item.Type.valueOf(type)));

        return "redirect:/main";
    }


    @RequestMapping(value = "/ski/create", method = RequestMethod.GET)
    public String createSkiForm(Model model) {

        if (!sessionObject.isLogged())
            return "redirect:/login";

        model.addAttribute("manufacturers", this.iManufacturerService.getManufacturerByType("Skis"));
        return "stationery_create";
    }

    @RequestMapping(value = "/ski/create", method = RequestMethod.POST)
    public String createSki(@RequestParam String name, @RequestParam Integer manufacturer,
                         @RequestParam Integer quantity,
                         @RequestParam Double price,
                         @RequestParam String description,
                         @RequestParam String type) {

        if (!sessionObject.isLogged())
            return "redirect:/login";

        Optional<Manufacturer> manufacturerBox  = iManufacturerService.getManufacturerById(manufacturer);

        if (manufacturerBox.isEmpty())
            return "redirect:/create";


        itemService.addItem(new Item(name, manufacturerBox.get(), price, description, quantity, Item.Type.valueOf(type)));

        return "redirect:/main";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Integer id) {

        if (!sessionObject.isLogged())
            return "redirect:/login";

        Optional<Item> itemBox = this.itemService.getItemById(id);

        if (itemBox.isEmpty()){
            return "redirect:/main";
        }

        this.itemService.deleteItem(itemBox.get());

        return "redirect:/main";
    }

    @RequestMapping(value = "/book/show", method = RequestMethod.GET)
    public String create(Model model) {

        if (!sessionObject.isLogged())
            return "redirect:/login";

        model.addAttribute("books", this.itemService.getItemsByType("Snowboard"));

        return "book_list";
    }

    @RequestMapping(value = "/ski/show", method = RequestMethod.GET)
    public String getSki(Model model) {

        if (!sessionObject.isLogged())
            return "redirect:/login";

        model.addAttribute("stationers", this.itemService.getItemsByType("Ski"));

        return "stationery_list";
    }
}
