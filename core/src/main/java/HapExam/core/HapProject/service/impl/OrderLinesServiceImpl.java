package hbi.core.HapProject.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import hbi.core.HapProject.dto.OrderLines;
import hbi.core.HapProject.mapper.OrderLinesMapper;
import hbi.core.HapProject.service.IOrderLinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jason on 2016/12/17.
 */
@Service
@Transactional
public class OrderLinesServiceImpl extends BaseServiceImpl<OrderLines> implements IOrderLinesService {

    @Autowired
    private OrderLinesMapper orderLinesMapper;

    @Override
    public List<OrderLines> getAllLines(IRequest iRequest, OrderLines orderLines, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return orderLinesMapper.getAllLines(orderLines);
    }
}
