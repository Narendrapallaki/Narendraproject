package com.batch_processing.service;

import org.springframework.web.multipart.MultipartFile;

public interface ProductService {

	public String saveProductData(MultipartFile file);
}
