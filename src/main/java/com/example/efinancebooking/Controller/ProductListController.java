package com.example.efinancebooking.Controller;

import com.example.efinancebooking.Model.BookingObject;
import com.example.efinancebooking.Repos.BookingObjectRepo;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Scope (value = "session")
@Component (value = "productList")
@ELBeanName(value = "productList")
@Join(path = "/", to = "/product-list.jsf")
public class ProductListController {
    @Autowired
    private BookingObjectRepo bookingObjectRepo;

    private List<BookingObject> products;

    @Deferred
    @RequestAction
    @IgnorePostback
    public void loadData() {
        products = bookingObjectRepo.findAll();
    }

    public List<BookingObject> getProducts() {
        return products;
    }

}