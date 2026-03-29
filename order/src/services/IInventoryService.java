package services;

import java.util.List;

public interface IInventoryService {

    boolean isAvailable(List<String> items);

    void reserveItems(List<String> items);

    void restoreInventory(List<String> items);

    void commitReservation(List<String> items);

    void releaseReservation(List<String> items);
}
