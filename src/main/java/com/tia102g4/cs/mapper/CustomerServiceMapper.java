package com.tia102g4.cs.mapper;

import java.text.SimpleDateFormat;
import java.util.Optional;

import com.tia102g4.admin.model.Admin;
import com.tia102g4.cs.model.CustomerService;
import com.tia102g4.cs.to.req.CSReqTO;
import com.tia102g4.member.model.Member;
import com.tia102g4.rest.model.Restaurant;

import common.CSFeedbackType;
import common.CSReplyHeading;

public class CustomerServiceMapper {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	// 後端拿會員客服的資料給前端
	public CSReqTO setCSReqTO(CustomerService customerService) {

		CSReqTO reqTO = new CSReqTO();
		reqTO.setCsId(customerService.getCsId());
		reqTO.setFeedbackType(CSFeedbackType.fromTypeId(customerService.getFeedbackType()));
		reqTO.setFeedbackContent(customerService.getFeedbackContent());
		reqTO.setFeedbackTime(dateFormat.format(customerService.getFeedbackTime()));
		reqTO.setDeletedAdmin(customerService.getDeletedAdmin());
		reqTO.setDeletedMember(customerService.getDeletedMember());
		reqTO.setDeletedRest(customerService.getDeletedRest());
		reqTO.setReplyStatus(customerService.getReplyStatus());
		
		//Lambda表達式 創建Optional容器 如果有值就回傳 沒值Optional為空容器
		reqTO.setReplyHeading(Optional.ofNullable(customerService.getReplyHeading())
								  	  .map(CSReplyHeading::fromHeadingId)
								  	  .orElse(null));
		reqTO.setReplyContent(Optional.ofNullable(customerService.getReplyContent())
									  .orElse(null));		
		reqTO.setReplyTime(Optional.ofNullable(customerService.getReplyTime())
								   .map(dateFormat::format)
								   .orElse(null));
		reqTO.setMemberId(Optional.ofNullable(customerService.getMember())
                				  .map(Member::getMemberId)
                				  .orElse(null));
		reqTO.setMemberName(Optional.ofNullable(customerService.getMember())
									.map(Member::getName)
									.orElse(null));
		reqTO.setRestName(Optional.ofNullable(customerService.getRestaurant())
		               				.map(Restaurant::getRestName)
		               				.orElse(null));
		reqTO.setAdminId(Optional.ofNullable(customerService.getAdmin())
    							   .map(Admin::getAdminId)
    							   .orElse(null));
		reqTO.setAdminName(Optional.ofNullable(customerService.getAdmin())
		                			.map(Admin::getName)
		                			.orElse(null));
		return reqTO;
	}
}
