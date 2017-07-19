package model;

import java.util.List;

public interface ItemDAO {
	public List<Item> getAllItems();
	public Item getItemById(String stockID);
	public Item getItemByName(String itemName);
	public boolean saveItem(Item item);
	public boolean deleteItem(String stockID);
	public boolean updateItem(Item item);
}
