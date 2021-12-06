package com.baboondev.baboonjobsmsjobs.controllers;

import com.baboondev.baboonjobsmsjobs.dtos.OfferDto;
import com.baboondev.baboonjobsmsjobs.models.Offer;
import com.baboondev.baboonjobsmsjobs.services.JobService;
import com.baboondev.baboonjobsmsjobs.util.JwtUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.util.Map;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST, RequestMethod.PUT })
@RequestMapping("offers")
public class OfferController {

	@Autowired
	private JobService jobService;

	@PostMapping("add")
	public ResponseEntity addOffer(@RequestHeader Map<String, String> headers, @Validated @RequestBody OfferDto offer) {
		try {
			String userRole = JwtUtils.getRoleByToken(headers.get("token"));

			if (!userRole.equals("employee") && !userRole.equals("admin")) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No authorized");
			}

			return ResponseEntity.ok(jobService.addOffer(offer));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping("accept")
	public ResponseEntity acceptOffer(@RequestHeader Map<String, String> headers, @Validated @RequestBody Offer offer) {
		try {
			String userRole = JwtUtils.getRoleByToken(headers.get("token"));

			if (!userRole.equals("employeer") && !userRole.equals("admin")) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No authorized");
			}

			return ResponseEntity.ok(jobService.acceptOffer(offer));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
