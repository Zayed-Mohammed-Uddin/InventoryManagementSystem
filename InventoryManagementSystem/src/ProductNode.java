public class ProductNode {
    private String productCode;
    private String productName;
    private String productCategory;
    private int productQuantity;
    
    public ProductNode(){
        // default constructor
    }
    public ProductNode(String productCode, String productName, String productCategory, int productQuantity) {
        this.productCode = productCode;
        this.productName = productName;
        this.productCategory = productCategory;
        this.productQuantity = productQuantity;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public int getProductQuantity() {
        return productQuantity;
    }
}
