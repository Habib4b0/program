package com.stpl.ifs.ui.errorhandling;
/**
 * Binder Error Handling
 * @author shrihariharan
 */
public interface ErrorDisplay {
        /**
         * Displays the error message
         * @param error 
         */
	void setError(String error);
        
        /**
         * Removes the Error message
         */
        void clearError();
}
