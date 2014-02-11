package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author benja_000
 */
public class MenuDAO {

    private DBAccessor db;
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/restaurant";
    private static final String ADMIN = "root";
    private static final String PASSWORD = "admin";
    private String sql = "select * from menu order by menu_id";
    private static final String MENU_ID = "menu_id";
    private static final String MENU_ITEM = "menu_item";
    private static final String MENU_ITEM_PRICE = "menu_item_price";
    private static final String MENU_VALUE = "menu_value";

    public MenuDAO(DBAccessor db) {
        this.db = db;
    }

    public List<MenuItem> getMenuChoices() throws RuntimeException {
        List<MenuItem> items = new ArrayList<MenuItem>();

        try {
            // Make sure you always open a connection before trying to
            // send commands to the database.            
            db.openConnection(DRIVER, URL, ADMIN, PASSWORD);

            List<Map> rawData = db.findRecords(sql, true);
            for (Map record : rawData) {
                MenuItem item = new MenuItem();
                int id = Integer.valueOf(record.get(MENU_ID).toString());
                item.setMenuId(id);
                String name = String.valueOf(record.get(MENU_ITEM));
                item.setMenuItem(name);
                Double price = Double.valueOf(record.get(MENU_ITEM_PRICE).toString());
                item.setItemPrice(price);
                String value = String.valueOf(record.get(MENU_VALUE).toString());
                item.setMenuValue(value);
                items.add(item);
            }

            return items;

        } catch (IllegalArgumentException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    public void takeOrder(List<MenuItem> orderList) throws RuntimeException {
        try {
            db.openConnection(ADMIN, sql, sql, sql);
            List keys = new ArrayList();
            List values = new ArrayList();
            for (MenuItem item : orderList) {
                keys.add("menu_id");
                values.add(item.getMenuId());
                db.insertRecord("menu", keys, values, true);
            }
            db.closeConnection();
        } catch (IllegalArgumentException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    public static void main(String[] args) {
        MenuDAO dao = new MenuDAO(new DB_Generic());
        List<MenuItem> items = dao.getMenuChoices();
        System.out.println(items);
    }
}
