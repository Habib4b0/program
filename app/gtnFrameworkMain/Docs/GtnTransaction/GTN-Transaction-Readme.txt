To Add a New Module in GTN-Transaction with Search & View Functionality

Steps:

1. Generate hbm file & Model (Pojo) class for the new interface 
2. Make sure all required columns present in both hbm.xml & Model class
3. If required, change type of the column in both hbm & Model class

4. Create a new portlet in portlet.xml with portlet name of Model class 
   and Map the following UI classes as per requirement
   
   View with One Tab : com.stpl.gtn.gtn2o.ui.GtnFrameworkTransactionPortlet
   View with Multiple Tab : com.stpl.gtn.gtn2o.ui.GtnFrameworkTransactionMultiTabPortlet (Yet to be created)
   
Note: Portlet-Name & Model class name should be same

5. Set the display-name and title with name to displayed in the UI

6.Create a json file with Model class name and place the file in GTN-Base-Path (..\jboss-7.1.1\gtnframework_base_path) in server
  and update the json files in resource folder of GTNTransaction project(..\Modules\GtnFrameworkTransaction\src\resources\) before you commit to git
  
7.In Json, each column should have following properties

  columnId - column to be mapped in the Model Class
  columnName -Name to displayed in the UI
  isSearchCriteria - Do you want the column in search criteria (true/false)
  isResultView - Do you want the column in result listview (true/false)
  componentType - text/date/combobox
  
  Note : if you set componentType as combobox, mention the listname in "listName" property
  
  Example:
  {
    "columnID": "pricingCodeStatus",
    "columnName": "Pricing Code Status",
    "isSearchCriteria": true,
    "isResultView": true,
    "componentType": "combobox",
	"listName":"STATUS"
  }
  
  All the above are mandatory properties for a column and following properties can be used if required
  
  loadDescription - If you want to load Description for a combobox, set this property as true (default is true)
  (We have Overloaded the method startSearchProcess(final List<String> vaadinFieldValues,final List<String> vaadinFieldDescriptionList, boolean isActive) with descriptionlist
  for getting combobox itemcaption instead of id in GtnUIFrameworkPagedTableLogic class)
      
  listViewIndex - If you want to order the visible column of the table , set the index no of the column (default it follow the natural order)
  

  

  
Local System Setup:
Copy the transaction_json folder from ..\Modules\GtnFrameworkTransaction\src\resources\ to your GTN-Base-Path (..\jboss-7.1.1\gtnframework_base_path) in server
  