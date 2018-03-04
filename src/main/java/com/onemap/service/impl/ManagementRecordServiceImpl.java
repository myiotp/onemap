package com.onemap.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onemap.dao.BaseDao;
import com.onemap.dao.ManagementRecordDao;
import com.onemap.domain.ManagementRecord;
import com.onemap.service.ManagementRecordService;

@Service
public class ManagementRecordServiceImpl extends BaseServiceImpl<ManagementRecord, Integer>
		implements ManagementRecordService {

	@Autowired
	private ManagementRecordDao dao;

	@Override
	public BaseDao<ManagementRecord, Integer> getDao() {
		return dao;
	}

	@Override
	public void fillDetail(ManagementRecord obj) throws Exception {
		// Date operationTime = obj.getOperationTime();
		// SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
		// String date = sdf.format(operationTime.getTime());

		// Site siteCapacity = getCapacityById(site.getId());
		// if(siteCapacity==null){
		// site.setCommonCapacity(0);
		// site.setCoreCapacity(0);
		// site.setRelatedCapacity(0);
		// return;
		// }
		// site.setCoreCapacity(siteCapacity.getCoreCapacity()!=null?siteCapacity.getCoreCapacity():0);
		// site.setRelatedCapacity(siteCapacity.getRelatedCapacity()!=null?siteCapacity.getRelatedCapacity():0);
		// site.setCommonCapacity(siteCapacity.getCommonCapacity()!=null?siteCapacity.getCommonCapacity():0);
	}

	@Override
	public ManagementRecord getByUrl(String var8) throws Exception {
		return this.dao.getByUrl(var8);
	}

	@Override
	public List<ManagementRecord> getByUsername(String username) {
		return null;
	}

	@Override
	public void updateStatus(Integer id, Integer status) {
		this.dao.updateStatus(id, status);
	}

	@Override
	public List<ManagementRecord> getByUsernameAndVehicle(String username, Integer truckId) throws Exception {
		ManagementRecord t = new ManagementRecord();
		t.setOperator(username);
		t.setTruckId(truckId);
		return dao.list(t);
	}

	@Override
	public List<ManagementRecord> getByUsernameAndCargo(String username, Integer cargoId) throws Exception {
		ManagementRecord t = new ManagementRecord();
		t.setOperator(username);
		t.setCargoId(cargoId);
		return dao.list(t);
	}

	@Override
	public List<ManagementRecord> getConfirmedByUsernameAndVehicle(String username, Integer truckId) throws Exception {
		ManagementRecord t = new ManagementRecord();
		t.setOperator(username);
		t.setTruckId(truckId);
		t.setStatus(1);
		return dao.listByStatus(t);
	}

	@Override
	public List<ManagementRecord> getCanceledByUsernameAndVehicle(String username, Integer truckId) throws Exception {
		ManagementRecord t = new ManagementRecord();
		t.setOperator(username);
		t.setTruckId(truckId);
		t.setStatus(-1);
		return dao.listByStatus(t);
	}

	@Override
	public List<ManagementRecord> getConfirmedByUsernameAndCargo(String username, Integer cargoId) throws Exception {
		ManagementRecord t = new ManagementRecord();
		t.setOperator(username);
		t.setCargoId(cargoId);
		t.setStatus(1);
		return dao.listByStatus(t);
	}

	@Override
	public List<ManagementRecord> getCanceledByUsernameAndCargo(String username, Integer cargoId) throws Exception {
		ManagementRecord t = new ManagementRecord();
		t.setOperator(username);
		t.setCargoId(cargoId);
		t.setStatus(-1);
		return dao.listByStatus(t);
	}

	@Override
	public boolean isConfirmedByUsernameAndVehicle(String username, Integer truckId) throws Exception {
		List<ManagementRecord> b = getConfirmedByUsernameAndVehicle(username, truckId);
		return b != null && b.size() > 0;
	}

	@Override
	public boolean isCanceledByUsernameAndVehicle(String username, Integer truckId) throws Exception {
		List<ManagementRecord> b = getCanceledByUsernameAndVehicle(username, truckId);
		return b != null && b.size() > 0;
	}

	@Override
	public boolean isConfirmedByUsernameAndCargo(String username, Integer cargoId) throws Exception {
		List<ManagementRecord> b = getConfirmedByUsernameAndCargo(username, cargoId);
		return b != null && b.size() > 0;
	}

	@Override
	public boolean isCanceledByUsernameAndCargo(String username, Integer cargoId) throws Exception {
		List<ManagementRecord> b = getCanceledByUsernameAndCargo(username, cargoId);
		return b != null && b.size() > 0;
	}

	@Override
	public void updateReturnOrder(ManagementRecord t) {
		dao.updateReturnOrder(t);

	}

	@Override
	public List<ManagementRecord> listByCargoIdOrTruckId(ManagementRecord t) {
		return dao.listByCargoIdOrTruckId(t);
	}

	@Override
	public void updateReturnOrderApprover1(ManagementRecord t) {
		dao.updateReturnOrderApprover1(t);
	}

	@Override
	public void updateReturnOrderApprover2(ManagementRecord t) {
		dao.updateReturnOrderApprover2(t);
	}

	@Override
	public void updateReturnOrderApprover3(ManagementRecord t) {
		dao.updateReturnOrderApprover3(t);		
	}

	@Override
	public void updateReturnOrderApprover4(ManagementRecord t) {
		dao.updateReturnOrderApprover4(t);		
	}

	@Override
	public void updateReturnOrderApprover5(ManagementRecord t) {
		dao.updateReturnOrderApprover5(t);		
	}

	@Override
	public void updateReturnOrderApprover6(ManagementRecord t) {
		dao.updateReturnOrderApprover6(t);	
	}

	@Override
	public void updateStatusWithApprovers(ManagementRecord t) {
		dao.updateStatusWithApprovers(t);		
	}

	@Override
	public void updateApproveLevel(Integer id, Integer approvelevel) {
		dao.updateApproveLevel(id, approvelevel);
	}

}
