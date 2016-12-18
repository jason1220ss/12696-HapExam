package hbi.core.HapProject.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import hbi.core.HapProject.dto.OrderLines;

import java.util.List;

/**
 * Created by jason on 2016/12/17.
 */
public interface IOrderLinesService extends IBaseService<OrderLines>, ProxySelf<IOrderLinesService> {
    public List<OrderLines> getAllLines(IRequest iRequest,OrderLines orderLines, int pageNum, int pageSize);
}
