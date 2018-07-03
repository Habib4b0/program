/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.supercode;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import java.util.List;

/**
 *
 * @author Jayaram.LeelaRam
 */
public interface ExcelInterface {

    List getExcelResultList(AbstractSelectionDTO selection);
}
