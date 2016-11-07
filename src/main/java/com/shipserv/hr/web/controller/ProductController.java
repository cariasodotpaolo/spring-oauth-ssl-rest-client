/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shipserv.hr.web.controller;

import com.shipserv.hr.domain.Product;
import com.shipserv.hr.service.ProductService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author paolo
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    
    @Resource
    private ProductService productService;
    
    
    @RequestMapping(value="/list", method = RequestMethod.GET)
    public String getProducts(@RequestParam String category,
                                    Model model,
                                    HttpServletRequest request) throws SQLException {
        
        HttpSession session = request.getSession(false);
        
        if(session == null) {
          return "redirect:/login.html";  
        }
        
        System.out.println("Retrieving products...");
        
        List<Product> products = productService.getProducts(category);
        
        
        for(Product p: products) {
            
            System.out.println("Code: " + p.getCode());            
        }
        
        //model.addAttribute("products", products);
        request.setAttribute("products", products);
        
        return "view-products";
    }
    
    /**
     *
     * @param productId
     * @param request
     * @return
     * @throws SQLException
     */
    @RequestMapping(value="/id/{productId}", method = RequestMethod.GET)
    public ModelAndView getProductById(@PathVariable("productId") int productId,
                                       HttpServletRequest request) throws SQLException {
        
        
        HttpSession session = request.getSession(false);
        ModelAndView model = null;
                
        System.out.println("Product ID: " + productId);
        
        if(session == null) {
            model = new ModelAndView("login");
            return model;
        } else {
            model = new ModelAndView("view-products");
        }
        
        List<Product> products = new ArrayList<>();
        Product product = productService.getProductById(productId);
        products.add(product);
        
        model.addObject("products", products);
        
        return model;       
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addProduct(HttpServletRequest request) throws SQLException {
        
        HttpSession session = request.getSession(false);
        ModelAndView model = null;
        
        if(session == null) {
            model = new ModelAndView("login");
            return model;
        } else {
            model = new ModelAndView("view-products");
        }
        
        
        List<Product> products = new ArrayList<>();
        
        Product product = new Product();
        product.setId(Integer.parseInt(request.getParameter("productId")));
        product.setCode(request.getParameter("code"));
        product.setPrice(Double.parseDouble(request.getParameter("price")));
        product.setSize(request.getParameter("price"));
        product.setStock(Integer.parseInt(request.getParameter("stock")));
        product.setCategory(request.getParameter("category"));
        
        Product savedProduct = productService.addProduct(product);        
        products.add(savedProduct);
        
        model.addObject("products", products);
        
        return model;
    }
   
}
