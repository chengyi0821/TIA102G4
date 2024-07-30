package common;

public enum CSReplyHeading {
	ACCOUNT_ISSUE(1), // 帳號問題
	FOOD_SAFETY_ISSUE(2), // 食安問題
	ORDER_ISSUE(3), // 訂單問題
	SYSTEM_ISSUE(4), // 系統問題
	OTHER_ISSUES(5); // 其他

	private final Integer replyHeading;

	private CSReplyHeading(Integer replyHeading) {
		this.replyHeading = replyHeading;
	}

	public Integer getFeedbackType() {
		return replyHeading;
	}

	@Override
	public String toString() {
		switch (this) {
		case ACCOUNT_ISSUE:
			return "帳號問題";
		case FOOD_SAFETY_ISSUE:
			return "食安問題";
		case ORDER_ISSUE:
			return "訂單問題";
		case SYSTEM_ISSUE:
			return "系統問題";
		case OTHER_ISSUES:
			return "其他";
		default:
			return "";
		}
	}
}
