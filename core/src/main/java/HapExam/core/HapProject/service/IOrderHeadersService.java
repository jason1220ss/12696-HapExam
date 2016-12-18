package hbi.core.HapProject.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import hbi.core.HapProject.dto.OrderHeaders;

import java.util.List;

/**
 * Created by jason on 2016/12/15.
 */
public interface IOrderHeadersService extends IBaseService<OrderHeaders>, ProxySelf<IOrderHeadersService> {

    public List<OrderHeaders> getAllOrders(IRequest iRequest,OrderHeaders orderHeaders, int pageNum, int pageSize);
}
