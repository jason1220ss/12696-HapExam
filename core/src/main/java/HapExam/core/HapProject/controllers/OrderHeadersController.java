package hbi.core.HapProject.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import hbi.core.HapProject.dto.OrderHeaders;
import hbi.core.HapProject.service.IOrderHeadersService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by jason on 2016/12/15.
 */
@Controller
@RequestMapping(value = "/orders")
public class OrderHeadersController extends BaseController {

    @Autowired
    private IOrderHeadersService orderHeadersService;

    @RequestMapping(value = "/query")
    @ResponseBody
    public List<OrderHeaders> getAllOrders(HttpServletRequest request,@RequestBody OrderHeaders orderHeaders,
                                           @RequestParam(name= "", defaultValue = DEFAULT_PAGE) int page,
                                     @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pagesize) throws Exception {
        IRequest iRequest = createRequestContext(request);
        List<OrderHeaders> orderHeadersList = orderHeadersService.getAllOrders(iRequest, orderHeaders,page,pagesize);
        return orderHeadersList;
    }
}
