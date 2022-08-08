package models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 交渉データのDTOモデル
 *
 */

@Table(name = JpaConst.TABLE_NEG)
@NamedQueries({
        @NamedQuery(name = JpaConst.Q_NEG_GET_ALL, query = JpaConst.Q_NEG_GET_ALL_DEF),
        @NamedQuery(name = JpaConst.Q_NEG_COUNT, query = JpaConst.Q_NEG_COUNT_DEF),
        @NamedQuery(name = JpaConst.Q_NEG_GET_ALL_MINE, query = JpaConst.Q_NEG_GET_ALL_MINE_DEF),
        @NamedQuery(name = JpaConst.Q_NEG_COUNT_ALL_MINE, query = JpaConst.Q_NEG_COUNT_ALL_MINE_DEF)
})

@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
@Entity

public class Negotiation {

    /**
     * id
     */
    @Id
    @Column(name = JpaConst.NEG_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 商談を登録した従業員
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.NEG_COL_EMP, nullable = false)
    private Employee employee;

    /**
     * 商談のタイトル
     */
    @Column(name = JpaConst.NEG_COL_TITLE, length = 255, nullable = false)
    private String title;

    /**
     * 商談の相手
     */
    @Column(name = JpaConst.NEG_COL_PAETNER, length = 255, nullable = false)
    private String partner;

    /**
     * 商談の内容
     */
    @Lob
    @Column(name = JpaConst.NEG_COL_CONTENT, nullable = false)
    private String content;

    /**
     * 承認フラグ
     */
    @Column(name = JpaConst.NEG_COL_APPROVAL_FLAG, nullable = false)
    private Integer approvalFlag;

    /**
     * 登録日時
     */
    @Column(name = JpaConst.NEG_COL_CREATED_AT, nullable = false)
    private LocalDateTime createdAt;

    /**
     * 更新日時
     */
    @Column(name = JpaConst.NEG_COL_UPDATED_AT, nullable = false)
    private LocalDateTime updatedAt;

}
