package services;

import java.time.LocalDateTime;
import java.util.List;

import actions.views.EmployeeConverter;
import actions.views.EmployeeView;
import actions.views.ReportConverter;
import actions.views.ReportView;
import constants.JpaConst;
import models.Report;
import models.validators.ReportValidator;

/**
 * 日報テーブルの操作に関わる処理を行うクラス
 */

public class ReportService extends ServiceBase {

    /**
     * 指定した従業員が作成した日報データを、指定されたページ数の一覧画面に表示する分取得しReportViewのリストで返却する
     * @param employee 従業員
     * @param page ページ数
     * @return 一覧画面に表示するデータのリスト
     */

    public List<ReportView> getMinePerPage(EmployeeView employee, int page) {

        List<Report> reports = em.createNamedQuery(JpaConst.Q_REP_GET_ALL_MINE, Report.class)
                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, EmployeeConverter.toModel(employee))
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return ReportConverter.toViewList(reports);
    }

    /**
     * 指定した従業員が作成した日報データの件数を取得し、返却する
     * @param employee
     * @return 日報データの件数
     */

    public long countAllMine(EmployeeView employee) {

        long count = (long) em.createNamedQuery(JpaConst.Q_REP_COUNT_ALL_MINE, Long.class)
                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, EmployeeConverter.toModel(employee))
                .getSingleResult();

        return count;
    }

    /**
     * 承認待ちの日報データを、指定されたページ数の一覧画面に表示する分取得しReportViewのリストで返却する
     * @param application 申請フラグ
     * @param page ページ数
     * @return 一覧画面に表示するデータのリスト
     */

    public List<ReportView> getAllApplicationPerPage(Integer applicationFlag, int page) {

        List<Report> reports = em.createNamedQuery(JpaConst.Q_REP_GET_APPLICATION_ALL_MINE, Report.class)
                .setParameter(JpaConst.JPQL_PARM_APPLICATION, applicationFlag)
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return ReportConverter.toViewList(reports);
    }


    /**
     * 承認待ちの日報データの件数を取得し、返却する
     * @param employee
     * @return 日報データの件数
     */

    public long countAllApplication(Integer applicationFlag) {

        long count = (long) em.createNamedQuery(JpaConst.Q_REP_COUNT_APPLICATION_ALL_MINE, Long.class)
                .setParameter(JpaConst.JPQL_PARM_APPLICATION, applicationFlag)
                .getSingleResult();

        return count;
    }


    /**
     * 指定されたページ数の一覧画面に表示する日報データを取得し、ReportViewのリストで返却する
     * @param page ページ数
     * @return 一覧画面に表示するデータのリスト
     */

    public List<ReportView> getAllPerPage(int page) {

        List<Report> reports = em.createNamedQuery(JpaConst.Q_REP_GET_ALL, Report.class)
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return ReportConverter.toViewList(reports);
    }

    /**
     * 日報テーブルのデータの件数を取得し、返却する
     * @return データの件数
     */

    public long countAll() {

        long reports_count = (long) em.createNamedQuery(JpaConst.Q_REP_COUNT, Long.class)
                .getSingleResult();
        return reports_count;
    }

    /**
     * idを条件に取得したデータをReportViewのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */

    public ReportView findOne(int id) {

        return ReportConverter.toView(findOneInternal(id));

    }

    /**
     * 画面から入力された日報の登録内容を元にデータを1件作成し、日報テーブルに登録する
     * @param rv 日報の登録内容
     * @return バリデーションで発生したエラーのリスト
     */

    public List<String> create(ReportView rv) {
        List<String> errors = ReportValidator.validate(rv);
        if (errors.size() == 0) {

            LocalDateTime ldt = LocalDateTime.now();
            rv.setCreatedAt(ldt);
            rv.setUpdatedAt(ldt);
            rv.setApplicationFlag(JpaConst.APPLICATION_FLAF_FALSE);
            createInternal(rv);

        }

        //バリデーションで発生したエラーを返却　（エラーがなければ0件の空リスト）
        return errors;
    }

    /**
     * 画面から入力された日報の登録内容を元に、日報データを更新する
     * @param rv 日報の更新内容
     * @return バリデーションで発生したエラーのリスト
     */

    public List<String> update(ReportView rv) {

        //バリデーションを行う
        List<String> errors = ReportValidator.validate(rv);

        if (errors.size() == 0) {

            //更新日時を現在時刻に設定
            LocalDateTime ldt = LocalDateTime.now();
            rv.setUpdatedAt(ldt);

            updateInternal(rv);
        }

        //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }

    /**
     * idを条件にデータを1件取得する
     * @param id
     * @return 取得データのインスタンス
     */

    private Report findOneInternal(int id) {

        return em.find(Report.class, id);

    }

    /**
     * 日報データを1件登録する
     * @param rv 日報データ
     */

    private void createInternal(ReportView rv) {

        em.getTransaction().begin();
        em.persist(ReportConverter.toModel(rv));
        em.getTransaction().commit();

    }

    /**
     * 日報データを更新する
     * @param rv 日報データ
     */

    private void updateInternal(ReportView rv) {

        em.getTransaction().begin();
        Report r = findOneInternal(rv.getId());
        ReportConverter.copyViewToModel(r, rv);
        em.getTransaction().commit();
    }

    /**
     * 日報データを承認する
     * @param rv 日報データ
     */

    public void approvalInternal(Integer id, String approvalstaff) {

        //idを条件に登録済みの日報情報を取得する
        ReportView savedRep = findOne(id);
        savedRep.setApprovalStaff(approvalstaff);
        //承認フラグと申請フラグの更新
        savedRep.setApprovalFlag(JpaConst.APPROVAL_FLAF_TRUE);
        savedRep.setApplicationFlag(JpaConst.APPLICATION_FLAF_TRUE);
        //承認日時を現在時刻に設定
        LocalDateTime ldt = LocalDateTime.now();
        savedRep.setCompletedAt(ldt);

        //更新処理を行う
        update(savedRep);

    }

    /**
     * 日報データを取り下げる（管理者側のアクション）
     * @param rv 日報データ
     */

    public void approvalbackInternal(Integer id, String approvalstaff) {

        //idを条件に登録済みの日報情報を取得する
        ReportView savedRep = findOne(id);
        savedRep.setApprovalStaff(approvalstaff);
        //申請フラグの更新
        savedRep.setApplicationFlag(JpaConst.APPLICATION_FLAF_RE);
        //承認日時を現在時刻に設定
        LocalDateTime ldt = LocalDateTime.now();
        savedRep.setCompletedAt(ldt);

        //更新処理を行う
        update(savedRep);

    }

    /**
     * 日報データの確定を解除する（管理者側のアクション）
     * @param rv 日報データ
     */

    public void approvalcancelInternal(Integer id, String approvalstaff) {

        //idを条件に登録済みの日報情報を取得する
        ReportView savedRep = findOne(id);
        savedRep.setApprovalStaff(approvalstaff);
        //承認フラグを取り合下げる
        savedRep.setApprovalFlag(JpaConst.APPROVAL_FLAF_FALSE);
        //申請フラグを取り下げる
        savedRep.setApplicationFlag(JpaConst.APPLICATION_FLAF_FALSE);
        //更新処理を行う
        update(savedRep);

    }


    /**
     * 日報データを申請する（制作者側のアクション）
     * @param rv 日報データ
     */
    public void applicationInternal(Integer id) {

        //idを条件に登録済みの日報情報を取得する
        ReportView savedRep = findOne(id);
        //申請フラグをたてる
        savedRep.setApplicationFlag(JpaConst.APPLICATION_FLAF_SEE);
        //更新処理を行う
        update(savedRep);

    }

    /**
     * 日報データの申請を取り下げる（制作者側のアクション）
     * @param rv 日報データ
     */
    public void applicationcanselInternal(Integer id) {

        //idを条件に登録済みの日報情報を取得する
        ReportView savedRep = findOne(id);
        //申請フラグを取り下げる
        savedRep.setApplicationFlag(JpaConst.APPLICATION_FLAF_FALSE);
        //更新処理を行う
        update(savedRep);

    }

}
