package com.syscxp.biz.core.db.query;

import com.syscxp.biz.core.db.search.header.Inventory;
import org.apache.commons.io.FileUtils;
import com.syscxp.biz.core.utils.zstack.path.PathUtil;
//import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class InventoryQueryDetailsGenerator {
    private static final Logger logger = LoggerFactory.getLogger(InventoryQueryDetailsGenerator.class);

    private static void generateInventoryDetails(String folder, Class<?> inventoryClass) throws IOException {
        String filePath = PathUtil.join(folder, inventoryClass.getSimpleName());
        Class<?> currentClass = inventoryClass;
        StringBuilder sb = new StringBuilder();
        do {
            for (Field f : currentClass.getDeclaredFields()) {
                String info = String.format("%s : %s\n", f.getName(), f.getType().getName());
                sb.append(info);
            }
            currentClass = currentClass.getSuperclass();
        } while (currentClass != Object.class);

        FileUtils.writeStringToFile(new File(filePath), sb.toString());
    }

    public static void generate(String exportPath, List<String> basePkgs) {
        try {
            if (exportPath == null) {
                exportPath = PathUtil.join(System.getProperty("user.home"), "inventory-query-details");
            }

            if (basePkgs == null || basePkgs.isEmpty()) {
                basePkgs = new ArrayList<String>(1);
                basePkgs.add("com.syscxp.biz");
            }

            FileUtils.deleteDirectory(new File(exportPath));
            File folder = new File(exportPath);
            folder.mkdirs();

            String folderName = folder.getAbsolutePath();
            ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
            scanner.addIncludeFilter(new AnnotationTypeFilter(Inventory.class));
            scanner.addExcludeFilter(new AnnotationTypeFilter(Controller.class));
            scanner.addExcludeFilter(new AnnotationTypeFilter(Component.class));
            for (String pkg : basePkgs) {
                for (BeanDefinition bd : scanner.findCandidateComponents(pkg)) {
                    Class<?> clazz = Class.forName(bd.getBeanClassName());
                    generateInventoryDetails(folderName, clazz);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
