import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.HashMap;

public class InventoryManagementSystem implements Methods {
    public Map<String, ProductNode> inventoryMap;
    private Map<String, String> categoryMap;
    public int productCounter = 0;


    public InventoryManagementSystem() {
        inventoryMap = new HashMap<String, ProductNode>();
        categoryMap = new HashMap<String, String>();
    }

    public void addProduct(ProductNode productInfo) {
        inventoryMap.put(productInfo.getProductCode(), productInfo);
        categoryMap.put(productInfo.getProductCode(), productInfo.getProductCategory());
        productCounter++;
    }

    public void removeProduct(ProductNode productInfo) {
        inventoryMap.remove(productInfo.getProductCode());
        productCounter--;
    }

    @Override
    public void organizeIntoCategory(String productCode, String newCategory) {
        if (categoryMap.containsKey(productCode)) {
            ProductNode productInfo = inventoryMap.get(productCode);
            String oldCategory = productInfo.getProductCategory();

            // Updating the category map with the new category
            categoryMap.put(productCode, newCategory);

            if (!oldCategory.equals(newCategory)) {
                productInfo = new ProductNode(productInfo.getProductCode(), productInfo.getProductName(), newCategory,
                        productInfo.getProductQuantity());
                inventoryMap.put(productCode, productInfo);
            }

        } else {
            System.out.println("Product Not Found in the Inventory!");
        }
    }

    @Override
    public void displayInventory() {
        SortedSet<String> sortedProducts = new TreeSet<String>(inventoryMap.keySet());
        
        System.out.println("Inventory Structure: (Sorted By Product Code)");
        System.out.println("Total Products in the Inventory: " + productCounter);
        int labelCounter = 0;
        for (String productCode : sortedProducts) {
            System.out.println("======== " + (labelCounter + 1) + " ========");
            ProductNode productInfo = inventoryMap.get(productCode);
            System.out.println("Product Code: " + productInfo.getProductCode());
            System.out.println("Product Name: " + productInfo.getProductName());
            System.out.println("Product Category: " + productInfo.getProductCategory());
            System.out.println("Product Quantity: " + productInfo.getProductQuantity() + "\n");
            labelCounter++;
        }
    }
}
