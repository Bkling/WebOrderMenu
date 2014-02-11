package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author benja_000
 */
public class MenuService {

    private List<MenuItem> menuList;
    private List<MenuItem> orderList;
    private MenuDAO dao = new MenuDAO(new DB_Generic());

    //Used this from Lombardo's Application. Wanted to use it so I give credit for it.
    private void initItemsDb() {
        dao = new MenuDAO(new DB_Generic());
        menuList = dao.getMenuChoices();
        orderList = new ArrayList<MenuItem>();
    }

    public List<MenuItem> getMenuList() {
        menuList = dao.getMenuChoices();
        return menuList;
    }

    public List<MenuItem> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<MenuItem> orderList) {
        this.orderList = orderList;
    }

    public void placeOrder() {
        dao.takeOrder(orderList);
        initItemsDb();
    }
}
