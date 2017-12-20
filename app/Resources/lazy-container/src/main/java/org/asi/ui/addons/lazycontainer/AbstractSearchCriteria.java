/*    */ package org.asi.ui.addons.lazycontainer;
/*    */ 
/*    */ 
/*    */ public class AbstractSearchCriteria
/*    */   implements SearchCriteria
/*    */ {
/*    */   private int lastCount;
/*    */   
/*    */   private boolean dirty;
/*    */   private String filter;
/*    */   
/*    */   public int getLastCount()
/*    */   {
/* 14 */     return this.lastCount;
/*    */   }
/*    */   
/*    */   public void setLastCount(int lastCount)
/*    */   {
/* 19 */     this.lastCount = lastCount;
/*    */   }
/*    */   
/*    */   public boolean isDirty()
/*    */   {
/* 24 */     return this.dirty;
/*    */   }
/*    */   
/*    */   public void setDirty(boolean dirty)
/*    */   {
/* 29 */     this.dirty = dirty;
/*    */   }
/*    */   
/*    */   public String getFilter()
/*    */   {
/* 34 */     return this.filter;
/*    */   }
/*    */   
/*    */   public void setFilter(String filter)
/*    */   {
/* 39 */     this.filter = filter;
/*    */   }
/*    */ }


/* Location:              C:\Users\Abishek.ram\.m2\repository\org\vaadin\addons\lazy-container\0.1\lazy-container-0.1.jar!\org\vaadin\addons\lazycontainer\AbstractSearchCriteria.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */