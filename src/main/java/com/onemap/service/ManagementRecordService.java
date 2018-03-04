package com.onemap.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.onemap.domain.ManagementRecord;

public interface ManagementRecordService extends
		BaseService<ManagementRecord, Integer> {

	ManagementRecord getByUrl(@Param("var8") String var8) throws Exception;

	List<ManagementRecord> getByUsername(String username);
	void updateStatus(@Param("id") Integer id, @Param("status") Integer status);
	void updateStatusWithApprovers(ManagementRecord t);
	void updateApproveLevel(@Param("id") Integer id, @Param("approvelevel") Integer approvelevel);
	
	//List<ManagementRecord> getByUsernameAndVehicleAndCargo(String username, Integer truckId, Integer cargoId) throws Exception;
	List<ManagementRecord> getByUsernameAndVehicle(String username, Integer truckId) throws Exception;
	List<ManagementRecord> getByUsernameAndCargo(String username, Integer cargoId) throws Exception;
	
	List<ManagementRecord> getConfirmedByUsernameAndVehicle(String username, Integer truckId) throws Exception;
	List<ManagementRecord> getCanceledByUsernameAndVehicle(String username, Integer truckId) throws Exception;
	List<ManagementRecord> getConfirmedByUsernameAndCargo(String username, Integer cargoId) throws Exception;
	List<ManagementRecord> getCanceledByUsernameAndCargo(String username, Integer cargoId) throws Exception;
	
	boolean isConfirmedByUsernameAndVehicle(String username, Integer truckId) throws Exception;
	boolean isCanceledByUsernameAndVehicle(String username, Integer truckId) throws Exception;
	boolean isConfirmedByUsernameAndCargo(String username, Integer cargoId) throws Exception;
	boolean isCanceledByUsernameAndCargo(String username, Integer cargoId) throws Exception;
	
	void updateReturnOrder(ManagementRecord t);

	List<ManagementRecord> listByCargoIdOrTruckId(ManagementRecord t);
	
	void updateReturnOrderApprover1(ManagementRecord t);
	void updateReturnOrderApprover2(ManagementRecord t);
	void updateReturnOrderApprover3(ManagementRecord t);
	void updateReturnOrderApprover4(ManagementRecord t);
	void updateReturnOrderApprover5(ManagementRecord t);
	void updateReturnOrderApprover6(ManagementRecord t);
}
