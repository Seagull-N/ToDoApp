package jp.kobespiral.fukushima.todo.dto;

import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

import jp.kobespiral.fukushima.todo.entity.ToDo;

@Data
public class ToDoForm {
    @NotBlank
    @Size(min = 1, max = 64)
    String title;     //題目

    public ToDo toEntity(String mid) {
        Date createdAt = new Date();
        ToDo t = new ToDo(null, title, mid, false, createdAt, null);
        return t;
    }
}