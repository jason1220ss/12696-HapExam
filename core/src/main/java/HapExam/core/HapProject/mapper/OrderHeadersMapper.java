package hbi.core.HapProject.mapper;

import com.hand.hap.core.IRequest;
import com.hand.hap.mybatis.common.Mapper;
import hbi.core.HapProject.dto.OrderHeaders;

import java.util.List;

/**
 * Created by jason on 2016/12/15.
 */
public interface OrderHeadersMapper extends Mapper<OrderHeaders> {

    List<OrderHeaders> getAllOrders(OrderHeaders orderHeaders);
}
