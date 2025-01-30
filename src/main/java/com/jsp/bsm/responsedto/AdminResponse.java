package com.jsp.bsm.responsedto;

import com.jsp.bsm.entity.Admin;
import com.jsp.bsm.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminResponse {

    private int adminId;
    private User user;
}
