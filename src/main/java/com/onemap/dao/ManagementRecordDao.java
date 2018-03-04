package com.onemap.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.onemap.domain.ManagementRecord;

public interface ManagementRecordDao extends BaseDao<ManagementRecord, Integer> {

	ManagementRecord getByUrl(@Param("var8") String var8);

	// Site getCapacityById(@Param("id")Integer id);
	void updateStatus(@Param("id") Integer id, @Param("status") Integer status);
	List<ManagementRecord> listByStatus(ManagementRecord t);
	void updateReturnOrder(ManagementRecord t);

	List<ManagementRecord> listByCargoIdOrTruckId(ManagementRecord t);

	void updateReturnOrderApprover1(ManagementRecord t);
	void updateReturnOrderApprover2(ManagementRecord t);
	void updateReturnOrderApprover3(ManagementRecord t);
	void updateReturnOrderApprover4(ManagementRecord t);
	void updateReturnOrderApprover5(ManagementRecord t);
	void updateReturnOrderApprover6(ManagementRecord t);

	void updateStatusWithApprovers(ManagementRecord t);

	void updateApproveLevel(@Param("id") Integer id, @Param("approvelevel") Integer approvelevel);
}
