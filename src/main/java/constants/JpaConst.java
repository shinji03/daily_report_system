package constants;

/**
 * DB関連の項目値を定義するインターフェース
 * ※インターフェイスに定義した変数は public static final 修飾子がついているとみなされる
 */
public interface JpaConst {

    //persistence-unit名
    String PERSISTENCE_UNIT_NAME = "daily_report_system";

    //データ取得件数の最大値
    int ROW_PER_PAGE = 15; //1ページに表示するレコードの数

    //従業員テーブル
    String TABLE_EMP = "employees"; //テーブル名
    //従業員テーブルカラム
    String EMP_COL_ID = "id"; //id
    String EMP_COL_CODE = "code"; //社員番号
    String EMP_COL_NAME = "name"; //氏名
    String EMP_COL_PASS = "password"; //パスワード
    String EMP_COL_ADMIN_FLAG = "admin_flag"; //管理者権限
    String EMP_COL_CREATED_AT = "created_at"; //登録日時
    String EMP_COL_UPDATED_AT = "updated_at"; //更新日時
    String EMP_COL_DELETE_FLAG = "delete_flag"; //削除フラグ

    //↓の内容を追加
    String EMP_COL_MANAGEMENT_FLAG = "management_flag"; //管理職権限
    //↑の内容を追加

    int ROLE_ADMIN = 1; //管理者権限ON(管理者)
    int ROLE_GENERAL = 0; //管理者権限OFF(一般)
    int EMP_DEL_TRUE = 1; //削除フラグON(削除済み)
    int EMP_DEL_FALSE = 0; //削除フラグOFF(現役)

    //↓の内容を追加
    int POSITION_GENERAL = 0; //一般職員
    int POSITION_SUPERIOR = 1; // 上長
    int POSITION_MANAGER = 2; // 部長
    //↑の内容を追加

    //日報テーブル
    String TABLE_REP = "reports"; //テーブル名
    //日報テーブルカラム
    String REP_COL_ID = "id"; //id
    String REP_COL_EMP = "employee_id"; //日報を作成した従業員のid
    String REP_COL_REP_DATE = "report_date"; //いつの日報かを示す日付
    String REP_COL_TITLE = "title"; //日報のタイトル
    String REP_COL_CONTENT = "content"; //日報の内容
    String REP_COL_CREATED_AT = "created_at"; //登録日時
    String REP_COL_UPDATED_AT = "updated_at"; //更新日時

    //追加分↓
    String REP_COL_APPROVAL_FLAG = "approval_flag"; //承認ステータス
    String REP_COL_COMPLETE_AT = "completed_at"; //承認日時
    String REP_COL_APPROVAL_STAFF = "approval_staff";//承認スタッフ
    String REP_COL_APPLICATION_FLAG = "application_flag";

    // 承認/未承認の判断
    int  APPROVAL_FLAF_TRUE = 1; //承認済み
    int  APPROVAL_FLAF_FALSE = 0; //未承認

    //申請/未申請の判断
    int  APPLICATION_FLAF_TRUE = 3; //確認済み
    int  APPLICATION_FLAF_RE = 2; //再提出
    int  APPLICATION_FLAF_SEE = 1;//申請中
    int  APPLICATION_FLAF_FALSE = 0;//未申請

    //追加分↑

    //Entity名
    String ENTITY_EMP = "employee"; //従業員
    String ENTITY_REP = "report"; //日報
    String ENTITY_NEG = "negotiation"; //商談

    //JPQL内パラメータ
    String JPQL_PARM_CODE = "code"; //社員番号
    String JPQL_PARM_PASSWORD = "password"; //パスワード
    String JPQL_PARM_EMPLOYEE = "employee"; //従業員
    String JPQL_PARM_APPLICATION = "applicationFlag"; //申請状況

    //NamedQueryの nameとquery
    //全ての従業員をidの降順に取得する
    String Q_EMP_GET_ALL = ENTITY_EMP + ".getAll"; //name
    String Q_EMP_GET_ALL_DEF = "SELECT e FROM Employee AS e ORDER BY e.id DESC"; //query
    //全ての従業員の件数を取得する
    String Q_EMP_COUNT = ENTITY_EMP + ".count";
    String Q_EMP_COUNT_DEF = "SELECT COUNT(e) FROM Employee AS e";
    //社員番号とハッシュ化済パスワードを条件に未削除の従業員を取得する
    String Q_EMP_GET_BY_CODE_AND_PASS = ENTITY_EMP + ".getByCodeAndPass";
    String Q_EMP_GET_BY_CODE_AND_PASS_DEF = "SELECT e FROM Employee AS e WHERE e.deleteFlag = 0 AND e.code = :" + JPQL_PARM_CODE + " AND e.password = :" + JPQL_PARM_PASSWORD;
    //指定した社員番号を保持する従業員の件数を取得する
    String Q_EMP_COUNT_REGISTERED_BY_CODE = ENTITY_EMP + ".countRegisteredByCode";
String Q_EMP_COUNT_REGISTERED_BY_CODE_DEF = "SELECT COUNT(e) FROM Employee AS e WHERE e.code = :" + JPQL_PARM_CODE;;

    //全ての日報をidの降順に取得する
    String Q_REP_GET_ALL = ENTITY_REP + ".getAll";
    String Q_REP_GET_ALL_DEF = "SELECT r FROM Report AS r ORDER BY r.id DESC";
    //全ての日報の件数を取得する
    String Q_REP_COUNT = ENTITY_REP + ".count";
    String Q_REP_COUNT_DEF = "SELECT COUNT(r) FROM Report AS r";
    //指定した従業員が作成した日報を全件idの降順で取得する
    String Q_REP_GET_ALL_MINE = ENTITY_REP + ".getAllMine";
    String Q_REP_GET_ALL_MINE_DEF = "SELECT r FROM Report AS r WHERE r.employee = :" + JPQL_PARM_EMPLOYEE + " ORDER BY r.id DESC";
    //指定した従業員が作成した日報の件数を取得する
    String Q_REP_COUNT_ALL_MINE = ENTITY_REP + ".countAllMine";
    String Q_REP_COUNT_ALL_MINE_DEF = "SELECT COUNT(r) FROM Report AS r WHERE r.employee = :" + JPQL_PARM_EMPLOYEE;


    //申請中の日報を全件idの降順で取得する
    String Q_REP_GET_APPLICATION_ALL_MINE = ENTITY_REP + ".getAllApplicationPerPage";
    String Q_REP_GET_APPLICATION_ALL_MINE_DEF = "SELECT r FROM Report AS r WHERE r.applicationFlag = :" + JPQL_PARM_APPLICATION + " ORDER BY r.id DESC";

    //申請中の日報の件数を取得する
    String Q_REP_COUNT_APPLICATION_ALL_MINE = ENTITY_REP + ".countAllApplication";
    String Q_REP_COUNT_APPLICATION_ALL_MINE_DEF = "SELECT COUNT(r) FROM Report AS r WHERE r.applicationFlag = :" + JPQL_PARM_APPLICATION;



    //↓の内容を追加↓

    //商談テーブル
    String TABLE_NEG = "negotiations"; //テーブル名
    //商談テーブルカラム
    String NEG_COL_ID = "id"; //id
    String NEG_COL_EMP = "employee_id"; //商談を作成した従業員のid
    String NEG_COL_TITLE = "title"; //商談のタイトル
    String NEG_COL_PAETNER = "partner";//商談の相手
    String NEG_COL_CONTENT = "content"; //商談の内容
    String NEG_COL_APPROVAL_FLAG = "approval_flag"; //完了ステータス
    String NEG_COL_CREATED_AT = "created_at"; //登録日時
    String NEG_COL_UPDATED_AT = "updated_at"; //更新日時

    //全ての商談をidの降順に取得する
    String Q_NEG_GET_ALL = ENTITY_NEG + ".getAll"; //name
    String Q_NEG_GET_ALL_DEF = "SELECT n FROM Negotiation AS n ORDER BY n.id DESC"; //query
    //全ての商談の件数を取得する
    String Q_NEG_COUNT = ENTITY_NEG + ".count";
    String Q_NEG_COUNT_DEF = "SELECT COUNT(n) FROM Report AS n";
    //指定した従業員が作成した商談を全件idの降順で取得する
    String Q_NEG_GET_ALL_MINE = ENTITY_NEG + ".getAllMine";
    String Q_NEG_GET_ALL_MINE_DEF = "SELECT n FROM Negotiation AS n WHERE n.employee = :" + JPQL_PARM_EMPLOYEE + " ORDER BY n.id DESC";
    //指定した従業員が作成した商談の件数を取得する
    String Q_NEG_COUNT_ALL_MINE = ENTITY_NEG + ".countAllMine";
    String Q_NEG_COUNT_ALL_MINE_DEF = "SELECT COUNT(n) FROM Negotiation AS n WHERE n.employee = :" + JPQL_PARM_EMPLOYEE;

    // 完了/継続の判断
    int  NEG_TRUE = 1; //完了済み
    int  NEG_NO_FINISH = 0; //継続


}
