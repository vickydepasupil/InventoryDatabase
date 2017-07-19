package serve;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Item;
import model.ItemDAO;

@Service
public class ItemService {
	
	@Autowired
	private ItemDAO itemDao;
	
	public ItemService() {
		
	}
	
	public Item getByID(String stockID) {
		return itemDao.getItemById(stockID);
	}
	
	public Item getByName(String itemName) {
		return itemDao.getItemByName(itemName);
	}
	
	public void saveItem(Item item) {
		itemDao.saveItem(item);
	}
	
	public void deleteItem(String stockID) {
		itemDao.deleteItem(stockID);
	}
	
	public List<Item> getAllItems() {
		return itemDao.getAllItems();
	}
	
	public void updateItem(Item item) {
		itemDao.updateItem(item);
	}
}
