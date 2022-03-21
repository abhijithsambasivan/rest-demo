package com.example.restservice.controller.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningController {

	@GetMapping(path = "/versioning/v1")
	public String uriVersioning1() {
		return "uri version 1";
	}

	@GetMapping(path = "/versioning/v2")
	public String uriVersioning2() {
		return "uri version 2";
	}

	@GetMapping(path = "/versioning", produces = "application/custom-version+v1-json")
	public String mimeVersioning1() {
		return "mime version 1";

	}

	@GetMapping(path = "/versioning", produces = "application/custom-version+v2-json")
	public String mimeVersioning2() {
		return "mime version 2";
	}
	
	@GetMapping(path = "/versioning", params = "version=v1")
	public String requestparamVersioning1() {
		return "request param 1";
	}


	@GetMapping(path = "/versioning", params = "version=v2")
	public String requestparamVersioning2() {
		return "request param 2";
	}
	
	
	

}
