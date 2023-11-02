package product_management;

public class Product {
	public String productName = "";
	public String productID = "";
	public String availableQuantity = "";
	public String productPrice = "";
	public String productCategory = "";
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public String getAvailableQuantity() {
		return availableQuantity;
	}
	public void setAvailableQuantity(String availableQuantity) {
		this.availableQuantity = availableQuantity;
	}
	public String getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	@Override
	public String toString() {
		return "Product [productName=" + productName + ", productID=" + productID + ", availableQuantity="
				+ availableQuantity + ", productPrice=" + productPrice + ", productCategory=" + productCategory + "]";
	}
	
}