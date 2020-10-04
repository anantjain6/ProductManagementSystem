package me.anant.PMS.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import me.anant.PMS.config.DocumentStorageProperty;
import me.anant.PMS.dao.ProductRepository;
import me.anant.PMS.model.Product;

@Service
public class ProductService {

	@Autowired
	ProductRepository pr;
	
	private final Path docStorageLocation;
	
	@Autowired
	public ProductService(DocumentStorageProperty documentStorageProperty) throws IOException {
		this.docStorageLocation = Paths.get("src/main/resources/product-img").toAbsolutePath().normalize();
		Files.createDirectories(this.docStorageLocation);
	}
	
	public void save(Product product) {
		pr.save(product);
	}
	public void delete(Long id) {
		pr.deleteById(id);
	}
	public List<Product> get(){
		return (List<Product>) pr.findAll();
	}
	public Optional<Product> findById(Long id) {
		return pr.findById(id);
	}
	public void deductQty(long id, int qty) {
		Product product = pr.findById(id).get();
		int newQty = product.getProductQty() - qty;
		product.setProductQty(newQty);
		pr.save(product);
	}
	public void addQty(long id, int qty) {
		Product product = pr.findById(id).get();
		int newQty = product.getProductQty() + qty;
		product.setProductQty(newQty);
		pr.save(product);
	}
	public String saveImage(MultipartFile imageFile) {
		String storageLocation = this.docStorageLocation.toAbsolutePath().toString()+"\\"+imageFile.getOriginalFilename();
		
		try {
			byte[] bytes = imageFile.getBytes();
			Path path = Paths.get(storageLocation);
			Files.write(path, bytes);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imageFile.getOriginalFilename();
	}
}
