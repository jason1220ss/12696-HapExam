package hbi.core.HapProject.mapper;

import com.hand.hap.mybatis.common.Mapper;
import hbi.core.HapProject.dto.OrderLines;

import java.util.List;

/**
 * Created by jason on 2016/12/17.
 */
public interface OrderLinesMapper extends Mapper<OrderLines> {
    List<OrderLines> getAllLines(OrderLines orderLines);
}
