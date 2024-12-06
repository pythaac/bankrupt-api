package com.bankrupt.bankruptapi.model;

import com.bankrupt.bankruptapi.dao.Board;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ScourtBoardDetail {
    String seller;
    String court;
    String title;
    String uploaded;
    String due;
    String file;
    String fileName;
    String telephoneNumber;

    public Board toBoard(Long seqId) {
        return Board.builder()
                .id(seqId)
                .seller(this.seller)
                .court(this.court)
                .title(this.title)
                .uploaded(this.uploaded)
                .due(this.due)
                .file(this.file)
                .fileName(this.fileName)
                .telephoneNumber(this.telephoneNumber)
                .build();
    }
}
