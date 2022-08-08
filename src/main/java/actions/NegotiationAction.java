package actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.NegotiationView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import services.NegotiationService;

/**
 * 商談に関する処理を行うActionクラス
 *
 */

public class NegotiationAction extends ActionBase {

    private NegotiationService negotiation;

    /**
     * メソッドを実行する
     */

    @Override
    public void process() throws ServletException, IOException {

        negotiation = new NegotiationService();

        //メソッドを実行
        invoke();
        negotiation.close();
    }

    /**
     * 一覧画面を表示する
     * @throws ServletException
     * @throws IOException
     */

    public void index() throws ServletException, IOException {

        //指定されたページ数の一覧画面に表示する日報データを取得
        int page = getPage();
        List<NegotiationView> negotiations = negotiation.getAllPerPage(page);

        //全日報データの件数を取得
        long negotiationsCount = negotiation.countAll();

        putRequestScope(AttributeConst.NEGOTINATIONS, negotiations); //取得した商談データ
        putRequestScope(AttributeConst.NEG_COUNT, negotiationsCount); //全ての商談データの件数
        putRequestScope(AttributeConst.PAGE, page); //ページ数
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); //1ページに表示するレコードの数

        //セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する
        String flush = getSessionScope(AttributeConst.FLUSH);
        if (flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }

        //一覧画面を表示
        forward(ForwardConst.FW_NEG_INDEX);

    }

}
