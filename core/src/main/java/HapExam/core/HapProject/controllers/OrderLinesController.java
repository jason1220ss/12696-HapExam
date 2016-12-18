package hbi.core.HapProject.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import hbi.core.HapProject.dto.OrderLines;
import hbi.core.HapProject.service.IOrderLinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by jason on 2016/12/17.
 */
@Controller
@RequestMapping(value = "/lines")
public class OrderLinesController extends BaseController {

    @Autowired
    private IOrderLinesService orderLinesService;

    @RequestMapping(value = "/query")
    @ResponseBody
    public List<OrderLines> getAllLines(HttpServletRequest request,@RequestBody OrderLines orderLines,
                                        @RequestParam(name= "", defaultValue = DEFAULT_PAGE) int page,
                                        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pagesize) throws Exception {
        IRequest iRequest = createRequestContext(request);
        List<OrderLines> orderLinesList = orderLinesService.getAllLines(iRequest, orderLines, page, pagesize);
        return orderLinesList;
    }
}
