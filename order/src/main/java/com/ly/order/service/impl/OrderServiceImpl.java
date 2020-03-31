package com.ly.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ly.commom.constant.ShopConstant;
import com.ly.commom.entity.OrderEntity;
import com.ly.commom.entity.ProductEntity;
import com.ly.commom.entity.UserEntity;
import com.ly.commom.exception.RException;
import com.ly.commom.utils.CommUtils;
import com.ly.order.client.ProductClient;
import com.ly.order.client.UserClient;
import com.ly.order.mapper.OrderMapper;
import com.ly.order.service.OrderService;
import com.ly.order.vo.OrderAddVo;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderEntity> implements OrderService {

    @Autowired
    UserClient userClient;

    @Autowired
    ProductClient productClient;

    @Autowired
    OrderMapper orderMapper;

    @GlobalTransactional
    @Override
    public void createOrder(OrderAddVo orderAddVo) {
        log.info("the orderAddVo is" + orderAddVo);
        UserEntity userEntity = vailUserInfo(orderAddVo);
        ProductEntity productEntity = vailProductInfo(orderAddVo);
        saveOrder(orderAddVo, userEntity);
        updateUserInfo(orderAddVo, userEntity);
        boolean updateProductRowNum = subProductStockNum(orderAddVo, productEntity);
        if (!updateProductRowNum) {
            throw new RException("更新商品信息失败");
        }
    }

    /**
     * 扣除库存
     *
     * @param orderAddVo
     * @param productEntity
     * @return
     */
    @Transactional
    boolean subProductStockNum(OrderAddVo orderAddVo, ProductEntity productEntity) {
        productEntity.setStock_num(productEntity.getStock_num() - orderAddVo.getProduct_num());
        boolean updateProductRowNum = productClient.updateProduct(productEntity);
        log.info("更新商品信息结果是：" + updateProductRowNum);
        return updateProductRowNum;
    }

    /**
     * 更新用户信息  金额和历史下单数
     *
     * @param orderAddVo
     * @param userEntity
     */
    @Transactional
    void updateUserInfo(OrderAddVo orderAddVo, UserEntity userEntity) {
        //        4. 扣除用户金额数，更新历史下单数
        userEntity.setBalance(userEntity.getBalance().subtract(orderAddVo.getPrice()));
        userEntity.setHis_order_quantity(userEntity.getHis_order_quantity() + 1);
        log.info("更新用户信息是：" + userEntity);
        boolean updateUserInfoResult = userClient.update(userEntity);
        log.info("更新用户信息结果是：" + updateUserInfoResult);
        if (!updateUserInfoResult) {
            throw new RException("更新用户信息失败");
        }
    }

    /**
     * 下单
     *
     * @param orderAddVo
     * @param userEntity
     */
    @Transactional
    void saveOrder(OrderAddVo orderAddVo, UserEntity userEntity) {
        //        3. 下单
        OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(orderAddVo, orderEntity);
        LocalDateTime localDateTime = LocalDateTime.now();
        orderEntity.setCreated_time(localDateTime);
        orderEntity.setUpdate_time(localDateTime);
        orderEntity.setCreator_id(userEntity.getId());
        orderEntity.setLast_operator_id(userEntity.getId());
        orderEntity.setCreator(userEntity.getUser_name());
        orderEntity.setLast_operator(userEntity.getUser_name());
        orderEntity.setIs_deleted(ShopConstant.isDelete.NORMAL);
        orderEntity.setOrder_sn(CommUtils.getUUID());
        orderEntity.setStore_id(orderAddVo.getStore_id());
        orderEntity.setStore_code(orderAddVo.getStore_code());
        orderEntity.setStatus(ShopConstant.OrderStatus.NOT_PAYED);
        log.info("the orderEntity is" + orderEntity);
        int insertResultRowNum = orderMapper.insert(orderEntity);
        if (insertResultRowNum != 1) {
            throw new RException("下单失败");
        }
    }

    /**
     * 验证订单信息
     *
     * @param orderAddVo
     * @return
     */
    @Transactional
    ProductEntity vailProductInfo(OrderAddVo orderAddVo) {
        //        2. 拿到商品信息，校验是否存在以及是否库存足够
        ProductEntity productEntity = productClient.findOne(orderAddVo.getProduct_id());
        log.info("the productInfo is" + productEntity);
        if (productEntity == null) {
            throw new RException("该商品不存在");
        }
        if (productEntity.getStock_num() < orderAddVo.getProduct_num()) {
            throw new RException("该商品库存不足");
        }
        if (productEntity.getPrice().multiply(BigDecimal.valueOf(orderAddVo.getProduct_num()))
                .compareTo(orderAddVo.getPrice()) != 0) {
            throw new RException("价格错误");
        }
        return productEntity;
    }

    /**
     * 验证用户信息
     *
     * @param orderAddVo
     * @return
     */
    private UserEntity vailUserInfo(OrderAddVo orderAddVo) {
        //        1. 拿到用户信息，校验是否存在以及是否金额足够
        UserEntity userEntity = userClient.user(orderAddVo.getUser_id());
        log.info("the userInfo is" + userEntity);
        if (userEntity == null) {
            throw new RException("该用户不存在");
        }
//        -1：小于；   0 ：等于；   1 ：大于；
        if (userEntity.getBalance().compareTo(orderAddVo.getPrice()) < 0) {
            throw new RException("您的余额不足");
        }
        return userEntity;
    }


}
