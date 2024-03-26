package com.secu.team5.comment.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentInfoVO {
	//COMMENT_INFO columns
    private int ciNum;
    private int uiNum;
    private int odiNum;
    private String ciContent;
    private String ciCredat;
    private String credat;
    private String cretim;
    private String lmodat;
    private String lmotim;
    private int ciScore;
    
    //USER_INFO columns(INNER JOIN 해서 사용)
    private String uiName;
    
    //ORDER_DETAIL_INFO columns(INNER JOIN 해서 사용)
    private int piNum;
    
    //PageHelper
    private int start;
	private int end;
	private int pageSize;
	private int page;
}
