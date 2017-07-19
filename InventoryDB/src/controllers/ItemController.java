package controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import model.Item;
import serve.ItemService;
import valid.ItemValidator;

@Controller
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ItemValidator itemValidator;
	
	public ItemController() {}
	
	@InitBinder("item")
	public void initBinder(WebDataBinder dataBinder) {
        dataBinder.setValidator(itemValidator);
    }
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String itemForm(Model model) {
		model.addAttribute("item", new Item());
		return "itemForm";
	}
	
	@RequestMapping(value = "/process", method = RequestMethod.POST, params = "save")
	public String saveItem(@ModelAttribute("item") @Valid Item item, BindingResult result,
			Model model) {
		 
		String view = "itemList";
		
		if (result.hasErrors()) {
			model.addAttribute("item", item);
			view = "itemForm";
		} else {			
			itemService.saveItem(item);
			List<Item> items = itemService.getAllItems();
			model.addAttribute("items", items);
		}
		return view;
	}
	
	@RequestMapping(value = "/process", method=RequestMethod.POST, params = "findID")
	public String findID(@RequestParam String stockID, Model model, RedirectAttributes attributes) {
		try {
			Item findItem = itemService.getByID(stockID.trim());
			
			if (findItem != null) {
				model.addAttribute("item", itemService.getByID(stockID.trim()));		
				return "itemDetails";
			}
		} catch (EmptyResultDataAccessException e) {}
		attributes.addFlashAttribute("errorID", "Item not found.");
		return "redirect:/item/add.html?button=search";
	}
	
	@RequestMapping(value = "/process", method=RequestMethod.POST, params = "findName")
	public String findName(@RequestParam String itemName, Model model, RedirectAttributes attributes) {
		try {
			Item findItem = itemService.getByName(itemName.trim());
			
			if (findItem != null) {
				model.addAttribute("item", itemService.getByName(itemName.trim()));		
				return "itemDetails";
			}
		} catch (EmptyResultDataAccessException e) {}
		
		attributes.addFlashAttribute("errorName", "Item not found.");
		return "redirect:/item/add.html?button=search";
			
	}
	
	@RequestMapping(value="/process", method=RequestMethod.POST, params = "list")
	public String getAllItems(Model model) {
		List<Item> itemList = itemService.getAllItems();
		model.addAttribute("items", itemList);
		return "itemList";
	}
	
	@RequestMapping(value="/process", method=RequestMethod.POST, params = "update")
	public String updateItem(@Valid Item item, BindingResult result, Model model) {
		
		String view = "itemList";
		
		if (result.hasErrors()) {
			model.addAttribute("item", item);
			view = "itemDetails";
		} else {
			//Validator class does not allow form submission if ID already exists -> FORM stockID DISABLED
			//Form stockID disabled, so ID has to be retrieved from DB by name search
			Item itemUpdate = itemService.getByName(item.getItemName());
			
			item.setStockID(itemUpdate.getStockID());
			itemService.updateItem(item);
			List<Item> items = itemService.getAllItems();
			model.addAttribute("items", items);
		}
		return view;
	}
	
	@RequestMapping(value="/process", method=RequestMethod.POST, params = "delete")
	public String removeItem(Item item) {
		itemService.deleteItem(item.getStockID());
		return "itemList";
	}	
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model) {
		List<Item> itemList = itemService.getAllItems();
		model.addAttribute("items", itemList);
		return "itemList";
	}
	
	@RequestMapping(value = "/process/{stockID}/{action}", method = RequestMethod.GET)
	public String detailForm(@PathVariable String stockID, @PathVariable String action, Model model) {
		
		String view = "itemList";
		
		switch (action) {
		case "edit":
			model.addAttribute("item", itemService.getByID(stockID));
			view = "itemDetails";
			break;
		case "delete":
			itemService.deleteItem(stockID);	
			List<Item> items = itemService.getAllItems();
			model.addAttribute("items", items);
		}
		return view;
	}
}
