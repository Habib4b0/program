/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.type;

/**
 *
 * @author Mohamed.Shahul
 */
public enum GtnUIFrameworkConstants {

	CONFIG("_config"), GTNFRAMEWORK_1("gtnFrameworkCol-1"), GTNFRAMEWORK_2("gtnFrameworkCol-2"), GTNFRAMEWORK_3(
			"gtnFrameworkCol-3"), GTNFRAMEWORK_4("gtnFrameworkCol-4"), GTNFRAMEWORK_5(
					"gtnFrameworkCol-5"), GTNFRAMEWORK_6("gtnFrameworkCol-6"), GTNFRAMEWORK_7(
							"gtnFrameworkCol-7"), GTNFRAMEWORK_8("gtnFrameworkCol-8"), GTNFRAMEWORK_9(
									"gtnFrameworkCol-9"), GTNFRAMEWORK_10("gtnFrameworkCol-10"), GTNFRAMEWORK_11(
											"gtnFrameworkCol-11"), GTNFRAMEWORK_12(
													"gtnFrameworkCol-12"), SINGLE_LINE_LAYOUT(
															"gtnGrid-single-ln-layout"), PADDING(
																	"stpl-padding-left-15 stpl-padding-right-10 stpl-padding-top-15 stpl-padding-bottom-15");
	private String constant;

	private GtnUIFrameworkConstants(String constant) {
		this.constant = constant;
	}

	@Override
	public String toString() {
		return constant;
	}

}
