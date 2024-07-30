package common;

public enum AnnoType {
	SYSTEM(1),		//系統公告
	FOOD_SAFETY(2); //食安新聞

	private final Integer typeId;

	AnnoType(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getTypeId() {
		return typeId;
	}
	
	@Override
	public String toString() {
		switch (this) {
		case SYSTEM:
			return "系統公告";
		case FOOD_SAFETY:
			return "食安新聞";
		default:
			return "";
		}
	}
}
