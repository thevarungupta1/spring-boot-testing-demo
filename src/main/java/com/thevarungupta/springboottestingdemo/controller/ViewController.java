package com.thevarungupta.springboottestingdemo.controller;

import com.thevarungupta.springboottestingdemo.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
public class ViewController {

    Logger logger = LoggerFactory.getLogger(ViewController.class);

    @GetMapping("/index")
    public ModelAndView index() {

        logger.error("INFO Message");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");

        List<Product> products = fetchProducts();
        modelAndView.addObject("products", products);

        return modelAndView;
    }

    /**
     * Dummy method to simulate fetching products from a data source.
     *
     * @return
     */
    private List<Product> fetchProducts() {
        Locale locale = LocaleContextHolder.getLocale();

        List<Product> products = new ArrayList<Product>();

        Product product = new Product();
        product.setName("television");
        product.setPrice(localizePrice(locale, 15678.43));
        product.setLastUpdated(localizeDate(locale,
                LocalDate.of(2021, Month.SEPTEMBER, 22)));
        products.add(product);

        product = new Product();
        product.setName("washingmachine");

        product.setPrice(localizePrice(locale, 152637.76));
        product.setLastUpdated(localizeDate(locale,
                LocalDate.of(2021, Month.SEPTEMBER, 20)));
        products.add(product);
        return products;
    }

    private String localizeDate(final Locale locale, final LocalDate date ) {
        String localizedDate = DateTimeFormatter.ISO_LOCAL_DATE.format(date);
        return localizedDate;
    }


    private String localizePrice(final Locale locale, final Double price ) {
        NumberFormat numberFormat=NumberFormat.getInstance(locale);
        String localizedPrice = numberFormat.format(price);
        return localizedPrice;
    }
}