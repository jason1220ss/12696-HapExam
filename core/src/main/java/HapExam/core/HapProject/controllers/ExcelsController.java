package hbi.core.HapProject.controllers;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hand.hap.core.IRequest;
import com.hand.hap.function.dto.Function;
import com.hand.hap.system.controllers.BaseController;
import hbi.core.HapProject.dto.ColumnsInfo;
import hbi.core.HapProject.dto.ExportsConfig;
import hbi.core.HapProject.service.IExportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jason on 2016/12/18.
 */
@Controller
public class ExcelsController extends BaseController {
    @Autowired
    IExportsService excelService;
    @Autowired
    ObjectMapper objectMapper;

    public ExcelsController() {
    }

    @RequestMapping({"/function/export"})
    public void createXLS(HttpServletRequest request, @RequestParam String config, HttpServletResponse httpServletResponse) {
        IRequest requestContext = this.createRequestContext(request);
        try {
            JavaType e = this.objectMapper.getTypeFactory().constructParametrizedType(ExportsConfig.class, ExportsConfig.class, new Class[]{Function.class, ColumnsInfo.class});
            ExportsConfig exportConfig = (ExportsConfig)this.objectMapper.readValue(config, e);
            this.excelService.exportsAndDownloadExcel("com.hand.hap.function.mapper.FunctionMapper.selectFunctions", exportConfig, request, httpServletResponse, requestContext);
        } catch (IOException var7) {
            var7.printStackTrace();
        }

    }
}
