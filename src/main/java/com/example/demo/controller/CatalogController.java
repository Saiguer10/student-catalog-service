package com.example.demo.controller;

import com.example.demo.models.Catalog;
import com.example.demo.services.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/catalog")
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    @GetMapping("/{courseCode}")
    public ResponseEntity<?> getCatalog(@PathVariable Long courseCode) {
        try {
            Catalog catalog = catalogService.getCatalogByCourseCode(courseCode);
            System.out.println(catalog);
            return (catalog != null)
                    ? ResponseEntity.ok(catalog)
                    : ResponseEntity.notFound().build();

        } catch (Exception e) {
            return ResponseEntity.status(502).build();
        }
    }
}