package actions.views;

import java.util.ArrayList;
import java.util.List;

import constants.AttributeConst;
import constants.JpaConst;
import models.Negotiation;

/**
 * 商談データのDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */

public class NegotiationConverter {

    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param nv NegotiationViewのインスタンス
     * @return Negotiationのインスタンス
     */

    public static Negotiation toModel(NegotiationView nv) {
        return new Negotiation(
                nv.getId(),
                EmployeeConverter.toModel(nv.getEmployee()),
                nv.getTitle(),
                nv.getPartner(),
                nv.getContent(),
                nv.getApprovalFlag() == null
                        ? null
                        : nv.getApprovalFlag() == AttributeConst.NEG_TRUE.getIntegerValue()
                                ? JpaConst.NEG_TRUE
                                : JpaConst.NEG_NO_FINISH,
                nv.getCreatedAt(),
                nv.getUpdatedAt());
    }

    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param n Negotiationのインスタンス
     * @return NegotiationViewのインスタンス
     */

    public static NegotiationView toView(Negotiation n) {

        if (n == null) {
            return null;
        }

        return new NegotiationView(
                n.getId(),
                EmployeeConverter.toView(n.getEmployee()),
                n.getTitle(),
                n.getPartner(),
                n.getContent(),
                n.getApprovalFlag() == null
                        ? null
                        : n.getApprovalFlag() == JpaConst.NEG_TRUE
                                ? AttributeConst.NEG_TRUE.getIntegerValue()
                                : AttributeConst.NEG_NO_FINISH.getIntegerValue(),
                n.getCreatedAt(),
                n.getUpdatedAt());
    }

    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param list DTOモデルのリスト
     * @return Viewモデルのリスト
     */

    public static List<NegotiationView> toViewList(List<Negotiation> list) {

        List<NegotiationView> evs = new ArrayList<>();

        for (Negotiation n : list) {
            evs.add(toView(n));
        }
        return evs;

    }

    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param n DTOモデル(コピー先)
     * @param nv Viewモデル(コピー元)
     */

    public static void copyViewToModel(Negotiation n, NegotiationView nv) {

        n.setId(nv.getId());
        n.setEmployee(EmployeeConverter.toModel(nv.getEmployee()));
        n.setTitle(nv.getTitle());
        n.setPartner(nv.getPartner());
        n.setContent(nv.getContent());
        n.setApprovalFlag(nv.getApprovalFlag());
        n.setCreatedAt(nv.getCreatedAt());
        n.setUpdatedAt(nv.getUpdatedAt());
    }

}
