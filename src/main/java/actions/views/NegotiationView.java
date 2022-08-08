package actions.views;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 商談情報について画面の入力値・出力値を扱うViewモデル
 *
 */
@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)

public class NegotiationView {

    /**
     * id
     */
    private Integer id;

    /**
     * 商談を登録した従業員
     */
    private EmployeeView employee;

    /**
     * 商談のタイトル
     */
    private String title;

    /**
     * 商談の相手
     */
    private String partner;

    /**
     * 日報の内容
     */
    private String content;

    /**
     * 承認フラグ
     */
    private Integer approvalFlag;

    /**
     * 登録日時
     */
    private LocalDateTime createdAt;

    /**
     * 更新日時
     */
    private LocalDateTime updatedAt;

}
