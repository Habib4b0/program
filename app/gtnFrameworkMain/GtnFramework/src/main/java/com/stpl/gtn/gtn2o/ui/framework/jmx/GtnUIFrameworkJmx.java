/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.jmx;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.asi.ui.custommenubar.CustomMenuBar;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;

import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.OptionGroup;
import com.vaadin.v7.ui.PopupDateField;
import com.vaadin.v7.ui.TextArea;
import com.vaadin.v7.ui.TextField;

/**
 *
 * @author Nadhiya.Jayaram
 */

public class GtnUIFrameworkJmx implements GtnUIFrameworkJmxMBean {

	private boolean enableJMX = true;
	private List<WeakReference<GtnUIFrameworkComponentData>> gtnComponentObjList = new ArrayList<>();

	public List<WeakReference<GtnUIFrameworkComponentData>> getGtnCompnentsObjList() {
		return gtnComponentObjList == null ? gtnComponentObjList : Collections.unmodifiableList(gtnComponentObjList);
	}

	public void setObjList(List<WeakReference<GtnUIFrameworkComponentData>> objList) {
		gtnComponentObjList = new ArrayList<>(objList);
	}

	@Override
	public int getVaadinComponentsCount() {
		int vaddinComponentsCount = 0;
		for (WeakReference<GtnUIFrameworkComponentData> gtnObject : gtnComponentObjList) {
			if (gtnObject.get() != null) {
				vaddinComponentsCount += gtnObject.get().getFrameworkConfigMap().getVaadinComponentMap().size();
			}
		}
		return vaddinComponentsCount;
	}

	@Override
	public int returnTextFieldCount() {
		int textFieldCount = 0;

		for (WeakReference<GtnUIFrameworkComponentData> gtnObject : gtnComponentObjList) {
			if (gtnObject.get() != null) {
				for (Map.Entry<String, WeakReference<AbstractComponent>> entry : gtnObject.get().getFrameworkConfigMap()
						.getVaadinComponentMap().entrySet()) {
					AbstractComponent value = entry.getValue().get();
					if (value instanceof TextField) {
						textFieldCount++;
					}
				}
			}
		}
		return textFieldCount;
	}

	@Override
	public int returnComboBoxCount() {
		int comBoBoxCount = 0;
		for (WeakReference<GtnUIFrameworkComponentData> gtnObject : gtnComponentObjList) {
			if (gtnObject.get() != null) {
				for (Map.Entry<String, WeakReference<AbstractComponent>> entry : gtnObject.get().getFrameworkConfigMap()
						.getVaadinComponentMap().entrySet()) {
					AbstractComponent value = entry.getValue().get();
					if (value instanceof ComboBox) {
						comBoBoxCount++;
					}
				}
			}
		}
		return comBoBoxCount;
	}

	@Override
	public int returnPopUpTextFieldCount() {
		int custumTextFieldCount = 0;
		for (WeakReference<GtnUIFrameworkComponentData> gtnObject : gtnComponentObjList) {
			if (gtnObject.get() != null) {
				for (Map.Entry<String, WeakReference<AbstractComponent>> entry : gtnObject.get().getFrameworkConfigMap()
						.getVaadinComponentMap().entrySet()) {
					AbstractComponent value = entry.getValue().get();
					if (value instanceof CustomTextField) {
						custumTextFieldCount++;
					}
				}
			}
		}
		return custumTextFieldCount;
	}

	@Override
	public int returnPopDateFieldCount() {
		int popUpDateFieldCount = 0;
		for (WeakReference<GtnUIFrameworkComponentData> gtnObject : gtnComponentObjList) {
			if (gtnObject.get() != null) {
				for (Map.Entry<String, WeakReference<AbstractComponent>> entry : gtnObject.get().getFrameworkConfigMap()
						.getVaadinComponentMap().entrySet()) {
					AbstractComponent value = entry.getValue().get();
					if (value instanceof PopupDateField) {
						popUpDateFieldCount++;
					}
				}
			}
		}
		return popUpDateFieldCount;
	}

	@Override
	public int returnExtPagedTableCount() {
		int extFilterTableCount = 0;
		for (WeakReference<GtnUIFrameworkComponentData> gtnObject : gtnComponentObjList) {
			if (gtnObject.get() != null) {
				for (Map.Entry<String, WeakReference<AbstractComponent>> entry : gtnObject.get().getFrameworkConfigMap()
						.getVaadinComponentMap().entrySet()) {
					AbstractComponent value = entry.getValue().get();
					if (value instanceof ExtPagedTable) {
						extFilterTableCount++;
					}
				}
			}

		}
		return extFilterTableCount;

	}

	@Override
	public int returnButtonCount() {
		int buttonCount = 0;
		for (WeakReference<GtnUIFrameworkComponentData> gtnObject : gtnComponentObjList) {
			if (gtnObject.get() != null) {
				for (Map.Entry<String, WeakReference<AbstractComponent>> entry : gtnObject.get().getFrameworkConfigMap()
						.getVaadinComponentMap().entrySet()) {
					AbstractComponent value = entry.getValue().get();
					if (value instanceof Button) {
						buttonCount++;
					}
				}

			}
		}

		return buttonCount;
	}

	@Override
	public int returnCustomMenuBarCount() {
		int customMenuBarCount = 0;
		for (WeakReference<GtnUIFrameworkComponentData> gtnObject : gtnComponentObjList) {
			if (gtnObject.get() != null) {
				for (Map.Entry<String, WeakReference<AbstractComponent>> entry : gtnObject.get().getFrameworkConfigMap()
						.getVaadinComponentMap().entrySet()) {
					AbstractComponent value = entry.getValue().get();
					if (value instanceof CustomMenuBar) {
						customMenuBarCount++;
					}
				}
			}

		}
		return customMenuBarCount;
	}

	@Override
	public int returnExtFilterTableCount() {
		int extFilterTableCount = 0;
		for (WeakReference<GtnUIFrameworkComponentData> gtnObject : gtnComponentObjList) {
			if (gtnObject.get() != null) {
				for (Map.Entry<String, WeakReference<AbstractComponent>> entry : gtnObject.get().getFrameworkConfigMap()
						.getVaadinComponentMap().entrySet()) {
					AbstractComponent value = entry.getValue().get();
					if (value instanceof ExtFilterTable) {
						extFilterTableCount++;
					}

				}
			}
		}
		return extFilterTableCount;
	}

	@Override
	public int returnTextAreaCount() {
		int textAreaCount = 0;
		for (WeakReference<GtnUIFrameworkComponentData> gtnObject : gtnComponentObjList) {
			if (gtnObject.get() != null) {
				for (Map.Entry<String, WeakReference<AbstractComponent>> entry : gtnObject.get().getFrameworkConfigMap()
						.getVaadinComponentMap().entrySet()) {
					AbstractComponent value = entry.getValue().get();
					if (value instanceof TextArea) {
						textAreaCount++;
					}
				}
			}
		}
		return textAreaCount;
	}

	@Override
	public int returnOptionGroupCount() {
		int optionGroupCount = 0;
		for (WeakReference<GtnUIFrameworkComponentData> gtnObject : gtnComponentObjList) {
			if (gtnObject.get() != null) {
				for (Map.Entry<String, WeakReference<AbstractComponent>> entry : gtnObject.get().getFrameworkConfigMap()
						.getVaadinComponentMap().entrySet()) {
					AbstractComponent value = entry.getValue().get();
					if (value instanceof OptionGroup) {
						optionGroupCount++;
					}
				}
			}
		}
		return optionGroupCount;
	}

	@Override
	public int returnFreezePagedTreeTableCount() {
		int freezePagedTreeTableCount = 0;
		for (WeakReference<GtnUIFrameworkComponentData> gtnObject : gtnComponentObjList) {
			if (gtnObject.get() != null) {
				for (Map.Entry<String, WeakReference<AbstractComponent>> entry : gtnObject.get().getFrameworkConfigMap()
						.getVaadinComponentMap().entrySet()) {
					AbstractComponent value = entry.getValue().get();
					if (value instanceof FreezePagedTreeTable) {
						freezePagedTreeTableCount++;
					}

				}
			}
		}
		return freezePagedTreeTableCount;
	}

	@Override
	public int objectListCount() {
		int listCount = 0;
		for (WeakReference<GtnUIFrameworkComponentData> weakReference : gtnComponentObjList) {
			if (weakReference.get() != null) {
				listCount++;
			}
		}

		return listCount;
	}

	@Override
	public void clearMap() {
		for (WeakReference<GtnUIFrameworkComponentData> gtnObject : gtnComponentObjList) {
			gtnObject.get().getFrameworkConfigMap().getVaadinComponentMap().clear();
		}

	}

	@Override
	public boolean isEnableJMX() {
		return enableJMX;
	}

	@Override
	public void getEnableJMX(boolean enableJMX) {
		this.enableJMX = enableJMX;
	}

	@Override
	public int returnFieldFactoryTextFieldCount() {
		int fieldFactoryTextFieldCount = 0;
		for (WeakReference<GtnUIFrameworkComponentData> gtnObject : gtnComponentObjList) {
			if (gtnObject.get() != null) {

				for (Map.Entry<String, WeakReference<AbstractComponent>> entry : gtnObject.get().getFrameworkConfigMap()
						.getFieldFactoryComponentMap().entrySet()) {
					AbstractComponent value = entry.getValue().get();
					if (value instanceof TextField) {
						fieldFactoryTextFieldCount++;
					}

				}
			}
		}

		return fieldFactoryTextFieldCount;
	}

	public List<WeakReference<GtnUIFrameworkComponentData>> getGtnComponentObjList() {
		return gtnComponentObjList == null ? gtnComponentObjList : Collections.unmodifiableList(gtnComponentObjList);
	}

	@Override
	public int returnFieldFactoryComboBoxCount() {
		int fieldFactoryComboBoxCount = 0;
		for (WeakReference<GtnUIFrameworkComponentData> gtnObject : gtnComponentObjList) {
			if (gtnObject.get() != null) {

				for (Map.Entry<String, WeakReference<AbstractComponent>> entry : gtnObject.get().getFrameworkConfigMap()
						.getFieldFactoryComponentMap().entrySet()) {
					AbstractComponent value = entry.getValue().get();
					if (value instanceof ComboBox) {
						fieldFactoryComboBoxCount++;
					}

				}
			}
		}

		return fieldFactoryComboBoxCount;
	}

	@Override
	public int returnFieldFactoryCustomTextFieldCount() {
		int fieldFactoryCustomTextFieldCount = 0;
		for (WeakReference<GtnUIFrameworkComponentData> gtnObject : gtnComponentObjList) {
			if (gtnObject.get() != null) {

				for (Map.Entry<String, WeakReference<AbstractComponent>> entry : gtnObject.get().getFrameworkConfigMap()
						.getFieldFactoryComponentMap().entrySet()) {
					AbstractComponent value = entry.getValue().get();
					if (value instanceof CustomTextField) {
						fieldFactoryCustomTextFieldCount++;
					}

				}
			}
		}

		return fieldFactoryCustomTextFieldCount;

	}

	@Override
	public int returnFieldFactoryPopupDateFieldCount() {
		int fieldFactoryPopupDateFieldCount = 0;
		for (WeakReference<GtnUIFrameworkComponentData> gtnObject : gtnComponentObjList) {
			if (gtnObject.get() != null) {

				for (Map.Entry<String, WeakReference<AbstractComponent>> entry : gtnObject.get().getFrameworkConfigMap()
						.getFieldFactoryComponentMap().entrySet()) {
					AbstractComponent value = entry.getValue().get();
					if (value instanceof PopupDateField) {
						fieldFactoryPopupDateFieldCount++;
					}

				}
			}
		}

		return fieldFactoryPopupDateFieldCount;

	}

	@Override
	public int returnFieldFactoryButtonCount() {
		int fieldFactoryButtonCount = 0;
		for (WeakReference<GtnUIFrameworkComponentData> gtnObject : gtnComponentObjList) {
			if (gtnObject.get() != null) {

				for (Map.Entry<String, WeakReference<AbstractComponent>> entry : gtnObject.get().getFrameworkConfigMap()
						.getFieldFactoryComponentMap().entrySet()) {
					AbstractComponent value = entry.getValue().get();
					if (value instanceof Button) {
						fieldFactoryButtonCount++;
					}

				}
			}
		}

		return fieldFactoryButtonCount;

	}

	@Override
	public int returnFieldFactoryTextAreaCount() {
		int fieldFactoryTextAreaCount = 0;
		for (WeakReference<GtnUIFrameworkComponentData> gtnObject : gtnComponentObjList) {
			if (gtnObject.get() != null) {

				for (Map.Entry<String, WeakReference<AbstractComponent>> entry : gtnObject.get().getFrameworkConfigMap()
						.getFieldFactoryComponentMap().entrySet()) {
					AbstractComponent value = entry.getValue().get();
					if (value instanceof TextArea) {
						fieldFactoryTextAreaCount++;
					}

				}
			}
		}

		return fieldFactoryTextAreaCount;

	}

	public void clear() {
		this.gtnComponentObjList.clear();
	}

	public void addGtnCompnentsObjList(WeakReference<GtnUIFrameworkComponentData> component) {

		this.gtnComponentObjList.add(component);

	}
}