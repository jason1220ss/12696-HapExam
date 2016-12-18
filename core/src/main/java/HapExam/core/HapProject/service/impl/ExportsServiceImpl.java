package hbi.core.HapProject.service.impl;


import com.hand.hap.core.IRequest;
import hbi.core.HapProject.dto.ColumnsInfo;
import hbi.core.HapProject.dto.ExportsConfig;
import hbi.core.HapProject.service.IExportsService;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by jason on 2016/12/18.
 */
@Service
@Transactional
public class ExportsServiceImpl implements IExportsService {
    @Autowired
    SqlSessionFactory sqlSessionFactory;
    private static final String ENC = "UTF-8";
    private static final int rowMaxNum = 100000;

    public ExportsServiceImpl() {
    }

    public void exporstExcel(String sqlId, ExportsConfig gridInfo, IRequest iRequest, OutputStream outputStream) throws IOException {
        String sheetName = gridInfo.getFileName();
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet(sheetName);
        XSSFRow firstRow = sheet.createRow(0);
        ArrayList styles = new ArrayList();
        int i = 0;

        for(Iterator count = gridInfo.getColumnsInfo().iterator(); count.hasNext(); ++i) {
            ColumnsInfo rowIndex = (ColumnsInfo)count.next();
            XSSFCell sqlSession = firstRow.createCell(i);
            sqlSession.setCellValue(rowIndex.getTitle());
            sheet.setColumnWidth(i, rowIndex.getWidth() * 80);
            XSSFCellStyle cellStyle = wb.createCellStyle();
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            sqlSession.setCellStyle(cellStyle);
            XSSFCellStyle cellStyles = wb.createCellStyle();
            styles.add(cellStyles);
        }

        AtomicInteger var25 = new AtomicInteger(1);
        AtomicInteger var26 = new AtomicInteger(1);
        SqlSession var27 = this.sqlSessionFactory.openSession();
        Throwable var28 = null;

        try {
            var27.select(sqlId, gridInfo.getParam(), (resultContext) -> {
                Object object = resultContext.getResultObject();
                int sheetNum = var25.get() / 100000;
                XSSFSheet sheets;
                if(var25.get() % 100000 == 0) {
                    sheets = wb.createSheet(sheetName + sheetNum);
                    int row = 0;

                    for(Iterator ii = gridInfo.getColumnsInfo().iterator(); ii.hasNext(); ++row) {
                        ColumnsInfo columnInfo = (ColumnsInfo)ii.next();
                        sheets.setColumnWidth(row, columnInfo.getWidth() * 80);
                    }

                    var26.set(0);
                }

                sheets = wb.getSheetAt(sheetNum);
                var25.getAndIncrement();
                XSSFRow var20 = sheets.createRow(var26.getAndIncrement());
                int var21 = 0;
                Iterator var22 = gridInfo.getColumnsInfo().iterator();

                while(var22.hasNext()) {
                    ColumnsInfo columnInfo1 = (ColumnsInfo)var22.next();
                    Object fieldObject = null;

                    try {
                        fieldObject = PropertyUtils.getProperty(object, columnInfo1.getName());
                    } catch (Exception var19) {
                        throw new RuntimeException(var19);
                    }

                    String type = columnInfo1.getType();
                    String align = columnInfo1.getAlign();
                    XSSFCell cell = var20.createCell(var21++);
                    if("center".equals(align)) {
                        ((XSSFCellStyle)styles.get(var21 - 1)).setAlignment(HorizontalAlignment.CENTER);
                    } else if("left".equals(align)) {
                        ((XSSFCellStyle)styles.get(var21 - 1)).setAlignment(HorizontalAlignment.LEFT);
                    } else if("right".equals(align)) {
                        ((XSSFCellStyle)styles.get(var21 - 1)).setAlignment(HorizontalAlignment.RIGHT);
                    } else if("string".equals(type)) {
                        ((XSSFCellStyle)styles.get(var21 - 1)).setAlignment(HorizontalAlignment.LEFT);
                    } else if(!"number".equals(type) && !"int".equals(type)) {
                        ((XSSFCellStyle)styles.get(var21 - 1)).setAlignment(HorizontalAlignment.CENTER);
                    } else {
                        ((XSSFCellStyle)styles.get(var21 - 1)).setAlignment(HorizontalAlignment.RIGHT);
                    }

                    cell.setCellStyle((CellStyle)styles.get(var21 - 1));
                    if(!"number".equals(type) && !"int".equals(type)) {
                        if("string".equals(type)) {
                            String var23 = (String)fieldObject;
                            cell.setCellType(1);
                            cell.setCellValue(var23);
                        } else if("date".equals(type)) {
                            Date var24 = (Date)fieldObject;
                            cell.setCellType(1);
                            cell.setCellValue(var24);
                        } else {
                            cell.setCellValue((String)fieldObject);
                        }
                    } else {
                        Long field = (Long)fieldObject;
                        if(field == null) {
                            cell.setCellType(1);
                            cell.setCellValue((String)null);
                        } else {
                            cell.setCellType(0);
                            cell.setCellValue((double)field.longValue());
                        }
                    }
                }

            });
        } catch (Throwable var23) {
            var28 = var23;
            throw var23;
        } finally {
            if(var27 != null) {
                if(var28 != null) {
                    try {
                        var27.close();
                    } catch (Throwable var22) {
                        var28.addSuppressed(var22);
                    }
                } else {
                    var27.close();
                }
            }

        }

        wb.write(outputStream);
    }

    public void exportsAndDownloadExcel(String sqlId, ExportsConfig exportConfig, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, IRequest iRequest) throws IOException {
        httpServletResponse.addHeader("Content-Disposition", "attachment;filename=\"" + URLEncoder.encode(exportConfig.getFileName() + ".xlsx", "UTF-8") + "\"");
        httpServletResponse.setContentType("application/vnd.ms-excel;charset=UTF-8");
        httpServletResponse.setHeader("Accept-Ranges", "bytes");
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        Throwable var7 = null;

        try {
            this.exporstExcel(sqlId, exportConfig, iRequest, outputStream);
        } catch (Throwable var16) {
            var7 = var16;
            throw var16;
        } finally {
            if(outputStream != null) {
                if(var7 != null) {
                    try {
                        outputStream.close();
                    } catch (Throwable var15) {
                        var7.addSuppressed(var15);
                    }
                } else {
                    outputStream.close();
                }
            }

        }

    }
}

