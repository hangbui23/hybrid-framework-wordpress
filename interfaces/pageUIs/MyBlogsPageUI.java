package pageUIs;

public class MyBlogsPageUI {
	public static final String BLOG_HEADER = "//h2";
	public static final String INFO_POSITION = "//thead//th[text()='%s']/preceding-sibling::th";
	public static final String INFO_DETAIL = "//tbody//tr[not(contains(@style,'display: none;'))][%s]//td[%s]";
}
