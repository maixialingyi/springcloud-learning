package com.mid.base.shejimoshi.price;

import java.math.BigDecimal;

/**
 * 计算支付金额的抽象类
 * @author admin
 *
 */
public abstract class BaseCountDecorator implements IBaseCount{
	
	private IBaseCount count;
	
	public BaseCountDecorator(IBaseCount count) {
		this.count = count;
	}

	@Override
	public BigDecimal countPayMoney(OrderDetail orderDetail) {
		
		
		BigDecimal payTotalMoney = new BigDecimal(0);
		
		if(count!=null) {
			payTotalMoney = count.countPayMoney(orderDetail);
		}
		return payTotalMoney;
	}
	

}
