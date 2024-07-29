package common;

public enum AnnoType {
	SYSTEM(1),
	FOOD_SAFETY(2);

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
			return "System Announcement";
		case FOOD_SAFETY:
			return "Food Safety News";
		default:
			return "";
		}
	}
}
