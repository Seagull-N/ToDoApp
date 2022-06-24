package jp.kobespiral.fukushima.todo.dto;
import jp.kobespiral.fukushima.todo.entity.Member;
import lombok.Data;

@Data
public class MemberForm {
    String mid; //メンバーID．
    String name; //名前

    public Member toEntity() {
        Member m = new Member(mid, name);
        return m;
    }
}