package com.bankrupt.bankruptapi.dto;

import com.bankrupt.bankruptapi.dao.Board;
import com.bankrupt.bankruptapi.dao.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BoardDto {
    private Long id;
    private String seller;
    private String court;
    private String title;
    private String uploaded;
    private String due;
    private String fileLink;
    private String telephoneNumber;
    private String views;
    private List<CategoryDto> categories;

    private static String fileUrl = "https://file.scourt.go.kr/AttachDownload?path=011";

    public static BoardDto of(Board board, List<Category> categories) {
        return BoardDto.builder()
                .id(board.getId())
                .seller(board.getSeller())
                .court(board.getCourt())
                .title(board.getTitle())
                .uploaded(board.getUploaded())
                .due(board.getDue())
                .telephoneNumber(board.getTelephoneNumber())
                .views(board.getViews())
                .fileLink(getFileLink(board.getFile(), board.getFileName()))
                .categories(categories.stream().map(CategoryDto::of).toList())
                .build();
    }

    private static String getFileLink(String file, String fileName) {
        return fileUrl + "&file=" + file + "&downFile=" + fileName;
    }
}
