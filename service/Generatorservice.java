package com.tadiewa.Pdfgenerator.service;

import com.tadiewa.Pdfgenerator.entity.Generator;
import com.tadiewa.Pdfgenerator.repository.GeneratorRepository;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import net.sf.jasperreports.engine.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Generatorservice {
    @Autowired
    private GeneratorRepository generatorRepository;

    public Generator create(Generator generator) {
        return generatorRepository.save(generator);
    }

    public List<Generator> list() {
        return generatorRepository.findAll();
    }

    public String printReport(String reportFormat) throws FileNotFoundException, JRException {
        String path = "C://Users//tadiewa//Documents//java//springboot//springboot//jpa//Reportsjasper";
        List<Generator> gen = generatorRepository.findAll();
        //load file and compile
        File file = ResourceUtils.getFile("classpath:reg.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(gen);
        Map<String, Object> map = new HashMap<>();
        map.put("createdBy", "tadiewa");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, dataSource);

        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "//Organicare.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {

            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "//Organicare.pdf");
        }
        return "report generated in path :" + path;
    }
}