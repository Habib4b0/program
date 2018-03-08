

		 <nav id="sidebar">
    	<ul class="list-unstyled components">
    <#--   <li><a> <span> Welcome</span></a></li>
      <li > <a href="#homeSubmenu"> <img src="${themeDisplay.getPathThemeRoot()}/img/home.png" alt=""> <span> Home</span> </a> </li> -->
	  	<#assign velocityCount = 0 />
      <#list nav_items as nav_item>
	  <#assign velocityCount = velocityCount + 1 />
      <#assign nav_item_css_class = "" />
      <#assign nav_item_attr_selected = "aria-expanded='false'" />
      <#assign nav_item_ul_css_class = "collapse list-unstyled" />
       <#if nav_item.isSelected()>
					<#assign nav_item_attr_selected = "aria-expanded='true'" />
					<#assign nav_item_css_class = "active" />
                                        <#assign nav_item_ul_css_class = "collapse in list-unstyled" />
	  </#if>
      <#if nav_item.hasChildren()>
      <li class="${nav_item_css_class}"> <a href="#pageSubmenu${velocityCount}" data-toggle="collapse" ${nav_item_attr_selected}> <img src="${nav_item.iconURL()?replace("img_id", "img_id=")}" alt=""> <span> ${nav_item.getName()} </span></a>
        <ul class="${nav_item_ul_css_class}" id="pageSubmenu${velocityCount}" ${nav_item_attr_selected}>
        <#list nav_item.getChildren() as nav_child>
        <#assign nav_child_css_class = "" />
        <#if nav_child.isSelected()>
		<#assign nav_child_attr_selected = "aria-selected='true'" />
		<#assign nav_child_css_class = "active" />
		</#if>
          <li class="${nav_child_css_class}"><a href="${nav_child.getURL()}"><span>${nav_child.getName()}</span></a></li>
          </#list>
        </ul>
      </li>
      <#else>
       <li><a class="${nav_item_css_class}" href="${nav_item.getURL()}"><span>${nav_item.getName()}</span></a></li>
      </#if>
      </#list>
    </ul>
     <button type="button" id="sidebarCollapse"> <i class="fa fa-angle-double-left "></i><i class="fa fa-angle-double-right icon-act"></i></button> </nav>
    </nav>

