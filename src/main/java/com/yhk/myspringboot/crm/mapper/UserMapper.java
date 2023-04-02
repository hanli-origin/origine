package com.yhk.myspringboot.crm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yhk.myspringboot.crm.entity.User;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author yhk
 * @since 2023-03-05
 */
public interface UserMapper extends BaseMapper<User> {


    @MapKey("id")
    List<Map<String, Object>> queryAllSales();
}
