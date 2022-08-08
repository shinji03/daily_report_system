package constants;

/**
 * 画面の項目値等を定義するEnumクラス
 *
 */
public enum AttributeConst {

    //フラッシュメッセージ
    FLUSH("flush"),

    //一覧画面共通
    MAX_ROW("maxRow"),
    PAGE("page"),

    //入力フォーム共通
    TOKEN("_token"),
    ERR("errors"),

    //ログイン中の従業員
    LOGIN_EMP("login_employee"),

    //ログイン画面
    LOGIN_ERR("loginError"),

    //従業員管理
    EMPLOYEE("employee"),
    EMPLOYEES("employees"),
    EMP_COUNT("employees_count"),
    EMP_ID("id"),
    EMP_CODE("code"),
    EMP_PASS("password"),
    EMP_NAME("name"),
    EMP_ADMIN_FLG("admin_flag"),
    EMP_MANAGEMENT_FLAG ("management_flag"),

    //管理者フラグ
    ROLE_ADMIN(1),
    ROLE_GENERAL(0),

    //役職フラグ（Management）
    POSITION_GENERAL(0), //一般職員
    POSITION_SUPERIOR(1), // 上長追加
    POSITION_MANAGER(2), // 部長追加

    //削除フラグ
    DEL_FLAG_TRUE(1),
    DEL_FLAG_FALSE(0),

    //日報管理
    REPORT("report"),
    REPORTS("reports"),
    REP_COUNT("reports_count"),
    REP_ID("id"),
    REP_DATE("report_date"),
    REP_TITLE("title"),
    REP_CONTENT("content_msg"),
    REP_APPROVAL_FLAG("approval_flag"),

    //商談管理
    NEGOTINATION("negotiation"),
    NEGOTINATIONS("negotiations"),
    NEG_COUNT("negotiations_count"),
    NEG_ID("id"),
    NEG_DATE("negotiation_date"),
    NEG_TITLE("title"),
    NEG_CONTENT("content_msg"),
    NEG_APPROVAL_FLAG("approval_flag"),

    //承認フラグ
    APPROVAL_FLAF_TRUE(1), //承認済み
    APPROVAL_FLAF_FALSE(0), //未承認

    // 完了/継続の判断
    NEG_TRUE(1), //完了済み
    NEG_NO_FINISH(0); //継続

    private final String text;
    private final Integer i;

    private AttributeConst(final String text) {
        this.text = text;
        this.i = null;
    }

    private AttributeConst(final Integer i) {
        this.text = null;
        this.i = i;
    }

    public String getValue() {
        return this.text;
    }

    public Integer getIntegerValue() {
        return this.i;
    }

}