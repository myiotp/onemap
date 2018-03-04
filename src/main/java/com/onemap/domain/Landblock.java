package com.onemap.domain;

public class Landblock extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6345955900177596917L;
	private Integer id;
	private String blockNumber;
	private Integer blockTypeId;
	private Integer landTypeId;
	private String memo;
	private Integer cooperativeId;
	
	private Double area;
	private Integer cropTypeId;
	
	private BlockType blockType;
	private LandType landType;
	private Site cooperative;
	private CropType cropType;
	private String zipcode;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBlockNumber() {
		return blockNumber;
	}
	public void setBlockNumber(String blockNumber) {
		this.blockNumber = blockNumber;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Integer getCooperativeId() {
		return cooperativeId;
	}
	public void setCooperativeId(Integer cooperativeId) {
		this.cooperativeId = cooperativeId;
	}
	public Site getCooperative() {
		return cooperative;
	}
	public void setCooperative(Site cooperative) {
		this.cooperative = cooperative;
	}
	public Double getArea() {
		return area;
	}
	public void setArea(Double area) {
		this.area = area;
	}
	public Integer getCropTypeId() {
		return cropTypeId;
	}
	public void setCropTypeId(Integer cropTypeId) {
		this.cropTypeId = cropTypeId;
	}
	public CropType getCropType() {
		return cropType;
	}
	public void setCropType(CropType cropType) {
		this.cropType = cropType;
	}
	public Integer getBlockTypeId() {
		return blockTypeId;
	}
	public void setBlockTypeId(Integer blockTypeId) {
		this.blockTypeId = blockTypeId;
	}
	public Integer getLandTypeId() {
		return landTypeId;
	}
	public void setLandTypeId(Integer landTypeId) {
		this.landTypeId = landTypeId;
	}
	public BlockType getBlockType() {
		return blockType;
	}
	public void setBlockType(BlockType blockType) {
		this.blockType = blockType;
	}
	public LandType getLandType() {
		return landType;
	}
	public void setLandType(LandType landType) {
		this.landType = landType;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

		

}
