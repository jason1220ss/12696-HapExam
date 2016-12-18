package hbi.core.HapProject.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import hbi.core.HapProject.dto.OrderHeaders;
import hbi.core.HapProject.mapper.OrderHeadersMapper;
import hbi.core.HapProject.service.IOrderHeadersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jason on 2016/12/15.
 */
@Service
@Transactional
public class OrderHeadersServiceImpl extends BaseServiceImpl<OrderHeaders> implements IOrderHeadersService {

    @Autowired
    private OrderHeadersMapper ordersMapper;

    @Override
    public List<OrderHeaders> getAllOrders(IRequest iRequest,OrderHeaders orderHeaders, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return ordersMapper.getAllOrders(orderHeaders);
    }
}
