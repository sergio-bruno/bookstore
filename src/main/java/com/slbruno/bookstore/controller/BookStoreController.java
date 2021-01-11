package com.slbruno.bookstore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/hw/books")	
public class BookStoreController {
	
	@ApiOperation(value = "Api rest para teste inicial")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucess method return")
	})
	@GetMapping
	public String hello() {
		return "Hello world, PR add!";
	}

}
