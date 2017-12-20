 package org.asi.ui.addons.lazycontainer;
 
 public class OrderByColumn
 {
   private String name;
   private Type type;
   
   public static enum Type {
     ASC,  DESC;
     
     private Type() {}
   }
   
   public OrderByColumn(String name, Type type)
   {
     this.name = name;
     this.type = type;
   }
   
   public String getName() {
     return this.name;
   }
   
   public void setName(String name) {
     this.name = name;
   }
   
   public Type getType() {
     return this.type;
   }
   
   public void setType(Type type) {
     this.type = type;
   }
 }


