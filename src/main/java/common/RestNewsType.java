package common;

public enum RestNewsType {
	ANNOUNCEMENT(1), 	// 公告
	ADVERTISEMENT(2); 	// 廣告

	private final Integer typeId;

	private RestNewsType(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public static RestNewsType fromTypeById(Integer typeId) {
		if (typeId == null) {
			throw new IllegalArgumentException("Type ID cannot be null");
		}
		for (RestNewsType type : RestNewsType.values()) {
			if (type.getTypeId().equals(typeId)) {
				return type;
			}
		}
		throw new IllegalArgumentException("No RestNewsType found for Type ID: " + typeId);
	}

	@Override
	public String toString() {
		switch (this) {
		case ANNOUNCEMENT:
			return "公告";
		case ADVERTISEMENT:
			return "廣告";
		default:
			return "";
		}
	}
}
