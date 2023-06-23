package b2bbebolino;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class ExcelReadingWriting {
    public static void writeExcel(List<Product> products) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Products");
        sheet.setColumnWidth(0, 2000);
        sheet.setColumnWidth(1, 6000);
        sheet.setColumnWidth(2, 5000);
        sheet.setColumnWidth(3, 3000);
        sheet.setColumnWidth(4, 8000);
        sheet.setColumnWidth(5, 5000);
        sheet.setColumnWidth(6, 3000);
        sheet.setColumnWidth(7, 5000);
        sheet.setColumnWidth(8, 5000);
        sheet.setColumnWidth(9, 4000);
        sheet.setColumnWidth(10, 6000);
        sheet.setColumnWidth(11, 6000);
        sheet.setColumnWidth(12, 4000);
        sheet.setColumnWidth(13, 6000);
        sheet.setColumnWidth(14, 8000);
        sheet.setColumnWidth(15, 4000);
        Row header = sheet.createRow(0);
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        XSSFFont font = workbook.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 16);
        font.setBold(true);
        headerStyle.setFont(font);
        Cell headerCell = header.createCell(0);
        headerCell.setCellValue("ID");
        headerCell.setCellStyle(headerStyle);
        headerCell = header.createCell(1);
        headerCell.setCellValue("Product code");
        headerCell.setCellStyle(headerStyle);
        headerCell = header.createCell(2);
        headerCell.setCellValue("Barcode");
        headerCell.setCellStyle(headerStyle);
        headerCell = header.createCell(3);
        headerCell.setCellValue("Title");
        headerCell.setCellStyle(headerStyle);
        headerCell = header.createCell(4);
        headerCell.setCellValue("Short description");
        headerCell.setCellStyle(headerStyle);
        headerCell = header.createCell(5);
        headerCell.setCellValue("Description");
        headerCell.setCellStyle(headerStyle);
        headerCell = header.createCell(6);
        headerCell.setCellValue("URL");
        headerCell.setCellStyle(headerStyle);
        headerCell = header.createCell(7);
        headerCell.setCellValue("Category");
        headerCell.setCellStyle(headerStyle);
        headerCell = header.createCell(8);
        headerCell.setCellValue("Manufacturer");
        headerCell.setCellStyle(headerStyle);
        headerCell = header.createCell(9);
        headerCell.setCellValue("Price");
        headerCell.setCellStyle(headerStyle);
        headerCell = header.createCell(10);
        headerCell.setCellValue("Original price");
        headerCell.setCellStyle(headerStyle);
        headerCell = header.createCell(11);
        headerCell.setCellValue("Stock status");
        headerCell.setCellStyle(headerStyle);
        headerCell = header.createCell(12);
        headerCell.setCellValue("Images");
        headerCell.setCellStyle(headerStyle);
        headerCell = header.createCell(13);
        headerCell.setCellValue("Meta title");
        headerCell.setCellStyle(headerStyle);
        headerCell = header.createCell(14);
        headerCell.setCellValue("Meta description");
        headerCell.setCellStyle(headerStyle);
        headerCell = header.createCell(15);
        headerCell.setCellValue("Variants");
        headerCell.setCellStyle(headerStyle);
        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);
        int index = 1;
        // Here we start put data in cells
        for (Product product : products) {
            Row row = sheet.createRow(index);
            Cell cell = row.createCell(0);
            cell.setCellValue(product.getId());
            cell.setCellStyle(style);
            cell = row.createCell(1);
            cell.setCellValue(product.getProductCode());
            cell.setCellStyle(style);
            cell = row.createCell(2);
            cell.setCellValue(product.getBarCode());
            cell.setCellStyle(style);
            cell = row.createCell(3);
            cell.setCellValue(product.getTitle());
            cell.setCellStyle(style);
            cell = row.createCell(4);
            cell.setCellValue(product.getShortDescription());
            cell.setCellStyle(style);
            cell = row.createCell(5);
            cell.setCellValue(product.getDescription());
            cell.setCellStyle(style);
            cell = row.createCell(6);
            cell.setCellValue(product.getUrl());
            cell.setCellStyle(style);
            if (product.getCategory() != null) {
                cell = row.createCell(7);
                cell.setCellValue(product.getCategory().toString());
                cell.setCellStyle(style);
            }else{
                cell = row.createCell(15);
                cell.setCellValue("");
                cell.setCellStyle(style);
            }
            cell = row.createCell(8);
            cell.setCellValue(product.getManufacturer());
            cell.setCellStyle(style);
            cell = row.createCell(9);
            cell.setCellValue(product.getPrice().toString());
            cell.setCellStyle(style);
            cell = row.createCell(10);
            cell.setCellValue(product.getOriginalPrice().toString());
            cell.setCellStyle(style);
            cell = row.createCell(11);
            cell.setCellValue(product.getStatus());
            cell.setCellStyle(style);
            cell = row.createCell(12);
            cell.setCellValue(product.getImages().toString());
            cell.setCellStyle(style);
            cell = row.createCell(13);
            cell.setCellValue(product.getMetaTitle());
            cell.setCellStyle(style);
            cell = row.createCell(14);
            cell.setCellValue(product.getMetaDescription());
            cell.setCellStyle(style);
            if (product.getVariants().toString() != null) {
                cell = row.createCell(15);
                cell.setCellValue(product.getVariants().toString());
                cell.setCellStyle(style);
            }else {
                cell = row.createCell(15);
                cell.setCellValue("");
                cell.setCellStyle(style);
            }
            index++;
        }
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        String fileLocation = path.substring(0, path.length() - 1) + "temp.xlsx";
        FileOutputStream outputStream = new FileOutputStream(fileLocation);
        workbook.write(outputStream);
        workbook.close();
    }

    public static void readExcel() throws IOException {
        FileInputStream file = new FileInputStream(new File("temp.xlsx"));
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);
        List<Product> products = new ArrayList<>();
        boolean isFirsRow = true;
        List<Category> categories = new ArrayList<>();
        List<Product> productsInCategory = new ArrayList<>();
        try {
            for (Row row : sheet) {
                if (isFirsRow){
                    isFirsRow = false;
                    continue;
                }
                Product product = new Product();
                try {
                    for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
                        Cell cell = row.getCell(j);
                        if (cell != null) {
                            if (cell.getColumnIndex() == 0) {
                                switch (cell.getCellType()) {
                                    case STRING:
                                        product.setId(cell.getStringCellValue());
                                        //System.out.println(product.getId());
                                        break;
                                    case NUMERIC:
                                        product.setId(String.valueOf(cell.getNumericCellValue()));
                                        //System.out.println(product.getId());
                                        break;
                                    default:
                                        break;
                                }
                            }
                            if (cell.getColumnIndex() == 1) {
                                switch (cell.getCellType()) {
                                    case STRING:
                                        product.setProductCode(cell.getStringCellValue());
                                        //System.out.println(product.getProductCode());
                                        break;
                                    case NUMERIC:
                                        product.setProductCode(String.valueOf(cell.getNumericCellValue()));
                                        //System.out.println(product.getProductCode());
                                        break;
                                    default:
                                        break;
                                }
                            }
                            if (cell.getColumnIndex() == 2) {
                                switch (cell.getCellType()) {
                                    case STRING:
                                        product.setBarCode(cell.getStringCellValue());
                                        //System.out.println(product.getBarCode());
                                        break;
                                    case NUMERIC:
                                        product.setBarCode(String.valueOf(cell.getNumericCellValue()));
                                        //System.out.println(product.getBarCode());
                                        break;
                                    default:
                                        break;
                                }
                            }
                            if (cell.getColumnIndex() == 3) {
                                switch (cell.getCellType()) {
                                    case STRING:
                                        product.setTitle(cell.getStringCellValue());
                                        //System.out.println(product.getId());
                                        break;
                                    case NUMERIC:
                                        product.setTitle(String.valueOf(cell.getNumericCellValue()));
                                        //System.out.println(product.getTitle());
                                        break;
                                    default:
                                        break;
                                }
                            }
                            if (cell.getColumnIndex() == 4) {
                                switch (cell.getCellType()) {
                                    case STRING:
                                        product.setShortDescription(cell.getStringCellValue());
                                        //System.out.println(product.getShortDescription());
                                        break;
                                    case NUMERIC:
                                        product.setShortDescription(String.valueOf(cell.getNumericCellValue()));
                                        //System.out.println(product.getShortDescription());
                                        break;
                                    default:
                                        break;
                                }
                            }
                            if (cell.getColumnIndex() == 5) {
                                switch (cell.getCellType()) {
                                    case STRING:
                                        product.setDescription(cell.getStringCellValue());
                                        //System.out.println(product.getDescription());
                                        break;
                                    case NUMERIC:
                                        product.setDescription(String.valueOf(cell.getNumericCellValue()));
                                        //System.out.println(product.getDescription());
                                        break;
                                    default:
                                        break;
                                }
                            }
                            if (cell.getColumnIndex() == 6) {
                                switch (cell.getCellType()) {
                                    case STRING:
                                        product.setUrl(cell.getStringCellValue());
                                        //System.out.println(product.getUrl());
                                        break;
                                    case NUMERIC:
                                        product.setUrl(String.valueOf(cell.getNumericCellValue()));
                                        //System.out.println(product.getUrl());
                                        break;
                                    default:
                                        break;
                                }
                            }
                            if (cell.getColumnIndex() == 7) {
                                if (cell.getStringCellValue() != null && !cell.getStringCellValue().equalsIgnoreCase("")) {
                                    String s = cell.getStringCellValue();
                                    String[] excelCategoryString = s.trim().split("<");
                                    String categoryName = excelCategoryString[0];
                                    Category category;
                                    if (s == null && !s.equalsIgnoreCase("")) {
                                        break;
                                    }
                                    else if (excelCategoryString[1] != null && !excelCategoryString[1].equalsIgnoreCase("")) {
                                        String parentName = excelCategoryString[1];
                                        Category parentCat = new Category(parentName);
                                        category = new Category(categoryName, parentCat);
                                        product.setCategory(category);
                                    } else {
                                        category = new Category(categoryName);
                                        product.setCategory(category);
                                    }
                                    if (!categories.contains(category)) {
                                        categories.add(category);
                                    }
                                    //System.out.println(product.getCategory().toString());
                                }
                            }
                            if (cell.getColumnIndex() == 8) {
                                switch (cell.getCellType()) {
                                    case STRING:
                                        product.setManufacturer(cell.getStringCellValue());
                                        //System.out.println(product.getManufacturer());
                                        break;
                                    case NUMERIC:
                                        product.setManufacturer(String.valueOf(cell.getNumericCellValue()));
                                        // System.out.println(product.getManufacturer());
                                        break;
                                    default:
                                        break;
                                }
                            }
                            if (cell.getColumnIndex() == 9) {
                                switch (cell.getCellType()) {
                                    case STRING:
                                        product.setPrice(BigDecimal.valueOf(Double.parseDouble(cell.getStringCellValue())));
                                        //System.out.println(product.getPrice().toString());
                                        break;
                                    case NUMERIC:
                                        product.setPrice(BigDecimal.valueOf(cell.getNumericCellValue()));
                                        break;
                                    default:
                                        break;
                                }
                            }
                            if (cell.getColumnIndex() == 10) {
                                switch (cell.getCellType()) {
                                    case STRING:
                                        product.setOriginalPrice(BigDecimal.valueOf(Double.parseDouble(cell.getStringCellValue())));
                                        //System.out.println(product.getPrice().toString());
                                        break;
                                    case NUMERIC:
                                        product.setOriginalPrice(BigDecimal.valueOf(cell.getNumericCellValue()));
                                        break;
                                    default:
                                        break;
                                }
                            }
                            if (cell.getColumnIndex() == 11) {
                                product.setStatus(cell.getStringCellValue());
                                // System.out.println(product.getStatus());
                            }
                            if (cell.getColumnIndex() == 12) {
                                String neshtoZaKartinki = cell.getStringCellValue().replace("[", "").replace("]", "");
                                String[] neshtoSiMsiv = neshtoZaKartinki.trim().split(",");
                                List<String> images = new ArrayList<>();
                                for (int m = 0; m < neshtoSiMsiv.length; m++) {
                                    images.add(neshtoSiMsiv[m]);
                                }
                                product.setImages(images);
                                //System.out.println(product.getImages().toString());
                            }
                            if (cell.getColumnIndex() == 13) {
                                product.setMetaTitle(cell.getStringCellValue());
                                //System.out.println(product.getMetaTitle());
                            }
                            if (cell.getColumnIndex() == 14) {
                                product.setMetaDescription(cell.getStringCellValue());
                                //System.out.println(product.getMetaDescription());
                            }
                            if (cell.getColumnIndex() == 15) {
                                if (cell.getStringCellValue() != null && !cell.getStringCellValue().equalsIgnoreCase("") && !cell.getStringCellValue().equalsIgnoreCase("[]")) {
                                    String excelVariantString = cell.getStringCellValue().trim();
                                    String nov = excelVariantString.trim().replace("[", "").replace("]", "").replaceAll(", ", "");
                                    String[] variantsArr = nov.trim().split(";");
                                    List<Variant> variants = new ArrayList<>();
                                    for (int n = 0; n < variantsArr.length; n++) {
                                        Variant variant = new Variant();
                                        String[] varData = variantsArr[n].trim().split(",");
                                        variant.setId(varData[0]);
                                        variant.setKey(varData[1]);
                                        variant.setStockStatus(varData[2]);
                                        variant.setValue(varData[3]);
                                        variant.setPrice(BigDecimal.valueOf(Double.parseDouble(varData[4])));
                                        variants.add(variant);
                                    }
                                    product.setVariants(variants);
                                }
                            }
                            if (cell.getColumnIndex() > 15) {
                                break;
                            }
                        }
                    }
                    System.out.println(String.format("ROW [%d] [%s] [%s] [%s] [%s]", row.getRowNum(), product.getId(), product.getProductCode(), product.getCategory(), product.getVariants()));
                    products.add(product);
                } catch (Exception e) {
                    System.out.println("Error reading row " + row.getRowNum());
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e) {
            System.out.println("Error reading sheet");
            e.printStackTrace();
        }
    }
}
