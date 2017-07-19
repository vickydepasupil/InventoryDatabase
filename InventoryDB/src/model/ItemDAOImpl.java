package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class ItemDAOImpl implements ItemDAO {
	private JdbcTemplate jdbcTemplate;
	
	private static final String FIND_BYID = "SELECT * FROM items WHERE stockID = ?";
	private static final String FIND_BYNAME = "SELECT * FROM items WHERE itemName = ?";
	private static final String RETRIEVE_ALL = "SELECT * FROM items";
	private static final String SAVE_ITEM = "INSERT INTO items (stockID, itemName, unitPrice, onStock) "
			+ "VALUES (?,?,?,?)";
	private static final String DELETE_ITEM = "DELETE FROM items WHERE stockID = ?";
	private static final String UPDATE_ITEM = "UPDATE items SET itemName = ?, unitPrice = ?, onStock = ? "
			+ "WHERE stockID = ?";

	
	public ItemDAOImpl (DataSource datasource) {
		jdbcTemplate = new JdbcTemplate(datasource);
	}

	@Override
	public Item getItemById(String stockID) {
		Item item = jdbcTemplate.queryForObject(FIND_BYID, new Object[] {stockID}, new ItemMapper());
		return new Item(item.getStockID(), item.getItemName(),
				item.getUnitPrice(), item.getOnStock());
	}

	@Override
	public Item getItemByName(String itemName) {
		Item item = jdbcTemplate.queryForObject(FIND_BYNAME, new Object[] {itemName}, new ItemMapper());
		return new Item(item.getStockID(), item.getItemName(),
				item.getUnitPrice(), item.getOnStock());
	}

	@Override
	public boolean saveItem(Item item) {		
		jdbcTemplate.update(SAVE_ITEM, new Object[] {item.getStockID(), item.getItemName(), item.getUnitPrice(), item.getOnStock()});
		return false;
	}

	@Override
	public boolean deleteItem(String stockID) {
		jdbcTemplate.update(DELETE_ITEM, new Object[] {stockID});
		return false;
	}
	
	@Override
	public List<Item> getAllItems() {
		List<Item> itemList = new ArrayList<>();
		List<Map<String,Object>> rows = jdbcTemplate.queryForList(RETRIEVE_ALL);
		
		for (Map row : rows) {
			Item item = new Item();
			
			item.setStockID((String) row.get("stockID"));
			item.setItemName((String) row.get("itemName"));
			item.setUnitPrice(Double.parseDouble(String.valueOf( row.get("unitPrice"))));
			item.setOnStock((Integer) row.get("onStock"));
			itemList.add(item);
		}
		return itemList;
	}
	
	public static final class ItemMapper implements RowMapper<Item> {
		@Override
		public Item mapRow(ResultSet rs, int i) throws SQLException {
			Item item = new Item();
			item.setStockID(rs.getString("stockID"));
			item.setItemName(rs.getString("itemName"));
			item.setUnitPrice(rs.getDouble("unitPrice"));
			item.setOnStock(rs.getInt("onStock"));
			return item;
		}
	}
	
	@Override
	public boolean updateItem(Item item) {		
		jdbcTemplate.update(UPDATE_ITEM, new Object[] {item.getItemName(), item.getUnitPrice(), item.getOnStock(), item.getStockID()});
		return true;
	}
}
