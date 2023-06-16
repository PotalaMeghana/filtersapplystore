package eStoreProduct.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import eStoreProduct.model.Category;
import eStoreProduct.model.Product;

public class ProductDAOImp implements ProductDAO {

	private static final String JDBC_DRIVER = "org.postgresql.Driver";
	private static final String DB_URL = "jdbc:postgresql://192.168.110.48:5432/plf_training";
	private static final String USERNAME = "plf_training_admin";
	private static final String PASSWORD = "pff123";

	@Override
	public List<String> getAllProductCategories() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Product> getProductsByCategory(Integer category_id) {
		List<Product> products = new ArrayList<>();

		try {
			Class.forName(JDBC_DRIVER);
			Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

			String query = "SELECT * FROM slam_Products WHERE prod_prct_id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, category_id);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int prod_id = resultSet.getInt("Prod_id");
				String prod_title = resultSet.getString("prod_title");
				int prod_prct_id = resultSet.getInt("prod_prct_id");
				int prod_gstc_id = resultSet.getInt("prod_gstc_id");
				String prod_brand = resultSet.getString("prod_brand");
				String image_url = resultSet.getString("image_url");
				String prod_desc = resultSet.getString("prod_desc");
				int reorderLevel = resultSet.getInt("reorderLevel");

				Product product = new Product(prod_id, prod_title, prod_prct_id, prod_gstc_id, prod_brand, image_url,
						prod_desc, reorderLevel);

				products.add(product);
			}

			resultSet.close();
			statement.close();
			connection.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return products;
	}

	public List<Product> getAllProducts() {
		List<Product> products = new ArrayList<>();

		try {
			Class.forName(JDBC_DRIVER);
			Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

			String query = "SELECT * FROM slam_Products";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int prod_id = resultSet.getInt("Prod_id");
				String prod_title = resultSet.getString("prod_title");
				int prod_prct_id = resultSet.getInt("prod_prct_id");
				int prod_gstc_id = resultSet.getInt("prod_gstc_id");
				String prod_brand = resultSet.getString("prod_brand");
				String image_url = resultSet.getString("image_url");
				String prod_desc = resultSet.getString("prod_desc");
				int reorderLevel = resultSet.getInt("reorderLevel");

				Product product = new Product(prod_id, prod_title, prod_prct_id, prod_gstc_id, prod_brand, image_url,
						prod_desc, reorderLevel);

				products.add(product);
			}

			resultSet.close();
			statement.close();
			connection.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return products;
	}

	public List<Category> getAllCategories() {
		List<Category> categories = new ArrayList<>();

		try {
			// Load the JDBC driver
			Class.forName(JDBC_DRIVER);

			// Establish the database connection
			Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

			// Prepare the SQL query
			String query = "SELECT * FROM slam_ProductCategories";
			PreparedStatement statement = connection.prepareStatement(query);

			// Execute the query
			ResultSet resultSet = statement.executeQuery();

			// Process the result set
			while (resultSet.next()) {
				// Retrieve category information from the result set
				int prct_id = resultSet.getInt("prct_id");
				String prct_title = resultSet.getString("prct_title");
				String prct_desc = resultSet.getString("prct_desc");
				Category ctg = new Category(prct_id, prct_title, prct_desc);

				// Add the category to the list
				categories.add(ctg);
			}

			// Close the result set, statement, and connection
			resultSet.close();
			statement.close();
			connection.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return categories;
	}

	public Product getProductById(Integer productId) {
		Product product = null;
		try {
			Class.forName(JDBC_DRIVER);
			Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			String query = "SELECT * FROM slam_Products WHERE prod_id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, productId);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				// Retrieve the product details from the result set
				int prod_id = resultSet.getInt("Prod_id");
				String prod_title = resultSet.getString("prod_title");
				int prod_prct_id = resultSet.getInt("prod_prct_id");
				int prod_gstc_id = resultSet.getInt("prod_gstc_id");
				String prod_brand = resultSet.getString("prod_brand");
				String image_url = resultSet.getString("image_url");
				String prod_desc = resultSet.getString("prod_desc");
				int reorderLevel = resultSet.getInt("reorderLevel");

				// Create a new Product object
				product = new Product(prod_id, prod_title, prod_prct_id, prod_gstc_id, prod_brand, image_url, prod_desc,
						reorderLevel);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

}