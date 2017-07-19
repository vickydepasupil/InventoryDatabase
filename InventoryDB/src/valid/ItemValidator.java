package valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import model.Item;
import serve.ItemService;

public class ItemValidator implements Validator {
	@Autowired
	ItemService itemService;

	@Override
	public boolean supports(Class<?> paramClass) {
		return  Item.class.isAssignableFrom(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {	
		Item item = (Item) obj;
		Double price = item.getUnitPrice();
		Integer stock = item.getOnStock();
		
		if (price == null ) {
			errors.rejectValue("unitPrice", "validation.number", "must be number");
		} else if (price <= 0 && price != null) {
			errors.rejectValue("unitPrice", "validation.negative", "must be > 0");
		}
		
		if (stock == null ) {
			errors.rejectValue("onStock", "validation.number", "must be number");
		} else if (stock <= 0 && stock != null) {
			errors.rejectValue("onStock", "validation.negative", "must be > 0");
		}
				
		try {
			Item existingItem = itemService.getByID(item.getStockID());
			
			if (existingItem != null) {
				errors.rejectValue("stockID", "validation.exists", "exists");
				
			}
		} catch (EmptyResultDataAccessException e) {
			
		}
		
	}
}

