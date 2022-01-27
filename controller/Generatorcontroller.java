package com.tadiewa.Pdfgenerator.controller;

import com.tadiewa.Pdfgenerator.entity.Generator;
import com.tadiewa.Pdfgenerator.service.Generatorservice;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
public class Generatorcontroller {

    @Autowired
    private Generatorservice generatorservice;

    @PostMapping("/create")
    public Generator create(@RequestBody Generator generator) {
        return generatorservice.create(generator);

    }

    @GetMapping("/list")
    public List<Generator> list() {

        return generatorservice.list();
    }

    @GetMapping("/report/{reportFormat}")
    public String printReport(@PathVariable("reportFormat") String reportFormat) throws JRException, FileNotFoundException {
        // log.info("inside  exportReportOrganicareEmployeeEarningController");
        return generatorservice.printReport(reportFormat);
    }
}
